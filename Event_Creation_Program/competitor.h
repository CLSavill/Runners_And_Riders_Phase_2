/*
 * Author: Chris Savill, chs17@aber.ac.uk
 * File Name: competitor.h
 * Description: Header file for the Competitor class.
 * First Created: 11/03/2013
 * Last Modified: 11/03/2013
 */

#include <string>

#ifndef COMPETITOR_H
#define	COMPETITOR_H

class Competitor {
private:
    int number; //The competitor's unique identification number for an event.
    std::string name; //The competitor's name.
    char course; //The course letter the competitor is entering in for.
    
    void set_number(int number); //Member function that will set the number of the competitor.
    void set_name(std::string name); //Member function that will set the name of the competitor.
    void set_course(char course); //Member function that will set the course letter for the competitor.
    
public:
    Competitor(int number);
};

#endif	/* COMPETITOR_H */

