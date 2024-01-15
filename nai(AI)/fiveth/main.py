from FileReader import FileReader
from CaseController import CaseController
from Case import Case
from sys import argv


#trainset = argv[1]
#testset = argv[2]

trainset = "trainingset.csv"
testset = "testset.csv"

f = FileReader(trainset)
cc = CaseController(f.getListCases(),f.getListSetArguments(),f.getSetDecisions())
f2 = FileReader(testset,False)
cc.testCases(f2.getListCases())

print("\nFor exit press x!")
while True:
    val = input("Enter data --> ")
    if val=="x":
        break
    try:
        cc.testCase(Case.generateCase(val.replace("\n","").split(","), ""))
    except ValueError:
        print("****Error****\nArguments have not similar params!\n")
    except IndexError:
        print("****Error****\nSuch count of items not required!\n")
