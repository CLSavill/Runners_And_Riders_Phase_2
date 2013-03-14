/*
 * Author: Chris Savill, chs17@aber.ac.uk
 * File Name: competitor.h
 * Description: Header file for the Competitor class.
 * First Created: 11/03/2013
 * Last Modified: 14/03/2013
 */

#ifndef COMPETITOR_H
#define	COMPETITOR_H

#include <memory>
#include <string>

#define MAX_COMPETITOR_NAME_LENGTH 51 //Includes null terminator \0.

class Event;

class Competitor {
private:
    int number; //The competitor's unique identification number for an event.
    std::string name; //The competitor's name.
    char course; //The course letter the competitor is entering in for.

    void set_number(int number); //Member function that will set the number of the competitor.
    void set_name(); //Member function that will set the name of the competitor.
    void set_course(Event *event); //Member function that will set the course letter for the competitor.

public:
    int get_number(); //Member function to return a competitor's number.
    std::string get_name(); //Member function to return a competitor's name.
    char get_course(); //Member function to return a competitor's course.
    Competitor(int number, Event *event);
};

#endif	/* COMPETITOR_H */

