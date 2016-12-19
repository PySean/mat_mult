/**
    "ParaMat.java", by Sean Soderman
    Enables parallel computation upon a matrix. Utilizes
    a "result" matrix object, a synchronized counter denoting the
    current row this thread is using, and a semaphore used
    as a barrier for the main thread to wait on until all
    computations upon the matrix are complete.

    Each thread represented by this class computes result values for
    *one row at a time*.
*/

import java.util.concurrent.Semaphore;
class ParaMat implements Runnable {
    Semaphore s;
    Matrix first, second, result;
    //Begins as the id of the thread. Represents the index of the
    //row, essentially.
    int id;
    int numThreads;
    /*
    public ParaMat(Counter c, Semaphore s, Matrix first, Matrix second, 
                   Matrix result, int id) {
        this.c = c;
        this.s = s;
        this.first = first;
        this.second = second;
        this.result = result;
        this.id = id;
    }
    */

    public ParaMat(Semaphore s, Matrix first, Matrix second, 
                   Matrix result, int id, int numThreads) {
        this.s = s;
        this.first = first;
        this.second = second;
        this.result = result;
        this.id = id;
        this.numThreads = numThreads;
    }

    public void run() {
        float [][] one = this.first.arr;
        float [][] two = this.second.arr;
        int n = this.first.rows;
        int m = this.first.cols;
        int k = this.second.cols;
        int curr = id;
        //id is the row of the result matrix I am sticking
        //values into.
        while (curr < n) {
            //System.out.println("Thread " + id); DEBUG
            float sum = 0;
            //Selects the column of the second array to multiply with.
            for (int i = 0; i < k; i++) {
                //Walks down row id, column i, multiplying and adding.
                for (int j = 0; j < m; j++) {
                    sum += one[curr][j] * two[j][i];
                }
                result.arr[curr][i] = sum;
                sum = 0;
            }
            curr += numThreads;
        }
        //The thread family is done with computation. Let the main thread know.
        System.out.println("Thread " + id + " finished!");
        s.release();
    }
}
