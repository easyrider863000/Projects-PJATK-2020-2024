from Vector import Vector
from random import uniform

class Perceptron:
    def __init__(self,mapVectors):
        #mapVectors: [group:[Vector1,Vector2,...],...]
        if len(mapVectors)!=2:
            raise ValueError("mapVector's length must be 2, not "+str(len(mapVectors)))
        self.__mapVectors = mapVectors
        self.__weigths = Vector([uniform(-5,5) for i in mapVectors[0][1][0].getCoords()])
        self.__prog = uniform(-5,5)

        self.__groups = [{"groupname":i[0],"index":0} for i in mapVectors]
        self.__groups[1]["index"] = 1
        self.__groups = self.__groups[:2]
    def getIndexGroup(self,groupName):
        return [i["index"] for i in self.__groups if i["groupname"]==groupName][0]
    def getGroups(self):
        return self.__groups
    def getMapVectors(self):
        return self.__mapVectors
    def __getListVectors(self):
        return sum([[(i[0],j) for j in i[1]] for i in self.getMapVectors()],[])

    def trainPerceptron(self, a, numberIterations=1):
        n = 0
        while n < numberIterations:
            for i in range(len(self.__getListVectors())):
                #self.__getListVectors()) --- [(group,Vector),...]
                trainVector = self.__getListVectors()[i][1]
                groupIndex = self.getIndexGroup(self.__getListVectors()[i][0])
                f = self.__weigths.dotVector(trainVector)
                y = 0.0
                if f >= self.__prog:
                    y = 1.0
                if groupIndex != int(y):
                    for j in range(len(trainVector.getCoords())):
                        self.__weigths[j] = float(self.__weigths[j])+float(a)*float(self.getIndexGroup(self.__getListVectors()[i][0])-y)*float(self.__getListVectors()[i][1][j])
                    self.__prog = self.__prog-(self.getIndexGroup(self.__getListVectors()[i][0])-y)*float(a)
                    #prog:   prog = prog-(d-y)*a
                    #wagi:   wagi = wagi+(d-y)*a*vector
            n+=1
        return self.__weigths

    def testPerceptron(self,testVector):
        # if f >= prog
        if self.__weigths.dotVector(testVector) >= self.__prog:
            return self.__groups[1]["groupname"]
        return self.__groups[0]["groupname"]

    def testPerceptronWithGroupName(self, testVector, groupname):
        return self.testPerceptron(testVector)==groupname