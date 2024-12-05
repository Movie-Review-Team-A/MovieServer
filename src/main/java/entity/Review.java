package entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity // 동일한 이름의 테이블과 매핑 시켜주는 어노테이션
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "review") // DB의 테이블 이름이 클래스 이름과 다를 경우 매핑 할 테이블의 이름을 지정하는 어노테이션
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 자동 생성
    private Long id;

    // 영화와의 관계
    @ManyToOne(fetch = FetchType.LAZY) // 다대일 관계 설정, 지연 로딩 사용(필요한 시점에만 관련 데이터를 로딩)
    @JoinColumn(name = "movie_id", nullable = false) // Review 테이블의 movie_id 열이 Movie 엔티티를 참조 = 외래키 매핑
    private Movie movie; // Review 엔티티가 참조하는 Movie 객체

    // 평점
    @Column(nullable = false)
    private Double rating;

    // 리뷰 내용
    @Column(nullable = false, length = 1000) // 최대 길이 제한
    private String content;

    // 등록 일자
    @CreationTimestamp // Hibernate가 제공하는 어노테이션으로 엔티티가 처음 생성될 때의 시간을 자동으로 설정
    // Hibernate란?
    // 자바 객체와 관계형 데이터베이스(RDBMS) 간의 매핑을 처리하는 ORM(Object-Relational Mapping) 라이브러리
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

}
