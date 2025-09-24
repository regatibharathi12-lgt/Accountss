package com.dhathri.Accounts.repository;


import com.dhathri.Accounts.entity.AccountsEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AccountsRepository extends JpaRepository<AccountsEntity,Long> {
    Optional<AccountsEntity> findByCustomerId(Long customerId);

    @Modifying
    @Transactional
    void deleteByCustomerId(Long customerId);
}