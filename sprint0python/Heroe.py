class Heroe:
    def __init__(self, nombre, ataque=20, defensa=15, salud=100):
        self.nombre = nombre
        self.ataque = ataque
        self.defensa = defensa
        self.salud = salud
        self.salud_maxima = salud

    def atacar(self, enemigo):  # el héroe ataca al enemigo, y si el ataque es mayor que su defensa le daña
        print("El héroe ataca a " + enemigo.nombre)

        if self.ataque > enemigo.defensa:
            print("El enemigo " + enemigo.nombre + " ha recibido " + str(self.ataque) + " puntos de daño.")
            enemigo.salud = enemigo.salud - self.ataque
        else:
            print("El enemigo ha bloqueado el ataque.")

    def curarse(self):  # el héroe se cura
        self.salud = self.salud + 10
        if self.salud > self.salud_maxima:      # aquí se evita que se cure por encima de su salud máxima
            self.salud = self.salud_maxima

        print("El héroe se ha curado. Salud actual: " + str(self.salud))

    def defenderse(self):        # aumenta la defensa del héroe
        self.defensa = self.defensa + 5
        print("La defensa del héroe ha aumentado temporalmente a " + str(self.defensa))

    def reset_defensa(self):    # devuelve la defensa a su valor original
        self.defensa = self.defensa - 5

    def esta_vivo(self):        # comprueba si el héroe está vivo
        if self.salud > 0:
            return True
        else:
            return False
