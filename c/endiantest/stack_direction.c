#include<stdio.h>  
static int stack_dir;  
static void find_stack_direction (void)  
{  
    static char *addr = NULL;   /* address of first `dummy', once known */  
    char dummy;          /* to get stack address */  

    if (addr == NULL)  
    {                           /* initial entry */  
        // printf("1. addr \t%d\n", sizeof addr);
        printf("1. addr \t%lx\n", addr);
        printf("1. dummy \t%0lx\n", &dummy);
        addr = &dummy;  
        find_stack_direction ();  /* recurse once */  
    }  
    else /* second entry */  
    {
        printf("2. addr \t%ld\n", (long)addr);
        printf("2. dummy \t%ld\n", (long)&dummy);
        if (&dummy > addr)  
            stack_dir = 1;            /* stack grew upward */  
        else  
            stack_dir = -1;           /* stack grew downward */  
    }
}  

int main(void)  
{  
    find_stack_direction();  
    if(stack_dir==1)  
        puts("stack grew upward");  
    else  
        puts("stack grew downward");  
    return 0;  
} 