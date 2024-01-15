from itertools import groupby
from Vector import Vector

class FileReader:
    def __init__(self,filePath):
        self.__filePath = filePath
        f = open(self.__filePath, "r", encoding="utf-8")
        lines = [[j.replace("\n", "") for j in i.split(";")] for i in f.readlines() if i != "\n" and i != ""]
        output = []
        groups = []
        for i in lines:
            if i[-1] not in groups:
                groups.append(i[-1])
                output.append((i[-1], []))
            [j[1].append(Vector([float(g) for g in i[:-1]])) for j in output if j[0] == i[-1]]
        self.__mapVectors = output
    def getMapVectors(self):
        return self.__mapVectors
    def getListVectors(self):
        return sum([[(i[0], j) for j in i[1]] for i in self.getMapVectors()], [])
