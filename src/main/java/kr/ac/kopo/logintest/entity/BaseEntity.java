package kr.ac.kopo.logintest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// 등록날짜와 수정날짜 정보
@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
abstract class BaseEntity {
    @CreatedDate
    @Column(name = "regDate", updatable = false) // 수정이 불가능하게 만들기 위해
    private LocalDateTime regDate; // 등록날짜

    @LastModifiedDate
    @Column(name = "modDate") // 수정이 가능하게 만들기 위해
    private LocalDateTime modDate; // 수정날짜

    

}
