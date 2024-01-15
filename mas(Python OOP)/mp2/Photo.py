class Photo:
    def __init__(self,url:str,photoName:str=None):
        self.__url = url
        self.__photoName = photoName #atrybut opcjonalny
    def getUrl(self)->str:
        return self.__url
    def getPhotoName(self)->str:
        if self.__photoName is None:
            return "photo is none"
        return self.__photoName