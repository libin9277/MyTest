package com.example.hellodemo.service.grant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author libin
 * @date 2022年01月19日 16:40
 */
@Service
public class QueryGrantTypeService {
    @Autowired
    private GrantTypeSerive grantTypeSerive;
    private Map<String, Function<String,String>> grantTypeMap=new HashMap<>();

    @PostConstruct
    public void dispatcherInit(){
        System.out.println("开始执行");
        grantTypeMap.put("红包",resourceId->grantTypeSerive.redPaper(resourceId));
        grantTypeMap.put("购物券",resourceId->grantTypeSerive.shopping(resourceId));
        grantTypeMap.put("qq会员",resourceId->grantTypeSerive.QQVip(resourceId));
    }
    public String getResult(String resourceType){
        //Controller根据 优惠券类型resourceType、编码resourceId 去查询 发放方式grantType
        Function<String,String> result=grantTypeMap.get(resourceType);
        if(result!=null){
            //传入resourceId 执行这段表达式获得String型的grantType
            return result.apply("2");
        }
        return "查询不到该优惠券的发放方式";
    }
}
