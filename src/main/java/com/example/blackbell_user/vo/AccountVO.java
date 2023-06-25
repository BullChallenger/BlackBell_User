package com.example.blackbell_user.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class AccountVO {

    @Getter
    public static class CreateRequestVO {

        @Email
        @NotNull(message = "Email cannot be null")
        @Size(min = 2, message = "Email not be less than two characters")
        private String email;

        @NotNull(message = "Name cannot be null")
        @Size(min = 2, message = "Name not be less than two characters")
        private String name;

        @NotNull(message = "Password cannot be null")
        @Size(min = 8, message = "Password must be equal or greater than two characters")
        private String password;
    }

    @Getter
    public static class LoginRequestVO {

        @Email
        @NotNull(message = "Email cannot be null")
        @Size(min = 2, message = "Email not be less than two characters")
        private String email;

        @NotNull(message = "Password cannot be null")
        @Size(min = 8, message = "Password must be equal or greater than two characters")
        private String password;
    }

    @Getter
    public static class CreateResponseVO {
        private String email;

        private String name;

        private String accountId;
    }

    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class GetResponseVO {
        private String email;

        private String name;

        private String accountId;

        private List<OrderVO.GetResponseVO> orders;
    }

}
