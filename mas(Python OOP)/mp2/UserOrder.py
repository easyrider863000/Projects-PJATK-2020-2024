class UserOrder:
    def __init__(self,user,order):
        self.__user = user
        self.__order = order
    def setOrder(self, order):
        if order != self.__order:
            self.__order = order
            order.addUserOrder
    def setUser(self, user):
        self.__user = user
    def getOrder(self):
        return self.__order
    def getUser(self):
        return self.__user