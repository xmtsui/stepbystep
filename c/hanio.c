#include <stdio.h>

void move(char from, char to);
void hanoi(int number, char from, char to, char tmp);

int main()
{
    char from = 'A';
    char to = 'C';
    char tmp ='B';

    hanoi(3,from,to,tmp);
    return 0;
}

void move(char from, char to)
{
    static int i=0; 
    printf("step %d : From %c to %c \n", i++, from, to);
}

void hanoi(int number, char from, char to, char tmp)
{
    if(number == 1)
    {
        move(from,to);
    }
    else
    {
        hanoi(number-1, from, tmp, to);
        move(from,to);
        hanoi(number-1, tmp, to, from);
    }
        
}