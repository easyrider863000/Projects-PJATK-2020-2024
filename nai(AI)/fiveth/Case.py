class Case:
    def __init__(self,listArguments,decisionArgument):
        self.__listArguments = listArguments# = [zachmurzenie,tak,srednia,niska]
        self.__decisionArgument = decisionArgument# = tak

    def getDecisionArgument(self):
        return self.__decisionArgument

    def getListArguments(self):
        return self.__listArguments

    @staticmethod
    def generateCase(params,decisionArg):
        return Case(params,decisionArg)



