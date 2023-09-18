package com.bloom.pium.data.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name="matching")
public class Matching {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchingId;    // 매칭 고유 번호

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private boolean participate;    // 참여 결정 여부

    @Column(nullable = false)
    private boolean deadline;

    @ManyToOne
    @JoinColumn(name = "userId")
    @ToString.Exclude
    private UserInfo userId;

    @ManyToOne
    @JoinColumn(name = "boardId")
    @ToString.Exclude
    private Board boardId;

    public Matching(Long matchingId, String title, String comment, boolean participate, boolean deadline, UserInfo userId, Board boardId) {
        this.matchingId = matchingId;
        this.title = "deadline";
        this.comment = "deadline";
        this.participate = participate;
        this.deadline = deadline;
        this.userId = userId;
        this.boardId = boardId;
    }

    public void saveToDatabase(EntityManager entityManager) {
        entityManager.persist(this); // 이 메서드를 호출하여 엔티티를 저장합니다.

    }
}
