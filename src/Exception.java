/* CMSC 451 Design Computer Algorithms
 *  Project 1
 *  UMGC CITE, Spring 2024
 *  Robert Carswell
 *  7 January 2024
 * */

/* Custom Exception class that extends UnsortedException */
class Exception extends UnsortedException {

    // Constructor that takes a message parameter
    public Exception(String message) {
        // Call to the superclass constructor with the provided message
        super(message);
    }
}
