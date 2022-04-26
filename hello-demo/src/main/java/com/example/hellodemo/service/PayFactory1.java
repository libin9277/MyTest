package com.example.hellodemo.service;

/**
 * @author libin
 * @date 2021年12月22日 11:23
 */
public class PayFactory1 implements PayAbstractFactory {
    @Override
    public Pay newPay() {
        return new WxPay();
    }
}
