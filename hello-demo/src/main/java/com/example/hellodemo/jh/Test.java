package com.example.hellodemo.jh;

/**
 * @author libin
 * @date 2022年01月25日 15:08
 */
public class Test {
    public static void main(String[] args) {
        List list = new ArrayList(3);
        list.add("1");
        list.add("2");
        list.add(0,"3");
        System.out.println(list.size());
    }
}
