package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        int getLastTimes = 10000;
        AList<Integer> list = new AList<>();
        AList<Double> timeInSeconds = new AList<>();
        list.addLast(1000);
        list.addLast(2000);
        list.addLast(4000);
        list.addLast(8000);
        list.addLast(16000);
        list.addLast(32000);
        list.addLast(64000);
        list.addLast(128000);
        AList<Integer> ops = new AList<>();
        for(int i = 0; i < list.size(); i++){
            SLList<Integer> slList= new SLList<>();
            for(int j = 0; j < list.get(i); j++){
                slList.addLast(j);
            }
            Stopwatch count = new Stopwatch();
            for(int j = 0; j < getLastTimes; j++){
                slList.getLast();
            }
            double timeTaken = count.elapsedTime();
            timeInSeconds.addLast(timeTaken);
            ops.addLast(getLastTimes);
        }
        printTimingTable(list,timeInSeconds,ops);
    }

}
