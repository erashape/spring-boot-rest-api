package com.healthapi.dto.manager;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ManagerDto {
    private long id;
    private String name;        // 매니저 이름
    private String email;       // 매니저 이메일 주소
    private boolean isDelete;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
