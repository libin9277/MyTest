package com.example.hellodemo.jh;

import java.util.Arrays;

/**
 * @author libin
 * @date 2022年01月25日 14:46
 */
public class ArrayList implements List {

    /**
     * 存储当前线性表内容
     */
    private Object[] elementData;
    /**
     * 存储当前线性表长度
     */
    private int size;
    /**
     * 默认的空线性表
     */
    private Object[] DEFAULT_EMPTY_ELEMENTDATA = {};
    /**
     * 定义一个List的最大容量
     */
    private int DEFAUIT_MAX_CAPACITY=Integer.MAX_VALUE-8;


    public ArrayList(int length){
        elementData = new Object[length];
    }

    public ArrayList(){
        elementData = DEFAULT_EMPTY_ELEMENTDATA;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object get(int index) {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean contains(Object element) {
        return false;
    }

    @Override
    public void add(Object element) {
        if(size>=elementData.length){
            grow(size+1);
        }
        elementData[size++] = element;
    }

    @Override
    public void remove(int index) {

    }

    @Override
    public void add(int index, Object element) {
        if(index<0 || index>=size){
            throw new  ArrayIndexOutOfBoundsException("数组下标越界");
        }
        Object[] newElementData = new Object[size+1];
        //将原来数组的index之前的元素复制到新数组
        System.arraycopy(elementData,0,newElementData,0,index);
        //将元素赋值给新数组的第index个元素
        newElementData[index] = element;
        //将原数组的index之后的元素复制到新数组的index元素之后
        System.arraycopy(elementData,index,newElementData,index+1,size-index);
        //将原来的数组指向新的数组
        elementData = newElementData;

        size++;
    }
    /**
     * 数组扩容
     * @author libin
     * @date 2022/1/25 14:56
     * @param minCapacity 需要扩大的最小容量
     */
    private void grow(int minCapacity){
        //现在数组的长度
        int oldCapacity = elementData.length;
        //新数组的长度，从现在有的数组长度的基础上，在增加1/2。这样不至于增加太多
        int newCapacity = oldCapacity+(oldCapacity>>1);
        //如果新的数组长度>目前需要的数组长度，则扩大现在需要的数组长度
        if(minCapacity>newCapacity){
            newCapacity = minCapacity;
        }
        //如果新的数组长度大于设定的最大值，则直接去Integer.MAX_VALUE的最大值
        if(newCapacity > DEFAUIT_MAX_CAPACITY){
            newCapacity = Integer.MAX_VALUE;
        }
        //通过数组拷贝，将原数组进行扩容
        elementData = Arrays.copyOf(elementData,newCapacity);
    }
}
