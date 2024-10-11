import random


class Tesoro:
    def __init__(self, ataque=5, defensa=5, salud=10):
        self.ataque = ataque
        self.defensa = defensa
        self.salud = salud

    def encontrar_tesoro(self, heroe):      # modifica un stat del héroe de forma aleatoria
        opcion = random.randint(1, 3)

        if opcion == 1:
            print("\nEl héroe ha encontrado un tesoro: ataque")

            heroe.ataque = heroe.ataque + self.ataque

            print("El ataque de " + heroe.nombre + "ha aumentado a " + str(heroe.ataque))

        elif opcion == 2:
            print("El héroe ha encontrado un tesoro: defensa")

            heroe.defensa = heroe.defensa + self.defensa

            print("La defensa de " + heroe.nombre + "ha aumentado a " + str(heroe.defensa))

        else:
            print("El héroe ha encontrado un tesoro: salud")

            heroe.salud = heroe.salud + self.salud
            if heroe.salud > heroe.salud_maxima:
                heroe.salud = heroe.salud_maxima

            print("La salud de " + heroe.nombre + "ha aumentado a " + str(heroe.salud))
