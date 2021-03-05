package com.cos.blog.model.repository;

import com.cos.blog.model.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository덕분에 자동으로 빈으로 등록됨.
public interface UserRepository extends JpaRepository<User, Integer> {
    //JPA naming query 전략.

    boolean existsByUsername(String username);

    Optional<User> findFirstByUsername(String username);

    //아래는 다음과 같음.
    //select * from user where user_name = ? and password = ?; 와 같이 쿼리문 자동생성.
//    Optional<User> findUserByUserNameAndPassword(String userName,String password);

    //위의 함수는 NativeQuery를 사용해서도 가능. 이렇게하면 직접 쿼리문 조작 가능.
//    @Query(value = "SELECT * FROM user WHERE user_name = ? AND password = ?;", nativeQuery = true)
//    Optional<User> login(String userName, String password);

}
