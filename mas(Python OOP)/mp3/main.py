from abc import ABC, abstractmethod


# Klasa abstrakcyjna
class Product(ABC):
    def __init__(self, name, price):
        self.name = name
        self.price = price

    @abstractmethod
    def get_description(self):
        pass


# Polimorficzne wołanie metod
class Clothing(Product):
    def __init__(self, name, price, size):
        super().__init__(name, price)
        self.size = size

    def get_description(self):
        return self.name+" - "+self.price+" zł - rozmiar: "+self.size


class Electronics(Product):
    def __init__(self, name, price, warranty):
        super().__init__(name, price)
        self.warranty = warranty

    def get_description(self):
        return self.name+" - "+self.price+" zł - gwarancja: "+self.warranty+" miesięcy"


class Food(Product):
    def __init__(self, name, price, expiration_date):
        super().__init__(name, price)
        self.expiration_date = expiration_date

    def get_description(self):
        return self.name+" - "+self.price+" zł - ważność do: "+self.expiration_date


# Overlapping
a = ["Hlopok","Bavovna"]
class Shirt(Clothing):
    def __init__(self,name,price,size,listOfTypes:list):
        self.listOfTypes = listOfTypes[:2]
        super().__init__(name,price,size)
    def get_description(self):
        if "Hlopok" in self.listOfTypes and "Bavovna" in self.listOfTypes:
            return self.name + " - " + self.price + " zł - rozmiar: " + self.size + " - kolor: niebieski, sostaw: 50%/50% "+str(self.listOfTypes)
        if "Hlopok" in self.listOfTypes:
            return self.name + " - " + self.price + " zł - rozmiar: " + self.size + " - kolor: niebieski, sostaw: 100% "+str(self.listOfTypes)
        if "Bavovna" in self.listOfTypes:
            return self.name + " - " + self.price + " zł - rozmiar: " + self.size + " - kolor: niebieski, sostaw: 100% "+str(self.listOfTypes)
        return self.name+" - "+self.price+" zł - rozmiar: "+self.size+" - kolor: niebieski"

#s = Shirt("shirt1","5.00$","M",a)
#print(s.get_description())




# Wielodziedziczenie
class SpecialOffer(Product):
    def __init__(self, product, discount):
        super().__init__(product.name, product.price)
        self.product = product
        self.discount = discount

    def get_description(self):
        return self.product.get_description()+" - "+self.discount+"% taniej"
    def CalcPrice(self):
        return self.product.price*self.discount


class SpecialFoodOffer(SpecialOffer, Food):
    def __init__(self, name,price,expiration_date, food, discount):
        SpecialOffer.__init__(self, food, discount)
        Food.__init__(self,name,price,expiration_date)
    def CalcPrice(self):
        return self.specialOffer.CalcPrice()
    def getDescription(self):
        return self.Food.get_description()



# Wieloaspektowe dziedziczenie
class Orderable(ABC):
    @abstractmethod
    def get_total_price(self):
        pass


class OrderItem(Orderable):
    def __init__(self, product, quantity):
        self.product = product
        self.quantity = quantity

    def get_total_price(self):
        return self.product.price * self.quantity


class Order(Orderable):
    def __init__(self, items):
        self.items = items

    def get_total_price(self):
        total = 0
        for item in self.items:
            total += item.get_total_price()
        return total


# Dynamiczne dziedziczenie
class Camera(Electronics):
    def __init__(self, name, price, warranty, memmory):
        super().__init__(name, price, warranty)
        self.memmory = memmory
    def __str__(self):
        return self.name+" ",self.price+" "+self.warranty+" "+self.memmory
class PhotoCamera(Camera):
    def __init__(self, camera:Camera, linse):
        super().__init__(camera.name, camera.price, camera.warranty, camera.memmory)
        self.linse = linse
        self.camera = camera
    def __str__(self):
        return "PhotoCamera "+self.camera.name+" "+self.linse





camera = Camera("Canon EOS M50", "3699", "24","500")
photoCamera = PhotoCamera(camera,"lite")
print(photoCamera)
