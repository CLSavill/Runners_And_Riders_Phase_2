/*
 * Author: Chris Savill, chs17@aber.ac.uk
 * File Name: event.h
 * Description: Header file for the Event class.
 * First Created: 11/03/2013
 * Last Modified: 14/03/2013
 */

#ifndef EVENT_H
#define	EVENT_H

#include <memory>
#include "competitor.h"
#include "course.h"
#include <vector>
#include <cstdlib>
#include <iostream>

#define MAX_EVENT_NAME_LENGTH 79
#define MAX_DATE_LENGTH 19

class Competitor;
class Course;

class Event {
private:
    std::string name; //Name of the event.
    std::string date; //Date of the event.
    std::string start_time; //Start time of the event.
    std::vector<Competitor*> *competitors; //Array of competitors to take part in the event.
    std::vector<Course*> *courses; //Array of courses that are part of an event.

    void set_name(); //Member function to get the user to input the events name.
    void set_date(); //Member function to get the user to input the date of the event.
    void set_start_time(); //Member function to get the user to input the start time of the event. 

public:
    Event();
    ~Event();
    std::vector<Course*>* getCourses(); //Member function that returns a pointer to the vector of courses.
    void add_competitor(); //Member function that will handle adding a competitor to the event.
    void add_course(); //Member function that will handle adding a course to the event.
    void export_event(); //Member function that will handle exporting the name, date and start_time of the event to a '.txt' file.
    void export_competitors(); //Member function that will handle the exporting of the array of competitors to a '.txt' file.
    void export_courses(); //Member function that will handle the exporting of the array of courses to a '.txt' file.
};

#endif	/* EVENT_H */

