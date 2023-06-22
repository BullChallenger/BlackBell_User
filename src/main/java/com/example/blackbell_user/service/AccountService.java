package com.example.blackbell_user.service;

import com.example.blackbell_user.dto.AccountDTO.*;
import com.example.blackbell_user.entity.AccountEntity;

public interface AccountService {

    CreateResponseDTO createAccount(CreateRequestDTO requestDTO);

    GetResponseDTO getAccountByAccountId(String accountId);

    Iterable<AccountEntity> getAllAccounts();
}
