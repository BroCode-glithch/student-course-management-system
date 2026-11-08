/**
 * Graphical User Interface for the Student Course Management System.
 * Uses Swing to provide a visual interface for all core functionalities.
 * Demonstrates proper use of GUI components, event handling, and integration
 * with the CourseManager business logic class.
 */
package com.miva.coursesystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * Main GUI window for the Student Course Management System.
 * Provides a clean, organized interface with all required features.
 */
public class CourseGUI extends JFrame implements ActionListener {
    private CourseManager manager;
    private JTextField codeField, titleField, unitsField, searchField, filePathField;
    private JTextArea outputArea;
    private JComboBox<String> choiceBox;

    // Menu items
    private JMenuItem addItem, viewItem, searchItem, totalItem, saveItem, loadItem, exitItem;

    public CourseGUI(CourseManager manager) {
        this.manager = manager;
        setTitle("Student Course Management System");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
        setResizable(false);

        // Create menu bar
        createMenuBar();

        // Initialize components
        initializeComponents();

        // Set up layout
        setupLayout();

        setVisible(true);
    }

    /**
     * Create the menu bar with all menu options.
     */
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // File menu
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        saveItem = new JMenuItem("Save to File\tCtrl+S");
        saveItem.addActionListener(this);
        saveItem.setActionCommand("save");
        fileMenu.add(saveItem);

        loadItem = new JMenuItem("Load from File\tCtrl+L");
        loadItem.addActionListener(this);
        loadItem.setActionCommand("load");
        fileMenu.add(loadItem);

        fileMenu.addSeparator();

        exitItem = new JMenuItem("Exit\tAlt+F4");
        exitItem.addActionListener(this);
        exitItem.setActionCommand("exit");
        fileMenu.add(exitItem);

        // Help menu
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);

        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Student Course Management System\nCOS 201 Lab Assessment\nMIVA Open University",
                "About", JOptionPane.INFORMATION_MESSAGE));
        helpMenu.add(aboutItem);

        JMenuItem instructionsItem = new JMenuItem("Instructions");
        instructionsItem.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "This system helps students record and manage courses.\n\n"
                        + "Features:\n"
                        + "• Add courses with code, title, and units\n"
                        + "• View all recorded courses\n"
                        + "• Search courses by code\n"
                        + "• Compute total credit units\n"
                        + "• Save/load course lists to/from files",
                "Instructions", JOptionPane.INFORMATION_MESSAGE));
        helpMenu.add(instructionsItem);
    }

    /**
     * Initialize all GUI components.
     */
    private void initializeComponents() {
        // Labels and text fields for Add Course
        codeField = new JTextField(15);
        titleField = new JTextField(15);
        unitsField = new JTextField(5);

        // Label and text field for Search
        searchField = new JTextField(15);

        // Label and text field for Save/Load file path
        filePathField = new JTextField(20);
        filePathField.setText("courses.dat");

        // Output area with scroll pane
        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Buttons
        JButton addBtn = new JButton("Add Course");
        addBtn.addActionListener(this);
        addBtn.setActionCommand("add");

        JButton viewBtn = new JButton("View All");
        viewBtn.addActionListener(this);
        viewBtn.setActionCommand("view");

        JButton searchBtn = new JButton("Search");
        searchBtn.addActionListener(this);
        searchBtn.setActionCommand("search");

        JButton totalBtn = new JButton("Total Units");
        totalBtn.addActionListener(this);
        totalBtn.setActionCommand("total");

        JButton saveBtn = new JButton("Save");
        saveBtn.addActionListener(this);
        saveBtn.setActionCommand("save_gui");

        JButton loadBtn = new JButton("Load");
        loadBtn.addActionListener(this);
        loadBtn.setActionCommand("load_gui");

        JButton clearBtn = new JButton("Clear");
        clearBtn.addActionListener(e -> outputArea.setText(""));

        // Choice combo box for quick operations
        choiceBox = new JComboBox<>(new String[]{
                "Add Course", "View All Courses", "Search Course",
                "Compute Total Units", "Save to File", "Load from File", "Exit"
        });
        choiceBox.addActionListener(this);
        choiceBox.setActionCommand("choice");

        // Status bar
        JLabel statusBar = new JLabel("Ready | Student Course Management System | COS 201 Lab Assessment");
        statusBar.setBorder(BorderFactory.createEtchedBorder());
        statusBar.setBackground(Color.LIGHT_GRAY);
        statusBar.setOpaque(true);
    }

    /**
     * Set up the layout using BorderLayout with panels.
     */
    private void setupLayout() {
        setLayout(new BorderLayout(5, 5));

        // Top panel: Choice combo box
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Operation:"));
        topPanel.add(choiceBox);
        add(topPanel, BorderLayout.NORTH);

        // Center panel: Form for Add Course
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        formPanel.add(new JLabel("Course Code:"));
        formPanel.add(codeField);
        formPanel.add(new JLabel("Course Title:"));
        formPanel.add(titleField);
        formPanel.add(new JLabel("Units:"));
        formPanel.add(unitsField);
        add(formPanel, BorderLayout.CENTER);

        // Bottom panel: Buttons and operations
        JPanel bottomPanel = new JPanel(new GridLayout(1, 0, 5, 5));
        bottomPanel.add(new JButton("Add")); // Will be replaced by choice-based switching
        bottomPanel.add(new JButton("View"));
        bottomPanel.add(new JButton("Search"));
        bottomPanel.add(new JButton("Total"));
        bottomPanel.add(new JButton("Save"));
        bottomPanel.add(new JButton("Load"));
        add(bottomPanel, BorderLayout.SOUTH);

        // Output area with scroll pane
        add(new JScrollPane(outputArea), BorderLayout.PAGE_END);
    }

    /**
     * Handle all action events from menu items, buttons, and combo box.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "add":
                addCourseFromGUI();
                break;
            case "view":
                viewAllFromGUI();
                break;
            case "search":
                searchCourseFromGUI();
                break;
            case "total":
                computeTotalFromGUI();
                break;
            case "save":
                saveFromGUI();
                break;
            case "load":
                loadFromGUI();
                break;
            case "exit":
                exitProgram();
                break;
            case "save_gui":
                saveFromGUI();
                break;
            case "load_gui":
                loadFromGUI();
                break;
            case "choice":
                handleChoiceSelection();
                break;
            default:
                // Do nothing
        }
    }

    /**
     * Handle course addition from GUI fields.
     */
    private void addCourseFromGUI() {
        String code = codeField.getText().trim();
        String title = titleField.getText().trim();
        String unitsStr = unitsField.getText().trim();

        if (code.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Course code cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            codeField.requestFocus();
            return;
        }
        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Course title cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            titleField.requestFocus();
            return;
        }
        if (unitsStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Units field cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            unitsField.requestFocus();
            return;
        }

        int units;
        try {
            units = Integer.parseInt(unitsStr);
            if (units <= 0) {
                JOptionPane.showMessageDialog(this, "Units must be a positive number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                unitsField.requestFocus();
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Units must be a valid integer.", "Input Error", JOptionPane.ERROR_MESSAGE);
            unitsField.requestFocus();
            return;
        }

        boolean success = manager.addCourse(code, title, units);
        if (success) {
            outputArea.append("✓ Course added: " + code + " - " + title + " (" + units + " units)\n");
            // Clear fields
            codeField.setText("");
            titleField.setText("");
            unitsField.setText("");
        } else {
            outputArea.append("✗ Failed to add course. Code may already exist.\n");
        }
    }

    /**
     * View all courses in the output area.
     */
    private void viewAllFromGUI() {
        manager.viewAllCourses();
        // Display results in GUI output area
        // We'll capture the output by re-printing; for simplicity, just show what's in the manager
        outputArea.append("\n=== All Recorded Courses ===\n");
        for (Course c : manager.getCourseList()) {
            outputArea.append(c.toString() + "\n");
        }
        outputArea.append(String.format("Total courses: %d%nTotal units: %d%n", 
                manager.getCourseList().size(), manager.getTotalUnits()));
    }

    /**
     * Search for a course by code from GUI.
     */
    private void searchCourseFromGUI() {
        String searchCode = searchField.getText().trim();
        if (searchCode.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a course code to search.", "Input Error", JOptionPane.ERROR_MESSAGE);
            searchField.requestFocus();
            return;
        }

        Course found = manager.searchCourse(searchCode);
        if (found != null) {
            outputArea.append("\n=== Search Result ===\n");
            outputArea.append(found.toString() + "\n");
        } else {
            outputArea.append("\nCourse with code '" + searchCode + "' not found.\n");
        }
        searchField.setText("");
    }

    /**
     * Compute and display total units.
     */
    private void computeTotalFromGUI() {
        outputArea.append("\n=== Total Credit Units ===\n");
        int total = manager.getTotalUnits();
        outputArea.append("Total units for " + manager.getCourseList().size() + " course(s): " + total + "\n");
        if (manager.getCourseList().size() > 0) {
            double average = (double) total / manager.getCourseList().size();
            outputArea.append(String.format("Average units per course: %.2f%n", average));
        }
    }

    /**
     * Save courses to file from GUI.
     */
    private void saveFromGUI() {
        String filePath = filePathField.getText().trim();
        if (filePath.isEmpty()) {
            filePath = "courses.dat";
            filePathField.setText(filePath);
        }

        boolean success = manager.saveToFile(filePath);
        if (success) {
            outputArea.append("\n✓ Successfully saved " + manager.getCourseList().size() + " courses to '" + filePath + "'.\n");
        } else {
            outputArea.append("\n✗ Failed to save courses.\n");
        }
    }

    /**
     * Load courses from file from GUI.
     */
    private void loadFromGUI() {
        String filePath = filePathField.getText().trim();
        if (filePath.isEmpty()) {
            filePath = "courses.dat";
            filePathField.setText(filePath);
        }

        manager.loadFromFile(filePath);
        outputArea.append("\n=== Loading from '" + filePath + "' ===\n");
        outputArea.append("Loaded " + manager.getCourseList().size() + " course(s).\n");
    }

    /**
     * Exit the program with confirmation.
     */
    private void exitProgram() {
        int choice = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to exit?\nYour current course list may not be saved.",
                "Confirm Exit", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (choice == JOptionPane.YES_OPTION) {
            // Save before exiting
            manager.saveToFile("courses.dat");
            dispose();
            System.exit(0);
        }
    }

    /**
     * Handle choice box selection.
     */
    private void handleChoiceSelection() {
        String selected = (String) choiceBox.getSelectedItem();
        outputArea.append("\n--- Selected: " + selected + " ---\n");

        switch (selected) {
            case "Add Course":
                // Fields already populated or cleared from previous operation
                outputArea.append("Please enter course details in the form above.\n");
                break;
            case "View All Courses":
                viewAllFromGUI();
                break;
            case "Search Course by Code":
                outputArea.append("Enter course code to search (or use the Search button).\n");
                break;
            case "Compute Total Units":
                computeTotalFromGUI();
                break;
            case "Save to File":
                saveFromGUI();
                break;
            case "Load from File":
                loadFromGUI();
                break;
            case "Exit Program":
                exitProgram();
                break;
        }
    }

    /**
     * Main method to launch the GUI application.
     * @param args command line arguments (optional file path to load)
     */
    public static void main(String[] args) {
        // Allow specifying a file to load on startup
        String filePath = args.length > 0 ? args[0] : "courses.dat";

        // Initialize CourseManager
        CourseManager manager = new CourseManager(filePath);

        // Launch GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new CourseGUI(manager);
        });
    }
}