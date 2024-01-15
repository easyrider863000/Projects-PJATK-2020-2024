#include<iostream>
using namespace std;


class Stwor {
public:
	string nazwa;
	virtual void wypisz() {
		cout << nazwa.c_str() << endl;
		cout << endl;
	}
	virtual ~Stwor()
	{
		cout << "Usunieto Stwora" << endl << endl;
	}
};

class StworWodny :Stwor {
public:
	string sposobPlywania;
	StworWodny(string name, string _sposobPlywania):Stwor() {
		this->nazwa = name;
		this->sposobPlywania = _sposobPlywania;
	}
	void wypisz() override {
		cout << nazwa.c_str() << endl;
		cout << sposobPlywania.c_str() << endl;
		cout << endl;
	}
	virtual ~StworWodny()
	{
		cout << "Usunieto Stwora Wodnego" << endl;
	}
};

class Kaczka :StworWodny {
public:
	string imieKaczki;
	void wypisz() override {
		cout << "Kaczka" << endl;
		cout << sposobPlywania.c_str() << endl;
		cout << imieKaczki.c_str() << endl;
		cout << endl;
	}
	Kaczka(string sposobplywania, string imieKaczki) :StworWodny("Kaczka", sposobplywania) {
		this->imieKaczki = imieKaczki;
	}
	virtual ~Kaczka()
	{
		cout << "Usunieto Kaczke" << endl;
	}
};

int main() {
	//zadanie-1
	//a)Klasa bazowa to ta, z której dziedziczymy. Klasa pochodna to ta, która dziedziczy po klasie bazowej
	//b)Specyfikator public i protected jest dostępny w klasie dziedziczonej, a specyfikator private nie jest dostępny w klasie dziedziczonej
	//c)w przypadku, gdy pochodna klasa dziedziczy klasę bazową publicznie, to znaczy ze specyfikatorem public
	//d)tworzymy klasę abstrakcyjną, gdy konieczne jest zdefiniowanie klasy, która nie implikuje tworzenia konkretnych obiektów. aby stworzyć klasę abstrakcyjną, musisz stworzyć co najmniej jedną funkcję wirtualną 
	//e)kolejność wywoływania destruktorów. do wywołania destruktora stosuje się późne wiązanie, to znaczy, gdy obiekt jest zniszczony, pobierany jest wskaźnik do klasy, to adres destruktora, którego potrzebujemy, jest określany z tablicy funkcji wirtualnych, a to jest destruktor klasa pochodna, która po swojej pracy zgodnie z oczekiwaniami wywołuje destruktor bazy. Wynik: obiekt jest zniszczony, pamięć zostaje zwolniona.


	//zadanie-2
	Stwor s = Stwor();
	s.nazwa = "stwor1";
	s.wypisz();
	StworWodny sw = StworWodny("stwor2", "sposob1");
	sw.wypisz();
	Kaczka k = Kaczka("sposob2", "Kasia");
	k.wypisz();
}
