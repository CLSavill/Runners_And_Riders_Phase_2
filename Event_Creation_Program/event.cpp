/*
 * Author: Chris Savill, chs17@aber.ac.uk
 * File Name: event.cpp
 * Description: cpp file that contains member function definitions for the event class.
 * First Created: 11/03/2013
 * Last Modified: 12/03/2013
 */

#include "event.h"
#include <iostream>
#include <fstream>
#include <limits>

using namespace std;

/* Member function to get the user to input the events name. */
void Event::set_name() {
    bool name_chosen = false;
    string name;
    char option;

    do {
        do {
            cout << "Please enter in the name for the event (no more than 79 characters): ";
            cin.clear();
            getline(cin, name);
        } while (name.length() > MAX_EVENT_NAME_LENGTH);

        cout << endl << endl << "Are you happy with the name: '" << name << "'?" << endl;

        do {
            cout << "If yes press 'y' then 'Enter'" << endl << "If no press 'n' then 'Enter'" << endl;
            cin.clear();
            option = cin.get();
            cin.ignore(numeric_limits<streamsize>::max(), '\n');

            if (option == 'y') name_chosen = true;
            else if (option == 'n') name_chosen = false;
            else cout << "Invalid option selected" << endl;
        } while (option != 'y' && option != 'n');

    } while (name_chosen == false);

    this->name = name;
}

/* Member function to get the user to input the date of the event. */
void Event::set_date() {
    bool date_chosen = false;
    string date;
    char option;

    do {
        do {
            cout << "Please enter in the date for the event (no more than 19 characters: ";
            cin.clear();
            getline(cin, date);
        } while (date.length() > MAX_DATE_LENGTH);

        cout << endl << endl << "Are you happy with the date: '" << date << "'?" << endl;

        do {
            cout << "If yes press 'y' then 'Enter'" << endl << "If no press 'n' then 'Enter'" << endl;
            cin.clear();
            option = cin.get();
            cin.ignore(numeric_limits<streamsize>::max(), '\n');

            if (option == 'y') date_chosen = true;
            else if (option == 'n') date_chosen = false;
            else cout << "Invalid option selected" << endl;
        } while (option != 'y' && option != 'n');

    } while (date_chosen == false);

    this->date = date;
}

/* Member function to get the user to input the start time of the event. */
void Event::set_start_time() {
    bool start_time_chosen = false;
    bool valid_hours = false;
    bool valid_minutes = false;
    int hours;
    int minutes;
    string start_time;
    char option;

    do {
        do {
            cout << "Please enter in the start time for the event with the 24 hour format 'HH:MM', hours first: ";
            cin.clear();
            cin >> hours;
            cin.ignore(numeric_limits<streamsize>::max(), '\n');
            cout << endl;

            if (hours <= 23 && hours >= 00) {
                cout << "Valid hours entered." << endl << endl;
                valid_hours = true;
            } else cout << "Invalid hours entered, please enter in a value between 00 and 23 inclusive." << endl << endl;
        } while (valid_hours == false);

        do {
            cout << "Please now enter in the minutes: ";
            cin.clear();
            cin >> minutes;
            cin.ignore(numeric_limits<streamsize>::max(), '\n');
            cout << endl;

            if (minutes <= 59 && minutes >= 00) {
                cout << "Valid minutes entered." << endl << endl;
                valid_minutes = true;
            } else cout << "Invalid minutes entered, please enter in a value between 00 and 59 inclusive." << endl << endl;
        } while (valid_minutes == false);

        cout << endl << endl << "Are you happy with the start time: '" << hours << ":" << minutes << "'?" << endl;

        do {
            cout << "If yes press 'y' then 'Enter'" << endl << "If no press 'n' then 'Enter'" << endl;
            cin.clear();
            option = cin.get();
            cin.ignore(numeric_limits<streamsize>::max(), '\n');

            if (option == 'y') start_time_chosen = true;
            else if (option == 'n') start_time_chosen = false;
            else cout << "Invalid option selected" << endl;
        } while (option != 'y' && option != 'n');

    } while (start_time_chosen == false);

    start_time = hours + ":" + minutes;
    this->start_time = start_time;
}

/* Member function that will handle adding a competitor to the event.
 * @param number The current competitor number.
 */
void Event::add_competitor(int number) {
    Competitor *competitor = new Competitor(number);
    competitors->push_back(*competitor);
    cout << "New competitor added to event." << endl << endl;
}

/* Member function that will handle adding a course to the event. */
void Event::add_course() {
    Course *course = new Course();
    courses->push_back(*course);
    cout << "New course added to event." << endl << endl;
}

/* Member function that will handle exporting the name, date and start_time of the event to a '.txt' file. */
void Event::export_event() {
    ofstream competitors_file;
    competitors_file.open("name.txt", ios::out);

    if (competitors_file.is_open()) {
        competitors_file << this->name << "\n" << this->date << "\n" << this->start_time;
        competitors_file.close();
    } else cout << "File 'name.txt' could not be written." << endl;
}

/* Member function that will handle the exporting of the array of competitors to a '.txt' file. */
void Event::export_competitors() {
    ofstream competitors_file;
    competitors_file.open("entrants.txt", ios::out);

    if (competitors_file.is_open()) {
        for (int counter = 0; counter < this->competitors->size(); counter++) {
            competitors_file << this->competitors->at(counter).get_number() << " " << this->competitors->at(counter).get_course()
                    << " " << this->competitors->at(counter).get_name() << "\n";
        }
        competitors_file.close();
    } else cout << "File 'entrants.txt' could not be written." << endl;
}

/* Member function that will handle the exporting of the array of courses to a '.txt' file. */
void Event::export_courses() {
    ofstream courses_file;
    courses_file.open("courses.txt", ios::out);

    if (courses_file.is_open()) {
        for (int counter = 0; counter < this->courses->size(); counter++) {
            courses_file << this->courses->at(counter).get_letter() << " " << this->courses->at(counter).get_number_of_nodes();

            for (int counter2 = 0; counter2 < this->courses->at(counter).get_number_of_nodes(); counter2++) {
                courses_file << " " << this->courses->at(counter).get_node(counter2) << "\n";
            }
        }
        courses_file.close();
    } else cout << "File 'courses.txt' could not be written." << endl;
}

/* Constructor for Event class. */
Event::Event() {
    competitors = new vector<Competitor > ();
    courses = new vector<Course > ();
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