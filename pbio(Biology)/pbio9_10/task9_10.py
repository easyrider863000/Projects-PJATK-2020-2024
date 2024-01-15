sekwencja_dna = "ATGCTACGATCGTACGGCGCAAATAGCTAGCTAGCTAGC"

ilosc_gc = sekwencja_dna.count("G") + sekwencja_dna.count("C")
procent_gc = (ilosc_gc / len(sekwencja_dna)) * 100

print("Procent zawartości GC:", procent_gc)