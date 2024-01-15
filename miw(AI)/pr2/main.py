import matplotlib.pyplot as plt
from sklearn.model_selection import train_test_split
from sklearn import datasets
import numpy as np
from MultiClassLogisticRegression import MultiClassLogisticRegression
from MultiClassPerceptron import MultiClassPerceptron
from plot_decision_regions import plot_decision_regions

def main():
    # pobiera danne do uczenia i testowania
    iris = datasets.load_iris()
    X = iris.data[:, [2, 3]]
    y = iris.target
    # podział danych na testowe i treningowe
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.3, random_state=1, stratify=y)




    #task1
    classifier = MultiClassPerceptron(eta=0.05, n_iter=10000)

    #task2
    #classifier = MultiClassLogisticRegression(eta=0.01, n_iter=10000)
    # uczenie modelu
    classifier.fit(X_train, y_train)

    #task3
    #classifier.print_proba(X_test)

    # wyświetla wykres
    plot_decision_regions(X=X_train, y=y_train, classifier=classifier)
    plt.xlabel(r'$x_1$')
    plt.ylabel(r'$x_2$')
    plt.legend(loc='upper left')
    plt.show()


if __name__ == '__main__':
    main()

