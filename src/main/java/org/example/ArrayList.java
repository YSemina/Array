package org.example;

import java.util.*;

/**
 * Класс для хранения массива данных.
 *
 * @author - Юля Семина
 */
public class ArrayList<E> {
    /**
     * Переменная для задания ёмкости массива, если используется конструктор по умолчанию
     *
     * @see ArrayList#ArrayList()
     */
    private static final int SIZE = 10;
    /**
     * Переменная для создания пустого массива, если в качестве ёмкости был передан 0
     *
     * @see ArrayList#ArrayList(int capacity)
     * Переменная также используется при удалении массива
     * @see ArrayList#removeAll()
     */
    private static final Object[] EMPTY_ARRAY = {};
    /**
     * Массив типа Object
     */
    private Object[] array;
    /**
     * Переменная для задания ёмкости массива
     */
    private int capacity;
    /**
     * Переменная, отвечающая за количество элементов в
     *
     * @see ArrayList
     */
    private int size = 0;

    /**
     * Конструктор по умолчанию создает пустой массив на 10 элементов
     */
    public ArrayList() {
        array = new Object[SIZE];
        capacity = SIZE;
    }

    /**
     * Конструктор
     *
     * @param capacity переменная для задания ёмкости массива
     * @throws IllegalArgumentException возникает, если переданный параметр - отрицательный
     */
    public ArrayList(int capacity) {
        if (capacity == 0) {
            array = EMPTY_ARRAY;
            this.capacity = 0;
        } else if (capacity > 0) {
            this.capacity = capacity;
            array = new Object[capacity];
        } else {
            throw new IllegalArgumentException("ArrayList не создан, потому что не может содержать отрицательное значение. Ваше значение: " + capacity);
        }
    }

    /**
     * @return возвращает количество элементов в
     * @see ArrayList
     * @see ArrayList#size
     */
    public int size() {
        return size;
    }

    /**
     * @return возвращает ёмкость массива
     * @see ArrayList
     * @see ArrayList#capacity
     */
    public int capacity() {
        return capacity;
    }

    /**
     * @param index индекс, по которому находится объект
     * @return возвращает элемент по переданному индексу
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
        return (E) array[index];
    }

    /**
     * Добавляет объект в конец массива
     *
     * @param obj объект, который нужно добавить в массив.
     *            Увеличивает емкость массива, если места недостаточно
     * @see ArrayList#resize(int)
     */
    public void add(E obj) {
        if (size < capacity) {
            array[size++] = obj;
        } else {
            resize(capacity * 2);
            add(obj);
        }
    }

    /**
     * Добавляет объект по указанному индексу.
     * При некорректной передаче index выводит предупреждающие сообщения и завершает работу.
     *
     * @param index индекс, по которому нужно добавить объект.
     * @param obj   объект, который нужно добавить.
     *              Увеличивает емкость массива, если места недостаточно
     * @see ArrayList#resize(int)
     */
    public void add(int index, E obj) {
        if (size == capacity) {
            resize(capacity * 2);
        }
        if (index >= capacity) {
            System.out.println("Нельзя вставить элемент по индексу " + index + " потому что он выходит за рамки размера ArrayList.");
            return;
        }
        if ((index != 0) && (array[index - 1] == null)) {
            System.out.println("Нельзя вставить элемент по индексу " + index + " потому что перед ним элемента нет.");
            return;
        }
        if ((array[index] == null) || (index == 0)) {
            array[size++] = obj;
        } else {
            insert(index, obj);
        }
    }

    /**
     * Удаляет элемент по указанному индексу.
     * При некорректной передаче index выводит предупреждающие сообщения и завершает работу.
     *
     * @param index индекс, по которму нужно удалить объект.
     */
    public void remove(int index) {
        if (index >= capacity) {
            System.out.println("Некорректный индекс!");
            return;
        }
        if (size == 0) {
            System.out.println("ArrayList пустой, удалять нечего.");
            return;
        }
        if (array[index] != null) {
            Object[] newArray = new Object[capacity];
            System.arraycopy(array, 0, newArray, 0, index);
            System.arraycopy(array, index + 1, newArray, index, size - index);
            --size;
            array = newArray;
            System.out.println("Объект по индексу " + index + " успешно удалён.");
        } else {
            System.out.println("Элеманта по индексу " + index + " не существует.");
        }
    }

    /**
     * После вызова метода, массив содержит ссылку на пустой массив.
     */
    public void removeAll() {
        array = EMPTY_ARRAY;
        this.capacity = 0;
        size = 0;
        System.out.println("ArrayList теперь пуст.");
    }

    /**
     * Устанавливает переданное значение по указанному индексу. Если по предыдущему индексу нет элемента, выводит предупреждающее сообщение.
     *
     * @param index индекс, по которому нужно установить значение.
     * @param obj   объект, которым нужно заменить значение по индексу.
     */
    public void set(int index, E obj) {
        if (index >= capacity) {
            System.out.println("Элемента с индексом " + index + " не существует!");
            return;
        }
        if (size == capacity - 1) {
            resize(capacity * 2);
        }
        if ((index == 0) || (array[index - 1] != null)) {
            if (array[index] == null) {
                size++;
            }
            array[index] = obj;
        } else {
            System.out.println("Невозможно установить значение, потому что предыдущий индекс пуст.");
        }
    }

    /**
     * Сортирует ArrayList, используя компаратор, переданный методу.
     *
     * @param c компаратор.
     */
    public void Sort(Comparator<? super E> c) {
        QuickSort(0, size - 1, c);
    }

    /**
     * Приватная быстрая сортировка, используется методом Sort().
     *
     * @param left  левая граница.
     * @param right правая граница.
     * @param c     компаратор.
     * @see ArrayList#Sort(Comparator)
     */
    @SuppressWarnings("unchecked")
    private void QuickSort(int left, int right, Comparator<? super E> c) {
        if (size == 0 || left >= right) return;
        int middle = left + (right - left) / 2;
        E line = (E) array[middle];
        int i = left, j = right;
        while (i <= j) {
            while ((c.compare((E) array[i], line) < 0)) i++;
            while (c.compare((E) array[j], line) > 0) j--;
            if (i <= j) {
                E swap = (E) array[i];
                array[i] = array[j];
                array[j] = swap;
                i++;
                j--;
            }
        }
        if (left < j) QuickSort(left, j, c);
        if (right > i) QuickSort(i, right, c);
    }

    /**
     * Приватный метод для вставки элемента в середину.
     *
     * @param index индекс, по которому нужно вставить элемент.
     * @param obj   объект, значение которого нужно установить по указанному индексу.
     * @see ArrayList#add(int, E)
     */
    private void insert(int index, E obj) {
        Object[] newArray = new Object[++capacity];
        if (index >= 0) {
            for (int i = 0; i < index; ++i) {
                newArray[i] = array[i];
            }
            for (int i = index + 1; i < size; ++i) {
                newArray[i] = array[i - 1];
            }
            newArray[index] = obj;
            ++size;
        }
        array = newArray;
    }

    /**
     * Приватный метод для увеличения емкости.
     *
     * @param newSize новый размер массива.
     * @see ArrayList#add(int, E)
     * @see ArrayList#set(int, E)
     */
    private void resize(int newSize) {
        Object[] newArray = new Object[newSize];
        if (size >= 0)
            System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
        capacity = newSize;
    }
}
