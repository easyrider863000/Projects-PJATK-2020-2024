#include<iostream>
using namespace std;

class Pracownik {
public:
	string imie;
	string nazwisko;
	double zarobkiBrutto;
	int id;
	static int lastId;
	static double skladki;
	double zarobkiNetto() {
		return zarobkiBrutto - zarobkiBrutto * skladki;
	}
	static void podwyzka(Pracownik *tab, int wym) {
		for (int i = 0; i < wym; i++)
		{
			(*(tab + i)).zarobkiBrutto = (*(tab + i)).zarobkiBrutto*1.1;
		}
	}
	Pracownik(string imie, string nazwisko, double zarobkiBrutto) {
		this->imie = imie;
		this->nazwisko = nazwisko;
		this->zarobkiBrutto = zarobkiBrutto;
		this->id = lastId + 1;
		lastId++;
	}
	~Pracownik()
	{
		std::cout << id << " " << imie.c_str() << " " << nazwisko.c_str() << endl;
	}
};
int Pracownik::lastId = 0;
double Pracownik::skladki = 0.5;

int main() {
	//zadanie-1
	//a)class
	//b)public(dostępne wszędzie), private(dostępne w klasie), protected(dostępne wewnątrz klasy oraz w klasach dziedziczących)
	//c)W strukturze wszystkie pola są publiczne, aw klasie te słowa służą do delegowania dostępu do zmiennych i metod klasy. Domyślnie wszystko w klasie jest publiczne
	//d)zdefiniowane za pomocą słowa kluczowego "static", inicjalizacja poza klasą poprzez jej w pełni kwalifikowaną nazwę przy użyciu operatora zakresu klasy „::”
	//e)czyści tylko stos z używaną pamięcią dla obiektu tej klasy


	//zadanie-2
	Pracownik tab1[4] = { Pracownik("Alex","Klar",32.5),
		Pracownik("John","Doe",125.5),
		Pracownik("Jack","Koln",32.34),
		Pracownik("Sasha","Grey",26.2), };
	std::cout << tab1[0].zarobkiNetto() << endl;
	Pracownik::podwyzka(tab1, 4);
	std::cout << tab1[0].zarobkiNetto() << endl;
}