/* CMSC 451 Design Computer Algorithms
 *  Project 1
 *  UMGC CITE, Spring 2024
 *  Robert Carswell
 *  7 January 2024
 * */

/* Custom RuntimeException class named UnsortedException */
class UnsortedException extends RuntimeException {

    // Constructor taking a message parameter, calling superclass constructor
    public UnsortedException(String message) {
        super(message);
    }

    // Static method to check if an array is sorted
    static void checkSorted(int[] array) {
        // Iterate over the array, starting from the second element
        for (int i = 1; i < array.length; i++) {
            // If the previous element is greater than the current element, array is not sorted
            if (array[i - 1] > array[i]) {
                // Throw a new Exception with a message indicating the array is not sorted
                throw new Exception("Array is not sorted");
            }
        }
    }
}
