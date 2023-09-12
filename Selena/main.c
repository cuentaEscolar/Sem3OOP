#include <stdlib.h>
#include <stdio.h>

int sumArray(int * arr, int n){
    int acc = 0;
    for (int count = 0; count < n; count++){
        acc += count[arr]; //lmao
    }
}
void printArray(int * arr, int n){

    for (int count = 0; count < n; count++){

        printf("%d\n", *(arr + count)); 

    }
}
void printArrays(int * arr1, int n1, int * arr2, int n2){
    printArray(arr1,n1);
    printArray(arr2,n2);
}
void readArray(int * arr, int * pN)
{ 
    int n ; 
    printf("Pronuncie usted el tamaño de su lista. \n");
    scanf("%d",&n);
    if ( n > 10) n = 10;
    printf("Tenga usted la amabilidad de llenar el arreglo siguiente.  \n");
    fflush(stdin);
    for (int count = 0; count < n; count++){

        scanf("%d",(arr + count));
        fflush(stdin);
 
    }
    *pN = n;
    fflush(stdin);
}
void sortArrays(int ** pp1, int * pN1, int ** pp2, int * pN2){
    int a = sumArray(*pp1, *pN1);
    int b = sumArray(*pp2, *pN2);
    if (a<b) return;    

    int *dummyPointer;
    int  dumber;
    // changing the arrays  
    dummyPointer = *pp1;
    *pp1 = *pp2 ;
    *pp2 = dummyPointer;
    // and the lengths. And thats it folks!
    dumber = *pN1;
    *pN1 = *pN2;
    *pN2 = dumber; 




}
int main(void) {
	int arr1[10]; //primer arreglo
	int arr2[10]; //segundo arreglo
	int * p1, * p2;
	int n1, n2; //tamaños

	p1 = arr1;
	p2 = arr2;

	readArray(arr1, &n1); //leer el primer arreglo
	readArray(arr2, &n2); //leer el segundo arreglo
	sortArrays(&p1, &n1, &p2, &n2); //ordenar los apuntadores a los arreglos
	printArrays(p1, n1, p2, n2); //mostrar los arreglos, ya ordenados

	return 0;
}
