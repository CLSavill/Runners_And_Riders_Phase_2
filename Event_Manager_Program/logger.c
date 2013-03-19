/* 
 * File: logger.c
 * Author: Chris Savill - chs17
 * Description: File that contains a single function to write a log to the "log.txt" file.
 */

#include "structs.h"
#include "prototypes.h"

/* Function to print a new log with the current time to the end "log.txt" file. */
void write_log(const char *log) {
    time_t current_time;
    time(&current_time); //Gets current time.
    
    FILE *log_file_pointer;
    log_file_pointer = fopen("log.txt", "a"); //Open log.txt in append mode.
    fprintf(log_file_pointer, "Action: %s, Date: %s", log, ctime(&current_time)); //Write new log to end of file.
    fclose(log_file_pointer); //Close file.
}
/*-----------------------------------------------------------------------*/
