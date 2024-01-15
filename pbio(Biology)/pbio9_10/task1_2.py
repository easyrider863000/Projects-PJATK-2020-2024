sekwencja_dna = "ATGCTACGATCGTAGCTAGCTAGCTAGCTAGC"

licznik_adeniny = 0
licznik_cytozyny = 0
licznik_guaniny = 0
licznik_tyminy = 0

for nukleotyd in sekwencja_dna:
    if nukleotyd == 'A':
        licznik_adeniny += 1
    elif nukleotyd == 'C':
        licznik_cytozyny += 1
    elif nukleotyd == 'G':
        licznik_guaniny += 1
    elif nukleotyd == 'T':
        licznik_tyminy += 1

print("Ilość adeniny:", licznik_adeniny)
print("Ilość cytozyny:", licznik_cytozyny)
print("Ilość guaniny:", licznik_guaniny)
print("Ilość tyminy:", licznik_tyminy)
