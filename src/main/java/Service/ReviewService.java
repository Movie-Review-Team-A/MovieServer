package Service;

import dto.ReviewRequestDTO;
import dto.ReviewResponseDTO;
import entity.Movie;
import entity.Review;
import org.springframework.stereotype.Service;
import repository.MovieRepository;
import repository.ReviewRepository;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;

    public ReviewService(ReviewRepository reviewRepository, MovieRepository movieRepository) {
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
    }

    // 리뷰 등록
    public ReviewResponseDTO createReview(ReviewRequestDTO reviewRequestDTO) {

        // 1. 영화 ID로 영화 정보 조회
        Movie movie = movieRepository.findById(reviewRequestDTO.getMovieId())
                .orElseThrow(() -> new IllegalArgumentException("영화 ID에 해당하는 영화가 없습니다."));

        // 2. 롬복 생성자(@AllArgsConstructor)로 Review 객체 생성
        Review review = new Review(
                null, // ID는 자동 생성됨
                movie, // Movie 객체
                reviewRequestDTO.getRating(), // 평점
                reviewRequestDTO.getContent(), // 리뷰 내용
                null // createdAt은 @CreationTimestamp에 의해 자동 설정됨
        );

        // 3. 리뷰 저장
        reviewRepository.save(review);

        // 저장된 리뷰를 바탕으로 ReviewResponseDTO 생성 후 반환
        return new ReviewResponseDTO(
                review.toReviewResponseDTO().getId(),
                review.toReviewResponseDTO().getMovieId(),
                review.toReviewResponseDTO().getRating(),
                review.toReviewResponseDTO().getContent(),
                review.toReviewResponseDTO().getCreatedAt()
        );
    }

    // 평점 이상인 리뷰 조회
    public List<ReviewResponseDTO> findMinRatingReviews(Long movieId, Double minRating) {

        List<Review> reviews = reviewRepository.findByMovieIdAndMinRating(movieId, minRating);

        // 리뷰가 없을 경우 예외 처리
        if (reviews.isEmpty()) {
            throw new IllegalArgumentException("해당 영화의 평점이 " + minRating + "점 이상인 리뷰가 없습니다.");
        }

        List<ReviewResponseDTO> reviewResponseDTOs = reviews.stream()
                .map(review -> review.toReviewResponseDTO())
                .toList();

        return reviewResponseDTOs;
    }

}
