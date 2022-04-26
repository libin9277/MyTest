package com.example.hellodemo.thread;

import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author libin
 * @date 2021年12月15日 16:08
 */
public class TestMain {
    public static void main(String[] args) {
        //for (int i = 0; i < 4; i++) {
//            TestRhread testRhread = new TestRhread("售票点1");
//            TestRhread testRhread1 = new TestRhread("售票点2");
//            TestRhread testRhread2 = new TestRhread("售票点3");
//            testRhread.start();
//            testRhread1.start();
//            testRhread2.start();
        //}
        List<Mune> list = new ArrayList<>();
        Mune mune1 = new Mune(1,"a1","菜单1",0);
        Mune mune2 = new Mune(2,"b1","菜单2",0);
        Mune mune3 = new Mune(3,"a101","菜单101",1);
        Mune mune4 = new Mune(4,"b101","菜单201",2);
        Mune mune5 = new Mune(5,"c101","菜单10101",3);
        list.add(mune1);
        list.add(mune2);
        list.add(mune3);
        list.add(mune4);
        list.add(mune5);
        long start = System.currentTimeMillis();
        List<Mune> list2 = new ArrayList<>();

        for (Mune m : list) {
            if(m.getParnetId() == 0){
                list2.add(m);
            }
        }

        for (Mune m: list2){
            m.setChildren(childrens(m.getId(),list));
        }
        long end = System.currentTimeMillis();
        System.out.println(JSONUtil.toJsonStr(list2));
        System.out.println("用时"+(end-start)+"毫秒");

        long start1 = System.currentTimeMillis();
       List<Mune> rnt = list.stream().filter(item->item.getParnetId()==0).map(item->{
            item.setChildren(getChildrens(item.getId(),list));
            return item;
        }).collect(Collectors.toList());
        long end1 = System.currentTimeMillis();
        System.out.println(JSONUtil.toJsonStr(rnt));
        System.out.println("用时"+(end1-start1)+"毫秒");
    }
    public static List<Mune> getChildrens(int parnetId,List<Mune> list){
        List<Mune> rnt = list.stream().filter(item->item.getParnetId()==parnetId).map(item->{
            List<Mune> childrens = getChildrens(item.getId(),list);
            if(childrens.size()>0){
                item.setChildren(childrens);
            }
            return item;
        }).collect(Collectors.toList());
       return rnt;
    }
    public static List<Mune> childrens(int parnetId,List<Mune> list){
        List<Mune> list1 = new ArrayList<>();
        for(Mune m : list){
            if(m.getParnetId() == parnetId){
                list1.add(m);
            }
        }
        for (Mune m: list1){
            m.setChildren(childrens(m.getId(),list));
        }

        if(list1.size() == 0){
            return null;
        }

        return list1;
    }
}
class Mune{
    private int id;
    private String code;
    private String name;
    private int parnetId;
    private List<Mune> children;

    public Mune(){

    }
    public Mune(int id, String code, String name,int parnetId) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.parnetId = parnetId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParnetId() {
        return parnetId;
    }

    public void setParnetId(int parnetId) {
        this.parnetId = parnetId;
    }

    public List<Mune> getChildren() {
        return children;
    }

    public void setChildren(List<Mune> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Mune{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", parnetId=" + parnetId +
                ", children=" + children +
                '}';
    }
}
