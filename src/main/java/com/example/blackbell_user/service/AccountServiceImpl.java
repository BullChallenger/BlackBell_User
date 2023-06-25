package com.example.blackbell_user.service;

import com.example.blackbell_user.constant.ResultType;
import com.example.blackbell_user.dto.AccountDTO.*;
import com.example.blackbell_user.entity.AccountEntity;
import com.example.blackbell_user.exception.BaseException;
import com.example.blackbell_user.repository.AccountRepository;
import com.example.blackbell_user.vo.OrderVO;
import lombok.RequiredArgsConstructor;
import org.hibernate.criterion.Order;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AccountEntity theAccount = accountRepository.findByEmail(email).orElseThrow(() -> {
            throw new BaseException(ResultType.SYSTEM_ERROR);
        });

        return new User(theAccount.getEmail(), theAccount.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
    }

    @Override
    public CreateResponseDTO createAccount(CreateRequestDTO requestDTO) {
        requestDTO.setAccountId(UUID.randomUUID().toString());

        AccountEntity theAccount = modelMapper.map(requestDTO, AccountEntity.class);
        theAccount.setEncryptedPassword(passwordEncoder.encode(requestDTO.getPassword()));

        return modelMapper.map(accountRepository.save(theAccount), CreateResponseDTO.class);
    }

    @Override
    public GetResponseDTO getAccountByAccountId(String accountId) {
        Optional<AccountEntity> optionalAccount = accountRepository.findByAccountId(accountId);
        if (optionalAccount.isEmpty()) { throw new BaseException(ResultType.SYSTEM_ERROR); }
        GetResponseDTO responseDTO = modelMapper.map(optionalAccount.get(), GetResponseDTO.class);

        List<OrderVO.GetResponseVO> orders = new ArrayList<>();
        responseDTO.setOrders(orders);

        return responseDTO;
    }

    @Override
    public Iterable<AccountEntity> getAllAccounts() {
        return accountRepository.findAll();
    }
}
