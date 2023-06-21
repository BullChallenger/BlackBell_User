package com.example.blackbell_user.controller;

import com.example.blackbell_user.constant.ResultType;
import com.example.blackbell_user.dto.AccountDTO.*;
import com.example.blackbell_user.dto.ResponseDTO;
import com.example.blackbell_user.service.AccountService;
import com.example.blackbell_user.vo.AccountVO.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController extends AbstractController {

    private final Environment env;
    private final ModelMapper modelMapper;

    private final AccountService accountService;

    @GetMapping(value = "/health_check")
    public String status() {
        return "It's Working in User Service";
    }

    @GetMapping(value = "/welcome")
    public String welcome() {
        return env.getProperty("greeting.message");
    }

    @PostMapping(value = "/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseDTO<CreateResponseVO> create(@RequestBody RequestVO requestVO) {
        CreateRequestDTO requestDTO = modelMapper.map(requestVO, CreateRequestDTO.class);
        CreateResponseVO responseVO = modelMapper.map(accountService.createAccount(requestDTO), CreateResponseVO.class);
        return ResponseDTO.ok(ResultType.CREATE_ACCOUNT_SUCCESS, responseVO);
    }
}
