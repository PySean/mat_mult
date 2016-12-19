/**
    "RunTest.java", by Sean Soderman
    The MAIN attraction! Opens the matrix input
    file specified on the command line, writes
    the values from the file to a Matrix
    object, then performs matrix multiplication
    in parallel.
*/

import java.io.File;
import java.util.concurrent.Semaphore;
class RunTest {
    public static void main(String [] args) {
        long startTime = System.currentTimeMillis();
        if (args.length < 6) {
            System.err.println("Usage: java RunTest <input1> <input2> <n> <m> <k> threads");
            System.exit(1);
        }
        int n = Integer.parseInt(args[2]);
        int m = Integer.parseInt(args[3]);
        int k = Integer.parseInt(args[4]);
        int numThreads = Integer.parseInt(args[5]);
        //Counter c = new Counter(numThreads - 1);
        //Set this to numThreads - 1, turned negative. This is so the main thread
        //awakens appropriately after all threads are done with their work.
        Semaphore s = new Semaphore((-1 * numThreads) + 1);
        Matrix first = new Matrix(n, m, new File(args[0]));
        Matrix second = new Matrix(m, k, new File(args[1]));
        //System.out.println(mat.toString());
        Matrix result = new Matrix(n, k);
        ParaMat [] workers = new ParaMat[numThreads];
        Thread  [] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            workers[i] = new ParaMat(s, first, second, result, i, numThreads);
        }
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(workers[i]);
        }
        System.out.println("Starting threads");
        for (Thread t: threads) {
            t.start();
        }
        try {
            s.acquire();
        }
        catch (Exception e) {
            //Not getting interrupted..
        }
        long endTime = System.currentTimeMillis();
        //System.out.println(result);
        System.out.println("Time (s): " + (float)(endTime - startTime)/1000 + 
                           "procs: " + numThreads);
    }

}
