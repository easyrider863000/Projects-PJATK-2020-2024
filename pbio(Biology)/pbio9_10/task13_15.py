import pandas as pd

data = pd.read_excel("dane_sekwencji.xlsx")

srednia = data.mean()
mediana = data.median()
odchylenie_std = data.std()

print("Średnia:")
print(srednia)
print()
print("Mediana:")
print(mediana)
print()
print("Odchylenie standardowe:")
print(odchylenie_std)
