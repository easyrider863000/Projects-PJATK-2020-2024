import abc


class Observer(metaclass=abc.ABCMeta):
    @abc.abstractmethod
    def update(self):
        pass


class Book:
    def __init__(self, author, datePublish, countPages):
        self.author = author
        self.datePublish = datePublish
        self.countPages = countPages
        self.observers = []

    def add_observer(self, observer):
        self.observers.append(observer)

    def remove_observer(self, observer):
        self.observers.remove(observer)

    def notify_observers(self):
        for observer in self.observers:
            observer.update()

    def addBook(self, author, datePublish, countPages):
        self.author = author
        self.datePublish = datePublish
        self.countPages = countPages
        self.notify_observers()


class Shop(Observer):
    def __init__(self, addres, email, name):
        self.addres = addres
        self.email = email
        self.name = name

    def update(self):
        self.updateDatabase()

    def updateDatabase(self):
        print('Updating the database...')


book = Book('John Doe', '01-01-2020', 250)
shop1 = Shop('Street 1', 'shop1@email.com', 'Shop 1')
shop2 = Shop('Street 2', 'shop2@email.com', 'Shop 2')

book.add_observer(shop1)
book.add_observer(shop2)

book.addBook('Jane Doe', '01-02-2020', 300)
