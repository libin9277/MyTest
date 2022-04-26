package com.example.hellodemo;

import lombok.Data;

import java.math.BigDecimal;

@BankAPI(url = "/bank/pay", desc = "支付接口")
@Data
public class PayAPI {
    private long userId;
    private BigDecimal amount;
}