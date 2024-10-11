import random


class Monstruo:
    def __init__(self, ataque=20, defensa=15, salud=50):
        nombres = ["Orco", "Duende", "Esqueleto"]

        self.nombre = random.choice(nombres)
        self.ataque = ataque
        self.defensa = defensa
        self.salud = salud

    def atacar(self, heroe):
        print("El monstruo " + self.nombre + " ataca a " + heroe.nombre)
        if self.ataque > heroe.defensa:
            damage = self.ataque - heroe.defensa
            heroe.salud = heroe.salud - damage

            print("El héroe " + heroe.nombre + " ha recibido " + damage + " puntos de daño")
        else:
            print("El héroe ha bloqueado el ataque")

    def esta_vivo(self):
        if self.salud > 0:
            return True
        else:
            return False
