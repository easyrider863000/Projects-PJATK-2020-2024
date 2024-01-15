#-*- coding: UTF-8 -*-
from sys import argv
from FileReader import FileReader
from PerceptronController import PerceptronController

a = float(argv[1])
folder_langs = str(argv[2])
fileReader = FileReader(folder_langs)
controller = PerceptronController(fileReader.getListVectors(),a)
controller.trainPerceptrons()
print("english test")
print("result =",controller.getResultGroupByTest(FileReader.getVectorByText("The self-study lessons in this section are written and organised according to the levels of the Common European Framework of Reference for languages (CEFR). There are different types of texts and interactive exercises that practise the reading skills you need to do well in your studies, to get ahead at work and to communicate in English in your free time.")))
print("\npolish test")
print("result =",controller.getResultGroupByTest(FileReader.getVectorByText("Żeby móc czytać hiszpańskie książki bez pomocy słownika nie potrzeba wcale lat nauki. Udowodnimy Ci to. W każdym rozdziale, oprócz naszego zespołu redaktorskiego, Twoimi nauczycielami będą wybitni pisarze i stworzone przez nich postacie. Z naszą niewielką pomocą będziesz w stanie przeczytać i zrozumieć fragmenty książek lub zaadaptowane teksty.")))
print("\nspanish test")
print("result =",controller.getResultGroupByTest(FileReader.getVectorByText("El extensísimo Diccionario PONS español-polaco te ofrece más de 300.000 términos, frases hechas, giros idiomáticos y traducciones del español al polaco y del polaco al español. Gracias a la revisión constante del corpus del Diccionario por lexicógrafos profesionales podemos garantizar una alta calidad. Todos los contenidos del Diccionario han sido completamente revisados por los expertos de nuestra redacción. Abarca desde el lenguaje coloquial a la terminología técnica teniendo en cuenta las variantes regionales de ambas lenguas. Cada usuario puede ampliar el diccionario creando sus propios artículos. El Diccionario en línea es ideal tanto para su uso en el colegio e instituto como en el ámbito profesional, ya que el contenido cubre todos los campos terminológicos y está inmejorablemente estructurado. Si tienes cualquier sugerencia acerca de nuestro ")))

print("\nFor exit press x!")
while True:
    val = input("Enter text --> ")
    if val=="x":
        break
    try:
        print("result =",controller.getResultGroupByTest(FileReader.getVectorByText(val)))
    except:
        print("Value is invalid")