import pandas as pd
from scipy import stats

df1 = pd.read_csv('dane_biologiczne1.csv')
df2 = pd.read_csv('dane_biologiczne2.csv')

srednia1 = df1['wartosc'].mean()
odchylenie_std1 = df1['wartosc'].std()

srednia2 = df2['wartosc'].mean()
odchylenie_std2 = df2['wartosc'].std()

t_stat, p_value = stats.ttest_ind(df1['wartosc'], df2['wartosc'])

print("Zbiór danych 1:")
print("Średnia:", srednia1)
print("Odchylenie standardowe:", odchylenie_std1)
print()

print("Zbiór danych 2:")
print("Średnia:", srednia2)
print("Odchylenie standardowe:", odchylenie_std2)
print()

print("Wyniki testu t:")
print("Statystyka t:", t_stat)
print("Wartość p:", p_value)
