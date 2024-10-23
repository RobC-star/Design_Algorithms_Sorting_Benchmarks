/* CMSC 451 Design Computer Algorithms
 *  Project 1
 *  UMGC CITE, Spring 2024
 *  Robert Carswell
 *  7 January 2024
 * */

/* This program uses JFileChooser to select and read a file produced by BenchmarkSorts
 *  to display and calculate the average and the coefficient of variance of count and time.
 * */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;

public class Report {
    public static void main(String[] args) {
        // Open a file chooser dialog to select the input file
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);

        // If the user selects a file, process it
        if (result == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            processFile(filePath);
        }
    }

    private static void processFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Create a table model to hold the data
            DefaultTableModel model = new DefaultTableModel();
            // Add columns to the table model
            model.addColumn("Size");
            model.addColumn("Avg Counts");
            model.addColumn("Coef Count");
            model.addColumn("Avg Time");
            model.addColumn("Coef Time");

            String line;
            // Read each line of the file
            while ((line = reader.readLine()) != null) {
                // Split the line into values separated by space
                String[] values = line.split(" ");
                // Create a LinkedHashMap to hold the table data for this row
                LinkedHashMap<String, Object> table = new LinkedHashMap<>();

                // Iterate over the values in the line
                for (int i = 0; i < values.length; i += values.length) {
                    String key = values[i];
                    // Populate the table data for each column
                    table.put(key, values[i]);
                    table.put(key + "avg_count", String.format("%.2f", calculateAvg(values, i + 1, i + values.length))); // Starts at 2, length 81
                    table.put(key + "coef_count", String.format("%.2f%%", calculateCoef(values, i + 1, i + values.length)));
                    table.put(key + "avg_time", String.format("%.2f", calculateAvg(values, i + 2, i + values.length))); // Starts at 3, length 81
                    table.put(key + "coef_time", String.format("%.2f%%", calculateCoef(values, i + 2, i + values.length)));
                }
                // Add the row data to the table model
                model.addRow(table.values().toArray());
            }

            // Create a JTable with the populated model
            JTable table = new JTable(model);
            // Display the table in a dialog
            JOptionPane.showMessageDialog(null, new JScrollPane(table));

        } catch (IOException exception) {
            // Handle IO exceptions
            exception.printStackTrace();
        }
    }

    // Method to calculate the average of values in a range
    private static double calculateAvg(String[] values, int start, int end) {
        double sum = 0;
        int count = 0;

        // Iterate over ever other value
        for (int i = start; i < end; i += 2) {
            // Sum the values and count the number of values
            sum += Double.parseDouble(values[i]);
            count++;
        }
        // Calculate and return the average
        return sum / count;
    }

    // Method to calculate the coefficient of variation of values in a range
    private static double calculateCoef(String[] values, int start, int end) {
        // Calculate the average
        double average = calculateAvg(values, start, end);
        double sumSquaredDiff = 0;

        // Iterate over ever other value
        for (int i = start; i < end; i += 2) {
            // Calculate the squared difference from the average
            double diff = Double.parseDouble(values[i]) - average;
            sumSquaredDiff += diff * diff;
        }

        // Calculate the standard deviation
        double sdev = Math.sqrt(sumSquaredDiff / ((values.length - 1.0) / 2)); //((values.length - 1.0) / 2)) = 40

        // Calculate and return the coefficient of variation
        return ((sdev / average) * 100);
    }
}
