#include<iostream>
#include<string>
using namespace std;

struct Kamien{
	double waga;
	string rodzaj;
	double obietosc;
};

Kamien* kamieni(int count) {
	Kamien* arr = new Kamien[count];
	string tmpstr;
	for (int i = 0; i < count; i++)
	{
		cout << i + 1 << ". Enter waga --> ";
		cin >> arr[i].waga;
		cout << i + 1 << ". Enter rodzaj --> ";
		std::cin >> arr[i].rodzaj;
		cout << i + 1 << ". Enter obietosc --> ";
		cin >> arr[i].obietosc;
	}
	return arr;
}
void printKamieni(Kamien* arr, int count) {
	cout << endl;
	for (int i = 0; i < count; i++)
	{
		cout << i + 1 << ". waga --> " << arr[i].waga << endl;
		cout << i + 1 << ". rodzaj --> " << arr[i].rodzaj << endl;
		cout << i + 1 << ". obietosc --> " << arr[i].obietosc << endl;
		cout << endl;
	}
}
void deleteKamieni(Kamien* arr) {
	delete[] arr;
	arr = nullptr;
}
int main() {
	//zadanie1
	//a) Stos to lista elementów(Obszar pamięci) działających na zasadzie tego, kto wszedł pierwszy, opuści ostatni.(Last In, First Out)
	//b) Sterta to magazyn pamięci, który umożliwia dynamiczną alokację pamięci i nie działa jak stos.
	//c) new jest to operator, który przydziela w stercie nową pamięć dla zmiennych.
	//d) memory leak to proces niekontrolowanego spadku ilości wolnej pamięci RAM lub pamięci wirtualnej komputera związany z błędami w działaniu programów, które nie zwalniają w czasie niepotrzebnych obszarów pamięci.
	//e) tablice statyczne są przechowywane na stosie i gdy są używane, nie może wystąpić wyciek pamięci, a tablice dynamiczne są przechowywane w stercie i tworzone przez operatora „new”, ale pamięcią trzeba zarządzać samodzielnie i wyczyścić ją na czas .
	//f) używając operatora delete, zwracamy pamięć, której już nie używamy, z powrotem do sterty. aby usunąć tabele, użyj operatora usuwania, nawiasów kwadratowych i nazwy tablicy, a dla zmiennych bez nawiasów kwadratowych.

	//zadanie2
	int count;
	cout << "Enter count of kamieni --> ";
	cin >> count;
	Kamien* kams = kamieni(count);
	printKamieni(kams, count);
	deleteKamieni(kams);
}