from Monstruo import Monstruo
from Tesoro import Tesoro


class Mazmorra:
    def __init__(self, heroe):
        self.heroe = heroe
        self.tesoro = Tesoro()
        self.monstruos = []

        for i in range(3):  # llena la lista con monstruos
            nuevo_monstruo = Monstruo()
            self.monstruos.append(nuevo_monstruo)

    def jugar(self):  # método principal
        ronda = 0
        victoria = True

        print("\nEl héroe entra en la mazmorra")

        while ronda < len(self.monstruos):  # se ejecuta hasta matar a todos los enemigos o hasta que muera el héroe

            print("\nTe has encontrado con un " + self.monstruos[ronda].nombre)

            while self.heroe.esta_vivo() and self.monstruos[ronda].esta_vivo():  # el combate dura hasta que
                opcion_combate = self.enfrentar_enemigo(self.monstruos[ronda])  # uno de los dos muere

                if self.monstruos[ronda].esta_vivo():
                    self.monstruos[ronda].atacar(self.heroe)
                else:
                    print("El monstruo ha sido derrotado")

                if opcion_combate == 2:
                    self.heroe.reset_defensa()

            if not self.heroe.esta_vivo:
                ronda = len(self.monstruos) + 1
                victoria = False
            else:
                self.buscar_tesoro()

            ronda += 1

        if victoria:
            print(f"\n¡{self.heroe.nombre} ha derrotado a todos los monstruos y ha conquistado la mazmorra!")
        else:
            print("\nEl héroe ha sido derrotado en la mazmorra.")

    def enfrentar_enemigo(self, enemigo):
        fin = False
        opcion = 0

        while not fin:
            print("\n¿Que deseas hacer?"
                  "\n1. Atacar"
                  "\n2. Defender"
                  "\n3. Curarse")
            opcion = int(input("Elige una opción: "))

            if opcion == 1:
                self.heroe.atacar(enemigo)
                fin = True

            elif opcion == 2:
                self.heroe.defenderse()
                fin = True

            elif opcion == 3:
                self.heroe.curarse()
                fin = True

            else:
                print("Opción no válida")

        return opcion

    def buscar_tesoro(self):
        print("\nBuscando tesoro...")
        self.tesoro.encontrar_tesoro(self.heroe)
