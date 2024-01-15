#include<iostream>
#include<string>
using namespace std;

class Figura {
public:
	string kolor;
	virtual void poleOrazKolor() {}
};
class Kwadrat :Figura {
public:
	double dlugoscBoku;
	Kwadrat(string _kolor, double _dlugoscBoku) {
		this->kolor = _kolor;
		this->dlugoscBoku = _dlugoscBoku;
	}
	void poleOrazKolor() override
	{
		cout << "Pole = " << pow(this->dlugoscBoku,2) << " Color = " << this->kolor.c_str() << endl;
	}
	~Kwadrat()
	{
		cout << "Zegnaj kwadracie" << endl;
	}
};

struct Ptak {
	string plec;
	string gatunek;
	string etapRozwoju;
};
Ptak* getPtaks(int count){
	Ptak* ptaks = new Ptak[count];
	string tmp;
	for (int i = 0; i < count; i++)
	{
		cout << "Enter plec --> ";
		cin.ignore();
		getline(cin, tmp);
		ptaks[i].plec = tmp;
		cout << "Enter gatunek --> ";
		getline(cin, tmp);
		ptaks[i].gatunek = tmp;
		cout << "Enter etap rozwoju --> ";
		getline(cin, tmp);
		ptaks[i].etapRozwoju = tmp;
	}
	return ptaks;
}

void show(Ptak* ptaks, int count) {
	for (int i = 0; i < count; i++)
	{
		cout << i + 1 << ".\tPlec = " << ptaks[i].plec << endl;
		cout << "\t Gatunek = " << ptaks[i].gatunek << endl;
		cout << "\t Etap rozwoju = " << ptaks[i].etapRozwoju << endl;
	}
}

void drop(Ptak* ptaks) {
	delete[] ptaks;
	ptaks = nullptr;
}

int main() {
	//zadanie 4
	Kwadrat k = Kwadrat("blue", 5.2);
	k.poleOrazKolor();

	//zadanie5
	int n = 0;
	cout << "Enter count of ptaks --> ";
	cin >> n;
	Ptak* myPtaks = getPtaks(n);
	show(myPtaks,n);
	drop(myPtaks);
}