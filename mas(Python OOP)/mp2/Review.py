from Product import Product

#kwalifikowana asocjacja - z klasa Product, reprezentujaca opinie o produkcie
class Review:
    def __init__(self, product: Product, rating: int, comment: str, id: str):
        self.__product = product
        self.__id = id
        self.__rating = rating
        self.__comment = comment

    def getProduct(self) -> Product:
        return self.__product

    def getRating(self) -> int:
        return self.__rating

    def getComment(self) -> str:
        return self.__comment

    def setProduct(self,product):
        self.__product = product
        product.addReview(self,self.__id)

    def removeProduct(self,product):
        self.__product = None
        product.removeReview(self.__id)

