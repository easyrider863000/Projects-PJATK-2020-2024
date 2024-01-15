sekwencja_dna = input("Podaj sekwencję DNA: ")

sekwencja_rna = sekwencja_dna.replace('T', 'U')
dlugosc_rna = len(sekwencja_rna)

print("Sekwencja RNA:", sekwencja_rna)
print("Długość sekwencji RNA:", dlugosc_rna)
