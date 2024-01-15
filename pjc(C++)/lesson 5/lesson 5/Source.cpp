#include <iostream>
#include <array>
#include <vector>

using namespace std;

double getAvg(int **tab, int len1, int len2) {
	int sum = 0;
	for (int i = 0; i < len1; i++)
	{
		for (int j = 0; j < len2; j++)
		{
			sum += *(*(tab + i) + j);
		}
	}
	return double(sum) / (len1*len2);
}

void isDivArr(array<int, 20> &arr, int num) {
	for (int i = 0; i < arr.size(); i++)
	{
		if (arr.at(i) % num == 0) {
			cout << arr.at(i) << ", ";
		}
	}
	cout << endl;
}

void vectorEx(vector<int> &vector) {
	for (int i = 0; i < 10; i++) {
		int num;
		cout << "Enter num --> ";
		cin >> num;
		if (vector.size() == 0 || num > vector.back()) {
			vector.push_back(num);
		}
		else if (num < vector.back()) {
			vector.pop_back();
			vector.push_back(num);
		}
	}
	for (int i = 0; i < vector.size(); i++) {
		cout << vector.at(i) << ", ";
	}
	cout << endl;
}


int main() {
	//------------------------------------------------------------------1
	int tab2dim[4][3] = { {1,2,3}, {4,5,6}, {7,8,9}, {10,11,12} };
	int *tabt[4];
	for (int i = 0; i < 4; i++)
	{
		tabt[i] = tab2dim[i];
	}
	cout << getAvg(tabt, 4, 3) << endl;
	//-------------------------------------------------------------------
	
	//------------------------------------------------------------------2
	array<int, 20> arr = { 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20 };
	isDivArr(arr, 2);
	//-------------------------------------------------------------------

	//------------------------------------------------------------------3
	vector<int> vector = { 1,2,3,4,5,6,7,8,9,10 };
	vectorEx(vector);
	//-------------------------------------------------------------------
}