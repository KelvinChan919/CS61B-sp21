package deque;


import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private class LinkedList {
        private LinkedList prev;
        private T item;
        private LinkedList next;

        private LinkedList(LinkedList prevNode, T value, LinkedList nextNode) {
            prev = prevNode;
            item = value;
            next = nextNode;
        }
    }
    private LinkedList sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new LinkedList(null, null, null);
        size = 0;
    }
    public void addFirst(T item) {
        LinkedList newNode = new LinkedList(sentinel, item, sentinel.next);
        if (sentinel.next == null) {
            sentinel.next = newNode;
            sentinel.prev = newNode;
        } else {
            sentinel.next.prev = newNode;
            sentinel.next = newNode;
        }
        size += 1;
    }
    public int size() {
        return size;
    }
    public void addLast(T arg) {
        if (sentinel.next == null) {
            LinkedList newNode = new LinkedList(sentinel, arg, null);
            sentinel.next = newNode;
            sentinel.prev = newNode;
        } else {
            LinkedList newNode = new LinkedList(sentinel.prev, arg, null);
            sentinel.prev.next = newNode;
            sentinel.prev = newNode;
        }
        size += 1;
    }
    public T removeFirst() {
        if (sentinel.next == null) {
            return null;
        } else {
            LinkedList toBeRemovedNode = sentinel.next;
            if (toBeRemovedNode.next == null) {
                sentinel.prev = null;
                sentinel.next = null;
                size = 0;
                return toBeRemovedNode.item;
            } else {
                sentinel.next = toBeRemovedNode.next;
                toBeRemovedNode.next.prev = sentinel;
                size -= 1;
                return toBeRemovedNode.item;
            }
        }
    }
    public T removeLast() {
        if (sentinel.next == null) {
            return null;
        } else {
            LinkedList toBeRemovedNode = sentinel.prev;
            if (toBeRemovedNode.prev == sentinel) {
                sentinel.prev = null;
                sentinel.next = null;
                size = 0;
                return toBeRemovedNode.item;
            } else {
                sentinel.prev = toBeRemovedNode.prev;
                sentinel.prev.next = null;
                toBeRemovedNode.prev = null;
                size -= 1;
                return toBeRemovedNode.item;
            }
        }
    }
    public T get(int index) {
        if (isEmpty()) {
            return null;
        } else {
            int count = 0;
            LinkedList pointer = sentinel.next;
            while (pointer != null) {
                if (count == index) {
                    return pointer.item;
                }
                count += 1;
                pointer = pointer.next;
            }
            return null;
        }
    }
    public void printDeque() {
        if (isEmpty()) {
            System.out.println();
        } else {
            LinkedList pointer = sentinel.next;
            while (pointer != null) {
                System.out.print(pointer.item + " ");
                pointer = pointer.next;
            }
            System.out.println();
        }
    }
    public T getRecursive(int index) {
        return recursionHelper(sentinel.next, 0, index);
    }
    private T recursionHelper(LinkedList node, int currentIndex, int targetIndex) {
        LinkedList pointer = node;
        if (pointer == null) {
            return null;
        } else {
            if (currentIndex == targetIndex) {
                return pointer.item;
            }
            return recursionHelper(pointer.next, currentIndex + 1, targetIndex);
        }
    }
    public Iterator<T> iterator() {
        return new Iterable();
    }
    private class Iterable<T> implements Iterator<T> {
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
