#include<iostream>
using namespace std;



//zadanie1
//task2
//int roznice(char *tab1, char *tab2, int len);
//int roznice(int *tab1, int *tab2, int len);

int roznice(char *tab1, char *tab2, int len) {
	int count = 0;
	for (int i = 0; i < len; i++) {
		if (*(tab1+i) != *(tab2+i)) {
			count++;
		}
	}
	return count;
}
int roznice(int *tab1, int *tab2, int len) {
	int count = 0;
	for (int i = 0; i < len; i++) {
		if (*(tab1+i) != *(tab2+i)) {
			count++;
		}
	}
	return count;
}

double** findMin(double **tab, int len1, int len2) {
	double **min = tab;
	for (int i = 0; i < len1; i++)
	{
		for (int j = 0; j < len2; j++)
		{
			if (**min > *(*(tab + i) + j)) {
				**min = *(*(tab + i) + j);
			}
		}
	}
	return min;
}

template<typename T>
int podobn(T tab1, T tab2, int len)
{
	int count = 0;
	for (int i = 0; i < len; i++)
	{
		if (tab1[i] == tab2[i]) {
			count++;
		}
	}
	return count;
}


int main() {

	//zadanie1
	//task1
	//a)Funkcja globalna jest widoczna wszędzie, a funkcja lokalna jest widoczna tylko w nawiasach klamrowych
	//b)przed wywołaniem funkcji, powyżej
	//c)można nie przekazywać zmiennych globalnych w parametry i nie pisac naglowka static f-cji
	//d)nie

	//zadanie1
	//task3
	/*int t[] = { 2,2,3,4,5 };
	int t2[] = { 1,2,2,4,5 };
	int* tabt1 = t;
	int* tabt2 = t2;
	cout << roznice(tabt1, tabt2, 5) << endl;
	char t3[] = { 'e','f','k' };
	char t4[] = { 'm','f','k' };
	char* tabt3 = t3;
	char* tabt4 = t4;
	cout << roznice(tabt3, tabt4, 3) << endl;*/


	//zadanie2
	/*double tab1[4][4] = { {5,6,7,8},{0.6,2,3,4},{10,11,12,13},{14,15,16,17} };
	double *tabt1[4];
	for (int i = 0; i < 4; i++)
	{
		tabt1[i] = tab1[i];
	}
	cout << **findMin(tabt1, 4, 4) << endl;*/

	//zadanie3
	/*double t[] = { 2,2,3,4,5 };
	double t2[] = { 1,2,2,4,5 };
	cout << podobn(t, t2, 5) << endl;
	char t3[] = { 'e','f','k' };
	char t4[] = { 'm','f','k' };
	cout << podobn(t3, t4, 3) << endl;*/

	//zadanie4
	/*int tab1[] = { 1,2,3,4,7 };
	int tab2[] = { 1,12,3,4,5 };
	int* tabt1 = tab1;
	int* tabt2 = tab2;
	auto roznpodobn = [](int* tab1, int* tab2, int len)
	{
		int count = 0;
		for (int i = 0; i < len; i++)
		{
			if (*(tab1+i) != *(tab2+i)) {
				count++;
			}
		}
		cout << "Ilosc roznych: " << count << endl;
		cout << "Ilosc podobnych: " << len-count << endl;
	};
	roznpodobn(tabt1, tabt2, 5);*/
}