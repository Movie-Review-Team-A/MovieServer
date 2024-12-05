package entity;

import dto.ReviewRequestDTO;
import dto.ReviewResponseDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import dto.ReviewRequestDTO;
import dto.ReviewResponseDTO;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 영화와의 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    // 평점
    @Column(nullable = false)
    private Double rating;

    // 리뷰 내용
    @Column(nullable = false, length = 1000) // 최대 길이 제한
    private String content;

    // 등록 일자
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // ReviewRequestDTO로 변환하는 메서드
    public ReviewRequestDTO toReviewRequestDTO() {
        return new ReviewRequestDTO(
                this.movie != null ? this.movie.getId() : null, // 영화 ID 전달
                this.rating, // 평점
                this.content // 리뷰 내용
        );
    }

    // ReviewResponseDTO로 변환하는 메서드
    public ReviewResponseDTO toReviewResponseDTO() {
        return new ReviewResponseDTO(
                this.id, // 리뷰 ID
                this.movie != null ? this.movie.getId() : null, // 영화 ID
                this.rating, // 평점
                this.content, // 리뷰 내용
                this.createdAt // 리뷰 생성 시간
        );
    }
}
