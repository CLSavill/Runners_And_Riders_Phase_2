/*
 * Author: Chris Savill, chs17@aber.ac.uk
 * File Name: event.cpp
 * Description: cpp file that contains member function definitions for the event class.
 * First Created: 11/03/2013
 * Last Modified: 12/03/2013
 */

#include "event.h"
#include "creator.h"
#include <iostream>
#include <stdlib.h>
#include <fstream>
#include <sstream>
#include <limits>

using namespace std;

/* Member function to get the user to input the events name. */
void Event::set_name() {
    bool name_chosen = false;
    string name;

    do {
        do {
            cout << "Please enter in the name for the event (no more than 79 characters): ";
            cin.clear();
            getline(cin, name);
        } while (name.length() > MAX_EVENT_NAME_LENGTH);

        cout << endl << endl << "Are you happy with the name: '" << name << "'?" << endl;
        name_chosen = get_acceptance();
    } while (name_chosen == false);

    this->name = name;
}

/* Member function to get the user to input the date of the event. */
void Event::set_date() {
    bool date_chosen = false;
    string date;

    do {
        do {
            cout << endl << endl << "Please enter in the date for the event (no more than 19 characters): ";
            cin.clear();
            getline(cin, date);
        } while (date.length() > MAX_DATE_LENGTH);

        cout << endl << endl << "Are you happy with the date: '" << date << "'?" << endl;
        date_chosen = get_acceptance();
    } while (date_chosen == false);

    this->date = date;
}

/* Member function to get the user to input the start time of the event. */
void Event::set_start_time() {
    bool start_time_chosen = false;
    bool valid_hours = false;
    bool valid_minutes = false;
    char input[3];
    int hours;
    int minutes;
    string start_time;
    string string_hours;
    string string_minutes;

    do {
        do {
            cout << endl << endl << "Please enter in the start time for the event with the 24 hour format 'HH:MM', hours first: ";
            cin.clear();
            cin >> input;
            cin.ignore(numeric_limits<streamsize>::max(), '\n');
            cout << endl;

            if (isdigit(input[0]) && isdigit(input[1])) {
                hours = atoi(input);

                if (hours <= 23 && hours >= 00) {
                    cout << "Valid hours entered." << endl << endl;
                    valid_hours = true;
                }
            } else cout << "Invalid hours entered, please enter in a value between 00 and 23 inclusive." << endl << endl;
        } while (valid_hours == false);

        do {
            cout << endl << endl << "Please now enter in the minutes: ";
            cin.clear();
            cin >> input;
            cin.ignore(numeric_limits<streamsize>::max(), '\n');
            cout << endl;

            if (isdigit(input[0]) && isdigit(input[1])) {
                minutes = atoi(input);

                if (minutes <= 59 && minutes >= 00) {
                    cout << "Valid minutes entered." << endl << endl;
                    valid_minutes = true;
                }
            } else cout << "Invalid minutes entered, please enter in a value between 00 and 59 inclusive." << endl << endl;
        } while (valid_minutes == false);

        cout << endl << endl << "Are you happy with the start time: '" << hours << ":" << minutes << "'?" << endl;
        start_time_chosen = get_acceptance();
    } while (start_time_chosen == false);

    ostringstream string_retreiver;
    string_retreiver << hours;
    string_hours = string_retreiver.str();
    string_retreiver.str("");
    string_retreiver << minutes;
    string_minutes = string_retreiver.str();

    start_time = string_hours + ":" + string_minutes;
    this->start_time = start_time;
}

/* Member function that will handle adding a competitor to the event.
 * @param number The current competitor number.
 */
void Event::add_competitor(int number) {
    Competitor *competitor = new Competitor(number);
    competitors->push_back(competitor);
    cout << "New competitor added to event." << endl << endl;
    cout << "Competitor number: " << competitors->back()->get_number();
    cout << "Competitor name: " << competitors->back()->get_name() << endl;
    cout << "Course: " << competitors->back()->get_course() << endl;
}

/* Member function that will handle adding a course to the event. */
void Event::add_course() {
    Course *course = new Course();
    courses->push_back(course);
    cout << "New course added to event." << endl << endl;
    cout << "Course letter: " << courses->back()->get_letter() << endl;
    cout << "Number of course nodes: " << courses->back()->get_number_of_nodes() << endl;
    cout << "Nodes: " << courses->back()->get_node(0);

    for (int counter = 1; counter < courses->back()->get_number_of_nodes(); counter++) {
        cout << ", " << courses->back()->get_node(counter);
    }

    cout << endl << endl;
}

/* Member function that will handle exporting the name, date and start_time of the event to a '.txt' file. */
void Event::export_event() {
    ofstream competitors_file;
    competitors_file.open("name.txt", ios::out);

    if (competitors_file.is_open()) {
        competitors_file << this->name << "\n" << this->date << "\n" << this->start_time;
        competitors_file.close();
        cout << "Event successfully exported to 'name.txt'." << endl << endl;
    } else cout << "File 'name.txt' could not be written." << endl;
}

/* Member function that will handle the exporting of the array of competitors to a '.txt' file. */
void Event::export_competitors() {
    ofstream competitors_file;
    competitors_file.open("entrants.txt", ios::out);

    if (competitors_file.is_open()) {
        for (int counter = 0; counter < this->competitors->size(); counter++) {
            competitors_file << this->competitors->at(counter)->get_number() << " " << this->competitors->at(counter)->get_course()
                    << " " << this->competitors->at(counter)->get_name() << "\n";
        }

        competitors_file.close();
        cout << "Competitors successfully exported to 'entrants.txt'." << endl << endl;
    } else cout << "File 'entrants.txt' could not be written." << endl;
}

/* Member function that will handle the exporting of the array of courses to a '.txt' file. */
void Event::export_courses() {
    ofstream courses_file;
    courses_file.open("courses.txt", ios::out);

    if (courses_file.is_open()) {
        for (int counter = 0; counter < this->courses->size(); counter++) {
            courses_file << this->courses->at(counter)->get_letter() << " " << this->courses->at(counter)->get_number_of_nodes();

            for (int counter2 = 0; counter2 < this->courses->at(counter)->get_number_of_nodes(); counter2++) {
                courses_file << " " << this->courses->at(counter)->get_node(counter2);
            }
            courses_file << "\n";
        }

        courses_file.close();
        cout << "Courses successfully exported to 'courses.txt'." << endl << endl;
    } else cout << "File 'courses.txt' could not be written." << endl;
}

/* Constructor for Event class. */
Event::Event() {
    competitors = new vector<Competitor* > ();
    courses = new vector<Course* > ();
    set_name();
    set_date();
    set_start_time();

    cout << "Event name: " << this->name << endl;
    cout << "Event date: " << this->date << endl;
    cout << "Event start time: " << this->start_time << endl << endl;
}

/* Destructor for Event class. */
Event::~Event() {
    delete(competitors);
    delete(courses);
}