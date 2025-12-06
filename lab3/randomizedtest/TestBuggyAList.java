package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void simpleTest(){
        AListNoResizing<Integer> noResize = new AListNoResizing<>();
        BuggyAList<Integer> buggyResize = new BuggyAList<>();
        for(int i = 3; i < 7; i++){
            noResize.addLast(i);
            buggyResize.addLast(i);
        }
        Assert.assertEquals(noResize.removeLast(),buggyResize.removeLast());
        Assert.assertEquals(noResize.removeLast(),buggyResize.removeLast());
        Assert.assertEquals(noResize.removeLast(),buggyResize.removeLast());
    }
    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();
        int N = 500000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int lSize = L.size();
                int bSize = B.size();
            }else if (operationNumber == 2) {
                int size = L.size();
                if (size > 0) {
                    assertEquals(L.getLast(), B.getLast());
                    assertEquals(L.removeLast(), B.removeLast());
                }
            }
        }

    }

}
