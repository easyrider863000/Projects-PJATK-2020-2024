from Order import Order

#atrybutowa asocjacja z klasą Order przez UserOrder
class User:
    def __init__(self, name: str):
        self.__name = name
        self.__userOrders = []

    def getName(self) -> str:
        return self.__name

    def addUserOrder(self, userOrder):
        if userOrder not in self.__userOrders:
            self.__userOrders.append(userOrder)
            userOrder.setUser(self)
    def removeUserOrder(self, userOrder):
        if userOrder in self.__userOrders:
            self.__userOrders.remove(userOrder)
            userOrder.setUser(None)
