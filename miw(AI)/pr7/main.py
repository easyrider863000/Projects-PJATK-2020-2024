"""
o = []
c = []
h = []
l = []
v = []
for j in response.json()['items']:
  o.append(float(j[1]['o']))
  c.append(float(j[1]['c']))
  h.append(float(j[1]['h']))
  l.append(float(j[1]['l']))
  v.append(float(j[1]['v']))"""

"""import requests
from sklearn.linear_model import LinearRegression
from sklearn.metrics import mean_squared_error
import matplotlib.pyplot as plt
import numpy as np

headers = {'content-type': 'application/json'}

url = "https://api.zonda.exchange/rest/trading/candle/history/BTC-PLN/900?from=1540210129000&to=1543410329000"

response = requests.request("GET", url)

data = response.json()['items']
split_index = int(len(data) * 0.8)
train_data = data[:split_index]
test_data = data[split_index:]

class AR:
    def __init__(self, period):
        self.period = period
        self.beta = None

    def fit(self, y):
        X = np.zeros((len(y) - self.period, self.period))
        for i in range(self.period):
            X[:, i] = y[(self.period - i - 1):(len(y) - i - 1)]
        y = y[self.period:]
        reg = LinearRegression().fit(X, y)
        self.beta = reg.coef_

    def predict(self, y):
        y_pred = np.zeros(len(y))
        for i in range(self.period, len(y)):
            y_pred[i] = np.dot(y[(i - self.period):i], self.beta)
        return y_pred[self.period:]

# Tworzenie wektora zamknięcia notowań
c = np.array([float(entry[1]['c']) for entry in train_data])

# Inicjalizacja i dopasowanie modelu AR
model = AR(period=50)
model.fit(c)

# Przewidywanie dla danych testowych
test_c = np.array([float(entry[1]['c']) for entry in test_data])
pred_c = model.predict(test_c)

test_c2 = test_c[:-model.period]

# Wykres wyników
plt.plot(test_c2, label='Rzeczywiste')
plt.plot(pred_c, label='Przewidywane')
plt.xlabel('Indeks próbki')
plt.ylabel('Wartość')
plt.legend()
plt.show()

# Obliczenie błędu średniokwadratowego (RMSE)
mse = mean_squared_error(test_c2, pred_c)
rmse = np.sqrt(mse)
print(rmse)"""












import requests
import numpy as np
import matplotlib.pyplot as plt
from sklearn.preprocessing import MinMaxScaler
from sklearn.metrics import mean_squared_error
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import LSTM, Dropout, Dense

# Pobranie danych notowań
url = "https://api.zonda.exchange/rest/trading/candle/history/LTC-PLN/900?from=1540210129000&to=1543410329000"
response = requests.get(url)
data = response.json()['items']

# Tworzenie wektora zamknięcia notowań
closing_prices = np.array([float(entry[1]['c']) for entry in data]).reshape(-1, 1)

# Przeskalowanie danych
scaler = MinMaxScaler(feature_range=(0, 1))
closing_prices_scaled = scaler.fit_transform(closing_prices)

# Podział danych na zbiór treningowy i testowy
train_size = int(len(closing_prices_scaled) * 0.8)
train_data = closing_prices_scaled[:train_size]
test_data = closing_prices_scaled[train_size:]

# Przygotowanie danych w formacie sekwencji
def create_sequences(data, seq_length):
    X, y = [], []
    for i in range(len(data) - seq_length):
        X.append(data[i:i+seq_length])
        y.append(data[i+seq_length])
    return np.array(X), np.array(y)

seq_length = 50  # Długość sekwencji wcześniejszych notowań
X_train, y_train = create_sequences(train_data, seq_length)
X_test, y_test = create_sequences(test_data, seq_length)

# Konstrukcja modelu LSTM
model = Sequential()
#LSTM (Long Short-Term Memory): LSTM to rodzaj komórki pamięci w sieciach rekurencyjnych, która ma zdolność do przechowywania informacji na dłuższe okresy czasu. W przeciwieństwie do prostych rekurencyjnych jednostek, które mogą mieć problemy z długotrwałym zapamiętywaniem, komórki LSTM mają dodatkowe struktury (bramy), które kontrolują przepływ informacji. Pozwala to na skuteczniejsze uczenie się długoterminowych zależności w danych sekwencyjnych.
#Dropout: Dropout jest techniką regularyzacji, która ma na celu zmniejszenie overfittingu (nadmiernego dopasowania) w sieciach neuronowych. Podczas treningu, na każdej warstwie ukrytej, losowo wybrane jednostki są deaktywowane (ustawiane na 0) z pewnym prawdopodobieństwem. To zmusza sieć do uczenia się na podstawie innych, losowo wybranych kombinacji jednostek, co może poprawić ogólną zdolność generalizacji modelu.
#Dense: Warstwa Dense (gęsta) to standardowa warstwa w sieciach neuronowych, w której każda jednostka jest połączona z każdą jednostką z poprzedniej i następnej warstwy. Jest to najprostszy rodzaj warstwy neuronowej, w której każda jednostka wykonuje operację ważonej sumy wejść i przechodzi przez funkcję aktywacji. Warstwa Dense jest odpowiedzialna za naukę nieliniowych zależności między danymi wejściowymi a wyjściowymi.
model.add(LSTM(100, input_shape=(seq_length, 1)))
model.add(Dropout(0.4))
model.add(Dense(1))
model.compile(loss='mean_squared_error', optimizer='adam')

# Trenowanie modelu
history = model.fit(X_train, y_train, epochs=20, batch_size=32, validation_data=(X_test, y_test), verbose=1)

# Predykcja dla danych testowych
y_pred = model.predict(X_test)

# Odwrócenie skalowania dla porównania wyników
y_test = scaler.inverse_transform(y_test)
y_pred = scaler.inverse_transform(y_pred)

# Wykres wyników
plt.plot(y_test, label='Rzeczywiste')
plt.plot(y_pred, label='Przewidywane')
plt.xlabel('Indeks próbki')
plt.ylabel('Wartość zamknięcia')
plt.legend()
plt.show()

# Obliczenie błędu średniokwadratowego (RMSE)
mse = mean_squared_error(y_test, y_pred)
rmse = np.sqrt(mse)
print(rmse)