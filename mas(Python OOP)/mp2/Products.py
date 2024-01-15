from Product import Product
from Brand import Brand
from Photo import Photo
import json

class Products:
    def __init__(self):
        self._products = []
    def addProduct(self,product:Product):
        self._products.append(product)
    def getProducts(self)->list:
        return self._products
    def setProducts(self, products:list):
        self._products = products
    def serialize(self,filename:str):
        jsonData = []
        for product in self.getProducts():
            jsonPhotos = []
            for photo in product.getBrand().getPhotos():
                jsonPhotos.append({
                    "url":photo.getUrl(),
                    "name":photo.getPhotoName()
                })
            jsonData.append({
                "price":product.getPrice(),
                "qty":product.getQty(),
                "brand":product.getBrand().getName(),
                "code":product.getCode(),
                "photos":jsonPhotos
            })
        f = open(filename,"w")
        f.write(json.dumps(jsonData))
        f.close()
    def deserialize(self,filename:str):
        f = open(filename, "r")
        jsonData = json.loads(f.read())
        products = []
        for product in jsonData:
            photos = []
            for photo in product['photos']:
                photos.append(Photo(
                    photo["url"],
                    photo["name"]
                ))
            products.append(Product(
                product["price"],
                product["qty"],
                product["code"],
                Brand(product["brand"],photos)
            ))
        f.close()
        self._products = products

    def __str__(self):
        return "Products: "+"\n".join([i.__str__() for i in self._products])