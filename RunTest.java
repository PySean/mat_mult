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
        if (args.length < 4) {
            System.err.println("Usage: java RunTest <input1> <input2> <n> <m> <k>");
            System.exit(1);
        }
        int n = Integer.parseInt(args[2]);
        int m = Integer.parseInt(args[3]);
        Matrix first = new Matrix(n, m, new File(args[0]));
        Matrix second = new Matrix(m, k, new File(args[1]));
        //System.out.println(mat.toString());
    }
}
