#-*- coding: UTF-8 -*-
from sys import argv
from FileReader import FileReader
from Vector import Vector
from KNN import KNN
from Drawer import Drawer

k = argv[1]
fileReaderTrainSet = FileReader(argv[2])
fileReaderTestSet = FileReader(argv[3])
knn = KNN(fileReaderTrainSet.getMapVectors())
[print(i[0],knn.get_KNN(k,i[1])) for i in fileReaderTestSet.getListVectors()]
successTest=len([i for i in fileReaderTestSet.getListVectors() if knn.get_KNN(k,i[1]) == i[0]])
allTest = fileReaderTestSet.getListVectors().__len__()
print("accuracy =",successTest/allTest)
d = Drawer(fileReaderTestSet.getMapVectors())
d.draw()
print("\nFor exit press x!")
while True:
    val = input("Enter vektor --> ")
    if val=="x":
        break
    try:
        print(knn.get_KNN(k,Vector(val.replace("\n","").split(";"))))
    except:
        print("Value is not vektor or have other length")





