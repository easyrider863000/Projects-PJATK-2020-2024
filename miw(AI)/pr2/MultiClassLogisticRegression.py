import numpy as np
import matplotlib.pyplot as plt
from sklearn import datasets
from sklearn.model_selection import train_test_split
from LogisticRegressionGD import LogisticRegressionGD

class MultiClassLogisticRegression(object):
    def __init__(self, eta=0.05, n_iter=100, random_state=1):
        self.eta = eta
        self.n_iter = n_iter
        self.random_state = random_state

    def fit(self, X, y):
        #Pętla for iteruje po wszystkich klasach.
        # Dla każdej klasy tworzony jest wektor binarny y_binary, który wskazuje, które próbki należą do danej klasy.
        # Tworzony jest również model regresji logistycznej (lr) dla każdej klasy.
        # Wynikiem tej metody jest macierz coefs_, która zawiera wiersze odpowiadające każdej z klas
        # i kolumny odpowiadające każdemu z atrybutów, zawierająca współczynniki modelu.
        self.classes_ = np.unique(y)
        self.coefs_ = np.zeros((len(self.classes_), 1 + X.shape[1]))

        for i, c in enumerate(self.classes_):
            y_binary = np.where(y == c, 1, 0)
            lr = LogisticRegressionGD(eta=self.eta, n_iter=self.n_iter, random_state=self.random_state)
            lr.fit(X, y_binary)
            self.coefs_[i] = lr.w_

        return self

    def predict_proba(self, X):
        #metoda zwraca macierz prawdopodobieństw dla każdej z klas dla podanych próbek wejściowych X.
        probas = np.zeros((X.shape[0], len(self.classes_)))
        for i, c in enumerate(self.classes_):
            probas[:, i] = self._sigmoid(X.dot(self.coefs_[i][1:]) + self.coefs_[i][0])
        return probas

    def predict(self, X):
        #Metoda zwraca przewidywanie klasy dla podanych próbek wejściowych X.
        # Jest to klasa z największym prawdopodobieństwem w macierzy zwracanej przez metodę predict_proba()
        return np.argmax(self.predict_proba(X), axis=1)# zwraca index z najwiekszym prawdopodobienstwiem

    def _sigmoid(self, z):
        # służy do wyliczania funkcji sigmoidalnej
        return 1. / (1. + np.exp(-np.clip(z, -250, 250)))

    def print_proba(self, X):
        probas = self.predict_proba(X)
        for i, c in enumerate(self.classes_):
            print("Class "+str(c)+": {"+str(probas[:, i])+"}")