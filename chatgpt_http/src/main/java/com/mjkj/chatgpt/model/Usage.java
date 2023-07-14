package com.mjkj.chatgpt.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * TODO
 *
 * @ClassName: Usage
 * @Author: SuJunXi
 * @Date: 2023-07-11 15:15
 * @Version: 1.0
 */
@Data
public class Usage {
    @JsonProperty("prompt_tokens")
    private int promptTokens;

    @JsonProperty("completion_tokens")
    private int completionTokens;

    @JsonProperty("total_tokens")
    private int totalTokens;

    // 省略 getter 和 setter 方法
}
