package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }
    public void addFirst(T item) {
        if (size + 1 > items.length) {
            sizeUp(size * 2);
        }
        items[nextFirst] = item;
        nextFirst -= 1;
        if (nextFirst == -1) {
            nextFirst = items.length - 1;
        }
        size += 1;
    }
    public void addLast(T item) {
        if (size + 1 > items.length) {
            sizeUp(size * 2);
        }
        items[nextLast] = item;
        nextLast += 1;
        if (nextLast == items.length) {
            nextLast = 0;
        }
        size += 1;
    }
    public int size() {
        return size;
    }
    public T get(int index) {
        if (index > size - 1 && index < 0) {
            return null;
        }
        int first = nextFirst + 1;
        if (first == items.length) {
            first = 0;
        }
        int actualLocation = first + index;
        if (actualLocation >= items.length) {
            return items[actualLocation - items.length];
        } else {
            return items[actualLocation];
        }
    }
    private void printDequeOfItems() {
        for (int i = 0; i < items.length; i++) {
            System.out.println(items[i]);
        }
        System.out.println(" ");
        System.out.println("Length is " + items.length);
    }
    public void printDeque() {
        int first = nextFirst + 1;
        for (int i = 0; i < size; i++) {
            if (first == items.length) {
                first = 0;
            }
            System.out.println(items[first]);
            first += 1;
        }
        System.out.println(" ");
    }
    public T removeFirst() {
        if (sizeDownDeterminant()) {
            sizeDown();
        }
        int prevFirst = nextFirst + 1;
        if (prevFirst == items.length) {
            prevFirst = 0;
        }
        if (items[prevFirst] == null) {
            return null;
        }
        T toBeRemoved = items[prevFirst];
        items[prevFirst] = null;
        nextFirst += 1;
        if (nextFirst == items.length) {
            nextFirst = 0;
        }
        size -= 1;
        return toBeRemoved;
    }
    public T removeLast() {
        if (sizeDownDeterminant()) {
            sizeDown();
        }
        int prevLast = nextLast - 1;
        if (prevLast == -1) {
            prevLast = items.length - 1;
        }
        if (items[prevLast] == null) {
            return null;
        }
        T toBeRemoved = items[prevLast];
        items[prevLast] = null;
        nextLast -= 1;
        if (nextLast == -1) {
            nextLast = items.length - 1;
        }
        size -= 1;
        return toBeRemoved;
    }
    private void sizeUp(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        int first = nextFirst + 1;
        for (int i = 0; i < size; i++) {
            if (first == items.length) {
                first = 0;
            }
            newItems[i] = items[first];
            first += 1;
        }
        nextFirst = newItems.length - 1;
        nextLast = items.length;
        items = newItems;
    }
    private void sizeDown() {
        T[] newItems = (T[]) new Object[items.length / 2];
        int first = nextFirst + 1;
        for (int i = 0; i < size; i++) {
            if (first == items.length) {
                first = 0;
            }
            newItems[i] = items[first];
            first += 1;
        }
        nextFirst = newItems.length - 1;
        nextLast = size;
        items = newItems;
    }
    private boolean sizeDownDeterminant() {
        double usageRatio = (double) (size - 1) / items.length;
        return (items.length >= 16 && usageRatio < 0.25);
    }
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }
    private class ArrayIterator<T> implements Iterator<T> {
        int count = 0;
        public boolean hasNext() {
            return count < size;
        }
        public T next() {
            T toBeReturnedValue = (T) get(count);
            count += 1;
            return toBeReturnedValue;
        }
    }
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Deque) {
            Deque<T> oDeque = (Deque<T>) o;
            if (this.size != oDeque.size()) {
                return false;
            }
            for (int i = 0; i < size(); i++) {
                if (!get(i).equals(oDeque.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
