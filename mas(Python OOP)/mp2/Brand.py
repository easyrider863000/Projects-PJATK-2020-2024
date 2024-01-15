from Photo import Photo

class Brand:
    def __init__(self,name:str,photos:list):
        self.__name = name
        self.__photos = photos#atr powtarzalny
    def getName(self):
        return self.__name
    def addPhoto(self,photo:Photo):
        self.__photos.append(photo)
    def removePhoto(self,photo:Photo):
        self.__photos.remove(photo)
    def getPhotos(self)->list:
        return self.__photos

    #atrybut pochodny
    @property
    def countPhotosInBlackList(self)->int:
        blackListPhotos = ["apple.com", "amazon.com"]
        sum = 0
        for ph in self.__photos:
            for blackWord in blackListPhotos:
                if blackWord in ph.getUrl():
                    sum+=1
        return sum

    def __str__(self):
        return "Brand: name: "+self.__name+", photos: "+",".join([i.getPhotoName() for i in self.__photos])