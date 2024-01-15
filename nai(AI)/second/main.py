#-*- coding: UTF-8 -*-
from sys import argv
from FileReader import FileReader
from Vector import Vector
from Perceptron import Perceptron

a = argv[1]
fileReaderTrainSet = FileReader(argv[2])
fileReaderTestSet = FileReader(argv[3])

perceptron = Perceptron(fileReaderTrainSet.getMapVectors())
output_arr = []
output = perceptron.trainPerceptron(a,1)

result = []
for i in fileReaderTestSet.getListVectors():
    result.append((i[0],perceptron.testPerceptronWithGroupName(i[1],i[0])))
#accuracy = success/all
print("accuracy =",len([i for i in result if i[1]])/len(fileReaderTestSet.getListVectors()))
print("accuracy for "+perceptron.getGroups()[0]["groupname"]+" =",len([i for i in result if perceptron.getGroups()[0]["groupname"]==i[0] and i[1]])/len([i for i in result if perceptron.getGroups()[0]["groupname"]==i[0]]))
print("accuracy for "+perceptron.getGroups()[1]["groupname"]+" =",len([i for i in result if perceptron.getGroups()[1]["groupname"]==i[0] and i[1]])/len([i for i in result if perceptron.getGroups()[1]["groupname"]==i[0]]))

while True:
    val = input("Train again? Press 'y' or 'n' (yes/no) --> ")
    if val == "n":
        break
    elif val == "y":
        perceptron.trainPerceptron(a, 1)
        result = []
        for i in fileReaderTestSet.getListVectors():
            result.append((i[0], perceptron.testPerceptronWithGroupName(i[1], i[0])))
        print("accuracy =", len([i for i in result if i]) / len(fileReaderTestSet.getListVectors()))
        print("accuracy for " + perceptron.getGroups()[0]["groupname"] + " =",len([i for i in result if perceptron.getGroups()[0]["groupname"] == i[0] and i[1]]) / len([i for i in result if perceptron.getGroups()[0]["groupname"] == i[0]]))
        print("accuracy for " + perceptron.getGroups()[1]["groupname"] + " =",len([i for i in result if perceptron.getGroups()[1]["groupname"] == i[0] and i[1]]) / len([i for i in result if perceptron.getGroups()[1]["groupname"] == i[0]]))

print("\nFor exit press x!")
while True:
    val = input("Enter vektor --> ")
    if val=="x":
        break
    try:
        print(perceptron.testPerceptron(Vector(val.replace("\n","").split(";"))))
    except:
        print("Value is not vektor or have other length")