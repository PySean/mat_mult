/**
    "RandomMatrixFile.java", by Sean Soderman
    Generates a file of floating point numbers representing an n x m matrix.
    Each number is no larger than 50 and is rounded to two decimal places.
*/

//The asterisk is cluttering the namespace, but
//the weird way Java has one write to files basically
//demands this (for this small program).
import java.nio.file.*;
import java.nio.charset.Charset;
import java.io.BufferedWriter;
import java.io.IOException;

class RandomMatrixFile {

    public RandomMatrixFile() {
        super();
    }
    //Essentially, this should be able to make the file
    //on its own. It's better decoupled as its own tool
    //rather than used within RunTest.java.
    public static void main(String [] args) {
        if (args.length < 3) {
            System.err.println("Usage: java RandomMatrixFile <outputFileName> <n> <m>");
            System.exit(1);
        }
        String fileName = args[0];
        int rows = 0;
        int cols = 0;
        try {
            rows = Integer.parseInt(args[1]);
            cols = Integer.parseInt(args[2]);
        }
        catch (NumberFormatException x) {
            System.err.println("One of those is not an integer!");
            System.exit(1);
        }
        makeMatrixFile(fileName, rows, cols);
    }
    public static void makeMatrixFile(String fileName, int n, int m) {
        Path file = null;
        BufferedWriter buffy = null;
        try { 
            file = Paths.get(fileName);
        }
        catch (InvalidPathException i) {
            System.out.println("Invalid path:" + i);
            return;
        }
        try {
            buffy = Files.newBufferedWriter(file, Charset.forName("US-ASCII"));
        }
        //Way too many little exceptions to mess around with for now.
        catch (Exception e) {
            System.out.println("Something bad happened with the buffered writer!");
            return;
        }
        //At last...write to the matrix file.
        //Initialize this, can be reused on each iteration.
        double [] writeRow = new double[m];
        for (int i = 0; i < n; i++) {
            //Construct a space-delimited string of characters 
            //representing a row of numbers.
            for (int j = 0; j < m; j++) {
                writeRow[j] = Math.random() * 50.0;
            }
            try {
                int x = 0;
                for (x = 0; x < writeRow.length - 1; x++) {
                    String num = String.format("%1$.2f ", writeRow[x]);
                    buffy.write(num, 0, num.length());
                }
                //Write out the final number, without the space at the
                //end.
                buffy.write(String.format("%1$.2f", writeRow[x]));
                buffy.newLine();
            }
            catch (IOException io) {
                System.err.println("Uh oh! Can't write to file! " + io);
                return;
            }
        }
        try {
            buffy.close();
        }
        catch (IOException io) {
            System.err.println("Couldn't close file for some reason");
            System.exit(1);
        }
    }
}
