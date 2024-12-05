package repository;

import entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 특정 영화의 리뷰 중 평점 평균 이상인 것만 조회
    @Query("SELECT r FROM Review r WHERE r.movie.id = :movieId AND r.rating >= :minRating")
    List<Review> findByMovieIdAndMinRating(@Param("movieId") Long movieId, @Param("minRating") Double minRating);

}
