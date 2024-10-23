import tkinter as tk


def seleccionar_modo():     # aquí elegiremos si van a jugar 1 o 2 personas
    frame_seleccion.pack()

    etiqueta_modo = tk.Label(frame_seleccion, text="Selecciona un modo:")
    etiqueta_modo.pack()
    boton1 = tk.Button(frame_seleccion, text="1 jugador", command=un_jugador)
    boton2 = tk.Button(frame_seleccion, text="2 jugadores", command=dos_jugadores)
    boton1.pack(pady=(10, 0))
    boton2.pack()


def un_jugador():           # empieza la partida para un jugador
    pass


def dos_jugadores():        # empieza la partida para dos jugadores
    pass


# inicializamos la ventana y creamos el primer frame
root = tk.Tk()
root.title("Piedra, papel, tijeras")
root.geometry("500x500")
frame_seleccion = tk.Frame(root)

seleccionar_modo()

root.mainloop()
