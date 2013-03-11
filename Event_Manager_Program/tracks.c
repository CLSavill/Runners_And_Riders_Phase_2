/* 
 * File: tracks.c
 * Author: Chris Savill - chs17
 * Description: File that contains functions related to handling an event's tracks.
 */

#include "structs.h"
#include "prototypes.h"

/* Function to get the number of tracks from a file supplied (probably named "tracks.txt"). */
int tracks_file_load(event_ptr event, char* file_name) {
    FILE *tracks_file; /* File pointer. */
    int load_status;
    int track_number;
    int track_start_number;
    int track_end_number;
    int track_max_time;
    track *new_track;
    new_track = event->track_head;
    event->track_head = NULL;

    if ((tracks_file = fopen(file_name, "r")) == NULL) { /* Open file with read permissions only and check file opened. */
        printf("Please enter in a valid file path and name.\n");
        return FAILURE;
    }

    while ((load_status = fscanf(tracks_file, " %d %d %d %d", &track_number, &track_start_number,
            &track_end_number, &track_max_time)) != EOF && load_status == 4) {

        if (event->number_of_tracks == 0) {
            new_track = malloc(sizeof (struct track));
        } else {
            new_track->next_track = malloc(sizeof (struct track)); /* Allocates memory for the next track. */
            new_track = new_track->next_track;
        }

        /* Initialise new track: */
        new_track->number = track_number;
        new_track->max_time = track_max_time;
        new_track->nodeA = get_node(event->node_head, track_start_number); /* Assigns the address of the start node. */
        new_track->nodeB = get_node(event->node_head, track_end_number); /* Assigns the address of the end node. */
        new_track->next_track = NULL;
        /*-----------------------------------------------------------------------*/

        /* Adds new track to linked list: */
        if (event->track_head == NULL) {
            event->track_head = new_track;
            printf("Head Track: Number: %d, Start: %d, End: %d, Max Time: %d\n",
                    new_track->number,
                    new_track->nodeA->number,
                    new_track->nodeB->number,
                    new_track->max_time);
        } else {
            printf("Track: Number: %d, Start: %d, End: %d, Max Time: %d\n",
                    new_track->number,
                    new_track->nodeA->number,
                    new_track->nodeB->number,
                    new_track->max_time);
        }
        /*-----------------------------------------------------------------------*/

        event->number_of_tracks++;
    }

    if (load_status == EOF) {
        printf("\nTracks file loaded in successfully.\n");
        fclose(tracks_file); /* Closes file as no longer needed. */
        return SUCCESS;
    } else if (load_status != 4) { /* Expected 4 inputs. */
        printf("Error loading in file, possible pattern mismatch.\n");
        fclose(tracks_file); /* Closes file as no longer needed. */
        return FAILURE;
    }
}
/*-----------------------------------------------------------------------*/

/* Function that finds the track that lies between two nodes and returns the corresponding track pointer. */
track* get_track(track* track_head, int nodeA, int nodeB) {
    track *current_track;
    current_track = track_head;

    while (current_track != NULL) {
        if (current_track->nodeA->number == nodeA && current_track->nodeB->number == nodeB) {
            return current_track;
        } else if (current_track->nodeA->number == nodeB && current_track->nodeB->number == nodeA) {
            return current_track;
        } else {
            current_track = current_track->next_track;
        }
    }
}
/*-----------------------------------------------------------------------*/
