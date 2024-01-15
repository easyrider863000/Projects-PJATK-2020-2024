from Case import Case

class FileReader:
    def __init__(self, filePath, isTrainSet=True):
        f = open(filePath, "r", encoding="utf-8")
        lines = [[j.replace("\n", "") for j in i.split(",")] for i in f.readlines() if i != "\n" and i != ""]

        if not isTrainSet:
            [i.append("") for i in lines]
        cases = []
        listArgs = []
        for i in range(len(lines[0])):
            args = set()
            for item in lines:
                args.add(item[i])
            listArgs.append(args)

        for item in lines:
            cases.append(Case(item[:-1],item[-1]))

        self.__cases = cases
        self.__listSetArguments = listArgs[:-1]
        self.__setDecisions = listArgs[-1]

    def getListCases(self):
        return self.__cases

    def getListSetArguments(self):
        return self.__listSetArguments

    def getSetDecisions(self):
        return self.__setDecisions
