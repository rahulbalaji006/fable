package com.fable.spring.repository;

import com.fable.spring.model.entity.UserLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLogRepo extends JpaRepository<UserLogEntity, Long> {

}
