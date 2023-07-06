package com.example.blackbell_user.client;

import com.example.blackbell_user.dto.ResponseDTO;
import com.example.blackbell_user.vo.OrderVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "black-bell-order-service")
public interface OrderServiceClient {

    @GetMapping(value = "/order-service/{accountId}/orders")
    ResponseDTO<List<OrderVO.GetResponseVO>> getOrders(@PathVariable(value = "accountId") String accountId);

}
