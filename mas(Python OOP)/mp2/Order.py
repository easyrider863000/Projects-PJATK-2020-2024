from Product import Product

#zwykla asocjacja *-* z klasa Product
class Order:
    def __init__(self):
        self.__products = []
        self.__userOrder = None

    def getProducts(self) -> list:
        return self.__products

    def addProduct(self, product: Product):
        if product not in self.__products:
            self.__products.append(product)
            product.addOrder(self)

    def removeProduct(self, product: Product):
        if product in self.__products:
            self.__products.remove(product)
            product.removeOrder(self)

    def addUserOrder(self, userOrder):
        if self.__userOrder is None or userOrder != self.__userOrder:
            self.__userOrder = userOrder
            userOrder.setOrder(self)

    def removeUserOrder(self,userOrder):
        if self.__userOrder is not None and userOrder == self.__userOrder:
            self.__userOrder = None
            userOrder.setUser(None)