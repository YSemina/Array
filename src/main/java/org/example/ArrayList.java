package org.example;

public class ArrayList<E extends Comparable<E>> {
    private static final int SIZE=10;
    private static final Object[] EMPTY_ARRAY = {};
    private  Object[] array;
    private int capacity;
    private int size=0;

    public ArrayList(){
        array=new Object[SIZE];
        capacity=SIZE;
    }

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

    public int size(){
        return size;
    }

    @SuppressWarnings("unchecked")
    public E get(int index){
        return (E)array[index];
    }

    public void add(E obj){
        if(size<capacity){
            array[size++]=obj;
        }
        else{
            resize(capacity*2);
            add(obj);
        }
    }

    public void add(int index,E obj){
        if(size==capacity){
            resize(capacity*2);
        }
        if(index>=capacity){
            System.out.println("Нельзя вставить элемент по индексу "+index+" потому что он выходит за рамки размера ArrayList.");
            return;
        }
        if(array[index-1]==null){
            System.out.println("Нельзя вставить элемент по индексу "+index+" потому что перед ним элемента нет.");
            return;
        }
        if(array[index]==null){
            array[size++]=obj;
        }
        else{
            insert(index,obj);
        }
    }

    public void remove(int index){
        if(size==0){
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

    public  void removeAll(){
        array=EMPTY_ARRAY;
        this.capacity=0;
        size=0;
        System.out.println("ArrayList теперь пуст.");
    }

    public void set(int index, E obj){
        if ((index < capacity) && (array[index] != null)) {
            array[index] = obj;
            System.out.println("Объект по индексу " + index + " успешно заменен на " + obj + ".");
        }
        else{
            System.out.println("Невозможно заменить значение, потому что по индексу "+index+" его нет.");
        }
    }

    private void insert(int index,E obj){
        Object[] newArray=new Object[capacity];
        if (index >= 0) {
            System.arraycopy(array, 0, newArray, 0, index);
            newArray[index] = obj;
            System.arraycopy(array,index,newArray,index+1,++size-index-1);
        }
        array=newArray;
    }

    private void resize(int newSize){
        Object[] newArray=new Object[newSize];
        if (size >= 0)
            System.arraycopy(array, 0, newArray, 0, size);
        array=newArray;
        capacity=newSize;
    }
}
class Quick<E extends Comparable<E>>{
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
                array.set(i,array.get(j));
                array.set(j,swap);
                i++;
                j--;
            }
        }
        if (left < j) QuickSort(left, j,array);
        if (right > i) QuickSort(i, right,array);
    }

    public void Sort(ArrayList<E> array){
        QuickSort(0,array.size()-1,array);
    }
}