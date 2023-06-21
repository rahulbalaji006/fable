package com.fable.spring.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "config")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConfigEntity {

    @Id
    @Column(name = "key", nullable = false, unique = true)
    private String key;

    @NonNull
    @Column(name = "value")
    private LocalDateTime value;
}
