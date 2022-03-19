package com.health.service.dto.user;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDto {
    private long id;
    private String name;        // 사용자 이름
    private String email;       // 사용자 이메일 주소
    private boolean delete;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
