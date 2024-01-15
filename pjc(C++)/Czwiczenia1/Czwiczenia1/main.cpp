#include<iostream>
using namespace std;

void main() {
	//1
	/*cout << sizeof(bool) << " bajt" << endl;
	cout << sizeof(char) << " bajt" << endl;
	cout << sizeof(int) << " bajt" << endl;
	cout << sizeof(double) << " bajt" << endl;*/

	//2
	string small = "qwertyuiopasdfghjklzxcvbnm";
	string big = "QWERTYUIOPASDFGHJKLZXCVBNM";
	string nums = "1234567890";
	char symb;
	cout << "Enter symbol --> ";
	cin >> symb;
	if (IsCharInString(small,symb)) {
		cout << "Symbol is small" << endl;
	}
	else if (IsCharInString(big, symb)) {
		cout << "Symbol is big" << endl;
	}
	else if (IsCharInString(nums, symb)) {
		cout << "Symbol is number" << endl;
	}
	else {
		cout << "Symbol is other" << endl;
	}


	
}
bool IsCharInString(string str, char ch) {
	if (str.find(ch, 0) >= 0 && str.find(ch, 0) != std::string::npos)
		return true;
	return false;
}