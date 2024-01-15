from keras.datasets import cifar10
from sklearn.model_selection import train_test_split
from keras.models import Sequential
from keras.layers import Conv2D, MaxPooling2D, Flatten, Dense
from matplotlib import pyplot as plt
from tensorflow.keras.utils import to_categorical


data = cifar10.load_data()

X=data[0][0].astype('float32') / 255.0
y=to_categorical(data[0][1])


#task1
# Podział danych na dane treningowe i dane testowe
x_train, x_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)



model = Sequential()
####
#model.add(Conv2D(32, (3, 3), activation='relu', input_shape=(32, 32, 3)))
#model.add(MaxPooling2D((2, 2)))
####v^
model.add(Conv2D(32, (3, 3), activation='relu', input_shape=(32, 32, 3)))
model.add(Conv2D(64, (3, 3), activation='relu'))
model.add(MaxPooling2D((2, 2)))
model.add(Conv2D(128, (3, 3), activation='relu'))
model.add(MaxPooling2D((2, 2)))
####
model.add(Flatten())
model.add(Dense(2, activation='softmax'))

model.compile(optimizer='rmsprop', loss='categorical_crossentropy', metrics=['accuracy'])

history = model.fit(x_train, y_train, epochs=20, batch_size=64, validation_data=(x_train, y_train))

model.save('miw_sXXXXX_f_{}_model_fit.h5'.format(1))




from tensorflow import keras
model = keras.models.load_model('miw_sXXXXX_f_{}_model_fit.h5'.format(1))
loss, acc = model.evaluate(x_test, y_test, verbose=0)
print('accuracy: {}'.format(acc))
print('loss: {}'.format(loss))

plt.plot(history.history['loss'])
plt.plot(history.history['val_loss'])
plt.ylabel('loss')
plt.xlabel('epoch')
plt.legend(['train', 'test'], loc='upper left')
plt.show()

plt.plot(history.history['accuracy'])
plt.plot(history.history['val_accuracy'])
plt.ylabel('accuracy')
plt.xlabel('epoch')
plt.legend(['train', 'test'], loc='upper left')
plt.show()



