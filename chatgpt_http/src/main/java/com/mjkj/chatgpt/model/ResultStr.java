package com.mjkj.chatgpt.model;

/**
 * TODO
 *
 * @ClassName: ResultStr
 * @Author: SuJunXi
 * @Date: 2023-07-11 15:10
 * @Version: 1.0
 */
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResultStr {
    @JsonProperty("id")
    private String id;

    @JsonProperty("object")
    private String object;

    @JsonProperty("created")
    private long created;

    @JsonProperty("model")
    private String model;

    @JsonProperty("choices")
    private Choice[] choices;

    @JsonProperty("usage")
    private Usage usage;

    // 省略 getter 和 setter 方法
}


