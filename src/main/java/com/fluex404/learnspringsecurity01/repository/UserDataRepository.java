package com.fluex404.learnspringsecurity01.repository;

import com.fluex404.learnspringsecurity01.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long>, JpaSpecificationExecutor<UserData> {
    Optional<UserData> findByUsername(String username);
    boolean existsByTokenAndUsername(String token, String username);
    @Modifying
    @Query(value = "update userdata set token=:token where username=:username", nativeQuery = true)
    @Transactional
    void updateTokenByUsername(@Param("token") String token, @Param("username") String username);
}