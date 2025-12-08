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
        if(size+ 1 > items.length){
            sizeUp(size*2);
        }
        items[nextFirst] = item;
        nextFirst -= 1;
        if(nextFirst == -1){
            nextFirst = items.length - 1;
        }
        size += 1;
    }
    public void addLast(T item){
        if(size+ 1 > items.length){
            sizeUp(size*2);
        }
        items[nextLast] = item;
        nextLast += 1;
        if(nextLast == items.length){
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
    public void printDequeOfItems(){
        for(int i = 0; i < items.length; i++){
            System.out.println(items[i]);
        }
        System.out.println(" ");
        System.out.println("Length is " + items.length);
    }
    public void printDeque(){
        int First = nextFirst + 1;
        for(int i = 0; i < size; i++){
            if(First == items.length){
                First = 0;
            }
            System.out.println(items[First]);
            First += 1;
        }
        System.out.println(" ");
    }
    public T removeFirst(){
        if(sizeDownDeterminant()){
            sizeDown();
        }
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
        if(sizeDownDeterminant()){
            sizeDown();
        }
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
    public void sizeUp(int capacity){
        T[] newItems = (T[]) new Object[capacity];
        int First = nextFirst + 1;
        for(int i = 0; i < size; i++){
            if(First == items.length){
                First = 0;
            }
            newItems[i] = items[First];
            First += 1;
        }
        nextFirst = newItems.length - 1;
        nextLast = items.length;
        items = newItems;
    }
    public void sizeDown(){
        T[] newItems = (T[]) new Object[items.length/2];
        int First = nextFirst + 1;
        for(int i = 0; i < size; i++){
            if(First == items.length){
                First = 0;
            }
            newItems[i] = items[First];
            First += 1;
        }
        nextFirst = newItems.length - 1;
        nextLast = size;
        items = newItems;
    }
    public boolean sizeDownDeterminant(){
        double usageRatio = (double) (size-1) / items.length;
        if(items.length >= 16 && usageRatio < 0.25){
            return true;
        }else{
            return false;
        }
    }
}
