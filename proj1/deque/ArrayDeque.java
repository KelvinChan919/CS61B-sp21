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
        if(index > size - 1 && index < 0){
            return null;
        }
        int First = nextFirst + 1;
        if(First == items.length){
            First = 0;
        }
        int actualLocation = First + index;
        if(actualLocation >= items.length){
            System.out.println("index" + " " + index + " is " + items[actualLocation - items.length]);
            return items[actualLocation - items.length];
        }else{
            System.out.println("index" + " " + index + " is " + items[actualLocation]);
            return items[actualLocation];
        }
    }
    public void printDeque(){
        for(int i = 0; i < items.length; i++){
            System.out.println(items[i]);
        }
        System.out.println(" ");
    }
    public T removeFirst(){
        int prevFirst = nextFirst + 1;
        if(prevFirst == items.length){
            prevFirst = 0;
        }
        if(items[prevFirst] == null){
            return null;
        }
        T toBeRemoved = items[prevFirst];
        items[prevFirst] = null;
        nextFirst += 1;
        if(nextFirst == items.length){
            nextFirst = 0;
        }
        size -= 1;
        return toBeRemoved;
    }
    public T removeLast(){
        int prevLast = nextLast - 1;
        if(prevLast == -1){
            prevLast = items.length - 1;
        }
        if (items[prevLast] == null){
            return null;
        }
        T toBeRemoved = items[prevLast];
        items[prevLast] = null;
        nextLast -= 1;
        if(nextLast == -1){
            nextLast = items.length - 1;
        }
        size -= 1;
        return toBeRemoved;
    }

}
