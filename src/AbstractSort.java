/* CMSC 451 Design Computer Algorithms
 *  Project 1
 *  UMGC CITE, Spring 2024
 *  Robert Carswell
 *  7 January 2024
 * */

/* Abstract class defining the structure for sorting algorithms */
abstract class AbstractSort {

    // Static variable to keep track of the number of comparisons
    private static int count = 0;
    // Variable to store the time taken for sorting
    private long time = 0;

    // Abstract method to be implemented by subclasses for sorting
    abstract void sort(int[] array);

    // Method to initialize sorting process
    void startSort() {
        // Reset the count of comparisons
        count = 0;
        // Record the start time of sorting
        time = System.nanoTime();
    }

    // Method to mark the end of sorting process and calculate the time taken
    void endSort() {
        // Calculate the sorting duration
        time = System.nanoTime() - time;
    }

    // Method to increment the count of swaps
    void incrementCount() {
        count++;
    }

    // Method to get the total count of comparisons
    int getCount() {
        return count;
    }

    // Method to get the time taken for sorting
    long getTime() {
        return time;
    }
}
