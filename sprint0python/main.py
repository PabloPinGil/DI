from Heroe import Heroe
from Mazmorra import Mazmorra


def main():     # instancia el héroe, la mazmorra y comienza el juego
    nombre_heroe = input("Introduce el nombre de tu héroe: ")
    heroe = Heroe(nombre_heroe)

    mazmorra = Mazmorra(heroe)
    mazmorra.jugar()


if __name__ == '__main__':
    main()
