/* CMSC 451 Design Computer Algorithms
 *  Project 1
 *  UMGC CITE, Spring 2024
 *  JavaTPoint CITE, 2024; https://www.javatpoint.com/bubble-sort-in-java
 *  Robert Carswell
 *  7 January 2024
 * */

/* Class implementing Bubble Sort algorithm extending AbstractSort class */
class BubbleSort extends AbstractSort {

    // Overridden method to sort the array using Bubble Sort algorithm
    @Override
    void sort(int[] array) {
        // Call bubbleSort method to perform sorting
        bubbleSort(array);
        // Check if the array is sorted after sorting
        checkSorted(array);
    }

    // Method to perform the Bubble Sort algorithm, and retrieved from JavaTPoint
    void bubbleSort(int[] array) {
        // Start sorting process
        startSort();
        // Get the length of the array
        int n = array.length;

        // Iterate over the array
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                // If the current element is greater than the next element, swap them
                if (array[j - 1] > array[j]) {
                    // Swap elements
                    int temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                    // Increment the count of comparisons
                    incrementCount();
                }
            }
        }
        // End sorting process
        endSort();
    }

    // Method to check if the array is sorted
    private void checkSorted(int[] array) {
        // Call the static method checkSorted of UnsortedException class
        UnsortedException.checkSorted(array);
    }
}
