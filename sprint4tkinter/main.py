import tkinter as tk
from controlador import GameController
from modelo import GameModel


def main():
    # Inicializar la ventana principal de Tkinter
    root = tk.Tk()
    root.title("Juego de Memoria")
    root.geometry("800x600")

    # Configurar el modelo del juego
    dificultad_inicial = "medio"
    nombre_jugador = "Jugador 1"
    model = GameModel(difficulty=dificultad_inicial, player_name=nombre_jugador)

    # Configurar el controlador
    controller = GameController(root)

    # Iniciar el bucle principal de Tkinter
    root.mainloop()


if __name__ == "__main__":
    main()