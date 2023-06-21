package com.fable.spring.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserLogDto {

    @NonNull
    @JsonProperty("id")
    private Long id;

    @NonNull
    @JsonProperty("unixTs")
    private Long unixTs;

    @NonNull
    @JsonProperty("userId")
    private Long userId;

    @NonNull
    @JsonProperty("event")
    private String event;
}
