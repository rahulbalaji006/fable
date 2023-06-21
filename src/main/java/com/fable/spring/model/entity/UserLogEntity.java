package com.fable.spring.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_log")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLogEntity extends AbstractEntity{

    @NonNull
    @JsonProperty("logId")
    @Column(name = "logId")
    private Long logId;

    @NonNull
    @JsonProperty("unix_ts")
    @Column(name = "unix_ts")
    private Long unixTs;

    @NonNull
    @JsonProperty("user_id")
    @Column(name = "user_id")
    private Long userId;

    @NonNull
    @JsonProperty("event")
    @Column(name = "event")
    private String event;

}
