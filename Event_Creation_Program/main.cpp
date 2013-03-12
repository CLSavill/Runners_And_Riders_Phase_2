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
    ecp_menu(event);

    return 0;
}

/* Function to get the user's input for accepting or rejecting their inputs. */
bool get_acceptance() {
    char option;
    
    do {
            cout << "If yes press 'y' then 'Enter'" << endl << "If no press 'n' then 'Enter'" << endl;
            cin.clear();
            option = cin.get();
            cin.ignore(numeric_limits<streamsize>::max(), '\n');

            if (option == 'y') return true;
            else if (option == 'n') return false;
            else cout << "Invalid option selected" << endl;
        } while (option != 'y' && option != 'n');
}

/* Function that displays the main menu for the event creation program. */
void ecp_menu(Event *event) {
    int option; //Field to store the user's option input.
    int number_of_competitors = 0;

    do {
        cout << "*********************************************************" << endl;
        cout << "*  Runners and Riders Event Creation Program Main Menu  *" << endl;
        cout << "*********************************************************" << endl;
        cout << "*              1. Add Competitor to Event               *" << endl;
        cout << "*              2. Add Course to Event                   *" << endl;
        cout << "*              3. Export Event to File                  *" << endl;
        cout << "*              4. Export Competitors to File            *" << endl;
        cout << "*              5. Export Courses to File                *" << endl;
        cout << "*              6. Exit Event Creation Program           *" << endl;
        cout << "*********************************************************" << endl << endl;

        cout << "Please enter in an option from the above an press 'Enter': ";
        cin.clear();
        cin >> option;
        cin.ignore();

        switch (option) {
            case 1:
                number_of_competitors++;
                event->add_competitor(number_of_competitors);
                break;
            case 2:
                //event->add_course();
                break;
            case 3:
                //event->export_event();
                break;
            case 4:
                //event->export_competitors();
                break;
            case 5:
                //event->export_courses();
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