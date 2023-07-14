package com.mjkj.chatgpt.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * TODO
 *
 * @ClassName: Choice
 * @Author: SuJunXi
 * @Date: 2023-07-11 15:14
 * @Version: 1.0
 */
@Data
public class Choice {
    @JsonProperty("text")
    private String text;

    @JsonProperty("index")
    private int index;

    @JsonProperty("logprobs")
    private Object logprobs;

    @JsonProperty("finish_reason")
    private String finishReason;
}
