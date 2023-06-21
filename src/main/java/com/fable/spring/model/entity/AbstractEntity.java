package com.fable.spring.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.time.OffsetDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Column(name = "id", unique = true)
    private Long id;

    @NonNull
    @JsonProperty("created_at")
    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    @NonNull
    @JsonProperty("updated_at")
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;
}
