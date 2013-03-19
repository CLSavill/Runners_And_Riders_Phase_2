/* 
 * File: loader.c
 * Author: Chris Savill - chs17
 * Description: File that contains functions related to handling the loading of files.
 */

#include "structs.h"
#include "prototypes.h"

/* Function to deal with file locking. */
struct flock *file_lock(short type, short whence) {
    static struct flock ret;
    ret.l_type = type;
    ret.l_start = 0;
    ret.l_whence = whence;
    ret.l_len = 0;
    ret.l_pid = getpid();
    return &ret;
}
/*-----------------------------------------------------------------------*/

/* Function to cycle through the process of calling all the loading functions. */
int loader(event_ptr event) {
    int load_status;

    do {
        printf("\nPlease enter in the file path and name of the event file: ");
        load_status = load_cycle(event, &event_file_load);
    } while (load_status != SUCCESS);
    printf("Event loading finished.\n");
    load_status = FAILURE;

    do {
        printf("\n\nPlease enter in the file path and name of the nodes file: ");
        load_status = load_cycle(event, &nodes_file_load);
    } while (load_status != SUCCESS);
    printf("Node loading finished.\n");

    do {
        printf("\n\nPlease enter in the file path and name of the tracks file: ");
        load_status = load_cycle(event, &tracks_file_load);
    } while (load_status != SUCCESS);
    printf("Track loading finished.\n");

    do {
        printf("\n\nPlease enter in the file path and name of the courses file: ");
        load_status = load_cycle(event, &courses_file_load);
    } while (load_status != SUCCESS);
    printf("Course loading finished.\n");

    do {
        printf("\n\nPlease enter in the file path and name of the competitors file: ");
        load_status = load_cycle(event, &competitors_file_load);
    } while (load_status != SUCCESS);

    printf("Competitor loading finished.\n");
    return SUCCESS;
}
/*-----------------------------------------------------------------------*/

/* Function to call the relevant loading/reading function using function pointer passed in. */
int load_cycle(event_ptr event, int (*load_function_ptr) (event_ptr, char*)) {
    char file_name[MAX_PATH_LENGTH];

    scanf("%s", file_name);

    if (load_function_ptr(event, file_name) == SUCCESS) {
        return SUCCESS;
    }

    printf("\nError: File failed to load.\n");
    return FAILURE;
}
/*-----------------------------------------------------------------------*/

void time_loader(event_ptr event) {
    int load_status = FAILURE;

    do {
        printf("\nPlease enter in the file path and name of the time record file: ");
        load_status = load_cycle(event, &read_times_file);
    } while (load_status != SUCCESS);
    printf("Time record loading finished.\n");
}

/* Function to read in a file of times. */
int read_times_file(event_ptr event, char* file_name) {
    FILE *times_file; /* File pointer */
    int load_status;
    char status;
    int checkpoint;
    int competitor_number;
    int hours;
    int minutes;
    competitor *competitor;

    int fileDescriptor = open(file_name, O_RDWR);
    if (fileDescriptor == -1) {
        printf("File descriptor error");
    }

    struct flock* fileLock = file_lock(F_WRLCK, SEEK_SET); /* Locks file */

    if (fcntl(fileDescriptor, F_SETLK, fileLock) == -1) {
        if (errno == EACCES || errno == EAGAIN) {
            printf("\nCould not load time record file as file was locked.\n");
            return; /* Exits function */
        }
    } else {
        times_file = fdopen(fileDescriptor, "r");

        do {
            load_status = fscanf(times_file, " %c %d %d %d:%d", &status, &checkpoint, &competitor_number, &hours, &minutes);

            if (load_status == EOF) {
                printf("\nEnd of file reached.");
            } else if (load_status == 5) {
                if ((chronological_check(event->current_time, hours, minutes)) == SUCCESS) {
                    competitor = get_competitor(event, competitor_number);
                    evaluate_status(event, competitor, status, checkpoint, hours, minutes);
                } else {
                    load_status = FAILURE;
                }
            } else {
                printf("Error reading in time file, possible pattern mismatch.\n");
            }
        } while (load_status != EOF && load_status == 5 && load_status != FAILURE);

        if (load_status == EOF) {
            fcntl(fileDescriptor, F_SETLKW, file_lock(F_UNLCK, SEEK_SET)); /* Releases file lock */
            fclose(times_file);
            printf("\nLoading of times files complete.\n");
            return SUCCESS;
        } else if (load_status == FAILURE) {
            printf("\nFile has not arrived in chronological order, permission to read in file denied.\n");
            fcntl(fileDescriptor, F_SETLKW, file_lock(F_UNLCK, SEEK_SET));
            fclose(times_file);
            return FAILURE;
        } else if (load_status != 5) {
            printf("Error reading in time file, possible pattern mismatch.\n");
            fcntl(fileDescriptor, F_SETLKW, file_lock(F_UNLCK, SEEK_SET));
            fclose(times_file);
            return FAILURE;
        }
    }
}
/*-----------------------------------------------------------------------*/

/* Function to check if the new time passed in is later than the current event time. */
int chronological_check(time_struct current_time, int hours, int minutes) {
    if (hours < current_time.hours) { /* Check if time file has arrived in chronological order */
        return FAILURE;
    } else if (hours == current_time.hours) {
        if (minutes < current_time.minutes) {
            return FAILURE;
        }
    }

    return SUCCESS;
}
/*-----------------------------------------------------------------------*/
