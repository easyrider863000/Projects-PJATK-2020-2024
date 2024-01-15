from Brand import Brand

class Product:
    currencyUsd = 4.33

    def __init__(self,price:float,qty:int,code:str,brand:Brand):
        self.__price = price
        self.__qty = qty
        self.__code = code
        self.__brand = brand
        self.__orders = []
        self.__reviews = {}
    def getPrice(self)->float:
        return self.__price
    def getQty(self)->int:
        return self.__qty
    def getCode(self)->str:
        return self.__code
    def getBrand(self)->Brand:
        return self.__brand

    #metoda klasowa
    @staticmethod
    def CalcPriceFromManufacturer(deliveryPrice:float,startPrice:float,extraPercentEarning:float)->float:
        return deliveryPrice+startPrice*extraPercentEarning

    #overloading
    @staticmethod
    def CalcPrice(self, *prices)->float:
        return sum(prices)

    #overriding
    def __add__(self, other)->float:
        if isinstance(other,Product):
            return self.getPrice()+other.getPrice()
        if isinstance(other,(int,float)):
            return self.getPrice()+other
        return self.getPrice()

    def __str__(self):
        return "Product: price: "+str(self.__price)+\
               ", qty: "+str(self.__qty)+\
               ", code: "+str(self.__code)+\
               ", brand: "+self.__brand.__str__()

    def addOrder(self, order):
        if order not in self.__orders:
            self.__orders.append(order)
            order.addProduct(self)
    def removeOrder(self,order):
        if order in self.__orders:
            self.__orders.remove(order)
            order.removeProduct(self)

    def addReview(self,review,id):
        if id not in list(self.__reviews.keys()):
            self.__reviews[id] = review
            review.setProduct(self)
        else:
            print("Was not added, review-id exists")
    def removeReview(self,id):
        if id in list(self.__reviews.keys()):
            self.__reviews[id].removeProduct(self)
            del self.__reviews[id]
        else:
            print("Was not deleted, review-id does not exist")
    def findReviewById(self,id):
        if id not in list(self.__reviews.keys()):
            return None
        return self.__reviews[id]