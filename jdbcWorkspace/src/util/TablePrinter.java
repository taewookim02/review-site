package util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TablePrinter {

    private static final String NO_DATA = "null"; // Placeholder for null values

    // Method without custom column names
    public static <T> void printTable(List<T> objects, String[] fieldNames) {
        // Call the overloaded method with fieldNames as both field names and column headers
        printTable(objects, fieldNames, fieldNames);
    }

    // Overloaded method with an additional parameter for custom column names
    public static <T> void printTable(List<T> objects, String[] fieldNames, String[] columnNames) {
        if (objects == null || objects.isEmpty() || fieldNames == null || fieldNames.length == 0
            || columnNames == null || columnNames.length != fieldNames.length) {
            System.out.println("Invalid data or configuration.");
            return;
        }

        List<String[]> rows = new ArrayList<>();
        rows.add(columnNames); // Use custom column names for header

        int[] maxWidths = new int[columnNames.length];
        for (int i = 0; i < columnNames.length; i++) {
            maxWidths[i] = columnNames[i].length(); // Initialize with header length
        }

        for (T obj : objects) {
            String[] row = new String[fieldNames.length];
            for (int i = 0; i < fieldNames.length; i++) {
                try {
                    Field field = obj.getClass().getDeclaredField(fieldNames[i]);
                    field.setAccessible(true);
                    Object value = field.get(obj);
                    row[i] = value != null ? value.toString() : NO_DATA;
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    row[i] = NO_DATA;
                }
                maxWidths[i] = Math.max(maxWidths[i], row[i].length());
            }
            rows.add(row);
        }

        printRows(rows, maxWidths);
    }

    // Helper method to print the rows
    private static void printRows(List<String[]> rows, int[] maxWidths) {
        StringBuilder formatBuilder = new StringBuilder();
        for (int maxWidth : maxWidths) {
            formatBuilder.append("%-").append(maxWidth + 2).append("s | ");
        }
        String format = formatBuilder.toString();

        for (String[] row : rows) {
            System.out.printf(format + "%n", (Object[]) row);
        }
    }
    
    public static <T> void printRecordsVertically(List<T> objects, String[] fieldNames, String[] columnNames) {
        if (objects == null || objects.isEmpty() || fieldNames == null || fieldNames.length == 0
            || columnNames == null || columnNames.length != fieldNames.length) {
            System.out.println("Invalid data or configuration for vertical display.");
            return;
        }

        for (T obj : objects) {
            System.out.println("---------------------------------------------------");
            for (int i = 0; i < fieldNames.length; i++) {
                String fieldName = columnNames[i]; // Use the user-friendly column name
                String fieldValue = NO_DATA;
                try {
                    Field field = obj.getClass().getDeclaredField(fieldNames[i]);
                    field.setAccessible(true);
                    Object value = field.get(obj);
                    fieldValue = value != null ? value.toString() : NO_DATA;
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    System.out.println(fieldName + ": " + NO_DATA);
                    continue;
                }
                System.out.println(fieldName + ": " + fieldValue);
            }
        }
        System.out.println("---------------------------------------------------");
    }

}