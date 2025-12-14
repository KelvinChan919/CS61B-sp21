package deque;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void ArrayTest(){
        ArrayDeque<String> arr = new ArrayDeque<String>();
        arr.addLast("a");
        arr.addLast("b");
        arr.addFirst("c");
        arr.addLast("d");
        arr.addLast("e");
        arr.addFirst("f");
        arr.addLast("g");
        arr.addLast("h");
        arr.addLast("a");
        arr.addLast("b");
        arr.addFirst("c");
        arr.addLast("d");
        arr.addLast("e");
        arr.addFirst("f");
        arr.addLast("g");
        arr.addLast("h");
        arr.addLast("a");
        arr.addLast("b");
        arr.addFirst("c");
        arr.addLast("d");
        arr.addLast("e");
        arr.addFirst("f");
        arr.addLast("g");
        arr.addLast("h");
        arr.addLast("a");
        arr.addLast("b");
        arr.addFirst("c");
        arr.addLast("d");
        arr.addLast("e");
        arr.addFirst("f");
        arr.addLast("g");
        arr.addLast("h");
        arr.addLast("h");
        for(int i = 0; i < 17; i++){
            arr.removeFirst();
        }
        arr.removeLast();
        System.out.println("size is " + arr.size);
        arr.printDequeOfItems();
        arr.printDeque();
    }
}
