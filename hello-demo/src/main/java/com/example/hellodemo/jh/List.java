package com.example.hellodemo.jh;

/**
 * @author libin
 * @date 2022年01月25日 14:38
 */
public interface List {
    /**
     * 获取线性表的长度
     * @author libin
     * @date 2022/1/25 14:38 
     * @return int
     */
    int size();
    
    /**
     * 获取指定索引的内容
     * @author libin
     * @date 2022/1/25 14:39
     * @param index 
     * @return java.lang.Object
     */
    Object get(int index);

    /**
     * 判断当前容器是否为空
     * @author libin
     * @date 2022/1/25 14:41
     * @return boolean
     */
    boolean isEmpty();
    /**
     * 判断当前容器是否包含当前对象
     * @author libin
     * @date 2022/1/25 14:42
     * @param element 
     * @return boolean
     */
    boolean contains(Object element);
    /**
     * 往容器中添加一个对象
     * @author libin
     * @date 2022/1/25 14:43 
     */
    void add(Object element);
    /**
     * 从容器中删除一个对象
     * @author libin
     * @date 2022/1/25 14:44
     * @param index 
     */
    void remove(int index);
    /**
     * 添加一个对象到指定位置
     * @author libin
     * @date 2022/1/25 14:45
     * @param index
     * @param element
     */
    void add(int index,Object element);

}
