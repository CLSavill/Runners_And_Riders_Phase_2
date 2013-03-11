/*
 * Author: Chris Savill, chs17@aber.ac.uk
 * File Name: competitor.cpp
 * Description: cpp file that contains function definitions for the start-up of the event creation program.
 * First Created: 11/03/2013
 * Last Modified: 11/03/2013
 */

#include "creator.h"
#include <iostream>
#include <cstdlib>

using namespace std;

/* Main function that just calls a function that takes over. */
int main(int argc, char** argv) {
    Event *event = new Event();
    ecp_menu(Event *event);

    return 0;
}

/* Function that displays the main menu for the event creation program. */
void ecp_menu(Event *event) {
    int option; //Field to store the user's option input.

    do {
        cout << "=======================================================" << endl;
        cout << "= Runners and Riders Event Creation Program Main Menu =" << endl;
        cout << "=======================================================" << endl;
        cout << "= 1. Add Competitor to Event                          =" << endl;
        cout << "= 2. Add Course to Event                              =" << endl;
        cout << "= 3. Export Event to File                             =" << endl;
        cout << "= 4. Export Competitors to File                       =" << endl;
        cout << "= 5. Export Courses to File                           =" << endl;
        cout << "= 6. Exit Event Creation Program                      =" << endl;
        cout << "=======================================================" << endl << endl;

        cout << "Please enter in an option from the above an press 'Enter': ";
        cin >> option;

        switch (option) {
            case 1:
                event->add_competitor();
                break;
            case 2:
                event->add_course();
                break;
            case 3:
                event->export_event();
                break;
            case 4:
                event->export_competitors();
                break;
            case 5:
                event->export_courses();
                break;
            case 6:
                delete(event);
                cout << "Exiting program..." << endl << endl;
                break;
            default:
                cout << "Please enter in a valid option." << endl << endl;
        }
    } while (option != 6);
}