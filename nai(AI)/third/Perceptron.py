class Perceptron:
    def __init__(self,listVectors, vectorWeights, prog, currentGroup):
        self.__listVectors = listVectors
        self.__weigths = vectorWeights
        self.__prog = prog

        self.__groups = []
        for i in listVectors:
            if i[1] not in [j["groupname"] for j in self.__groups]:
                groupObj = {"groupname":i[1], "index": 1}
                if currentGroup != i[1]: # initialize other groups as 0
                    groupObj["index"] = 0
                self.__groups.append(groupObj)
        self.__currentGroup = currentGroup

    def getIndexGroup(self,groupName):
        return [i["index"] for i in self.__groups if i["groupname"]==groupName][0]
    def getGroups(self):
        return self.__groups
    def getCurrentGroup(self):
        return self.__currentGroup
    def __getListVectors(self):
        return self.__listVectors

    def trainPerceptron(self, a, numberIterations=1):
        n = 0
        while n < numberIterations:
            isTrained = True
            for i in range(len(self.__getListVectors())):
                trainVector = self.__getListVectors()[i][0]
                groupIndex = self.getIndexGroup(self.__getListVectors()[i][1])
                f = self.__weigths.dotVector(trainVector) #net
                y = 0.0
                if f >= self.__prog:
                    y = 1.0
                if groupIndex != int(y):
                    if isTrained:
                        isTrained = False
                    for j in range(len(trainVector.getCoords())):
                        self.__weigths[j] = float(self.__weigths[j])+(float(a)*float(float(self.getIndexGroup(self.__getListVectors()[i][1]))-y)*float(self.__getListVectors()[i][0][j]))
                    #self.__prog = self.__prog-((float(self.getIndexGroup(self.__getListVectors()[i][1]))-y)*float(a))
                    # prog:   prog = prog-(d-y)*a
                    # wagi:   wagi = wagi+(d-y)*a*vector
            if not isTrained:
                numberIterations+=1
            n+=1
        return self.__weigths

    def getNetFromTestPerceptron(self, testVector):
        return self.__weigths.dotVector(testVector)