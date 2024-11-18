from controlador import GameController
import tkinter as tk

if __name__ == "__main__":
    root = tk.Tk()
    root.title("Juego de Memoria")
    controller = GameController(root)
    root.mainloop()
