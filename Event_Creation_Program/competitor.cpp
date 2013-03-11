/*
 * Author: Chris Savill, chs17@aber.ac.uk
 * File Name: competitor.cpp
 * Description: cpp file that contains member function definitions for the competitor class.
 * First Created: 11/03/2013
 * Last Modified: 11/03/2013
 */

#include "competitor.h"
#include <ctype.h>

using namespace std;

/* Member function that will set the number of the competitor.
 * @param number The number for the competitor.
 */
void Competitor::set_number(int number) {
    this->number = number;
}

/* Member function that will set the name of the competitor. */
void Competitor::set_name() {
    bool valid_name = false;
    string name;
    char option;

    do {
        cout << "Please enter in the name for the competitor: ";
        cin >> name;
        cout << endl << endl << "Are you happy with the name: '" << name << "'?" << endl;

        do {
            cout << "If yes press 'y' then 'Enter'" << endl << "If no press 'n' then 'Enter'" << endl;
            cin >> option;

            if (option == 'y') valid_name = true;
            else if (option == 'n') valid_name = false;
            else cout << "Invalid option selected" << endl;
        } while (option != 'y' || option != 'n');

    } while (valid_name == false);

    this->name = name;
}

/* Member function that will set the course letter for the competitor. */
void Competitor::set_course() {
    bool valid_letter = false;
    bool letter_chosen = false;
    char letter;
    char option;

    do {
        do {
            cout << "Please enter in the course letter for the course: ";
            cin >> letter;

            if (isalpha(letter)) valid_letter == true;
            else {
                cout << "Please enter in a valid course letter a-z or A-Z." << endl << endl;
                valid_letter = false;
            }
        } while (valid_letter == false);

        cout << endl << "Are you happy with the course letter: '" << letter << "'?" << endl;

        do {
            cout << "If yes press 'y' then 'Enter'" << endl << "If no press 'n' then 'Enter'" << endl;
            cin >> option;

            if (option == 'y') letter_chosen = true;
            else if (option == 'n') letter_chosen = false;
            else cout << "Invalid option selected" << endl;
        } while (option != 'y' || option != 'n');

    } while (letter_chosen == false);

    this->name = name;
}

/* Member function that will export the competitor's number, name and course letter to a '.txt' file. */
void Competitor::export_competitor() {

}

/* Constructor for Competitor class.
 * @param number The number for the new competitor.
 */
Competitor::Competitor(int number) {
    set_number(number);
    cout << "Competitor number: " << this->number << endl;
    set_name();
    cout << "Competitor name: " << this->name << endl;
    set_course();
    cout << "Competitor course:" << this-> course << endl;
}

/* Destructor for Competitor class. */
Competitor::~Competitor() {

}