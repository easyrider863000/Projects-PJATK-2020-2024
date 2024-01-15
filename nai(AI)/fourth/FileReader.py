from Vector import Vector

class FileReader:
    def __init__(self,filePath):
        f = open(filePath, "r", encoding="utf-8")
        lines = [[j.replace("\n", "") for j in i.split(";")] for i in f.readlines() if i != "\n" and i != ""]
        self.__listVectors = [Vector(i,0) for i in lines]
    def getListVectors(self):
        return self.__listVectors
