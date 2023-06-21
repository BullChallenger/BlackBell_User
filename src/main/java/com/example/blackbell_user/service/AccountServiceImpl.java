package com.example.blackbell_user.service;

import com.example.blackbell_user.dto.AccountDTO.*;
import com.example.blackbell_user.entity.AccountEntity;
import com.example.blackbell_user.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final ModelMapper modelMapper;

    private final AccountRepository accountRepository;

    @Override
    public CreateResponseDTO createAccount(RequestDTO requestDTO) {
        requestDTO.setAccountId(UUID.randomUUID().toString());

        AccountEntity theAccount = modelMapper.map(requestDTO, AccountEntity.class);
        theAccount.setEncryptedPassword("encryptedPassword");

        return modelMapper.map(accountRepository.save(theAccount), CreateResponseDTO.class);
    }
}