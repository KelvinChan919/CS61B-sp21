package deque;

public class ArrayDeque<T>{
    public T[] items;
    public int size;
    public int nextFirst;
    public int nextLast;

    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }
    public void addFirst(T item){
        items[nextFirst] = item;
        nextFirst -= 1;
        if(nextFirst == -1){
            nextFirst = items.length - 1;
        }
        size += 1;
    }
    public void addLast(T item){
        items[nextLast] = item;
        nextLast += 1;
        if(nextFirst == items.length){
            nextLast = 0;
        }
        size += 1;
    }
    public boolean isEmpty(){
        if(size == 0){
            return true;
        }else{
            return false;
        }
    }
    public int size(){
        return size;
    }
    public T get(int index){
        return items[index];
    }
    public void printDeque(){
        for(int i = 0; i < items.length; i++){
            System.out.println(items[i]);
        }
        System.out.println(" ");
    }

}
