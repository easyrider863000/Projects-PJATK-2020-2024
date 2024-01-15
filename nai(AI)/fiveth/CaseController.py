class CaseController:
    def __init__(self, listCases, listSetArguments, setDecisions):
        self.__listCases = listCases# = [Case(),Case(),Case(),..]
        self.__listSetArguments = listSetArguments# = [{arg1,arg2,arg3},{arg1,arg2},{arg1,arg2},{arg1,arg2,arg3}]
        self.__setDecisions = setDecisions# = {decision1,decision2,decision3}

    def getListSetArguments(self):
        return self.__listSetArguments

    def getListCases(self):
        return self.__listCases

    def getSetDecisions(self):
        return self.__setDecisions

    def __getProbabilityByArgument(self,argumentIndex,argumentValue,listWithDecision):
        countDecisions = len(listWithDecision)

        countFinded = 0

        for item in listWithDecision:
            if item.getListArguments()[argumentIndex] == argumentValue:
                countFinded+=1

        if countFinded==0:
            #Laplac'e
            countFinded = 1
            countDecisions += len(self.__listSetArguments[argumentIndex])
        return countFinded/countDecisions



    def __getProbability(self,case,decisionValue):
        listWithDecision = ([i for i in self.__listCases if i.getDecisionArgument()==decisionValue])
        countWithDecision = len(listWithDecision)

        result = countWithDecision/len(self.__listCases)

        for i in range(len(case.getListArguments())):
            result*=self.__getProbabilityByArgument(i,case.getListArguments()[i],listWithDecision)

        return result

    def getDecision(self,case):
        maxProbability = None# = naprzyklad (0.012501878287002258, 'nie')
        for item in self.__setDecisions:
            newProbability = self.__getProbability(case,item)
            if maxProbability is None or maxProbability[0]<newProbability:
                maxProbability = (newProbability,item)
        return maxProbability[1]

    def testCases(self,cases):
        for item in cases:
            self.testCase(item)

    def testCase(self,case):
        for i in range(len(self.__listSetArguments)):
            if case.getListArguments()[i] not in self.__listSetArguments[i]:
                raise ValueError("Arguments are not correct")
        print(*case.getListArguments(), self.getDecision(case),sep=',')
