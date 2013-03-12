/*
 * Author: Chris Savill, chs17@aber.ac.uk
 * File Name: course.cpp
 * Description: cpp file that contains member function definitions for the course class.
 * First Created: 11/03/2013
 * Last Modified: 12/03/2013
 */

#include "course.h"
#include "creator.h"
#include <iostream>
#include <fstream>
#include <limits>

using namespace std;

/* Member function to return a course's letter. */
char Course::get_letter() {
    return this->letter;
}

/* Member function to return a course's number of nodes. */
int Course::get_number_of_nodes() {
    return this->number_of_nodes;
}

/* Member function to return a node from the course's vector of nodes. */
int Course::get_node(int index) {
    return this->nodes->at(index);
}

/* Member function that will set the letter of the course. */
void Course::set_letter() {
    bool valid_letter = false;
    bool letter_chosen = false;
    char letter;

    do {
        do {
            cout << "Please enter in the course letter for the course: ";
            cin.clear();
            letter = cin.get();
            cin.ignore(numeric_limits<streamsize>::max(), '\n');

            if (isalpha(letter)) valid_letter == true;
            else {
                cout << "Please enter in a valid course letter a-z or A-Z." << endl << endl;
                valid_letter = false;
            }
        } while (valid_letter == false);

        cout << endl << "Are you happy with the course letter: '" << letter << "'?" << endl;
        letter_chosen = get_acceptance();
    } while (letter_chosen == false);

    this->letter = letter;
}

/* Member function that will set the number of nodes of the course. */
void Course::set_number_of_nodes() {
    bool number_chosen = false;
    int number;

    do {
        cout << "Please enter in the number of nodes for this course: ";
        cin.clear();
        cin >> number;
        cin.ignore(numeric_limits<streamsize>::max(), '\n');

        cout << endl << endl << "Are you happy with the number of nodes: '" << number << "'?" << endl;
        number_chosen = get_acceptance();
    } while (number_chosen == false && number > 0);

    this->number_of_nodes = number;
}

/* Member function that reads in the nodes from the 'nodes.txt' file and adds them to the nodes available array. */
void Course::read_nodes_available() {
    ifstream nodes_file;
    int node_number;

    nodes_file.open("nodes.txt", ios::in);

    if (nodes_file.is_open()) {
        while (nodes_file.good()) {
            nodes_file >> node_number;
            this->nodes_available->push_back(node_number);
        }

        nodes_file.close();
    } else cout << "File 'nodes.txt' could not be opened. Please check file is in correct directory and permissions." << endl;
}

/* Member function that adds a new node to the course. */
void Course::add_node() {
    bool number_chosen = false;
    int number;
    char option;

    do {
        do {
            cout << "Please enter in the number of the node you wish to add to the course: ";
            cin >> number;
        } while (!check_node_exists(number));

        cout << endl << endl << "Are you happy with the node number: '" << number << "'?" << endl;

        do {
            cout << "If yes press 'y' then 'Enter'" << endl << "If no press 'n' then 'Enter'" << endl;
            cin >> option;

            if (option == 'y') number_chosen = true;
            else if (option == 'n') number_chosen = false;
            else cout << "Invalid option selected" << endl;
        } while (option != 'y' || option != 'n');

    } while (number_chosen == false);

    this->nodes->push_back(number);
}

/* Member function that checks that the node being added exists in the array of node available. */
bool Course::check_node_exists(int number) {
    for (int counter = 0; counter < this->nodes_available->size(); counter++) {
        if (number == this->nodes_available->at(counter)) return true;
    }

    return false;
}

/* Constructor for Course class. */
Course::Course() {
    nodes = new vector<int>();
    nodes_available = new vector<int>();
    read_nodes_available();
    set_letter();
    set_number_of_nodes();

    for (int counter = 0; counter < number_of_nodes; counter++) {
        add_node();
    }
}

/* Destructor for Course class. */
Course::~Course() {
    delete(nodes);
    delete(nodes_available);
}