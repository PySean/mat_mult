/**
    "Counter.java", by Sean Soderman
    Implements a synchronized counter object usable by threads.
*/

class Counter {
    private int value;

    //The passed in value MUST equal the number of threads 
    //summoned (in this case, 1 to 4) minus one in order to
    //work correctly.
    public Counter(int value) {
        this.value = value;
    }

    public synchronized int increment() {
        return ++value;
    }

}
