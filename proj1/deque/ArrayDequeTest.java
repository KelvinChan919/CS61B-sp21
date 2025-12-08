package deque;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void ArrayTest(){
        ArrayDeque<Integer> arr = new ArrayDeque<Integer>();
        arr.addFirst(1);
        arr.addFirst(2);
        arr.addFirst(3);
        arr.addLast(4);
        arr.addLast(5);
        arr.addFirst(6);
        arr.addFirst(7);
        arr.addFirst(8);
        arr.removeLast();
        arr.removeLast();
        arr.removeLast();
        arr.removeLast();
        arr.removeLast();
        arr.removeFirst();
        arr.removeFirst();
        System.out.println(arr.nextFirst);
        arr.removeFirst();
        arr.printDeque();
    }
}
