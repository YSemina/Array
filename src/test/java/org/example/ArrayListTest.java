package org.example;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayListTest {
    /**
     * Попытка создать ArrayList с отрицательным значением ёмкости
     */
    @Test
            (expected = IllegalArgumentException.class)
    public void initNegative() {
        ArrayList<Integer> test = new ArrayList<>(-15);
    }

    /**
     * Создание ArrayList'а без передачи значения для задания ёмкости
     */
    @Test
    public void initEmpty() {
        ArrayList<Integer> test = new ArrayList<>();
        assertEquals(0, test.size());
    }

    /**
     * Создание ArrayList'а с заданной ёмкостью 0
     */
    @Test
    public void initZero() {
        ArrayList<Integer> test = new ArrayList<>(0);
        assertEquals(0, test.size());
    }

    /**
     * Создание ArrayList'а с заданной положительной ёмкостью
     */
    @Test
    public void initPositive() {
        ArrayList<Integer> test = new ArrayList<>(100);
        assertEquals(100, test.capacity());
    }

    /**
     * Добавление 1000 элементов по порядку при помощи метода add() и передачи ему объекта
     */
    @Test
    public void add1000() {
        ArrayList<Integer> test = new ArrayList<>();
        for (int i = 0; i < 1000; ++i) {
            test.add(i + 2);
        }
        assertEquals(1000, test.size());
    }

    /**
     * Добавление 1000 элементов по порядку при помощи метода add() и передачи ему индекса, по которому нужно установить значение
     * и объекта, а также вставка элемента в середину ArrayList'а
     */
    @Test
    public void addByIndex() {
        ArrayList<Integer> test = new ArrayList<>();
        for (int i = 0; i < 1000; ++i) {
            test.add(i, i + 2);
        }
        test.add(10, 1);
        assertEquals(1001, test.size());
    }

    /**
     * Попытка добавить элемент по индексу, который выходит за пределы ArrayList'а
     */
    @Test
    public void addOutsideTheIndex() {
        ArrayList<Integer> test = new ArrayList<>(200);
        test.add(200, 15);
        for (int i = 0; i < 200; ++i) {
            test.add(i + 1);
        }
        assertEquals(200, test.size());
    }

    /**
     * Метод не вставляет элемент по индексу, если значение перед ним не заполнено
     */
    @Test
    public void notAdd() {
        ArrayList<Integer> test = new ArrayList<>(200);
        test.add(15, 222);
        assertEquals(0, test.size());
    }

    /**
     * Удаление элементов по индексу
     */
    @Test
    public void remove() {
        ArrayList<Integer> test = new ArrayList<>();
        for (int i = 0; i < 1000; ++i) {
            test.add(i, i + 2);
        }
        for (int i = 999; i > 0; --i) {
            test.remove(i);
        }
        test.remove(2000);
        assertEquals(1, test.size());
    }

    /**
     * Удаление ArrayList'а полностью
     */
    @Test
    public void removeAll() {
        ArrayList<Integer> test = new ArrayList<>();
        for (int i = 0; i < 1000; ++i) {
            test.add(i, i + 2);
        }
        test.removeAll();
        assertEquals(0, test.size());
    }

    /**
     * Установка значения по индексу по порядку
     */
    @Test
    public void set1000() {
        ArrayList<Integer> test = new ArrayList<>();
        for (int i = 0; i < 1000; ++i) {
            test.set(i, i + 2);
        }
        assertEquals(1000, test.size());
    }

    /**
     * Правильность получения элемента по индексу
     */
    @Test
    public void get() {
        ArrayList<Integer> test = new ArrayList<>();
        for (int i = 0; i < 500; ++i) {
            test.set(i, i + 2);
        }
        test.set(15, 222);
        assertEquals(222, test.get(15));
    }

    /**
     * Установка значений по индексу
     */
    @Test
    public void set() {
        ArrayList<Integer> test = new ArrayList<>();
        int ch = 0;
        for (int i = 0; i < 500; ++i) {
            test.set(i, 1);
            ch += test.get(i);
        }
        assertEquals(500, ch);
    }

    /**
     * Корректность возврвта размера ArrayList'а
     */
    @Test
    public void size() {
        ArrayList<Integer> test = new ArrayList<>(200);
        for (int i = 0; i < 200; ++i) {
            test.set(i, i + 2);
        }
        assertEquals(200, test.size());
    }

    /**
     * Корректность работы сортировки
     */
    @Test
    public void Sort() {
        Comparator<Integer> comp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        };
        ArrayList<Integer> test = new ArrayList<>();
        for (int i = 0; i < 1000; ++i) {
            test.add(i + 2);
        }
        test.set(10, 10000);
        test.set(900, 15);
        test.Sort(comp);
        assertTrue(test.get(10) < test.get(900));
    }

    /**
     * Корректность работы сортировки пустого ArrayList'а
     */
    @Test
    public void EmptySort() {
        Comparator<Integer> comp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        };
        ArrayList<Integer> test = new ArrayList<>(0);
        test.Sort(comp);
        assertEquals(0, test.size());
    }
}