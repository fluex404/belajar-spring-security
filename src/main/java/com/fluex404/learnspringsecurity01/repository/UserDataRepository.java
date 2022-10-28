package com.fluex404.learnspringsecurity01.repository;

import com.fluex404.learnspringsecurity01.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long>, JpaSpecificationExecutor<UserData> {
    Optional<UserData> findByUsername(String username);
}