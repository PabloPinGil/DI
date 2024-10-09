class Monstruo:
    def __init__(self, nombre, ataque, defensa, salud):
        self.nombre = nombre
        self.ataque = ataque
        self.defensa = defensa
        self.salud = salud

    def atacar(self, heroe):
        print("El monstruo " + self.nombre + " ataca a " + heroe.nombre)
        if self.ataque > heroe.defensa:
            damage = self.ataque - heroe.defensa
            print("El héroe " + heroe.nombre + " ha recibido " + damage + " puntos de daño")
        else:
            print("El héroe ha bloqueado el ataque")

    def esta_vivo(self):
        if self.salud > 0:
            return True
        else:
            return False
