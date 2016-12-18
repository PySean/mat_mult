/**
    "Matrix.java", by Sean Soderman
    A class wrapping a 2D array of floating point numbers
    supporting some convenient operations.
*/

import java.util.Scanner;
import java.io.FileNotFoundException;

class Matrix {
    //Array of integers representing a matrix.
    float [][] arr;
    int rows;
    int cols;

    /**
        Simple constructor for allocating an n x m matrix.
        @return: A freshly allocated Matrix object.
    */
    public Matrix(int n, int m) {
        arr = new float[n][m];
        this.rows = n;
        this.cols = m;
    }
    /**
        The initialization actually used in the benchmark...the above could
        still prove useful, however.
        @return: A freshly allocated Matrix object.
    */
    public Matrix(File matrixFile) {
        initializeMatrix(matrixFile);
    }

    /**
        Initializes the internal array with a file of numbers
        representing an n x m matrix, where n is the number
        of lines in the file, and m is the number of columns in the file.
        @return: a reference to the array.
    */
    public initializeMatrix(File matfile) {
        //Used for grabbing rows of numbers from the file.
        Scanner reader;
        try {
            reader = new Scanner(matfile);
        }
        catch (FileNotFoundException f) {
            System.out.println("File not found: " + f);
        }
        int rows = 0;
        int cols = 0;
        while (reader.hasNext()) {
            //Reset for the current iteration.
            cols = 0;
            String numbers = reader.readLine();
            //Used for parsing each row of numbers grabbed.
            //A bit inefficient, but the overhead is useful
            //for exemplifying the serial fraction.
            Scanner numParser = new Scanner(numbers);
            while (numParser.hasNextFloat()) {
                this.arr[rows][cols] = numParser.nextFloat();
                cols++;
            }
            numParser.close();
            rows++;
        }
        if (rows == -1) {
            System.out.println("Please don't give me empty files.");
        }
        this.rows = rows;
        this.cols = cols;
        reader.close();
    }
    public toString() {
        String build = "";
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                build += this.arr[i][j] + " ";
            }
            build += "\n";
        }

    }
}
