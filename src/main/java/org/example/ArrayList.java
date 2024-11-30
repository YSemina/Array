package org.example;
/**
 *Переменные в ArrayList:
 * Статические, т.к. не нужны для каждого экземпляра класса:
 * SIZE - константа для задания емкости массива по умолчанию
 * EMPTY_ARRAY - заготовка для создания пустого массива

 * Не статические:
 * array - массив типа Object
 * capacity - емкость массива
 * size - количество ненулевых элементов
 */
public class ArrayList<E extends Comparable<E>> {
    private static final int SIZE=10;
    private static final Object[] EMPTY_ARRAY = {};
    private  Object[] array;
    private int capacity;
    private int size=0;

    /**
     * Конструктор по умолчанию создает пустой массив на 10 элементов
     */
    public ArrayList(){
        array=new Object[SIZE];
        capacity=SIZE;
    }
    /**
     *Конструктор, принимающий в качестве параметра емкость массива.
     *Если передается 0, создается пустой массив.
     * Если передается отрицательное значение, выбрасывается исключение, потому что нельзя создавать массив с отрицательным кол-вом элементов.
     */
    public  ArrayList (int capacity){
        if(capacity==0){
            array=EMPTY_ARRAY;
            this.capacity=0;
        }
        else if (capacity > 0) {
            this.capacity = capacity;
            array = new Object[capacity];
        }
        else{
            throw new IllegalArgumentException("ArrayList не создан, потому что не может содержать отрицательное значение. Ваше значение: "+ capacity);
        }
    }
    /**
     *Возвращает количество ненулевых элементов
     */
    public int size(){
        return size;
    }
    /**
     *Возвращает ёмкость массива
     */
    public int capacity(){
        return capacity;
    }
    /**
     *Возвращает элемент по переданному индексу
     */
    @SuppressWarnings("unchecked")
    public E get(int index){
        return (E)array[index];
    }
    /**
     *Принимает в кач-ве параметра объект, добавляет его по порядку
     */
    public void add(E obj){
        if(size<capacity){
            array[size++]=obj;
        }
        else{
            resize(capacity*2);
            add(obj);
        }
    }
    /**
     *Принимает индекс, по которому нужно добавить и сам объект.
     * Если емкости недостаточно, увеличиваем емкость в 2 раза
     * Если переданный индекс больше емкости, метод завершает работу, предварительно выводя сообщение и незаконости действия
     * Если по предшествующему индексу нет элемента (null) метод завершает работу, предварительно выводя сообщение
     * Если проверки пройдены, то добавляется элемент
     */
    public void add(int index,E obj){
        if(size==capacity){
            resize(capacity*2);
        }
        if(index>=capacity){
            System.out.println("Нельзя вставить элемент по индексу "+index+" потому что он выходит за рамки размера ArrayList.");
            return;
        }
        if((index!=0)&&(array[index-1]==null)){
            System.out.println("Нельзя вставить элемент по индексу "+index+" потому что перед ним элемента нет.");
            return;
        }
        if((array[index]==null)||(index==0)){
            array[size++]=obj;
        }
        else{
            insert(index,obj);
        }
    }
    /**
     *Принимает индекс, по которому нужно удалить элемент
     * Если индекс выходит за рамки емкости массива, метод завершает работу с прдепреждающим сообщением
     * Если массив пуст, метод завершает работу, предварительно выводя сообщение
     * Если элемент по указанному индексу существует,он удаляетсяж если нет - выводится сообщение об отсутствии элемента
     */
    public void remove(int index){
        if (index >= capacity) {
            System.out.println("Некорректный индекс!");
            return;
        }
        if (size == 0) {
            System.out.println("ArrayList пустой, удалять нечего.");
            return;
        }
        if(array[index]!=null){
            Object[] newArray=new Object[capacity];
            System.arraycopy(array, 0, newArray, 0, index);
            System.arraycopy(array, index+1,newArray,index,size-index);
            --size;
            array=newArray;
            System.out.println("Объект по индексу "+index+" успешно удалён.");
        }
        else{
            System.out.println("Элеманта по индексу "+index+" не существует.");
        }
    }
    /**
     *Делает массив пустым
     */
    public  void removeAll(){
        array=EMPTY_ARRAY;
        this.capacity=0;
        size=0;
        System.out.println("ArrayList теперь пуст.");
    }
    /**
     *Принимает индекс, по которому нужно установить значение и элемент, который нужно вставить по этому индексу.
     * Не устанавливает значение, если предшествующий элемент пуст
     */
    public void set(int index, E obj){
        if(size==capacity-1){
            resize(capacity*2);
        }
        if ((index < capacity) &&(index==0)||((index < capacity) && (array[index-1] != null))) {
            add(index,obj);
        }
        else{
            System.out.println("Невозможно установить значение, потому что предыдущий индекс пуст.");
        }
    }
    /**
     *"Сухая" вставка элемента без сдвига других элементов, для сортировки
     */
    public  void setForSort(int index,E obj){
        array[index]=obj;
    }
    /**
     *Этот метод используется add'ом, чтобы подвинуть другие элементы
     */
    private void insert(int index,E obj){
        Object[] newArray=new Object[capacity];
        if (index >= 0) {
            //System.arraycopy(array, 0, newArray, 0, index);
            for(int i=0;i<index;++i){
                newArray[i]=array[i];
            }
            for(int i=index+1;i<size;++i){
                newArray[i]=array[i-1];
            }
            newArray[index] = obj;
            //System.arraycopy(array,index,newArray,index+1,++size-index-1);
        }
        array=newArray;
    }
    /**
     *Приватный метод для увеличения емкости
     */
    private void resize(int newSize){
        Object[] newArray=new Object[newSize];
        if (size >= 0)
            System.arraycopy(array, 0, newArray, 0, size);
        array=newArray;
        capacity=newSize;
    }
}

class Quick<E extends Comparable<E>>{
    /**
     *Быстрая сортировка
     */
    private void QuickSort(int left, int right, ArrayList<E> array){
        if (array.size() == 0 || left >= right) return;
        int middle = left + (right - left) / 2;
        E line = array.get(middle);
        int i = left, j = right;
        while (i <= j) {
            while ((array.get(i).compareTo(line)<0)) i++;
            while (array.get(j).compareTo(line)>0) j--;
            if (i <= j) {
                E swap = array.get(i);
                array.setForSort(i,array.get(j));
                array.setForSort(j,swap);
                i++;
                j--;
            }
        }
        if (left < j) QuickSort(left, j,array);
        if (right > i) QuickSort(i, right,array);
    }
    /**
     *Публичный метод для удобного, короткого названия
     */
    public void Sort(ArrayList<E> array){
        QuickSort(0,array.size()-1,array);
    }
}