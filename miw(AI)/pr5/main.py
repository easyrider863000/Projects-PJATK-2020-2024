import numpy as np
import matplotlib.pyplot as plt
from sklearn.model_selection import train_test_split
from sklearn import datasets
from tensorflow.keras.utils import to_categorical

iris = datasets.load_iris()
X = iris.data
y = iris.target
print(y)
y=to_categorical(y)
print(y)
#norm=np.linalg.norm(x)
#x/=norm

#print(np.shape(X))
#print(np.shape(y))
# print(y)


class Sigmoid():
  def acti(self, s):
    return 1.
  def der(self, x):
    return 1.

class Neuron():
  def __init__(self, input, acti, eta):
    self.W=np.random.rand(input)
    self.Wb=np.random.rand(1)[0]
    self.acti=acti
    self.eta=eta

  def predict(self, x):
    return 1.

  def fit(self, e):
    return 1.

class Layer():
  def __init__(self, input, output, acti, eta):
    self.neurons=[]
    for i in range(output):
      self.neurons.append(Neuron(input, acti, eta))

  def predict(self, x):
    return 1.

  def fit(self, e):
    return 1.


class NeuronNetwork():
    def __init__(self, layers, acti, eta):
        self.layers = []
        for i in range(1, len(layers)):
            self.layers.append(Layer(layers[i - 1], layers[i], acti, eta))

    def predict(self, x):
        return 1.

    def fit(self, X, y, e):
        return 1.

def batch(NN, epoch=100):
  for i in range(epoch):
    e=0
    for xe, ye in zip(X, y):
      p = NN.predict(xe)
      e+=(p-ye)
    e/=len(X)
    NN.fit(X, y, e)

def online(NN, epoch=100):
  for i in range(epoch):
    for xe, ye in zip(X, y):
      e = NN.predict(xe)
      e-=ye
      NN.fit(X, y, e)

batch(NeuronNetwork([4, 10, 20, 50, 3], Sigmoid(), 0.001), 1)
online(NeuronNetwork([4, 10, 20, 50, 3], Sigmoid(), 0.001), 1)

