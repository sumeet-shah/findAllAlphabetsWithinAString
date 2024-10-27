import org.apache.commons.lang3.time.StopWatch;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

public class Program {
    public static void main(String[] args) throws FileNotFoundException {
        // Read string from the input file
        String input = Util.readFile("input.txt");
        // start the stop watch
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        // get the result
        WithoutThreads obj = new WithoutThreads();
        boolean answer = obj.perform(input);
        // stop the stop watch
        stopWatch.stop();
        System.out.println("contains all the alphabets? " + answer + ". It " +
                                   "took " + stopWatch.getTime() + "ms");
        System.out.println();
        // multithreading logic
        System.out.println("starting with multithreading logic now");
        System.out.println();
        MultithreadingLogic multithreadingLogic = new MultithreadingLogic();
        stopWatch.reset();
        stopWatch.start();
        answer = multithreadingLogic.perform(
                input);
        stopWatch.stop();
        System.out.println(
                "contains all the alphabets? " + answer + ". It took " + stopWatch.getTime() + "ms");


        //     starting the processing with parallel stream logic
        System.out.println();
        System.out.println();
        System.out.println("using parallel streams ");
        System.out.println();
        UsingParallelStreams usingParallelStreams = new UsingParallelStreams();
        stopWatch.reset();
        stopWatch.start();
        boolean perform = usingParallelStreams.perform(input);
        stopWatch.stop();
        System.out.println(
                "contains all the alphabets? " + perform + ". It took " + stopWatch.getTime() + "ms");

    }

}
