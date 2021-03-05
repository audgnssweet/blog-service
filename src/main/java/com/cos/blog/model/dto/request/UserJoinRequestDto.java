package com.cos.blog.model.dto.request;

import com.cos.blog.domain.Social;
import com.cos.blog.domain.UserRole;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserJoinRequestDto {

    @NotNull(message = "username은 필수 입력사항입니다")
    @NotEmpty(message = "username은 비어있을 수 없습니다")
    private String username;

    @NotEmpty(message = "password는 필수 입력사항입니다")
    private String password;

    @NotEmpty(message = "email은 필수 입력사항입니다")
    private String email;

    private Social social;
}
