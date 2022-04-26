package com.example.hellodemo.service.grant;

import org.springframework.stereotype.Service;

/**
 * @author libin
 * @date 2022年01月19日 16:40
 */
@Service
public class GrantTypeSerive {

    public String redPaper(String resourceId){
        System.out.println("redPaper"+resourceId);
        //红包的发放方式
        return "每周末9点发放";
    }
    public String shopping(String resourceId){
        System.out.println("shopping"+resourceId);
        //购物券的发放方式
        return "每周三9点发放";
    }
    public String QQVip(String resourceId){
        System.out.println("shopping"+resourceId);
        //qq会员的发放方式
        return "每周一0点开始秒杀";
    }
}
