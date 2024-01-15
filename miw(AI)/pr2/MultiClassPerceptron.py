import matplotlib.pyplot as plt
import numpy as np
from sklearn.model_selection import train_test_split
from sklearn import datasets
from Perceptron import Perceptron


class MultiClassPerceptron(object):
    def __init__(self, eta=0.01, n_iter=10):
        self.eta = eta
        self.n_iter = n_iter

    def fit(self, X, y):
        self.classes_ = np.unique(y)
        self.classifiers_ = {}
        for cls in self.classes_:
            binary_y = np.where(y == cls, 1, -1)
            self.classifiers_[cls] = Perceptron(self.eta, self.n_iter).fit(X, binary_y)
        return self

    def predict(self, X):
        pred = np.zeros((X.shape[0], len(self.classes_)))
        for i, cls in enumerate(self.classes_):
            pred[:, i] = self.classifiers_[cls].net_input(X)
        print(pred)
        return np.argmax(pred, axis=1)

