package com.example.blackbell_user.dto;

import com.example.blackbell_user.vo.OrderVO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

public class AccountDTO {

    @Getter
    @Setter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CreateRequestDTO {

        private String email;

        private String name;

        private String password;

        private String accountId;
    }

    @Getter
    @Setter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CreateResponseDTO {

        private String email;

        private String name;

        private String password;

        private String accountId;

        private String encryptedPassword;

        private Date createdAt;
        private Date updatedAt;
        private boolean isDeleted;
    }

    @Getter
    @Setter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class GetResponseDTO {
        private String email;

        private String name;

        private String accountId;

        private List<OrderVO.GetResponseVO> orders;
    }
}
