from matplotlib.colors import ListedColormap
import matplotlib.pyplot as plt
import numpy as np
from sklearn import datasets
from sklearn import tree
from sklearn.model_selection import train_test_split
from sklearn import tree
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import accuracy_score


def plot_decision_regions(X, y, classifier, test_idx=None, resolution=0.02):

        # konfiguruje generator znaczników i mapę kolorów
        markers = ('s', 'x', 'o', '^', 'v')
        colors = ('red', 'blue', 'lightgreen', 'gray', 'cyan')
        cmap = ListedColormap(colors[:len(np.unique(y))])

        # rysuje wykres powierzchni decyzyjnej
        x1_min, x1_max = X[:, 0].min() - 1, X[:, 0].max() + 1
        x2_min, x2_max = X[:, 1].min() - 1, X[:, 1].max() + 1
        xx1, xx2 = np.meshgrid(np.arange(x1_min, x1_max, resolution), np.arange(x2_min, x2_max, resolution))
        Z = classifier.predict(np.array([xx1.ravel(), xx2.ravel()]).T)
        Z = Z.reshape(xx1.shape)
        plt.contourf(xx1, xx2, Z, alpha=0.3, cmap=cmap)
        plt.xlim(xx1.min(), xx1.max())
        plt.ylim(xx2.min(), xx2.max())

        # rysuje wykres wszystkich próbek
        for idx, cl in enumerate(np.unique(y)):
            plt.scatter(x=X[y == cl, 0], y=X[y == cl, 1], alpha=0.8, c=cmap(idx), marker=markers[idx], label=cl, edgecolor='black')



iris = datasets.load_iris()
X = iris.data[:, [1,2]]
y = iris.target
#task1
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.3, random_state=31)
"""
#task2
#clf = tree.DecisionTreeClassifier(criterion='gini')
#clf = tree.DecisionTreeClassifier(criterion='entropy')

#task3
clf = tree.DecisionTreeClassifier(max_depth=10)
#clf = tree.DecisionTreeClassifier(max_depth=3)

clf = clf.fit(X, y)
tree.plot_tree(clf)
plt.show()


"""
#task4
n_estimators_list = [1, 10, 100,1000]

for n in n_estimators_list:
    clf = RandomForestClassifier(n_estimators=n, random_state=31)
    clf.fit(X_train, y_train)
    predicted = clf.predict(X_test)
    acc = accuracy_score(y_test, predicted)
    print(n,"---",acc)
