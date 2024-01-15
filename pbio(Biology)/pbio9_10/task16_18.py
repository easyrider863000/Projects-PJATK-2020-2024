import pandas as pd
import matplotlib.pyplot as plt

dane = pd.read_csv('dane_szczepienia.csv')

grupy_wiekowe = dane['Grupa wiekowa']
liczba_zaszczepionych = dane['Liczba zaszczepionych']

plt.bar(grupy_wiekowe, liczba_zaszczepionych)
plt.xlabel('Grupa wiekowa')
plt.ylabel('Liczba zaszczepionych')
plt.title('Liczba zaszczepionych przeciwko COVID-19 w różnych grupach wiekowych')
plt.show()