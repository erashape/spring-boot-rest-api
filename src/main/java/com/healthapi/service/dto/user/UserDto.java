package com.healthapi.service.dto.user;

import com.healthapi.domain.user.User;
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
    private boolean isDelete;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public static UserDto reverse(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .isDelete(user.isDelete())
                .createdDate(user.getCreatedDate())
                .modifiedDate(user.getModifiedDate())
                .build();
    }
}
