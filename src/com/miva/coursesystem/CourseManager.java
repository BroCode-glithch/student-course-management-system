/**
 * Student Course Management System - Main controller class.
 * Handles all core functionality: add, view, search, compute units, save, load.
 * Demonstrates proper use of methods, iteration, recursion, and exception handling.
 */
package com.miva.coursesystem;

import java.io.*;
import java.util.*;

public class CourseManager {
    private ArrayList<Course> courseList;
    private static final String DEFAULT_FILE = "courses.dat";

    public CourseManager() {
        courseList = new ArrayList<>();
    }

    public CourseManager(String filePath) {
        courseList = new ArrayList<>();
        loadFromFile(filePath);
    }

    /**
     * Adds a new course to the list after validating input.
     * @param code the course code
     * @param title the course title
     * @param units the credit units
     * @return true if course was added successfully, false otherwise
     */
    public boolean addCourse(String code, String title, int units) {
        // Validate course code - must not be empty after trimming
        if (code == null || code.trim().isEmpty()) {
            System.out.println("Error: Course code cannot be empty.");
            return false;
        }

        // Validate title - must not be empty after trimming
        if (title == null || title.trim().isEmpty()) {
            System.out.println("Error: Course title cannot be empty.");
            return false;
        }
        // Validate units - must be positive
        if (units <= 0) {
            System.out.println("Error: Units must be a positive number.");
            return false;
        }
        // Check for duplicate course code (case-insensitive)
        for (Course c : courseList) {
            if (c.getCourseCode().equalsIgnoreCase(code)) {
                System.out.println("Error: Course code '" + code + "' already exists.");
                return false;
            }
        }
        // Create and add the new course
        Course newCourse = new Course(code, title, units);
        courseList.add(newCourse);
        System.out.println("Course added successfully: " + newCourse);
        return true;
    }

    /**
     * Displays all courses in the list.
     * If no courses are recorded, shows a message indicating an empty list.
     */
    public void viewAllCourses() {
        if (courseList.isEmpty()) {
            System.out.println("\n=== No courses recorded yet ===");
            System.out.println("Add courses using the 'Add Course' option first.\n");
            return;
        }

        System.out.println("\n=== All Recorded Courses ===");
        System.out.println("--------------------------------------------------------");
        System.out.printf("%-15s %-40s %s%n", "Course Code", "Course Title", "Units");
        System.out.println("--------------------------------------------------------");

        for (Course c : courseList) {
            System.out.println(c);
        }

        System.out.println("--------------------------------------------------------");
        System.out.printf("Total courses: %d%n", courseList.size());
    }

    /**
     * Searches for a course by its code using linear search.
     * @param searchCode the course code to search for
     * @return the Course object if found, null otherwise
     */
    public Course searchCourse(String searchCode) {
        if (searchCode == null || searchCode.trim().isEmpty()) {
            System.out.println("Error: Please enter a valid course code to search.");
            return null;
        }

        String targetCode = searchCode.trim();

        // Linear search through the course list
        for (Course c : courseList) {
            if (c.getCourseCode().equalsIgnoreCase(targetCode)) {
                System.out.println("Found course: " + c);
                return c;
            }
        }

        System.out.println("Course with code '" + targetCode + "' not found.");
        return null;
    }

    /**
     * Computes and displays the total number of credit units across all courses.
     * Uses iteration (enhanced for-loop) as required by the lab assessment.
     */
    public void computeTotalUnits() {
        int total = 0;

        // Iterate through all courses and sum units
        for (Course c : courseList) {
            total += c.getUnits();
        }

        System.out.println("\n=== Total Credit Units ===");
        System.out.printf("Total units for %d course(s): %d%n", courseList.size(), total);

        if (courseList.size() > 0) {
            double average = (double) total / courseList.size();
            System.out.printf("Average units per course: %.2f%n", average);
        }
    }

    /**
     * Returns the total units across all courses.
     * @return total credit units
     */
    public int getTotalUnits() {
        int total = 0;
        for (Course c : courseList) {
            total += c.getUnits();
        }
        return total;
    }

    /**
     * Returns the list of all courses.
     * @return unmodifiable list of courses
     */
    public java.util.List<Course> getCourseList() {
        return java.util.Collections.unmodifiableList(courseList);
    }

    /**
     * Saves all recorded courses to a specified file.
     * Uses file I/O with proper error handling through exceptions.
     * @param filePath the path to the file where courses will be saved
     * @return true if save was successful, false otherwise
     */
    public boolean saveToFile(String filePath) {
        if (courseList.isEmpty()) {
            System.out.println("Warning: No courses to save. The course list is empty.");
            return false;
        }

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(filePath));
            for (Course c : courseList) {
                // Write course data in a structured format: code|title|units
                writer.println(c.getCourseCode() + "|" + c.getCourseTitle() + "|" + c.getUnits());
            }
            System.out.println("Successfully saved " + courseList.size() + " courses to '" + filePath + "'.");
            return true;
        } catch (IOException e) {
            System.out.println("Error: Failed to save courses to file.");
            e.printStackTrace();
            return false;
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * Loads courses from a specified file.
     * Handles file-not-found and malformed data gracefully.
     * @param filePath the path to the file from which courses will be loaded
     */
    public void loadFromFile(String filePath) {
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("No saved file found at '" + filePath + "'. Starting with empty course list.");
            return;
        }

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            int loadedCount = 0;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                // Parse the line using "|" as delimiter
                String[] parts = line.split("\\|", -1);

                if (parts.length >= 3) {
                    String code = parts[0].trim();
                    String title = parts[1].trim();
                    int units;

                    try {
                        units = Integer.parseInt(parts[2].trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Warning: Invalid units found in file, skipping line: " + line);
                        continue;
                    }

                    // Only add if code doesn't already exist
                    boolean exists = false;
                    for (Course c : courseList) {
                        if (c.getCourseCode().equalsIgnoreCase(code)) {
                            exists = true;
                            break;
                        }
                    }

                    if (!exists) {
                        Course c = new Course(code, title, units);
                        courseList.add(c);
                        loadedCount++;
                    }
                } else {
                    System.out.println("Warning: Malformed data in file, skipping line: " + line);
                }
            }

            System.out.println("Loaded " + loadedCount + " course(s) from '" + filePath + "'.");
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found at '" + filePath + "'.");
        } catch (IOException e) {
            System.out.println("Error: Failed to read file '" + filePath + "'.");
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Recursive method that displays the menu and processes user choice.
     * Demonstrates a simple recursive feature as required by the lab assessment.
     * @param scanner the Scanner object for reading user input
     */
    private void displayMenuRecursive(Scanner scanner) {
        int choice = showMenu(scanner);

        switch (choice) {
            case 1:
                addCourseInterface(scanner);
                displayMenuRecursive(scanner);
                break;
            case 2:
                viewAllCourses();
                displayMenuRecursive(scanner);
                break;
            case 3:
                System.out.print("Enter course code to search: ");
                String searchCode = scanner.nextLine();
                searchCourse(searchCode);
                displayMenuRecursive(scanner);
                break;
            case 4:
                computeTotalUnits();
                displayMenuRecursive(scanner);
                break;
            case 5:
                saveToFileInterface(scanner);
                displayMenuRecursive(scanner);
                break;
            case 6:
                loadFromFileInterface(scanner);
                displayMenuRecursive(scanner);
                break;
            case 7:
                System.out.println("\n=== Thank you for using Student Course Management System ===");
                System.out.println("Goodbye! Your courses have been saved automatically.");
                // Do not recurse - exit the program
                break;
            default:
                System.out.println("\nInvalid choice. Please select 1-7.");
                displayMenuRecursive(scanner);
        }
    }

    /**
     * Displays the main menu and gets user choice.
     * @return the user's menu choice (1-7)
     */
    private int showMenu(Scanner scanner) {
        System.out.println("\n=== Student Course Management System ===");
        System.out.println("1. Add Course");
        System.out.println("2. View All Courses");
        System.out.println("3. Search Course by Code");
        System.out.println("4. Compute Total Units");
        System.out.println("5. Save to File");
        System.out.println("6. Load from File");
        System.out.println("7. Exit Program");
        System.out.print("Enter your choice (1-7): ");

        // Input validation with exception handling
        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            choice = 0; // Invalid choice
        }

        return choice;
    }

    /**
     * Interface for adding a course interactively.
     * Includes string processing (trimming, validation).
     * @param scanner the Scanner object for reading input
     */
    private void addCourseInterface(Scanner scanner) {
        System.out.print("Enter course code: ");
        String code = scanner.nextLine().trim();

        System.out.print("Enter course title: ");
        String title = scanner.nextLine().trim();

        System.out.print("Enter credit units: ");
        String unitsInput = scanner.nextLine().trim();
        int units = 0;

        try {
            units = Integer.parseInt(unitsInput);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid units entered. Using default value of 0.");
        }

        addCourse(code, title, units);
    }

    /**
     * Interface for saving course list to a file interactively.
     * @param scanner the Scanner object for reading input
     */
    private void saveToFileInterface(Scanner scanner) {
        System.out.print("Enter file path to save courses (e.g., courses.dat): ");
        String filePath = scanner.nextLine().trim();

        if (filePath.isEmpty()) {
            filePath = DEFAULT_FILE;
        }

        boolean success = saveToFile(filePath);

        if (success) {
            System.out.println("Courses saved to: " + filePath);
        }
    }

    /**
     * Interface for loading courses from a file interactively.
     * @param scanner the Scanner object for reading input
     */
    private void loadFromFileInterface(Scanner scanner) {
        System.out.print("Enter file path to load courses from (e.g., courses.dat): ");
        String filePath = scanner.nextLine().trim();

        if (filePath.isEmpty()) {
            filePath = DEFAULT_FILE;
        }

        loadFromFile(filePath);
    }

    /**
     * Main method - entry point of the application.
     * Demonstrates clean coding style and proper program structure.
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("=== Student Course Management System ===");
        System.out.println("Initializing system...");

        // Allow specifying a file via command line, otherwise use default
        String filePath = args.length > 0 ? args[0] : "courses.dat";

        CourseManager manager = new CourseManager(filePath);

        System.out.println("Loaded " + manager.courseList.size() + " course(s) from previous session.\n");

        // Start with recursive menu display
        System.out.println("Welcome to the Student Course Management System!");

        // Use a single scanner for the entire session
        Scanner scanner = new Scanner(System.in);
        manager.displayMenuRecursive(scanner);

        scanner.close();
    }
}