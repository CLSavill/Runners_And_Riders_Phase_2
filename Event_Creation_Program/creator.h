/*
 * Author: Chris Savill, chs17@aber.ac.uk
 * File Name: creator.h
 * Description: Header file for the starter function declarations.
 * First Created: 11/03/2013
 * Last Modified: 12/03/2013
 */

#ifndef CREATOR_H
#define	CREATOR_H

#include <memory>
#include "event.h"


bool get_acceptance(); //Function to get the user's input for accepting or rejecting their inputs.
bool checkCourseExists(char letter, Event *event); //Member function that checks if the letter given be the user matches any of the course letters.
void ecp_menu(Event *event); //Function that launches the event creation program menu.

#endif	/* CREATOR_H */

