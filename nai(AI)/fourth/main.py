from FileReader import FileReader
from CentroidController import CentroidController
from sys import argv

#k = int(argv[1])
#f = FileReader(argv[2])
k = 3
f = FileReader("data.csv")
cc = CentroidController(k,f.getListVectors())
cc.startCentroides()
cc.printStats()
