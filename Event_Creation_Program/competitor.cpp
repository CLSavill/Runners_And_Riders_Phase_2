/*
 * Author: Chris Savill, chs17@aber.ac.uk
 * File Name: competitor.cpp
 * Description: cpp file that contains member function definitions for the competitor class.
 * First Created: 11/03/2013
 * Last Modified: 12/03/2013
 */

#include "competitor.h"
#include "creator.h"
#include <ctype.h>
#include <iostream>
#include <limits>

using namespace std;

/* Member function to return a competitor's number. */
int Competitor::get_number() {
    return this->number;
}

/* Member function to return a competitor's name. */
string Competitor::get_name() {
    return this->name;
}

/* Member function to return a competitor's course. */
char Competitor::get_course() {
    return this->course;
}

/* Member function that will set the number of the competitor.
 * @param number The number for the competitor.
 */
void Competitor::set_number(int number) {
    this->number = number;
}

/* Member function that will set the name of the competitor. */
void Competitor::set_name() {
    bool name_chosen = false;
    string name;

    do {
        do {
            cout << endl << endl << "Please enter in the name for the competitor (no more than 50 characters): ";
            getline(cin, name);
        } while (name.length() > MAX_COMPETITOR_NAME_LENGTH);

        cout << endl << endl << "Are you happy with the name: '" << name << "'?" << endl;

        name_chosen = get_acceptance();

    } while (name_chosen == false);

    this->name = name;
}

/* Member function that will set the course letter for the competitor. */
void Competitor::set_course(Event *event) {
    bool valid_letter = false;
    bool letter_chosen = false;
    char letter;

    do {
        do {
            cout << endl << endl << "List of courses available for the competitor to enter on: " << event->getCourses()->front()->get_letter();

            if (event->getCourses()->size() > 1) {
                for (int counter = 1; counter < event->getCourses()->size(); counter++)
                    cout << ", " << event->getCourses()->at(counter)->get_letter();
            }

            cout << endl << endl << "Please enter in the letter of the course that the competitor is entering: ";
            cin.clear();
            letter = cin.get();
            cin.ignore(numeric_limits<streamsize>::max(), '\n');

            if (isalpha(letter) && checkCourseExists(letter, event)) valid_letter = true;
            else {
                cout << "Please enter in a valid course letter." << endl << endl;
                valid_letter = false;
            }
        } while (valid_letter == false);

        cout << endl << "Are you happy with the course letter: '" << letter << "'?" << endl;
        letter_chosen = get_acceptance();
    } while (letter_chosen == false);

    this->course = letter;
}

/* Constructor for Competitor class.
 * @param number The number for the new competitor.
 */
Competitor::Competitor(int number, Event *event) {
    set_number(number);
    cout << "Competitor number: " << this->number << endl;
    set_name();
    cout << "Competitor name: " << this->name << endl;
    set_course(event);
    cout << "Competitor course:" << this-> course << endl;
}