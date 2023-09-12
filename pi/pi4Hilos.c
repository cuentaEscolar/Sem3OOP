#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include <pthread.h>


typedef struct get_Pi_args_S{
    unsigned long long c;       //counter
    unsigned long long l;       //limit for the loop :)
    unsigned long long index;         //array index of where we are storing this portion of PI in
} Packer;
volatile long double PI[] = {0,0,0,0};


#pragma omp parallel
void * getPy( void * args)
{
    Packer * dummy = (struct get_Pi_args_S *) args;
    unsigned long long count = (dummy)->c;
    unsigned long long denominator = 0x1 + (count<<2);
    unsigned long long limiter = (dummy)->l >>1;


    long double r[4] = {0,0,0,0};

    unsigned long long index = ((struct get_Pi_args_S *) args)->index;
    #pragma omp simd
    for (; count< limiter; count+=4 ){
        r[0] +=  1.0/(denominator)        - 1.0/(denominator + 2);
        r[1] += 1.0/(denominator + 4 )    - 1.0/(denominator + 6);
        r[2] += 1.0/(denominator + 8 )    - 1.0/(denominator + 10);
        r[3] += 1.0/(denominator + 12)    - 1.0/(denominator + 14);

        denominator += 16;
    }
    PI[index] = r[0] + r[1] + r[2] + r[3];
    return NULL;
}

int main(){
    const unsigned long long mult = 12500000000;
    Packer a, b, c, d;
    pthread_t larry, judith, saky, kimy ;
    // 1, 2, 3

    a.l = 1 * mult;
    b.l = 2 * mult;
    c.l = 3 * mult;
    d.l = 4 * mult;
    a.c = 0 * mult;
    b.c = 0.5 * mult ;
    c.c = 1.0 * mult;
    d.c = 1.5 * mult;
    a.index = 0;
    b.index = 1;
    c.index = 2;
    d.index = 3;

    clock_t start = clock();
    // getPy(&a);
    // getPy(&b);
    // getPy(&c);
    // getPy(&d);
    pthread_create(&larry, NULL, getPy, (void*) &a);
    pthread_create(&judith, NULL, getPy, (void*) &b);
    pthread_create(&saky, NULL, getPy, (void*) &c);
    getPy((void*) &d);
    // pthread_create(&kimy, NULL, getPy, (void *) &d);

    // // // getPy(&a);

    pthread_join(larry, NULL);
    pthread_join(judith, NULL);
    pthread_join(saky, NULL);
    PI[0] = (PI[0] + PI[1] + PI[2] + PI[3]);
    PI[0] = 4 * PI[0];
    // PI[0] = PI[0] *4;

    clock_t stop = clock();
    printf("%.10Lf\n", PI[0]);
    int tiempo = (stop - start);
    printf("%d ms", tiempo);
    return 0;
}