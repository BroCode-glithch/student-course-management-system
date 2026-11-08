# Student Course Management System
## COS 201 Programming I - Mid-Semester Lab Assessment

---

## **PROJECT OVERVIEW**

**Course:** COS 201 Programming I  
**Assignment:** Mid-Semester Lab Assessment Project  
**Department:** Software Engineering  
**Matriculation Number:** 2025/B/SENG/0421  
**Student Name:** Emmanuel Ogunfunwa  
**Email:** e.ogunfunwa1129@miva.edu.ng  
**Date:** November 2026

---

## **PROJECT DESCRIPTION**

This project implements a **Student Course Management System (SCMS)** - a simple desktop application designed to help students record and manage the courses they are taking for a semester. The application allows users to:

- Add courses with details (course code, title, and unit)
- Display all recorded courses clearly
- Search for specific courses using course code
- Compute and display total credit units
- Save course list to a file
- Load previously saved courses from a file

The system can be used via a **text-based menu** or a **simple graphical interface** (Swing-based GUI).

---

## **SYSTEM ARCHITECTURE**

The system consists of three main Java classes:

| Class | Purpose |
|-------|---------|
| `Course.java` | Entity class representing a single course with encapsulation, getters/setters, and toString() method |
| `CourseManager.java` | Controller/manager handling all core operations, file I/O, and recursive menu display |
| `CourseGUI.java` | Graphical User Interface using Swing with visual components and modal dialogs |

---

## **FILE STRUCTURE**

```
Student-Course-Management-System/
├── README.md                                          # This file
├── DOC-20261102-WA0007.pdf                           # Original lab requirements
├── src/
│   └── com/
│       └── miva/
│           └── coursesystem/
│               ├── Course.java                      # Course entity class
│               ├── CourseManager.java               # Main controller with all functionality
│               └── CourseGUI.java                   # Graphical UI (Swing)
├── bin/
│   └── com/
│       └── miva/
│           └── coursesystem/
│               ├── Course.class                     # Compiled Course class
│               ├── CourseManager.class              # Compiled CourseManager class
│               └── CourseGUI.class                  # Compiled CourseGUI class
└── submission_report.txt                             # Full report for PDF conversion
```

---

## **FEATURES IMPLEMENTED**

### Core Functionality (All 7 Required Operations)

1. **Add Course** - Add courses with validation (code, title, units); prevents duplicates
2. **View All Courses** - Display formatted table with course details and total count
3. **Search Course by Code** - Case-insensitive linear search through course list
4. **Compute Total Units** - Sum all units + calculate average units per course
5. **Save to File** - Persist courses to file in `code|title|units` format
6. **Load from File** - Restore courses from file; handles malformed data gracefully
7. **Exit Program** - Clean exit with confirmation

### Additional Technical Features

- **Proper Use of Classes and Objects** - Encapsulated Course class with full functionality
- **Methods for Operations** - Separate methods for each operation with clear responsibilities
- **Logical Decision-Making** - Switch-case menu with proper branching and validation
- **Invalid Input Handling** - Validation for empty codes, titles, positive units
- **String Processing** - trim(), equalsIgnoreCase(), toUpperCase(), split()
- **Clean Coding Style** - Javadoc comments, meaningful variable names, proper formatting
- **Iteration (Loops)** - Enhanced for-loops throughout for summing, saving, loading, searching
- **Recursive Feature** - `displayMenuRecursive()` method demonstrating recursion practical use
- **Exception Handling** - try-catch-finally blocks for file I/O and input validation
- **File Reading & Writing** - PrintWriter/FileWriter for saving, BufferedReader/FileReader for loading
- **Two Interface Options** - Console-based (CourseManager.main) and GUI-based (CourseGUI.main)

---

## **HOW TO RUN THE PROJECT**

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Any Java IDE (IntelliJ, Eclipse, NetBeans) or command-line

### Compile and Run

```bash
# 1. Navigate to project directory
cd Student-Course-Management-System

# 2. Compile all source files
javac -d bin src/com/miva/coursesystem/Course.java
              src/com/miva/coursesystem/CourseManager.java
              src/com/miva/coursesystem/CourseGUI.java

# 3. Run Console Version
java -cp bin com.miva.coursesystem.CourseManager

# 4. Run GUI Version
java -cp bin com.miva.coursesystem.CourseGUI

# 5. Run GUI with specific data file
java -cp bin com.miva.coursesystem.CourseGUI courses.dat
```

### Example Console Output

```
=== Student Course Management System ===
1. Add Course
2. View All Courses
3. Search Course by Code
4. Compute Total Units
5. Save to File
6. Load from File
7. Exit Program
Enter your choice (1-7): 1
Enter course code: CS101
Enter course title: Introduction to Programming
Enter credit units: 3
Course added successfully: CS101        Introduction to Programming  3 units

=== All Recorded Courses ===
--------------------------------------------------------
Course Code    Course Title                 Units
--------------------------------------------------------
CS101          Introduction to Programming  3 units
--------------------------------------------------------
Total courses: 1
```

### Example GUI Interface

The GUI provides a visual window with:
- Input fields for course code, title, and units
- Buttons for Add, View, Search, Total, Save, Load
- Output area with scroll pane for course display
- Menu selection via combo box
- Status bar at bottom
- Modal dialogs for errors and information

---

## **USAGE EXAMPLES**

### Adding a Course (Console)

```
1. Add Course
Enter course code: CS201
Enter course title: Data Structures
Enter credit units: 4
Course added successfully: CS201        Data Structures               4 units
```

### Searching for a Course

```
3. Search Course by Code
Enter course code to search: CS101
Found course: CS101        Introduction to Programming  3 units
```

### Computing Total Units

```
4. Compute Total Units
=== Total Credit Units ===
Total units for 1 course(s): 3
Average units per course: 3.00
```

### Saving and Loading

```
5. Save to File
Enter file path to save courses (e.g., courses.dat): my_courses.dat
✓ Successfully saved 1 courses to 'my_courses.dat'.

6. Load from File
Enter file path to load courses from (e.g., my_courses.dat):
=== Loading from 'my_courses.dat' ===
Loaded 1 course(s).
```

---

## **TECHNICAL IMPLEMENTATION DETAILS**

### Course.java - Entity Class

- **Fields:** courseCode (String), courseTitle (String), units (int)
- **Constructors:** Default and Parameterized
- **Methods:** getters/setters with validation, toString(), equals(), hashCode()
- **Validation:** Non-empty codes/titles, positive units, duplicate prevention

### CourseManager.java - Controller Class

- **Field:** ArrayList<Course> courseList for storage
- **Methods:** addCourse(), viewAllCourses(), searchCourse(), computeTotalUnits(), 
  saveToFile(), loadFromFile(), getTotalUnits(), getCourseList()
- **File Format:** `code|title|units` per line (e.g., `CS101|Intro to Prog|3`)
- **Exception Handling:** FileNotFoundException, IOException, NumberFormatException
- **Recursion:** displayMenuRecursive() for menu loop

### CourseGUI.java - Graphical Interface

- **Components:** JTextFields, JTextArea, JButtons, JComboBox, JMenuBar
- **Layout:** BorderLayout with panels for organization
- **Events:** ActionListener for all UI components
- **Integration:** Uses CourseManager business logic methods

---

## **SCREENSHOTS FOR SUBMISSION**

**Recommended screenshots to capture:**

1. **Console Menu** - Running the program and selecting options
   ```
   java -cp bin com.miva.coursesystem.CourseManager
   ```

2. **After Adding Courses** - Show courses displayed in table format

3. **GUI Window** - The graphical interface with input fields and buttons

4. **Search Results** - Demonstrating course search by code

5. **Total Units** - Display showing total and average units

6. **Save/Load Operations** - Confirmation messages after file operations

**How to capture:**
- Run the program: `java -cp bin com.miva.coursesystem.CourseManager`
- Use `Print Screen` key or `Snipping Tool`
- Or use IDE's built-in screenshot capability

---

## **ACADEMIC INTEGRITY**

**This is an individual lab exercise.** Every student must submit their own original work.

- Collaboration is permitted for discussing ideas and enhancing understanding
- Copying, sharing, or submitting another student's completed work is strictly prohibited
- Evidence of duplication or plagiarism will attract severe academic penalties
- All source code must be your original work
- This repository contains my personal implementation for reference only

---

## **LAB REFERENCES**

- **Lab Assignment:** COS 201 Programming I - Mid-Semester Lab Assessment Project
- **Case Study:** Student Course Management (Mini-System)
- **Original PDF:** DOC-20261102-WA0007.pdf
- **Institution:** MIVA Open University
- **Year:** 2025/2026 Academic Session

---

## **PUSH TO GITHUB INSTRUCTIONS**

### Step 1: Initialize Git Locally

```bash
cd Student-Course-Management-System
git init
git add -
git commit -m "Initial commit: Student Course Management System"
```

### Step 2: Create GitHub Repository

1. Go to [github.com](https://github.com) and log in
2. Click **+** in the top right → **New Repository**
3. Repository name: `student-course-management-system` (or your preferred name)
4. Description: "COS 201 Programming I - Mid-Semester Lab Assessment - Student Course Management System"
5. Select **Public** or **Private** (your choice)
6. **Do NOT** initialize with README, .gitignore, or license (we already have files)
7. Click **Create repository**

### Step 3: Push Local Repository to GitHub

```bash
# Replace YOUR_USERNAME with your GitHub username
git remote add origin git@github.com:YOUR_USERNAME/student-course-management-system.git
# OR using HTTPS:
# git remote add origin https://github.com/YOUR_USERNAME/student-course-management-system.git

git branch -M main
git push -u origin main
```

### Step 4: Verify

- Visit your repository URL on GitHub
- Confirm all files are uploaded correctly
- README should be displayed automatically

---

## **SUBMISSION CHECKLIST**

- [x] Source code files (Course.java, CourseManager.java, CourseGUI.java)
- [x] Student information (name, email, department, matriculation number)
- [x] Comprehensive README.md file
- [x] All 7 required operations implemented
- [x] Proper exception handling and validation
- [x] File I/O save/load functionality
- [x] Recursive menu feature
- [x] GUI and console interface options
- [x] Academic integrity noted
- [x] Ready for LMS submission
- [ ] Convert to PDF format for LMS upload (if required)
- [ ] Add screenshots to PDF submission
- [ ] Submit before LMS deadline

---

## **CONTACT & SUPPORT**

**Student:** Emmanuel Ogunfunwa  
**Email:** e.ogunfunwa1129@miva.edu.ng  
**Department:** Software Engineering  
**Matriculation:** 2025/B/SENG/0421  

**Course:** COS 201 Programming I  
**Institution:** MIVA Open University  

--- 

*This project was developed as part of the COS 201 Programming I Mid-Semester Lab Assessment. All code is original work and demonstrates proper Java programming practices including OOP, file handling, exception management, and recursion.*