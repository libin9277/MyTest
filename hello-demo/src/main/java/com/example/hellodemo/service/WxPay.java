package com.example.hellodemo.service;

/**
 * @author libin
 * @date 2021年12月22日 11:01
 */
@PayChannel(channelId = 1)
public class WxPay implements Pay {
    @Override
    public void harnldPay() {
        System.out.println("调用微信支付");
    }
}
