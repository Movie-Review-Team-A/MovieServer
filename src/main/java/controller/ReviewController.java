package controller;


import Service.ReviewService;
import dto.ReviewRequestDTO;
import dto.ReviewResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api") // 기본 URL 설정
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // 1. 리뷰 생성
    @PostMapping("/reviews")
    public ResponseEntity<ReviewResponseDTO> createReview(@Valid @RequestBody ReviewRequestDTO reviewRequestDTO) {
        // 리뷰 생성 및 DTO 반환
        ReviewResponseDTO responseDTO = reviewService.createReview(reviewRequestDTO);

        // 201 Created 상태 코드로 DTO 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    // 평점 이상인 리뷰 조회
    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewResponseDTO>> findMinRatingReviews(
            @RequestParam Long movieId,
            @RequestParam Double minRating) {

        List<ReviewResponseDTO> reviews = reviewService.findMinRatingReviews(movieId, minRating);

        // 200 OK 상태 코드로 리뷰 리스트 반환
        return ResponseEntity.status(HttpStatus.OK).body(reviews);
    }

}
