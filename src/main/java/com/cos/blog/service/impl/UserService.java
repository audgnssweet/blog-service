package com.cos.blog.service.impl;

import com.cos.blog.domain.Social;
import com.cos.blog.exception.exceptions.InvalidateUserUpdateRequestException;
import com.cos.blog.exception.exceptions.UserNotFoundException;
import com.cos.blog.model.entity.User;
import com.cos.blog.model.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//service layer에서 transaction 처리를 해주어야함.
//repository는 하나의 작업만 하기 떄문에.
//여러개의 작업이 모여 하나의 서비스 - 여러개의 작업 중 하나가 실패시 ? rollback 필요.
//데이터의 정합성문제 - Phantom read문제를 해결하기 위해서? repeatable read 방식 필요.
//phantom read 문제 - 한 트랜잭션 중 select시 data가 다르거나, 없거나 하는 경우.
//mysql - InnoDB storage engine + repeatable read 사용. 정합성문제 발생 X.
//repeatable read - 한 Transaction이 자신의 번호보다 작은 Transaction들이 남긴, undo log만 보고 select를 진행.
//그런데 insert는 undo table이 없기 때문에, 정합성문제를 해결할 수 없다.
//update시에도 완벽한 정합성을 유지하는 것은 아니다.
//repeatable read - 동시성과 안정성의 균형을 잘 맞춤. (snapshot으로 select)
//serializable - 동시성을 상당부분 포기하고 안정성에 무게를 둠.
//serializable - transaction시 해당 row에 lock을 걸어서 insert, update 불가능.
//DB lock
//1. shared lock? 데이터를 읽을 때. 내가 보고있는 데이터를 다른 사용자도 읽을 수 있지만 변경은 불가능.
//2. Exclusive lock 데이터를 변경할 때. 내가 수정하는 데이터를 다른 사용자가 읽거나 쓸 수 없다.

//Spring JPA의 OSIV 전략 -
// presentation layer에서 프록시 객체를 통한 fetchtype LAZY 로딩 가능. 그러나 controller딴에서
//데이터를 변경하거나 해도, Transaction이 닫혔기 때문에 감지불가.
//이게 yml의 JPA open-in-view 옵션.
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;    //유저 테이블에 넣을 때 비밀번호를 해시해야 함.

    //    한 페이지당 2건의 데이터를 받을 것.
//    원래는 페이징을 엄청나게 귀찮은 과정으로 로직을 설계해야하는데 jpa에서 강력한 paging 기능 제공.
//    @Transactional(readOnly = true)
//    public List<User> getUserPage(Pageable pageable) {
////        뒤에 .getContent()로 위에서말한 추가데이터 없이 List<User>만 받는 것도 가능하다.
////        jpa가 제공하는 paging은 index범위를 벗어나도 [] (빈 json)을 return.
//        final Page<User> page = userRepository.findAll(pageable);
//        return page.getContent();
//    }

    @Transactional(readOnly = true)
    public User getUserDetail(Integer id) {
//        findbyid -> optional 리턴. 이후 찾으면 User 객체리턴, 못찾으면 IllegalArgumentException 리턴.
//        orElseGet으로 User객체 리턴해주면? 빈 User가 감.
//        return userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
//            @Override
//            public IllegalArgumentException get() {
//                return new IllegalArgumentException("잘못된 유저 id값 입력입니다. 입력된 id = " + id);
//            }
//        });
//        인터페이스를 new 하려면 익명객체를 생성해줘야한다. 바로구현해주면돼.
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    //    업데이트. 1. 가져와서 save. 2. 더티체킹
    @Transactional
    public String updateUser(Integer id, User user) {

//        2번 방식. 더티체킹 영속성 컨텍스트의 캐시를 이용 @Transactional 이용
        final String encoded = encoder.encode(user.getPassword());
        User foundUser = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        //validation check
        if (foundUser.getSocial() != Social.BLOG) {
            throw new InvalidateUserUpdateRequestException();
        }

        foundUser.setPassword(encoded);
        return foundUser.getUsername() + " 님의 정보가 업데이트 되었습니다";

//        1번 방식.
//        JPA는 기본적으로 이미 존재하는 id에 대해서 save를 요청하면 update를 해줌.
//        final Optional<User> found = userRepository.findById(id);
//        User foundUser = found.orElseThrow(() -> {
//            return new IllegalArgumentException("해당 id의 사용자는 없습니다.");
//        });
//        foundUser.setPassword(user.getPassword());
//        foundUser.setEmail(user.getEmail());
//        return userRepository.save(foundUser);
    }

    //    처음에 모르는에러로 날려서 걔를찾아서 처리.
//    @Transactional
//    public String deleteUser(Integer id) {
//        User foundUser = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
//        userRepository.delete(foundUser);
//        return "유저가 삭제 완료되었습니다. id = " + id;
//    }

    //security login으로 대체할 것.
//    @Transactional(readOnly = true) //트랜잭션동안 data의 정합성유지.
//    public User login(User user) {
//        final Optional<User> principal = userRepository.login(user.getUserName(), user.getPassword());
//        return principal.orElseThrow(LoginFailException::new);
//    }

    @Transactional(readOnly = true)
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findFirstByUsername(username);
    }

}
