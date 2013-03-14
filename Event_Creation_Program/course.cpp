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
#include <sstream>
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

/* Member function that checks if the letter given be the user matches any of the course letters. */
bool checkCourseExists(char letter, Event *event) {
    for (int counter = 0; counter < event->getCourses()->size(); counter++) {
        if (letter == event->getCourses()->at(counter)->get_letter()) return true;
    }
    
    return false;
}

/* Member function that will set the letter of the course. */
void Course::set_letter(Event *event) {
    bool valid_letter = false;
    bool letter_chosen = false;
    char letter;

    do {
        do {
            cout << endl << endl << "Please enter in the course letter for the course: ";
            cin.clear();
            letter = cin.get();
            cin.ignore(numeric_limits<streamsize>::max(), '\n');

            if (isalpha(letter) && !checkCourseExists(letter, event)) valid_letter = true;
            else {
                cout << "Please enter in a valid course letter that does not already exist in this event, a-z or A-Z." << endl << endl;
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
        cout << endl << endl << "Please enter in the number of nodes for this course: ";
        cin.clear();
        cin >> number;
        cin.ignore(numeric_limits<streamsize>::max(), '\n');

        cout << endl << endl << "Are you happy with the number of nodes: '" << number << "'?" << endl;
        number_chosen = get_acceptance();
    } while (number_chosen == false && number > 0);

    this->number_of_nodes = number;
}

/* Member function that reads in the nodes from the 'nodes.txt' file and adds them to the nodes available array. */
bool Course::read_nodes_available() {
    ifstream nodes_file;
    string input;
    int node_number;

    nodes_file.open("nodes.txt", ios::in);

    if (nodes_file.is_open()) {
        while (getline(nodes_file, input)) {
            stringstream int_retreiver(input);
            int_retreiver >> node_number;
            this->nodes_available->push_back(node_number);
        }

        nodes_file.close();
        cout << "Nodes from 'nodes.txt' read in successfully." << endl;
        cout << "Nodes read in: " << nodes_available->at(0);
        for (int counter = 1; counter < nodes_available->size(); counter++) cout << ", " << nodes_available->at(counter);
        cout << endl << endl;
    } else cout << "File 'nodes.txt' could not be opened. Please check file is in correct directory and permissions." << endl;
}

/* Member function that adds a new node to the course. */
void Course::add_node() {
    bool number_chosen = false;
    string input;
    int number = 0;

    do {
        do {
            cout << "Please enter in the node number you wish to add to the course: ";
            getline(cin, input);
            stringstream int_retreiver(input);
            int_retreiver >> number;
        } while (duplicated_last_node(number) || !check_node_exists(number));

        cout << endl << endl << "Are you happy with the node number: '" << number << "'?" << endl;
        number_chosen = get_acceptance();
    } while (number_chosen == false);

    this->nodes->push_back(number);
}

/* Member function to check if the new node being selected matches the last node added. */
bool Course::duplicated_last_node(int number) {
    if (!nodes->empty()) {
        if (number == nodes->back()) {
            cout << "Node matches last node. Please choose a different node number to add." << endl;
            return true;
        }
    }

    return false;
}

/* Member function that checks that the node being added exists in the array of node available. */
bool Course::check_node_exists(int number) {
    for (int counter = 0; counter < this->nodes_available->size(); counter++) {
        if (number == this->nodes_available->at(counter)) return true;
    }

    cout << "Node does not exist, please choose a different node number to add." << endl;
    return false;
}

/* Constructor for Course class. */
Course::Course(Event *event) {
    this->nodes = new vector<int>();
    this->nodes_available = new vector<int>();

    if (read_nodes_available()) {
        set_letter(event);
        set_number_of_nodes();

        for (int counter = 0; counter < number_of_nodes - 1; counter++) {
            add_node();
        }

        nodes->push_back(nodes->front()); //Adds the last node, matching the first node to the course.
    } else cout << "Nodes could not be read in from 'nodes.txt' file. Course creation cancelled." << endl << endl;
}

/* Destructor for Course class. */
Course::~Course() {
    delete(nodes);
    delete(nodes_available);
}