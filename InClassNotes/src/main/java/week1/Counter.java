package week1;

public class Counter {

    // 2. Add a private field
    // TODO: Declare private int count;
    private int count;

    // 3. Write the default constructor
    public Counter() {
        count = 0;
    }

    // 4. Add an alternate constructor
    public Counter(int startingCounter) {
        count = startingCounter;
    }

    // 5. Write an accessor method
    public int getCount() {
        return count;
    }

    // 6. Add an update method
    public void counterMove() {
        count++;
        saveHistory();
    }
    
    // 7. Overload increment
    public void updateCounter(int number) {
        count = count + number;
        saveHistory();
    }

   
    // 8. Add a reset method
    public void resetCount() {
        count = 0;
        saveHistory();
    }

    //9. Add a fixed-size history array that records the last 5 states of the counter. All logic must happen inside of the object and be hidden from the user.
    private int[] history = new int[5];

    private void saveHistory() {
        history[0] = history[1];
        history[1] = history[2];
        history[2] = history[3];
        history[3] = history[4];
        history[4] = count;
    }
}