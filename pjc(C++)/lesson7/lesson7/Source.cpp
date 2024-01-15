#include<iostream>
using namespace std;

bool Func(int **tab1, int **tab2, int len1, int len2) {
	int sum1 = 0;
	int sum2 = 0;
	for (int i = 0; i < len1; i++)
	{
		for (int j = 0; j < len2; j++)
		{
			sum1 += *(*(tab1 + i) + j) * *(*(tab1 + i) + j);
			sum2 += *(*(tab2 + i) + j) * *(*(tab2 + i) + j);
		}
	}
	if (sum1 > sum2) {
		return true;
	}
	return false;
}

int main() {
	////zadanie1
	//wynik1
	//*p=9
	//refk=11*/

	//wynik2
	//Przed:
	//1 2 3 4
	//7 6 0 0
	//1 0 0 0
	//Po :
	//1 2 3 4
	//7 6 0 0
	//1 0 0 0


	////zadanie2
	//std:array vs std:vector
    //std: array ma stały rozmiar, a std : vector zmienia rozmiar w miarę dodawania i usuwania elementów.Każdy element std : vector odwołuje się do siebie w pamięci i może być zapisany nie w rzędzie w pamięci, ale dla std : array część pamięci jest przydzielana dla określonej liczby elementów, a adresy komórek są uporządkowane.Wewnętrznie działa to tak - aby znaleźć wartości elementów w std : array, musisz znać adres pierwszej komórki i liczbę elementów, a także poznać wartości elementów w std : wektor, musisz znać adres każdej komórki.


	////zadanie3
	//int *y[3]; //-tablica 3-elementowa wskaznikow do inta
	//int(&x)[3]; //-referencja do 3-elementowej tablicy
	//int &(*f)(int*, int&); //-wskaznik fo funkcji przyjmujacej wskaznik do wartosci typu int oraz referencje do wartosci typu int i zwracajacej referencje do wartosci typu int
	//int *(&z)[3]; //-referencja do 3-elementowej tablicy wskaznikow typu int


	//zadanie4
	int tab1[4][4] = { {5,6,7,8},{1,2,3,4},{10,11,12,13},{14,15,16,17} };
	int tab2[4][4] = { {5,6,7,7},{1,2,3,4},{10,11,12,13},{14,15,16,17} };
	int *tabt1[4];
	int *tabt2[4];
	for (int i = 0; i < 4; i++)
	{
		tabt1[i] = tab1[i];
		tabt2[i] = tab2[i];
	}
	cout << Func(tabt1, tabt2, 4, 4) << endl;
}