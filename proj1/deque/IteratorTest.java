package deque;

import org.junit.Test;

public class IteratorTest {
    @Test
    public void Test(){
        ArrayDeque<String> arr = new ArrayDeque<String>();
        LinkedListDeque<String> newList = new LinkedListDeque<String>();
        arr.addFirst("a");
        arr.addFirst("ayuty4");
        arr.addFirst("as");
        arr.addFirst("c");
        newList.addFirst("a");
        newList.addFirst("ayuty4");
        newList.addFirst("as");
        newList.addFirst("c");
        System.out.println(arr.equals(newList));
    }

}
