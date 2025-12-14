package deque;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void ArrayTest(){
        ArrayDeque arr = new ArrayDeque();
        arr.addLast(1);
        arr.addLast("b");
        arr.addLast("d");
        arr.addLast("e");
        arr.addLast("g");
        arr.addLast("h");
        arr.addLast(1);
        ArrayDeque arr1 = new ArrayDeque();
        arr1.addLast(1);
        arr1.addLast("b");
        arr1.addLast("d");
        arr1.addLast("e");
        arr1.addLast("g");
        arr1.addLast("h");
        arr1.addLast(1);
        System.out.println(arr.equals(arr1));
    }
}
