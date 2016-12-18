/**
    "RandomMatrixFile.java", by Sean Soderman
    Generates a file of floating point numbers representing an n x m matrix.
*/

//The asterisk is cluttering the namespace, but
//the weird way Java has one write to files basically
//demands this (for this small program).
import java.nio.file.*;
import java.nio.charset.Charset;

class RandomMatrixFile() {

    public RandomMatrixFile() {
        super();
    }

    public static makeMatrixFile(String fileName, int n, int m) {
        Path file = null;
        BufferedWriter buffy = null;
        try { 
            file = Paths(fileName);
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
        float writeRow = new float[m];
        for (int i = 0; i < n; i++) {
            //Construct a space-delimited string of characters 
            //representing a row of numbers.
            for (int j = 0; j < m; j++) {
                writeRow[j] = Math.random() * 50;
            }
            for (float f : writeRow) {
                String num = String.format("%1$f.2 ", f);
                buffy.write(num, 0, num.length());
            }
            buffy.newLine();
        }
        buffy.close();
    }
}
