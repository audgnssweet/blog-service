package com.cos.blog.model.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardSaveRequestDto {

    @NotNull(message = "title이 없습니다")
    @NotEmpty(message = "title을 입력해야합니다")
    private String title;

    @NotNull(message = "content가 없습니다")
    @NotEmpty(message = "content를 입력해야합니다")
    private String content;

    @NotNull(message = "userId가 없습니다")
    private Integer userId;
}
