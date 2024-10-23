/* CMSC 451 Design Computer Algorithms
 *  Project 1
 *  UMGC CITE, Spring 2024
 *  Robert Carswell
 *  7 January 2024
 * */

/* This program does a warm-up before tabulating BubbleSorts and InsertionSorts critical swaps and its time required to
*  sort. Then it builds a table for each algorithm with 12 different sizes arrays and 40 data sets of random positive
*  integers using JFileChooser to select and read a file produced by BenchmarkSorts. */

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

// BenchmarkSorts class for benchmarking Bubble Sort and Insertion Sort algorithms
class BenchmarkSorts {
    // Maps to store benchmarking results for Bubble Sort and Insertion Sort
    private static final Map<Integer, int[][]> resultTableBubble = new LinkedHashMap<>();
    private static final Map<Integer, int[][]> resultTableInsertion = new LinkedHashMap<>();

    // Method to generate an array of positive random integers
    private static int[] generatePositiveRandomIntArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        int min = 1;

        // Filling the array with random positive integers
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(Integer.MAX_VALUE - min) + min;
        }
        return array;
    }

    // Method to run the benchmark for Bubble Sort and Insertion Sort
    private static void runSortBenchmark(AbstractSort bubbleSortAlgorithm, AbstractSort insertionSortAlgorithm, int size, int numberOfDataSets) {
        int[][] dataSetResultsBubble = new int[numberOfDataSets][2];
        int[][] dataSetResultsInsertion = new int[numberOfDataSets][2];

        // Running the benchmark for each data set
        for (int dataSet = 0; dataSet < numberOfDataSets; dataSet++) {
            int[] randomArray = generatePositiveRandomIntArray(size);

            // Running Bubble Sort on the same original array
            bubbleSortAlgorithm.sort(Arrays.copyOf(randomArray, randomArray.length));
            dataSetResultsBubble[dataSet][0] = bubbleSortAlgorithm.getCount();
            dataSetResultsBubble[dataSet][1] = (int) bubbleSortAlgorithm.getTime();

            // Running Insertion Sort on the same original array
            insertionSortAlgorithm.sort(Arrays.copyOf(randomArray, randomArray.length));
            dataSetResultsInsertion[dataSet][0] = insertionSortAlgorithm.getCount();
            dataSetResultsInsertion[dataSet][1] = (int) insertionSortAlgorithm.getTime();
        }

        // Storing the benchmarking results
        resultTableBubble.put(size, dataSetResultsBubble);
        resultTableInsertion.put(size, dataSetResultsInsertion);
    }

    // Method to output benchmarking results to files
    private static void outputTable(){
        try (PrintStream output = new PrintStream("BubbleSort.txt")){
            buildTable(output, resultTableBubble);
        }
        catch(FileNotFoundException | SecurityException exception){
            throw new UnsortedException(exception.getMessage());
        }

        try (PrintStream output = new PrintStream("InsertionSort.txt")) {
            buildTable(output, resultTableInsertion);
        }
        catch(FileNotFoundException | SecurityException exception){
            throw new UnsortedException(String.valueOf(exception));
        }
    }

    // Method to build the table of benchmarking results
    private static void buildTable(PrintStream output, Map<Integer, int[][]> resultTableInsertion) {
        for (Map.Entry<Integer, int[][]> entry : resultTableInsertion.entrySet()) {
            int size = entry.getKey();
            int[][] dataSetResults = entry.getValue();

            // Writing data to the output file
            output.print (size);
            for (int[] dataSetResult : dataSetResults) {
                output.print(" " + dataSetResult[0]);
                output.print(" " + dataSetResult[1]);
            }
            output.print("\n");
        }
    }

    // Method to perform the warm-up phase
    private static void performWarmUp(int[] arraySizes, int numberOfDataSets) {
        int warmup_iterations = 200;
        for (int i = 0; i < warmup_iterations; i++) {
            long startTime = System.nanoTime();
            for (int size : arraySizes) {
                BubbleSort bubbleSortWarmUp = new BubbleSort();
                InsertionSort insertionSortWarmUp = new InsertionSort();
                runSortBenchmark(bubbleSortWarmUp, insertionSortWarmUp, size, numberOfDataSets);
            }
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            System.out.println("Warming-up: " + (i+1) + " of " + warmup_iterations + ", Nano Seconds elapsed: " + duration);
        }
    }

    // Main method
    public static void main(String[] args){
        // Number of data sets and array sizes for benchmarking
        int numberOfDataSets = 40;
        int[] arraySizes = {100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 1100, 1200};

        // Warm-up phase
        performWarmUp(arraySizes, numberOfDataSets);

        // Resetting the result table for actual measurements
        resultTableBubble.clear();
        resultTableInsertion.clear();

        // Running the benchmark for each array size
        for (int size : arraySizes) {
            // Creating instances of Bubble Sort and Insertion Sort algorithms
            BubbleSort bubbleSort = new BubbleSort();
            InsertionSort insertionSort = new InsertionSort();
            // Running the benchmark
            runSortBenchmark(bubbleSort, insertionSort, size, numberOfDataSets);
        }

        // Outputting the benchmarking results to files
        outputTable();
    }
}
