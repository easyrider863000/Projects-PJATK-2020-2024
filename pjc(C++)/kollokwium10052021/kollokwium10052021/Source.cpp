#include <iostream>
using namespace std;
void showNewArr(int **tab1, int **tab2, int len)
{
	for (int i = 0; i < len; i++)
	{
		for (int j = 0; j < len; j++)
		{
			cout << *(*(tab1 + i) + j) - *(*(tab2 + i) + j) << " ";
		}
		cout << endl;
	}
}

int main() {
	int const lengthArr = 4;
	int tab1[lengthArr][lengthArr] = { {2,2,3,10},
										{4,6,6,1},
										{7,8,10,7},
										{10,11,12,6} };
	int tab2[lengthArr][lengthArr] = { {1,2,3,10},
										{4,5,6,1},
										{7,8,9,7},
										{10,11,12,5} };
	int *tabt1[lengthArr];
	int *tabt2[lengthArr];
	for (int i = 0; i < lengthArr; i++)
	{
		tabt1[i] = tab1[i];
		tabt2[i] = tab2[i];
	}
	showNewArr(tabt1, tabt2, lengthArr);
}



