/**
    "Matrix.java", by Sean Soderman
    A class wrapping a 2D array of floating point numbers
    supporting some convenient operations.
*/

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

class Matrix {
    //Array of integers representing a matrix.
    float [][] arr;
    int rows;
    int cols;

    /**
        Simple constructor for allocating an n x m matrix.
        @return: A freshly allocated Matrix object.
    */
    public Matrix(int n, int m, File matrixFile) {
        arr = new float[n][m];
        this.rows = n;
        this.cols = m;
        initializeMatrix(matrixFile);
    }
    /**
        Initializes the internal array with a file of numbers
        representing an n x m matrix, where n is the number
        of lines in the file, and m is the number of columns in the file.
        @return: a reference to the array.
    */
    public void initializeMatrix(File matfile) {
        //Used for grabbing rows of numbers from the file.
        Scanner reader = null;
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
            String numbers = reader.nextLine();
            //Used for parsing each row of numbers grabbed.
            //A bit inefficient, but the overhead is useful
            //for exemplifying the serial fraction.
            Scanner numParser = new Scanner(numbers);
            while (numParser.hasNextFloat()) {
                float floater = numParser.nextFloat();
                this.arr[rows][cols] = floater;
                cols++;
            }
            numParser.close();
            rows++;
        }
        if (rows == -1) {
            System.out.println("Please don't give me empty files.");
        }
        /*
        this.rows = rows;
        this.cols = cols;
        */
        reader.close();
    }
    public String toString() {
        String build = "";
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                build += this.arr[i][j] + " ";
            }
            build += "\n";
        }
        return build;
    }
}
