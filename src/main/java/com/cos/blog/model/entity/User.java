package com.cos.blog.model.entity;

import com.cos.blog.domain.Social;
import com.cos.blog.domain.UserRole;
import com.cos.blog.model.dto.request.UserJoinRequestDto;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

//@DynamicInsert
//얘는 insert query 쏠 때 필드값이 null이면 자동으로 default값으로 채워줌. null값 말고. 즉, 쿼리에서 해당 column을 지워버림
//근데 별로 좋은 어노테이션은 아니니까 enum을 사용해서 대체해주는 편이 낫다.
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity //이 클래스는 DB에 table로 자동생성 될 것. 어떻게? application.yml에 ddl-auto를 creation으로 해놓았기 때문.
public class User {

    @Id //이건 primary key다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //연결된 DB의 전략을 따라간다. mysql은 auto-increment, oracle은 sequence
    private Integer id; //primary key, auto-increment

    @Column(nullable = false, length = 30, unique = true)  //null이 될 수 없고, 길이는 30자 이상 될 수 없음.
    private String username; //아이디

    //카카오 로그인을 위해 password nullable 설정을 해제했음
    @Column(length = 100) //해시한 값을 넣을 것이므로 길어도된다.
    private String password; //패스워드

    @Column(nullable = false, length = 50)
    private String email;

    @Enumerated(EnumType.STRING)
    private Social social;

    //이 default value는 query문 자체에 아예 column이 포함이 안되어있을 때 들어간다. 들어있으면 null로 들어감.
//    @ColumnDefault("'user'")    //기본적으로 user이고, 이것은 문자라는 것을 알려줘야 한다.
//    private String role;    //얘는 String이 아니라 Enum으로 해야한다 원래. 왜? 권한은 중요한것이기 때문에 실수 X.
    //ex) admin, user, manager 등 도메인 -> 범위 role의 도메인은 왼쪽과 같음.

    //    위에꺼를 ENUM으로 대체한 것.
    @Enumerated(EnumType.STRING)    //DB에는 UserRole Type이 없으니까 이건 Enum(String)이다. 라고 알려주는 것.
    private UserRole role;

    @CreationTimestamp  //DB에 들어갈 때 자동으로 시간을 생성해서 넣어줌.
    private LocalDateTime createDate;

    public User(UserJoinRequestDto userJoinRequestDto) {
        this.username = userJoinRequestDto.getUsername();
        this.password = userJoinRequestDto.getPassword();
        this.email = userJoinRequestDto.getEmail();
        this.social = userJoinRequestDto.getSocial();
    }

}
