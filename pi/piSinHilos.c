#include <stdlib.h>
#include <stdio.h>
#include <time.h>
typedef struct get_Pi_args_S{
    long long c; //counter
    long long l; //limit for the loop :)
    int index; //array index of where we are storing this portion of PI in 
} Packer;

double PI[] = {0,0,0,0};
long long denominator = 1;



void * getPy( void * args)
{
    char posNeg = ! (denominator &    1) ;
    long long count = ((struct get_Pi_args_S *) args)->c;
    long long limiter = ((struct get_Pi_args_S *) args)->l;
    int index = ((struct get_Pi_args_S *) args)->index;

    for (; count< limiter; count++){
        if (posNeg){
            PI[index] -= 1.0/(denominator);

        }
        else{
            PI[index] += 1.0/(denominator);
        }
        denominator += 2;
        posNeg = !posNeg;
    }

    return NULL;
}


int main()
{
    Packer upTo ;
    // upTo.l = 50000000000;
    upTo.l = 50000000000;
    upTo.c = 0;
    upTo.index = 0;
    clock_t start = clock();
    getPy(&upTo);
    PI[0] = PI[0] + PI[0] + PI[0] + PI[0];
    clock_t stop = clock();
    int tiempo = (stop - start);
    printf("%.10f\n", PI[0]);
    printf("%d ms", tiempo);
    return 0;
}
