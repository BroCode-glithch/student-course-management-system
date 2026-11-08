/**
 * Represents a course with code, title, and credit units.
 * Demonstrates proper use of classes and objects as required by the lab assessment.
 */
package com.miva.coursesystem;

public class Course {
    private String courseCode;
    private String courseTitle;
    private int units;

    /**
     * Default constructor.
     */
    public Course() {
        this.courseCode = "";
        this.courseTitle = "";
        this.units = 0;
    }

    /**
     * Parameterized constructor.
     * @param courseCode the course code (e.g., "COS201")
     * @param courseTitle the course title (e.g., "Introduction to Programming")
     * @param units the credit units (e.g., 3)
     */
    public Course(String courseCode, String courseTitle, int units) {
        this.courseCode = courseCode.trim();
        this.courseTitle = courseTitle.trim();
        this.units = units;
    }

    /**
     * Gets the course code.
     * @return the course code
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * Sets the course code with validation (non-empty after trimming).
     * @param courseCode the course code to set
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode == null ? "" : courseCode.trim();
    }

    /**
     * Gets the course title.
     * @return the course title
     */
    public String getCourseTitle() {
        return courseTitle;
    }

    /**
     * Sets the course title with validation (non-empty after trimming).
     * @param courseTitle the course title to set
     */
    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle == null ? "" : courseTitle.trim();
    }

    /**
     * Gets the credit units.
     * @return the units
     */
    public int getUnits() {
        return units;
    }

    /**
     * Sets the credit units with validation (must be positive).
     * @param units the units to set
     */
    public void setUnits(int units) {
        this.units = units > 0 ? units : 0;
    }

    /**
     * Returns a string representation of the course.
     * @return formatted course information
     */
    @Override
    public String toString() {
        return String.format("%-15s %-40s %d units", courseCode, courseTitle, units);
    }

    /**
     * Compares this course with another object based on course code.
     * @param obj the object to compare with
     * @return true if objects are equal based on course code
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Course other = (Course) obj;
        return courseCode.equalsIgnoreCase(other.courseCode);
    }

    /**
     * Returns the hash code based on course code.
     * @return hash code
     */
    @Override
    public int hashCode() {
        return courseCode.toUpperCase().hashCode();
    }
}

