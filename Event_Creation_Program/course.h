/*
 * Author: Chris Savill, chs17@aber.ac.uk
 * File Name: course.h
 * Description: Header file for the Course class.
 * First Created: 11/03/2013
 * Last Modified: 11/03/2013
 */

#include <vector>

#ifndef COURSE_H
#define	COURSE_H

class Course {
private:
    char letter; //The courses unique identification letter for an event.
    int number_of_nodes; //The number of nodes the course contains.
    std::vector<int> nodes; //An array of nodes that are contained in the course.
    std::vector<int> nodes_available; //An array of nodes that are available to select from, read in from the 'nodes.txt' file.
    
    void set_letter(); //Member function that will set the letter of the course.
    void set_number_of_nodes(); //Member function that will set the number of nodes of the course.
    void read_nodes_available(); //Member function that reads in the nodes from the 'nodes.txt' file and adds them to the nodes available array.
    void add_node(); //Member function that adds a new node to the course.
    bool check_node_exists(int number); //Member function that checks that the node being added exists in the array of nodes available.

public:
    Course();
    ~Course();
};

#endif	/* COURSE_H */
