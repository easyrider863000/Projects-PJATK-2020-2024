from math import sqrt

class Vector:
    def __init__(self,coords, group):
        self.__coords = [float(i) for i in coords]
        self.__group = group
    def getCoords(self):
        return self.__coords
    def setCoords(self, coords):
        self.__coords = [float(i) for i in coords]
    def getGroup(self):
        return self.__group
    def setGroup(self,group):
        self.__group = group
    def getDistanceToVector(self,vector2):
        return sqrt(self.getDistanceToVectorWithoutSQRT(vector2))
    def getDistanceToVectorWithoutSQRT(self,vector2):
        if len(vector2.getCoords()) != len(self.getCoords()):
            return ValueError("Error object vector2")
        i = 0
        sumV = 0
        while i < len(self.getCoords()):
            sumV += pow(self.getCoords()[i] - vector2.getCoords()[i], 2)
            i += 1
        return sumV
    def dotVector(self,vector2):
        if len(vector2.getCoords())!=len(self.getCoords()):
            return ValueError("Error object vector2")
        i = 0
        result = 0.0
        while i< len(vector2.getCoords()):
            result+=(vector2.getCoords()[i]*self.getCoords()[i])
            i+=1
        return result
    def __getitem__(self, key):
        return self.getCoords()[key]
    def __setitem__(self, key, value):
        self.__coords[key] = value
    def __str__(self):
        return str(self.getCoords())


