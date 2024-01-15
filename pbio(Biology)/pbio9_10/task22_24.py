from Bio.Seq import Seq

# AATGCGCTTGCGTATTACGAT
sekwencja_DNA = input("Wprowadź sekwencję DNA: ")

sekwencja_RNA = Seq(sekwencja_DNA).transcribe()

lancuch_bialkowy = sekwencja_RNA.translate()

print("Sekwencja DNA:", sekwencja_DNA)
print("Sekwencja RNA:", sekwencja_RNA)
print("Łańcuch białkowy:", lancuch_bialkowy)
