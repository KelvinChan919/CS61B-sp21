package deque;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void ArrayTest(){
        ArrayDeque<String> arr = new ArrayDeque<String>();
        arr.addLast("a");
        arr.addLast("b");
        arr.addLast("d");
        arr.addLast("e");
        arr.addLast("g");
        arr.addLast("h");
        arr.addLast("a");
        ArrayDeque<String> arr1 = new ArrayDeque<String>();
        arr1.addLast("a");
        arr1.addLast("b");
        arr1.addLast("d");
        arr1.addLast("e");
        arr1.addLast("g");
        arr1.addLast("h");
        arr1.addLast("a");
        System.out.println(arr.equals(arr1));
    }
}
