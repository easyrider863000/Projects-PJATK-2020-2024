#include<iostream>
using namespace std;

bool IsCharInString(string str, char ch) {
	if (str.find(ch, 0) >= 0 && str.find(ch, 0) != std::string::npos)
		return true;
	return false;
}

double MultiplicationOrDivision2Double(double num1, double num2, bool IsMult) {
	if (IsMult)
		return num1 * num2;
	return num1 / num2;
}
void CoutHotOrColdColor(bool color) {
	if (color)
		cout << "Color is hot" << endl;
	else
		cout << "Color is cold" << endl;
}

void main() {
	//1
	/*cout << sizeof(bool) << " bajt" << endl;
	cout << sizeof(char) << " bajt" << endl;
	cout << sizeof(int) << " bajt" << endl;
	cout << sizeof(double) << " bajt" << endl;*/

	//2
	/*string small = "qwertyuiopasdfghjklzxcvbnm";
	string big = "QWERTYUIOPASDFGHJKLZXCVBNM";
	string nums = "1234567890";
	char symb;
	cout << "Enter symbol --> ";
	cin >> symb;
	if (IsCharInString(small, symb))
		cout << "Symbol is small" << endl;
	else if (IsCharInString(big, symb))
		cout << "Symbol is big" << endl;
	else if (IsCharInString(nums, symb))
		cout << "Symbol is number" << endl;
	else
		cout << "Symbol is other" << endl;*/

	//3
	/*cout << "Write number 1 -->";
	double num1;
	cin >> num1;
	cout << "Write number 2 -->";
	double num2;
	cin >> num2;
	cout << "Choose \"/\" or \"*\"-->";
	char symb;
	cin >> symb;
	switch (symb)
	{
	case '*':
		cout << "Result = " << MultiplicationOrDivision2Double(num1, num2, true) << endl;
		break;
	case '/':
		cout << "Result = " << MultiplicationOrDivision2Double(num1, num2, false) << endl;
		break;
	}*/

	//4
	/*enum Colors : bool {
		Red = 1,
		Yellow = 1,
		Orange = 1,
		Blue = 0,
		Purple = 0,
		Green = 0
	};
	CoutHotOrColdColor(Colors::Red);
	CoutHotOrColdColor(Colors::Blue);*/
}