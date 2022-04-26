package com.example.hellodemo.service;

/**
 * @author libin
 * @date 2021年12月22日 11:09
 */
public class Context {
    private Pay pay;

    public Pay getPay() {
        return pay;
    }

    public void setPay(Pay pay) {
        this.pay = pay;
    }

    public void harnldPay() {
        pay.harnldPay();
    }
}
