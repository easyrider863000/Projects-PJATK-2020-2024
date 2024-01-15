homozygoty_dominujace = 100
heterozygoty = 200
homozygoty_recesywne = 100

liczba_osobnikow = homozygoty_dominujace + heterozygoty + homozygoty_recesywne

czestotliosc_allelu_dominujacego = (homozygoty_dominujace + 0.5 * heterozygoty) / liczba_osobnikow
czestotliosc_allelu_recesywnego = (homozygoty_recesywne + 0.5 * heterozygoty) / liczba_osobnikow

print("Częstotliwość allelu dominującego (A):", czestotliosc_allelu_dominujacego)
print("Częstotliwość allelu recesywnego (a):", czestotliosc_allelu_recesywnego)