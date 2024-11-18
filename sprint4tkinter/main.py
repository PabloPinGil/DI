from controlador import GameController
import tkinter as tk

if __name__ == "__main__":
    root = tk.Tk()  # Crea la ventana principal
    root.title("Juego de Memoria")  # Opcional: Configura el título de la ventana
    controller = GameController(root)  # Inicia el controlador con la instancia de root
    root.mainloop()  # Ejecuta el bucle principal de Tkinter
