import numpy as np
import matplotlib.pyplot as plt
from sklearn.model_selection import train_test_split


file = open("Dane/dane2.txt","r")
X_y = np.loadtxt(file)
X = X_y[:,0]
y = X_y[:,1]

#task1
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.3, random_state=31)
plt.plot(X_train, y_train, 'b.')

#task2,3
a, b = np.polyfit(X_train, y_train, deg=1)
#polyfit zwraca współczynniki wielomianu, który najlepiej dopasowuje się do danych treningowych,
# w tym przypadku jest to funkcja liniowa. Argument deg=1 oznacza stopień wielomianu, który ma zostać dopasowany,
# czyli w tym przypadku 1 (linia prosta).
mse1 = np.mean(((a*X_test + b) - y_test)**2)
print("Mean squared error for Model 1 =",mse1)
plt.plot(X_train, a*X_train + b, 'r-')

#task4,5
p = np.polyfit(X_train, y_train, deg=3)
a2, b2, c2, d2 = p[0], p[1], p[2], p[3]
X_train.sort()
plt.plot(X_train, a2*X_train**3 + b2*X_train**2 + c2*X_train + d2, 'c-')

mse2 = np.mean(((a2 * X_test**3 + b2 * X_test**2 + c2 * X_test + d2) - y_test)**2)
print("Mean squared error for Model 2 =",mse2)
if mse1<mse2:
  print("Better is model 1")
elif mse1>mse2:
  print("Better is model 2")
else:
  print("MSE1 = MSE2")

plt.show()

