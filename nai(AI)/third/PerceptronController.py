from Perceptron import Perceptron
from Vector import Vector
from random import uniform

class PerceptronController:
    def __init__(self, listVectors, a, numberIterations=1):
        self.__listVectors = listVectors
        self.__weigths = Vector([uniform(-5,5) for i in listVectors[0][0].getCoords()])
        self.__prog = uniform(-5,5)
        self.__a = a
        self.__numberIterations = numberIterations

        groups = list(set([item[1] for item in listVectors]))

        self.__perceptrons = []
        for gr in groups:
            self.__perceptrons.append(Perceptron(listVectors,Vector(self.__weigths.getCoords()),self.__prog,gr))


    def __getListVectors(self):
        return self.__listVectors
    def getPerceptrons(self):
        return self.__perceptrons
    def trainPerceptrons(self):
        [i.trainPerceptron(self.__a,self.__numberIterations) for i in self.__perceptrons]
    def testPerceptrons(self,testVector):
        return [(i.getNetFromTestPerceptron(testVector), i.getCurrentGroup()) for i in self.__perceptrons]
    def getResultGroupByTest(self,testVector):
        return sorted(self.testPerceptrons(testVector),key=lambda x:x[0])[-1][1]
        # sort and get last element(groupname)
