#include<iostream>
using namespace std;

class Pupil {
public:
	class Wlasciciel
	{
	public:
		string Imie;
		string Nazwisko;
		string numerTelefonu;
		Wlasciciel(const Wlasciciel &w) {
			this->Imie = w.Imie;
			this->Nazwisko = w.Nazwisko;
			this->numerTelefonu = w.numerTelefonu;
		}
		Wlasciciel(string Imie, string Nazwisko, string numerTelefonu){
			this->Imie = Imie;
			this->Nazwisko = Nazwisko;
			this->numerTelefonu = numerTelefonu;
		}
		Wlasciciel(){
			this->Imie = "noname";
			this->Nazwisko = "nosurname";
			this->numerTelefonu = "nophone";
		}
		void printWlasciciel() const {
			cout << Imie.c_str() <<" "<< Nazwisko.c_str() <<" "<< numerTelefonu.c_str() << endl;
		}
	};
	int numer;
	static int lastNumer;
	string imie;
	string rasa;
	double waga;
	Wlasciciel wlasciciel;
	Pupil() {
		numer = lastNumer;
		lastNumer++;
		imie = "noname";
		rasa = "norasa";
		waga = 50;
		wlasciciel = Wlasciciel();
	}
	Pupil(const Pupil &p) {
		this->numer = lastNumer;
		this->lastNumer++;
		this->imie = p.imie;
		this->rasa = p.rasa;
		this->waga = p.waga;
		this->wlasciciel = Wlasciciel(wlasciciel);
	}
	Pupil(string imie, string rasa, double waga, Wlasciciel wlasciciel) {
		this->numer = lastNumer;
		this->lastNumer++;
		this->imie = imie;
		this->rasa = rasa;
		this->waga = waga;
		this->wlasciciel = Wlasciciel(wlasciciel);
	}
	~Pupil() {	
		cout << numer << " " << imie.c_str() << " " << rasa.c_str() << endl;
	}
	void printPupil() const {
		cout << numer << " " << imie.c_str() << " " << rasa.c_str() << " " << waga << " " << endl;
		cout << "Wlasciciel:" << endl;
		wlasciciel.printWlasciciel();
		cout << endl;
	}
};
int Pupil::lastNumer = 1;




int main() {
	//zadanie1
	//a)w metodzie stałej ten obiekt i jego komponenty nie mogą być zmieniane. słowo kluczowe „const” należy umieścić przed otwarciem nawiasu klamrowego lub przed przecinkiem w deklaracji metody
	//b)"mutable" można dodać do zmiennych składowych klasy, aby wskazać, że dana zmienna może się zmieniać nawet w funkcji stałej.
	//c)funkcje zaprzyjaźnione to funkcje, które nie są członkami klasy, ale mają dostęp do jej prywatnych elementów członkowskich - zmiennych i funkcji, które mają specyfikator prywatny. Słowo kluczowe "friend" służy do definiowania funkcji zaprzyjaźnionej.

	//zadanie2
	Pupil* pupili = new Pupil[4];
	Pupil::Wlasciciel w1 = Pupil::Wlasciciel("Vasya","Reszkovich","8657982135");
	Pupil p1 = Pupil("Alex", "black", 12.2, w1);
	Pupil p2 = Pupil("John", "white", 38.6, w1);
	Pupil p3 = Pupil(p1);
	Pupil p4 = Pupil();
	pupili[0] = p1;
	pupili[1] = p2;
	pupili[2] = p3;
	pupili[3] = p4;
	for (int i = 0; i < 4; i++)
	{
		pupili[i].printPupil();
	}
	delete[] pupili;
}