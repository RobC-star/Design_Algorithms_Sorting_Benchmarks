/* CMSC 451 Design Computer Algorithms
 *  Project 1
 *  UMGC CITE, Spring 2024
 *  Programiz CITE, 2024; https://www.programiz.com/dsa/insertion-sort
 *  Robert Carswell
 *  7 January 2024
 * */

/* Class implementing Insertion Sort algorithm extending AbstractSort class. */
class InsertionSort extends AbstractSort {

    // Overridden method to sort the array using Insertion Sort algorithm
    @Override
    void sort(int[] array) {
        // Call insertionSort method to perform sorting
        insertionSort(array);
        // Check if the array is sorted after sorting
        checkSorted(array);
    }

    // Method to perform the Insertion Sort algorithm, and retrieved from Programiz
    void insertionSort(int[] array) {
        // Start sorting process
        startSort();
        // Get the length of the array
        int size = array.length;

        // Iterate over the array starting from the second element
        for (int step = 1; step < size; step++) {
            // Store the current element in key
            int key = array[step];
            // Initialize j to the previous index
            int j = step - 1;

            // Move elements of array[0..step-1], that are greater than key, to one position ahead of their current position
            while (j >= 0 && key < array[j]) {
                array[j + 1] = array[j];
                j--;

                // Increment the count of comparisons
                incrementCount();
            }
            // Insert the key at its correct position in the sorted array
            array[j + 1] = key;
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
