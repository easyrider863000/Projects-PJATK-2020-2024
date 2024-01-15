#ograniczenie atrybutu
class BankAccount:
    def __init__(self, account_number, name):
        self._account_number = account_number
        self.name = name

    @property
    def account_number(self):
        return self._account_number

    @account_number.setter
    def account_number(self, value):
        raise AttributeError("Cannot modify account number.")

    def __str__(self):
        return self.name+" "+self._account_number

#account = BankAccount("123456789","Vasya")
#print(account.account_number)
#account.account_number = "987654321"


#unique
class ShoppingCart:
    def __init__(self):
        self._items = set()

    def add_item(self, item):
        if item not in self._items:
            self._items.add(item)
    def __str__(self):
        return ",".join(self._items)

#cart = ShoppingCart()
#cart.add_item("Apple")
#cart.add_item("Orange")
#cart.add_item("Apple")
#print(cart)

#ograniczenie subset
class Animal:
    def __init__(self, name):
        self.name = name

class Dog(Animal):
    def __init__(self, name):
        super().__init__(name)

class Cat(Animal):
    def __init__(self, name):
        super().__init__(name)

class Zoo:
    def __init__(self):
        self.animals = []

    def add_animal(self, animal):
        if isinstance(animal, Animal):
            self.animals.append(animal)

    def __str__(self):
        return ",".join([i.name for i in self.animals])

#zoo = Zoo()
#dog = Dog("Buddy")
#cat = Cat("Whiskers")
#zoo.add_animal(dog)
#zoo.add_animal(cat)
#zoo.add_animal("Lion")
#print(zoo)




#ograniczenie ordered
from collections import OrderedDict

class Playlist:
    def __init__(self):
        self.songs = OrderedDict()

    def add_song(self, title, artist):
        self.songs[title] = artist

    def play(self):
        for title, artist in self.songs.items():
            print("Now playing: "+title+" by "+artist)


#playlist = Playlist()
#playlist.add_song("Song 1", "Artist 1")
#playlist.add_song("Song 3", "Artist 1")
#playlist.add_song("Song 2", "Artist 3")
#playlist.play()



#ograniczenie bag
from collections import Counter

class ShoppingCart2:
    def __init__(self):
        self._items = []

    def add_item(self, item):
        self._items.append(item)

    def get_item_count(self):
        return Counter(self._items)

#cart = ShoppingCart2()
#cart.add_item("Apple")
#cart.add_item("Orange")
#cart.add_item("Apple")
#print(cart.get_item_count())



#ograniczenie xor
class LightSwitch:
    def __init__(self):
        self._is_on = False

    def toggle(self):
        self._is_on = not self._is_on

class Room:
    def __init__(self):
        self.light_switch = LightSwitch()
        self.light_bulb = LightBulb()

    def toggle_light(self):
        self.light_switch.toggle()
        self.light_bulb.update_state(self.light_switch._is_on)

class LightBulb:
    def __init__(self):
        self._is_on = False

    def update_state(self, is_on):
        self._is_on = is_on


#room = Room()
#print(room.light_bulb._is_on)
#room.toggle_light()
#print(room.light_bulb._is_on)
#room.toggle_light()
#print(room.light_bulb._is_on)



#ograniczenie wlasne
class Patient:
    def __init__(self, name, temperature):
        if temperature <32 or temperature >42:
            raise ValueError("Temperature is invalid")
        self._temperature = temperature
        self.name = name

    def get_temperature(self):
        return self._temperature

    def set_temperature(self, temperature):
        if temperature <32 or temperature >42:
            raise ValueError("Temperature is invalid")
        self._temperature = temperature

    def __str__(self):
        return self.name+" "+str(self._temperature)

#p = Patient("Vasya",39.2)
#print(p)
#p.set_temperature(35.4)
#p.set_temperature(53.2)

