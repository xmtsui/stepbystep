#include <stdio.h>
int main(void)
{
    FILE *fp;
    char *fname = "test.txt", *newname, first;

    newname = mktemp(fname);
    fp = fopen(fname,"w+");
    fprintf(fp,"abcdefghijklmnopqrstuvwxyz");
    rewind(fp);
    // fseek(fp, 10, SEEK_SET);
    fscanf(fp,"%c",&first);
    printf("The first character is: %c\n",first);
    fclose(fp);
    remove(newname);
    return 0;
}