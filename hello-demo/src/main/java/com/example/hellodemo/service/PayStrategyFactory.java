package com.example.hellodemo.service;

import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Set;

/**
 * @author libin
 * @date 2021年12月22日 11:38
 */
public class PayStrategyFactory {
    //饿汉式 单例模式
    private static PayStrategyFactory payStrategyFactory=new PayStrategyFactory();

    /**
     * 单例模式-私有构造器
     */
    private PayStrategyFactory(){

    }
    public static PayStrategyFactory getInstance(){
        return payStrategyFactory;
    }

    /**
     * 重点：存储所有的payChannel
     */
    public static HashMap<Integer,String> payChannelMap=new HashMap<>();

    static {
        //1、扫描支付渠道的实现类 ,Reflections 依赖 Google 的 Guava 库和 Javassist 库
        Reflections reflections = new Reflections("com.example.hellodemo.service");
        //2、获取所有包含PayChannel注解的类
        Set<Class<?>> classList = reflections.getTypesAnnotatedWith(PayChannel.class);
        for (Class clazz : classList) {
            PayChannel t = (PayChannel) clazz.getAnnotation(PayChannel.class);
            //3、赋值payChannelMap，存储所有的支付渠道
            payChannelMap.put(t.channelId(), clazz.getCanonicalName());
        }

    }

    /**
     *  根据channelId获取对应的具体实现
     * @param channelId
     * @return
     */
    public Pay create(Integer channelId) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //1.获取渠道对应的类名
        String clazz=payChannelMap.get(channelId);
        Class clazz_=Class.forName(clazz);
        /**
         * newInstance ：工厂模式经常使用newInstance来创建对象，newInstance()是实现IOC、反射、依赖倒置 等技术方法的必然选择
         *      调用class的加载方法加载某个类，然后实例化
         *
         * new 只能实现具体类的实例化，不适合于接口编程
         */
        return (Pay) clazz_.newInstance();
    }
}
