package com.example.hellodemo.test;


import com.example.hellodemo.annotation.ApiIdempotent;
import com.example.hellodemo.common.ServerResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 幂等性测试接口
 * Created by double on 2019/7/11.
 */
@RestController
@RequestMapping("/test1")
public class Test1Controller {

    @ApiIdempotent
    @RequestMapping("testIdempotent")
    public ServerResponse testIdempotent() {
        return ServerResponse.success("test idempotent success");
    }

}