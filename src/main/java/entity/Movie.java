package entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity // 동일한 이름의 테이블과 매핑 시켜주는 어노테이션
@Getter
@Setter
@NoArgsConstructor // JPA에서 필수, 빈 객체 생성.
@AllArgsConstructor // 모든 필드를 초기화하는 생성자.
@Builder // 원하는 필드만 초기화하며 객체 생성
@Table(name = "movie") // DB의 테이블 이름이 클래스 이름과 다를 경우 매핑 할 테이블의 이름을 지정하는 어노테이션
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 자동 생성 및 할당
    private Long id;

    // 타이틀
    @Column(nullable = false) // @Column은 DB의 열에 매핑 하는 어노테이션이며, NOT NULL 제약 조건 추가
    private String title;

    // 장르
    @Column(nullable = false) // @Column은 DB의 열에 매핑 하는 어노테이션이며, NOT NULL 제약 조건 추가
    private String genre;

    // 개봉일
    @Column(name = "release_date", nullable = false) // 특수한 형식이라 release_date을 명시적으로 지정 후 테이블과 매핑
    private LocalDate releaseDate;

    // 상영 종료일
    @Column(name = "end_date")
    private LocalDate endDate;

    // 상영 중 여부
    @Column(name = "is_showing", nullable = false)
    private Boolean isShowing;

    // 등록 일자
    @Column(name = "created_at", nullable = false, updatable = false) // 수정을 불가능하게 함
    private LocalDateTime createdAt;

    // 수정 일자
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // 삭제 여부
    @Column(name = "is_deleted", nullable = false) // Soft Delete: 삭제되어도 일반 조회에서만 보이지 않게 처리하고 데이터베이스에는 남아있음
    private Boolean isDeleted = false; // 기본값 false

}
