#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include <pthread.h>

typedef struct get_Pi_args_S{
    long long c; //counter
    long long l; //limit for the loop :)
    int index; //array index of where we are storing this portion of PI in 
} Packer;

double PI[] = {0,0,0,0};




void * getPy( void * args)
{
    long long denominator = 1;
    // char posNeg = ! (denominator &    1) ;
    long long count = ((struct get_Pi_args_S *) args)->c;
    long long limiter = ((struct get_Pi_args_S *) args)->l >>3;
    int index = ((struct get_Pi_args_S *) args)->index;

    for (; count< limiter; count++){
        PI[index] += 1.0/(denominator++) - 1.0/(++denominator);
        denominator += 2;
        PI[index] += 1.0/(denominator++) - 1.0/(++denominator);
        denominator += 2;
        PI[index] += 1.0/(denominator++) - 1.0/(++denominator);
        denominator += 2;
        PI[index] += 1.0/(denominator++) - 1.0/(++denominator);
        denominator += 2;
    }

    return NULL;
}

int main(){
    // const mult = 100000000;
    const long long mult = 10000000;
    Packer a, b, c, d;
    pthread_t larry, judith, saky, kimy ;

        a.l = 125 * mult;
    b.l = 250 * mult;
    c.l = 375 * mult;
    d.l = 500 * mult;
    a.c = 0   * mult;
    b.c = 125 * mult;
    c.c = 250 * mult;
    d.c = 375 * mult;
    a.index = 0;
    b.index = 1;
    c.index = 2;
    d.index = 3;
    clock_t start = clock();
    pthread_create(&larry, NULL, getPy, (void *) &a);
    pthread_create(&judith, NULL, getPy, (void*) &b);
    pthread_create(&saky, NULL, getPy, (void *) &c);
    // pthread_create(&kimy, NULL, getPy, (void *) d);
    getPy((void *) &d);

    // getPy(&a);

    pthread_join(larry, NULL);
    pthread_join(judith, NULL);
    pthread_join(saky, NULL);

    PI[0] = PI[0] + PI[1] + PI[2] + PI[3];
    PI[0] = PI[0] + PI[0] + PI[0] + PI[0];
    clock_t stop = clock();
    printf("%.10f\n", PI[0]);
    int tiempo = (stop - start);
    printf("%d ms", tiempo);
    return 0;
}