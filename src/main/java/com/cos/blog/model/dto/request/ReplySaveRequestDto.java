package com.cos.blog.model.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplySaveRequestDto {

    @NotNull(message = "content가 있어야합니다")
    @NotEmpty(message = "content를 입력해야합니다")
    private String content;

    @NotNull(message = "userId가 있어야합니다")
    private Integer userId;

    @NotNull(message = "boardId가 있어야합니다")
    private Integer boardId;
}
