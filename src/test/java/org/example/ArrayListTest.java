package org.example;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ArrayListTest {

    @Test
            (expected = IllegalArgumentException.class)
    public void initNegative(){
        ArrayList<Integer> test = new ArrayList<>(-15);
    }

    @Test
    public void initEmpty() {
        ArrayList<Integer> test = new ArrayList<>();
        assertEquals(0,test.size());
    }

    @Test
    public void initZero() {
        ArrayList<Integer> test = new ArrayList<>(0);
        assertEquals(0,test.size());
    }

    @Test
    public void initPositive() {
        ArrayList<Integer> test = new ArrayList<>(100);
        assertEquals(100,test.capacity());
    }

    @Test
    public void add1000() {
        ArrayList<Integer> test = new ArrayList<>();
        for (int i = 0; i < 1000; ++i) {
            test.add(i + 2);
        }
        assertEquals(1000,test.size());
    }

    @Test
    public void addByIndex() {
        ArrayList<Integer> test = new ArrayList<>();
        for (int i = 0; i < 1000; ++i) {
            test.add(i,i + 2);
        }
        assertEquals(1000,test.size());
    }

    @Test
    public void addOutsideTheIndex() {
        ArrayList<Integer> test = new ArrayList<>(200);
        test.add(200,15);
        for(int i=0;i<200;++i){
            test.add(i+1);
        }
        assertEquals(200,test.size());
    }

    @Test
    public void notAdd() {
        ArrayList<Integer> test = new ArrayList<>(200);
        test.add(15,222);
        assertEquals(0,test.size());
    }

    @Test
    public void remove() {
        ArrayList<Integer> test = new ArrayList<>();
        for (int i = 0; i < 1000; ++i) {
            test.add(i,i + 2);
        }
        for (int i = 999; i >0; --i) {
            test.remove(i);
        }
        test.remove(2000);
        assertEquals(1,test.size());
    }

    @Test
    public void removeAll() {
        ArrayList<Integer> test = new ArrayList<>();
        for (int i = 0; i < 1000; ++i) {
            test.add(i,i + 2);
        }
        test.removeAll();
        assertEquals(0,test.size());
    }

    @Test
    public void set1000() {
        ArrayList<Integer> test = new ArrayList<>();
        for (int i = 0; i < 1000; ++i) {
            test.set(i,i + 2);
        }
        assertEquals(1000,test.size());
    }

    @Test
    public void get() {
        ArrayList<Integer> test = new ArrayList<>();
        for (int i = 0; i < 500; ++i) {
            test.set(i,i + 2);
        }
        test.set(15,222);
        assertEquals(222,test.get(15));
    }

    @Test
    public void set1() {
        ArrayList<Integer> test = new ArrayList<>();
        for (int i = 0; i < 500; ++i) {
            test.set(i,i + 2);
        }
        test.set(15,222);
        assertEquals(222,test.get(15));
    }

    @Test
    public void set2() {
        ArrayList<Integer> test = new ArrayList<>();
        int ch=0;
        for (int i = 0; i < 500; ++i) {
            test.set(i,1);
            ch+=test.get(i);
        }
        assertEquals(500,ch);
    }

    @Test
    public void size() {
        ArrayList<Integer> test = new ArrayList<>(200);
        for (int i = 0; i < 200; ++i) {
            test.set(i,i + 2);
        }
        assertEquals(200,test.size());
    }

    @Test
    public void Sort(){
        Quick<Integer> testSort=new Quick<>();
        ArrayList<Integer> test = new ArrayList<>();
        for (int i = 0; i < 1000; ++i) {
            test.add(i + 2);
        }
        test.set(10,10000);
        test.set(900,15);
        testSort.Sort(test);
        assertTrue(test.get(10)<test.get(900));
    }

    @Test
    public void EmptySort(){
        Quick<Integer> testSort=new Quick<>();
        ArrayList<Integer> test = new ArrayList<>(0);
        testSort.Sort(test);
        assertEquals(0,test.size());
    }

}