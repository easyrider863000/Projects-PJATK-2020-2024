#include <iostream>
using namespace std;
typedef double* (&M);
typedef double(&N)[16];
using B = double* [4];
using V = double(*)[16];

double Func1(double **tab, int len1, int len2) {
	double diagon = 0.0;
	double downDiagon = 0.0;
	for (int i = 0; i < len1; i++)
	{
		for (int j = 0; j < len2; j++)
		{
			if (i == j) {
				diagon += *(*(tab + i) + j);
			}
			if (i > j) {
				downDiagon += *(*(tab + i) + j);
			}
		}
	}
	if (downDiagon > diagon) {
		return downDiagon;
	}
	return diagon;
}

int main() {

	//zadanie1--------------------------------------------------------------
	//int tab[15]; //tablica 15-elementowa typu int
	//int *u[7]; //tablica 7-elementowa wskaznikow do inta
	//int(&l)[10]; //referencja do 10-elementowej tablicy
	//int*(&a)[33]; //referencja do 33-elementowej tablicy wskaznikow typu int
	//int*(*t)(int&, int*); //wskaznik fo funkcji przyjmujacej referencje do wartosci typu int oraz wskaznik do wartosci typu int i zwracajacej wskaznik do wartosci typu int
	//-----------------------------------------------------------------------	
	
	//zadanie2---------------------------------------------------------------
	//M m;   //a
	//N n;   //b
	//B b;   //c
	//V v;   //d
	//-----------------------------------------------------------------------
	
	//zadanie3---------------------------------------------------------------
	double tab[4][4] = { {5,6,7,8},{1,2,3,4},{10,11,12,13},{14,15,16,17} };
	B tabt;
	for (int i = 0; i < 4; i++)
	{
		tabt[i] = tab[i];
	}
	cout << Func1(tabt, 4, 4) << endl;
	//-----------------------------------------------------------------------
}