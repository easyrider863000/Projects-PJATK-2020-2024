import pandas as pd
import numpy as np
from matplotlib import pyplot as plt


def isWin(myChoice:str,opponentChoice:str)->int:
    if myChoice==opponentChoice:
        return 0
    if myChoice=="k":
        if opponentChoice=="n":
            return 1
        return -1
    if myChoice=="n":
        if opponentChoice=="p":
            return 1
        return -1
    if myChoice=="p":
        if opponentChoice == "k":
            return 1
        return -1

def generateChoice(p:list)->str:
    if len(p)!=3:
        return ValueError()
    s = sum(p)
    if s==0:
        p = [1,1,1]
        s = 3
    return np.random.choice(['k', 'n', 'p'], p=[p[0]/s, p[1]/s, p[2]/s])

def generateChoices(length:int)->list:
    return [generateChoice([1,1,1]) for i in range(length)]

def calcComputerChoice(df:pd.DataFrame,prevChoice:str)->str:
    if prevChoice=="k":
        ind = 0
    elif prevChoice=="n":
        ind = 1
    elif prevChoice=="p":
        ind = 2
    else:
        return ValueError()
    return generateChoice(list(df.iloc[ind]))

def getUpdatedDf(df:pd.DataFrame,prevChoice:str,currChoice:str)->pd.DataFrame:
    if prevChoice not in ['k','n','p'] or currChoice not in ['k','n','p']:
        return ValueError()

    if prevChoice == "k":
        ind = 0
    elif prevChoice == "n":
        ind = 1
    else:
        ind = 2

    if currChoice=="k":
        df["next_K"][ind]+=1
    elif currChoice=="n":
        df["next_N"][ind]+=1
    else:
        df["next_P"][ind]+=1

    return df

n_Games = 10
df = pd.DataFrame()
df["next_K"] = [1,1,1]
df["next_N"] = [1,1,1]
df["next_P"] = [1,1,1]
myChoices = generateChoices(n_Games)
previousComputerChoice = None
score = 0
scores = []
for j in range(2000):
    for i in range(n_Games):
        if previousComputerChoice is None:
            computerChoice = generateChoice([1,1,1])
        else:
            computerChoice = calcComputerChoice(df,previousComputerChoice)
            df = getUpdatedDf(df,previousComputerChoice,computerChoice)
        score += isWin(computerChoice,myChoices[i])
        previousComputerChoice = computerChoice
        scores.append(score)
print(df)


plt.plot(range(len(scores)),scores)
plt.show()

