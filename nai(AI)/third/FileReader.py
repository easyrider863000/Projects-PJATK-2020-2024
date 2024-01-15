from Vector import Vector
from os import listdir
from os.path import isfile, join, isdir

class FileReader:
    alphabet = "qwertyuiopasdfghjklzxcvbnm"
    def __init__(self, pathToDirectory):
        pathToDirectory = pathToDirectory.replace("/","")
        listVectors = []

        directories = [f for f in listdir(pathToDirectory) if isdir(join(pathToDirectory, f))]
        for langFolder in directories:
            files = [f for f in listdir(pathToDirectory+"/"+langFolder) if isfile(join(pathToDirectory+"/"+langFolder, f))]
            for langFile in files:
                fileObj = open(pathToDirectory+"/"+langFolder+"/"+langFile, encoding="utf-8")
                fileText = "".join(["".join([ch for ch in line.lower() if ch in self.alphabet]) for line in fileObj.readlines()])

                listVectors.append((self.getVectorByText(fileText),langFolder))

        self.__listVectors = listVectors

    @staticmethod
    def getVectorByText(text):
        return Vector([text.count(ch)/len(text) for ch in FileReader.alphabet])

    def getListVectors(self):
        return self.__listVectors
