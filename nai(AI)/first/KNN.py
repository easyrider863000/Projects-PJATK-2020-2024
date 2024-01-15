class KNN:
    def __init__(self,mapVectors):
        self.__mapVectors = mapVectors
    def getMapVectors(self):
        return self.__mapVectors
    def __getListVectors(self):
        return sum([[(i[0],j) for j in i[1]] for i in self.getMapVectors()],[])
    def get_KNN(self,k,startVector):
        allNN = [g[0] for g in sorted([(i[0],i[1],startVector.getDistanceToVector(i[1])) for i in self.__getListVectors()],
                      key=lambda obj:obj[2])][:int(k)]
        return max(allNN, key=allNN.count)