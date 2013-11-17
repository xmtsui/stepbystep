#ifndef _mt_date_h
#define _mt_date_h
#endif

#include <stdio.h>
#include <time.h>
void mt_date()
{
        time_t timer;
        /*gets time of the day*/
        timer = time(NULL);
        /*convert time to a structure*/
        struct tm *t = localtime(&timer);
        printf("%s",asctime(t));
}

