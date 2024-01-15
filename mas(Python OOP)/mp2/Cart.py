from Product import Product

#kompozycja 1-* z klasa Product, reprezentujaca koszyk zakupowy uzytkownika
class Cart:
    def __init__(self):
        self.__products = []

    def getProducts(self) -> list:
        return self.__products

    def addProduct(self, product: Product):
        self.__products.append(product)

    def removeProduct(self, product: Product):
        self.__products.remove(product)