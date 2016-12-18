/**
    "RunTest.java", by Sean Soderman
    The MAIN attraction! Opens the matrix input
    file specified on the command line, writes
    the values from the file to a Matrix
    object, then performs matrix multiplication
    in parallel.
*/

import java.io.File;

class RunTest {
    public static void main(String [] args) {
        if (args.length < 3) {
            System.err.println("Usage: java RunTest <input> <n> <m>");
            System.exit(1);
        }
        int n = Integer.parseInt(args[1]);
        int m = Integer.parseInt(args[2]);
        Matrix matlab = new Matrix(n, m, new File(args[0]));
        System.out.println(matlab.toString());
    }
    
}
