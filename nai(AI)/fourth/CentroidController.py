from Vector import Vector
from random import sample
import matplotlib.pyplot as plt

class CentroidController:
    def __init__(self, k, listVectors):
        self.__E = -1
        self.__listVectors = listVectors
        self.__groupId = 0
        self.__centroides = [Vector(i.getCoords(),self.__getNextGroup()) for i in sample(listVectors,k=k)]#sample: get k unique random elements from list

    def getCentroides(self):
        return self.__centroides

    def startCentroides(self):
        while True:
            self.printStats()
            self.__updateVectorGroups()
            self.__updateCentroides()

            ##drawer
            #x = [i.getCoords()[0] for i in self.__listVectors]
            #y = [i.getCoords()[1] for i in self.__listVectors]
            #x.extend([i.getCoords()[0] for i in self.__centroides])
            #y.extend([i.getCoords()[1] for i in self.__centroides])
            #colors = [i.getGroup() for i in self.__listVectors]
            #colors.extend([len(self.__getGroups())+1 for i in self.__centroides])
            #area = 100
            #plt.scatter(x, y, s=area, c=colors, alpha=0.5)
            #plt.show()

            if not self.__isChangedE():
                break

    def __updateVectorGroups(self):
        for vector in self.__listVectors:
            minDistance = None
            for centroid in self.__centroides:
                newDistance = (vector.getDistanceToVectorWithoutSQRT(centroid),centroid.getGroup())
                if minDistance is None or minDistance[0]>newDistance[0]:
                    minDistance = newDistance
            vector.setGroup(minDistance[1])

    def __updateCentroides(self):
        for centroid in self.__centroides:
            group = centroid.getGroup()
            vectorsWithGroup = [i for i in self.__listVectors if i.getGroup()==group]
            newCoords = []
            for coordI in range(len(self.__listVectors[0].getCoords())):
                sum = 0.0
                for coord in vectorsWithGroup:
                    sum += coord[coordI]
                newCoords.append(sum/len(vectorsWithGroup))
            centroid.setCoords(newCoords)

    def __isChangedE(self):
        newE = sum([i.getDistanceToVectorWithoutSQRT([j for j in self.__centroides if j.getGroup()==i.getGroup()][0]) for i in self.__listVectors])
        if newE!=self.__E:
            self.__E = newE
            return True
        return False

    def printStats(self):
        mapGroups = list(map(lambda x:x.getGroup(),self.__listVectors))
        for item in self.__getGroups():
            print("Group",item,"=",len([i for i in mapGroups if i==item]))
        print(self.__E)

    def __getNextGroup(self):
        self.__groupId += 1
        return self.__groupId

    def __getGroups(self):
       return set(map(lambda x: x.getGroup(), self.__listVectors))


