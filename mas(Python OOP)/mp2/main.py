from Products import Products
from Product import Product
from Brand import Brand
from Photo import Photo

#ps = Products()
#ps.addProduct(Product(12,2,"qwe",Brand("asd",[])))
#ps.addProduct(Product(12.67,1,"qwe2",Brand("asd6",[])))
#ps.addProduct(Product(112,23,"qwe1",Brand("asd5",[])))
#ps.serialize("save.json")

#ps = Products()
#ps.deserialize("save.json")
#print(ps)

b = Brand("apple",[Photo("sdsd/d/apple.com/s","23"),Photo("eeamazon.com/1","25"),Photo("2","12"),Photo("1","25")])
print(b.countPhotosInBlackList)