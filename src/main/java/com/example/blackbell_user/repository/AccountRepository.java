package com.example.blackbell_user.repository;

import com.example.blackbell_user.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByEmail(String email);

    Optional<AccountEntity> findByAccountId(String accountId);
}
