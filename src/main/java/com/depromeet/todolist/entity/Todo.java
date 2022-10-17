package com.depromeet.todolist.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "title", nullable = false)
    private String title;


//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
//    private User user;

    @Column(name = "user_id", nullable = false)
    private String userName;

    public Todo(String title, String userName) {
        this.title = title;
        this.userName = userName;
    }

//    public Todo(String title, User user) {
//        this.title = title;
//        this.user = user;
//    }


    public void updateTitle(String title) { // 더티 체킹을 의도했는데..
        this.title = title;
    }
}
