package Data_Structures;

/**
 * @author Chris Savill, chs17@aber.ac.uk * 
 * File Name: Competitor.java
 * Description: Competitor class which stores all members and functions pertaining to a competitor. 
 * First Created: 15/03/2013
 * Last Modified: 15/03/2013
 */
public class Competitor {

    private String name;
    private int number;
    private char course;
    
    /** Constructor to initialise competitor.
     * @param number The competitor's number.
     * @param course The competitor's course.
     * @param name The competitor's name.
     */
    public Competitor(int number, char course, String name) {
        this.number = number;
        this.course = course;
        this.name = name;
    }
    
    /** Method to return the competitor's number. */
    public int getNumber() {
        return number;
    }
    
    /** Method to return the course the competitor is entered on. */
    public char getCourse() {
        return course;
    }
    
    /** Method to return the competitor's name. */
    public String getName() {
        return name;
    }
}
