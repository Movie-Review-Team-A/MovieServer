package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReviewResponseDTO {

    private final Long id;          // 리뷰 ID
    private final Long movieId;     // 영화 ID
    private final Double rating;    // 평점
    private final String content;   // 리뷰 내용
    private final LocalDateTime createdAt; // 리뷰 작성 시간

}
