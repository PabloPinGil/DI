import random


class Monstruo:
    def __init__(self, ataque=20, defensa=15, salud=50):
        nombres = ["Orco", "Duende", "Esqueleto"]

        self.nombre = random.choice(nombres)
        self.ataque = ataque
        self.defensa = defensa
        self.salud = salud

    def atacar(self, heroe):    # el monstruo daña al héroe
        print("El monstruo " + self.nombre + " ataca a " + heroe.nombre)
        if self.ataque > heroe.defensa:
            damage = self.ataque - heroe.defensa    # el daño que recibe el héroe es su defensa - el daño del monstruo
            heroe.salud = heroe.salud - damage

            print("El héroe " + heroe.nombre + " ha recibido " + str(damage) + " puntos de daño")
        else:
            print("El héroe ha bloqueado el ataque")        # si su defensa es superior al daño lo bloquea

    def esta_vivo(self):        # comprueba si el monstruo está vivo
        if self.salud > 0:
            return True
        else:
            return False
