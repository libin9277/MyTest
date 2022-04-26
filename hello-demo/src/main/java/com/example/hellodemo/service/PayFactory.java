package com.example.hellodemo.service;

/**
 * @author libin
 * @date 2021年12月22日 11:15
 */
public class PayFactory {
    public static Pay makePay(String param) {
        switch (param) {
            case "wx":
                return new WxPay();
            case "zfb":
                return new ZfbPay();
        }
        return null;
    }
}
