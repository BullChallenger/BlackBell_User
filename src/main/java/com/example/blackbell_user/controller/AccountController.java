package com.example.blackbell_user.controller;

import com.example.blackbell_user.constant.ResultType;
import com.example.blackbell_user.dto.AccountDTO.*;
import com.example.blackbell_user.dto.ResponseDTO;
import com.example.blackbell_user.entity.AccountEntity;
import com.example.blackbell_user.service.AccountService;
import com.example.blackbell_user.vo.AccountVO.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account-service")
public class AccountController extends AbstractController {

    private final Environment env;
    private final ModelMapper modelMapper;

    private final AccountService accountService;

    @GetMapping(value = "/health_check")
    public String status() {
        return String.format("It's Working in User Service on PORT %s", env.getProperty("local.server.port"));
    }

    @GetMapping(value = "/welcome")
    public String welcome() {
        return env.getProperty("greeting.message");
    }

    @PostMapping(value = "/accounts/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseDTO<CreateResponseVO> create(@RequestBody CreateRequestVO requestVO) {
        CreateRequestDTO requestDTO = modelMapper.map(requestVO, CreateRequestDTO.class);
        CreateResponseVO responseVO = modelMapper.map(accountService.createAccount(requestDTO), CreateResponseVO.class);
        return ResponseDTO.ok(ResultType.CREATE_ACCOUNT_SUCCESS, responseVO);
    }

    @GetMapping(value = "/accounts")
    public ResponseDTO<List<GetResponseVO>> readAll() {
        Iterable<AccountEntity> accountList = accountService.getAllAccounts();

        List<GetResponseVO> responseVOList = new ArrayList<>();
        accountList.forEach(account -> {
            responseVOList.add(modelMapper.map(account, GetResponseVO.class));
        });

        return ResponseDTO.ok(ResultType.SUCCESS, responseVOList);
    }

    @GetMapping(value = "/accounts/{accountId}")
    public ResponseDTO<GetResponseVO> read(@PathVariable("accountId") String accountId) {
        GetResponseDTO responseDTO = accountService.getAccountByAccountId(accountId);
        return ResponseDTO.ok(ResultType.SUCCESS, modelMapper.map(responseDTO, GetResponseVO.class));
    }
}
