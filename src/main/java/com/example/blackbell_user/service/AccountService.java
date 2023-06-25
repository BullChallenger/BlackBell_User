package com.example.blackbell_user.service;

import com.example.blackbell_user.dto.AccountDTO.*;
import com.example.blackbell_user.entity.AccountEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService extends UserDetailsService {

    CreateResponseDTO createAccount(CreateRequestDTO requestDTO);

    GetResponseDTO getAccountByAccountId(String accountId);

    Iterable<AccountEntity> getAllAccounts();
}
