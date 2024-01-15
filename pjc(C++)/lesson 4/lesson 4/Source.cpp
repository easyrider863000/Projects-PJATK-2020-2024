#include <iostream>
using namespace std;

void myPrintArray(char arr[]) {
	cout << sizeof(arr) << endl;
	char samogloski_eng[] = "aeiou";
	int size2 = (sizeof(samogloski_eng) / sizeof(char));
	bool isExist = false;
	for (int i = 0; true; i++) {
		if (arr[i] == '\0') {
			break;
		}
		isExist = false;
		for (int j = 0; j < size2 && !isExist; j++) {
			if (arr[i] == samogloski_eng[j]) {
				isExist = true;
			}
		}
		if (!isExist) {
			cout << arr[i];
		}
	}
	cout << endl;
}

int main() {
	cout << "zadanie1" << "---------------------------" << endl;
	int num = 5;
	int *num_wsk_ref = &num;
	num++;
	cout << *num_wsk_ref << endl;
	num_wsk_ref++;
	cout << *num_wsk_ref << endl;
	cout << "zadanie2" << "---------------------------" << endl;
	char arr[] = "hello pjatk";
	int arr2[] = { 1,2,3,4,5,6,7,8,9,10,11 };
	for (int i = 0; i < 11; i++) {
		cout << arr[i];
	}
	cout << endl;
	for (int i = 0; i < 11; i++) {
		cout << arr2[i];
	}
	cout << endl;
	cout << "length char array: " << sizeof(arr) / sizeof(char) << endl;
	cout << "length int array: " << sizeof(arr2) / sizeof(int) << endl;
	cout << "zadanie3" << "---------------------------" << endl;
	char arr3[] = "hello world";
	myPrintArray(arr3);
	cout << "zadanie4" << "---------------------------" << endl;
	int arr4[3][3] = { {1,2,3},{4,5,6},{7,8,9}};

}
