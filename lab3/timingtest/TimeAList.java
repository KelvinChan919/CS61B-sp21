package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hug.
 */
public class TimeAList {
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
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        AList<Integer> parameters = new AList<>();
        AList<Double> timeInSeconds = new AList<>();
        int parametersLength = 0;
        parameters.addLast(1000);
        parameters.addLast(2000);
        parameters.addLast(4000);
        parameters.addLast(8000);
        parameters.addLast(16000);
        parameters.addLast(32000);
        parameters.addLast(64000);
        parameters.addLast(128000);
        parameters.addLast(10000000);
        while(parametersLength < parameters.size()){
            AList<Integer> operations = new AList<>();
            Stopwatch count = new Stopwatch();
            for(int i = 0; i < parameters.get(parametersLength); i++){
                operations.addLast(i);
            }
            double timeTaken = count.elapsedTime();
            timeInSeconds.addLast(timeTaken);
            parametersLength += 1;
        }
        printTimingTable(parameters, timeInSeconds, parameters);
    }
}
