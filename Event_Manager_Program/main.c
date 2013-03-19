/* 
 * File: main.c
 * Author: Chris Savill - chs17
 * Description: Main file for event monitoring program (menu).
 */

#include "structs.h"
#include "prototypes.h"

/* Main function that just calls the loader and menu functions. */
int main(int argc, char** argv) {
    event_ptr event;
    event = malloc(sizeof (struct event)); /* Allocates memory for an event struct. */

    printf("Event Monitoring Program Launching...\n");

    if (loader(event) == SUCCESS) {
        printf("Loading Cycle Finished.");

        if (menu(event) == SUCCESS) {
            printf("\n\nExiting Program...\n");
        }
    }
    return EXIT_SUCCESS;
}
/*-----------------------------------------------------------------------*/

/* Function to load the main menu for the program. */
int menu(event_ptr event) {
    int option;

    do {
        printf("\nPress enter to continue.\n");
        getchar(); /* Consumes \n. */
        getchar(); /* Pauses program until enter is entered. */

        printf("\n===================================== MAIN MENU =====================================");
        printf("\n|                                                                                   |");
        printf("\n| 1: Query competitor for current location/status.                                  |");
        printf("\n| 2: Display how many competitors have not started yet.                             |");
        printf("\n| 3: Display how many competitors are out on the courses.                           |");
        printf("\n| 4: Display how many competitors have completed their course successfully.         |");
        printf("\n| 5: Read in a file of times at which competitors have reached time checkpoints.    |");
        printf("\n| 6: Display the result times for the successfully completed.                       |");
        printf("\n| 7: Display the competitors who have been excluded.                                |");
        printf("\n| 8: Exit program.                                                                  |");
        printf("\n|                                                                                   |");
        printf("\n=====================================================================================");

        printf("\n\nPlease select from one of the options above (number): ");
        scanf("%d", &option);

        switch (option) {
            case 1:
                query_location(event);
                log("Queried competitor\n");
                break;
            case 2:
                print_not_started(event);
                log("Viewed list of competitors not started.\n");
                break;
            case 3:
                print_out_on_course(event);
                log("Viewed list of competitors out on course.\n");
                break;
            case 4:
                print_finished(event);
                log("Viewed list of competitors that have finished.\n");
                break;
            case 5:
                read_times_file(event);
                log("Read in a time records file.\n");
            case 6:
                print_results(event);
                log("Viewed results of competitors that completed their course successfully.\n");
                break;
            case 7:
                print_excluded(event);
                log("Viewed results of competitors that were excluded.\n");
                break;
            case 8:
                log("Quit Program.\n");
                break;
            default:
                printf("\nPlease enter in a valid option.");
        }
    } while (option != 8);

    return SUCCESS;
}
/*-----------------------------------------------------------------------*/
