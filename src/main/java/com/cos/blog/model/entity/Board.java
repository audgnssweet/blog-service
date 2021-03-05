package com.cos.blog.model.entity;

import com.cos.blog.model.dto.request.BoardSaveRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob    //대용량 데이터임을 표시.
    private String content; //섬머노트 라이브러리를 사용할 것. 글을 디자인해주는 것인데 <html>태그가 섞여서 디자인. 용량 매우 큼.

    //    @ColumnDefault("0") //쌍따옴표는 기본으로 감싸야 함. 얘는 Int값임.
    private Integer views;  //조회수

    @ManyToOne(fetch = FetchType.EAGER) //한 Board를 가져오면 한 User가 매칭된다. -> 디폴트 전략임
    //그러므로 User은 하나 -> select할 때 무조건 가져옴 -> FetchType.EAGER
    @JoinColumn(name = "user_id")
    private User user;     //데이터간 연관성을 표시하기 위함. 이것을 이용해서 join하거나 하는 것.
    //그런데 ORM에서는 key값을 직접적으로 사용하지 않는다. (JDBC)에서는 썼었는데.
    //JPA ORM에서는 Foreign key로 찾는 것이 아니라, Object를 바로 넘겨버린다.
    //그러나 내부적으로 DB에서는 한 컬럼에 객체를 저장할 수 없으므로 PK를 찾아서 테이블을 구성함을 알아야 한다.

    //얘는 디폴트 전략이 LAZY임. 왜? 한 board에 reply가 엄청나게 많을 수 있기 때문에 board를 select할 때마다
    //모든 replies를 가져오는 것은 비효율적이다. 그래서 기본적으로 LAZY. 디폴트로 가져오지는 않는다.
    //그러나 UI를 어떻게 짜냐에 따라서, board에 무조건 댓글을 표시하도록 하려면 EAGER 전략을 사용해야한다.
    //cascade - 테이블간 연관이 있을 때 사용. remove이므로 board가 지워지면 연관된 reply들 다 지워짐.
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    //이건 Reply 객체 안의 board변수를 의미함.
    @JsonIgnoreProperties(value = "board")  //무한참조를 방지하기위한 jsonignore
    //단, 이 변수를 통해서 가져오는 board만 무시한다는 것. 그냥 board를 참조하면 무시하지 않는다.
    @OrderBy("id desc") //eager전략으로 가져온 뒤, 정렬을 위임
    private List<Reply> replies;    //이건 JPA에서 data를 가져올 때, join을 해서 가져오는데 원래는
    //FK (foreign key)를 이용해서 가져오는데 얘는 table 자체적으로 foreign key로 갖고있지는 않은 것을 가져올 때.
    //왜? 하나의 board는 많은 reply를 가질 수 있기 때문에, foreign key로 지정한다면 여러개가 되고,
    //이는 DB의 설계원칙을 위반하게 된다.
    //결론은 DB Table에 직접 foreign key로서 넣지는 않지만 board를 select할 때
    //함께 묶어서 가져올 수 있도록 필드를 만들어두는 것.

    @CreationTimestamp
    private LocalDateTime createDate;

    public Board(BoardSaveRequestDto boardSaveRequestDto) {
        this.title = boardSaveRequestDto.getTitle();
        this.content = boardSaveRequestDto.getContent();
    }
}
