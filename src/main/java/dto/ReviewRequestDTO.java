package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 필드를 파라미터로 받는 생성자
public class ReviewRequestDTO {

    private Long movieId;  // 리뷰할 영화의 ID
    private Double rating; // 평점
    private String content; // 리뷰 내용
}

