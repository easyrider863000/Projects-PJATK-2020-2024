import matplotlib.pyplot as plt

class Drawer:
    def __init__(self, mapVectors):
        j=0
        self.__listTypes = []
        while j<len(mapVectors):
            self.__listTypes.append({"title": mapVectors[j][0], "number": j})
            j+=1
        self.__mapVectors = mapVectors
    def getMapVectors(self):
        return self.__mapVectors
    def __getNumberByName(self, name):
        return [i['number'] for i in self.__listTypes if i['title'] == name][0]
    def __getListVectors(self):
        return sum([[(i[0],j,self.__getNumberByName(i[0])) for j in i[1]] for i in self.getMapVectors()],[])
    def drawline(self,vector2,x1=0.0,y1=0.0):
        plt.plot(x1, y1, vector2.getCoords()[0],vector2.getCoords()[1], marker='o')
    def draw(self):
        x = [i[1].getCoords()[0] for i in self.__getListVectors()]
        y = [i[1].getCoords()[1] for i in self.__getListVectors()]
        colors = [i[2] for i in self.__getListVectors()]
        area = 100
        plt.scatter(x, y, s=area, c=colors, alpha=0.5)
    def show(self):
        plt.show()