package com.fable.spring.repository;

import com.fable.spring.model.entity.ConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfigRepo extends JpaRepository<ConfigEntity, Long> {

    Optional<ConfigEntity> findByKey(String key);
}
