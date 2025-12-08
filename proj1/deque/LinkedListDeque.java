package deque;


public class LinkedListDeque<T>{
    public class LinkedList{
        public LinkedList prev;
        public T item;
        public LinkedList next;

        public LinkedList(LinkedList prevNode, T value, LinkedList nextNode){
            prev = prevNode;
            item = value;
            next = nextNode;
        }
    }
    private LinkedList sentinel;
    private int size;

    public LinkedListDeque(){
        sentinel = new LinkedList(null,null,null);
        size = 0;
    }
    public boolean isEmpty(){
        if(sentinel.next == null){
            return true;
        }else{
            return false;
        }
    }

    public LinkedListDeque(T arg){
        sentinel = new LinkedList(null,arg,null);
        sentinel.next = new LinkedList(sentinel,arg,null);
        sentinel.prev = sentinel.next;
        size = 1;
    }
    public void addFirst(T item){
        LinkedList newNode = new LinkedList(sentinel, item, sentinel.next);
        if(sentinel.next == null){
            sentinel.next = newNode;
            sentinel.prev = newNode;
        }else{
            sentinel.next.prev = newNode;
            sentinel.next = newNode;
        }
        size += 1;
    }
    public int size(){
        return size;
    }
    public void addLast(T arg){
        if(sentinel.next == null){
            LinkedList newNode = new LinkedList(sentinel, arg, null);
            sentinel.next = newNode;
            sentinel.prev = newNode;
        }else{
            LinkedList newNode = new LinkedList(sentinel.prev, arg, null);
            sentinel.prev.next = newNode;
            sentinel.prev = newNode;
        }
        size += 1;
    }
    public T removeFirst(){
        if(sentinel.next == null){
            return null;
        }else{
            LinkedList toBeRemovedNode = sentinel.next;
            if(toBeRemovedNode.next == null){
                sentinel.prev = null;
                sentinel.next = null;
                size = 0;
                return toBeRemovedNode.item;
            }else{
                sentinel.next = toBeRemovedNode.next;
                toBeRemovedNode.next.prev = sentinel;
                size -= 1;
                return toBeRemovedNode.item;
            }
        }
    }
    public T removeLast(){
        if(sentinel.next == null){
            return null;
        }else{
            LinkedList toBeRemovedNode = sentinel.prev;
            if(toBeRemovedNode.prev == sentinel){
                sentinel.prev = null;
                sentinel.next = null;
                size = 0;
                return toBeRemovedNode.item;
            }else{
                sentinel.prev = toBeRemovedNode.prev;
                sentinel.prev.next = null;
                toBeRemovedNode.prev = null;
                size -= 1;
                return toBeRemovedNode.item;
            }
        }
    }
    public T get(int index){
        if(isEmpty()){
            return null;
        }else{
            int count = 0;
            LinkedList pointer = sentinel.next;
            while(pointer != null){
                if(count == index){
                    return pointer.item;
                }
                count += 1;
                pointer = pointer.next;
            }
            return null;
        }
    }
    public void printDeque(){
        if(isEmpty()){
            System.out.println();
        }else{
            LinkedList pointer = sentinel.next;
            while(pointer != null){
                System.out.print(pointer.item + " ");
                pointer = pointer.next;
            }
            System.out.println();
        }
    }
    public T getRecursive(int index){
        return recursionHelper(sentinel.next, 0, index);
    }
    public T recursionHelper(LinkedList node, int currentIndex, int targetIndex){
        LinkedList pointer = node;
        if(pointer == null){
            return null;
        }else{
            if(currentIndex == targetIndex){
                return pointer.item;
            }
            return recursionHelper(pointer.next, currentIndex+1, targetIndex);
        }
    }
}
