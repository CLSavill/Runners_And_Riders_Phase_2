/*
 * Author: Chris Savill, chs17@aber.ac.uk
 * File Name: event.cpp
 * Description: cpp file that contains member function definitions for the event class.
 * First Created: 11/03/2013
 * Last Modified: 11/03/2013
 */

#include "event.h"


using namespace std;

/* Member function to get the user to input the events name. */
void Event::set_name() {
    bool valid_name = false;
    string name;
    char option;

    do {
        cout << "Please enter in the name for the event: ";
        cin >> name;
        cout << endl << endl << "Are you happy with the name: '" << name << "'?" << endl;

        do {
            cout << "If yes press 'y' then 'Enter'" << endl "If no press 'n' then 'Enter'" << endl;
            cin >> option;

            if (option == 'y') valid_name = true;
            else if (option == 'n') valid_name = false;
            else cout "Invalid option selected" << endl;
        } while (option != 'y' || option != 'n');

    } while (valid_name == false);

    this->name = name;
}

/* Member function to get the user to input the date of the event. */
void Event::set_date() {
    bool valid_date = false;
    string date;
    char option;

    do {
        cout << "Please enter in the date for the event: ";
        cin >> date;
        cout << endl << endl << "Are you happy with the date: '" << date << "'?" << endl;

        do {
            cout << "If yes press 'y' then 'Enter'" << endl "If no press 'n' then 'Enter'" << endl;
            cin >> option;

            if (option == 'y') valid_date = true;
            else if (option == 'n') valid_date = false;
            else cout "Invalid option selected" << endl;
        } while (option != 'y' || option != 'n');

    } while (valid_date == false);

    this->date = date;
}

/* Member function to get the user to input the start time of the event. */
void Event::set_start_time() {
    bool valid_start_time = false;
    bool valid_hours = false;
    bool valid_minutes = false;
    int hours;
    int minutes;
    string start_time;
    char option;

    do {
        do {
            cout << "Please enter in the start time for the event with the 24 hour format 'HH:MM', hours first: ";
            cin >> hours;
            cout << endl;

            if (hours <= 23 && hours >= 00) {
                cout << "Valid hours entered." << endl << endl;
                valid_hours = true;
            } else cout << "Invalid hours entered, please enter in a value between 00 and 23 inclusive." << endl << endl;
        } while (valid_hours == false);

        do {
            cout << "Please now enter in the minutes: ";
            cin >> minutes;
            cout << endl;

            if (minutes <= 59 && minutes >= 00) {
                cout << "Valid minutes entered." << endl << endl;
                valid_minutes = true;
            } else cout << "Invalid minutes entered, please enter in a value between 00 and 59 inclusive." << endl << endl;
        } while (valid_minutes == false);

        cout << endl << endl << "Are you happy with the start time: '" << hours << ":" << minutes << "'?" << endl;

        do {
            cout << "If yes press 'y' then 'Enter'" << endl "If no press 'n' then 'Enter'" << endl;
            cin >> option;

            if (option == 'y') valid_start_time = true;
            else if (option == 'n') valid_start_time = false;
            else cout "Invalid option selected" << endl;
        } while (option != 'y' || option != 'n');

    } while (valid_start_time == false);

    start_time = hours":"minutes;
    this->start_time = start_time;
}

/* Member function that will handle adding a competitor to the event. */
void Event::add_competitor() {

}

/* Member function that will handle adding a course to the event. */
void Event::add_course() {

}

/* Member function that will handle exporting the name, date and start_time of the event to a '.txt' file. */
void Event::export_event() {

}

/* Member function that will handle the exporting of the array of competitors to a '.txt' file. */
void Event::export_competitors() {

}

/* Member function that will handle the exporting of the array of courses to a '.txt' file. */
void Event::export_courses() {

}

/* Constructor for Event class. */
Event::Event() {
    competitors = new vector<Competitor>;
    courses = new vector<Course>;
    set_name();
    cout << "Event name: " << this->name << endl;
    set_date();
    cout << "Event date: " << this->date << endl;
    set_start_time();
    cout << "Event start time: " << this->start_time << endl << endl;
}

/* Destructor for Event class. */
Event::~Event() {
    delete(competitors);
    delete(courses);
}