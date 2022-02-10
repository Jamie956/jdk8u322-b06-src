package com.cat.collection;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ArrayListTest {

    @Test
    public void newArrayListArg() {
        ArrayList<Integer> list = new ArrayList<>(6);
    }

    @Test
    public void newArrayList() {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
    }

    @Test
    public void newArrayListByList() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        ArrayList<Integer> list2 = new ArrayList<>(list);
    }

    @Test
    public void trimToSizeTest() throws NoSuchFieldException, IllegalAccessException {
        ArrayList<Integer> list = new ArrayList<>(12);
        for (int i = 0; i < 14; i++) {
            list.add(1);
        }

        //数组实际长度
        Field f = list.getClass().getDeclaredField("elementData");
        f.setAccessible(true);
        Object[] arr = (Object[]) f.get(list);
        System.out.println(arr.length);
        System.out.println(list.size());

        list.trimToSize();
        System.out.println(list.size());
    }

    @Test
    public void ensureCapacityTest() {
        ArrayList<Integer> list = new ArrayList<>();
        list.ensureCapacity(12);

        ArrayList<Integer> list2 = new ArrayList<>(3);
        list2.ensureCapacity(12);
    }

    @Test
    public void indexOfTest() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        int i = list.indexOf(2);
    }

    @Test
    public void lastIndexOfTest() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        int i = list.lastIndexOf(2);
    }

    @Test
    public void cloneTest() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);

        Object clone = list.clone();
    }

    @Test
    public void toArrayTest() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);

        list.toArray();
    }

    @Test
    public void toArrayTest2() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);

        Integer[] arr = new Integer[]{2, 3};
        list.toArray(arr);
    }

    @Test
    public void getTest() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);

        list.get(0);
    }

    @Test
    public void setTest() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        list.set(1, 9);
    }

    @Test
    public void addTest() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
    }

    @Test
    public void addTestByIndex() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(0, 2);
    }

    //
    @Test
    public void removeTest() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);

        list.remove(0);
    }

    @Test
    public void removeTest2() {
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");

        list.remove("2");
    }

    @Test
    public void clearTest() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);

        list.clear();
    }

    @Test
    public void addAllTest() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);

        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(3);
        list2.add(4);

        list.addAll(list2);
    }

    @Test
    public void addAllByIndexTest() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(9);

        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(3);
        list2.add(4);

        list.addAll(1, list2);
    }

    @Test
    public void removeAllTest() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);

        list.removeAll(list2);
    }

}