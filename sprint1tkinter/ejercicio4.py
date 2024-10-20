import tkinter as tk


def mostrar_seleccion():
    seleccion.config(text=f"Leer: {var_leer.get()}\n"
                          f"Deporte: {var_deporte.get()}\n"
                          f"Música: {var_musica.get()}")
    seleccion.pack()


root = tk.Tk()
root.title("Ejercicio 4")
root.geometry("300x200")

var_leer = tk.BooleanVar()
var_deporte = tk.BooleanVar()
var_musica = tk.BooleanVar()

casilla1 = tk.Checkbutton(root, text="Leer", variable=var_leer, command=mostrar_seleccion)
casilla2 = tk.Checkbutton(root, text="Deporte", variable=var_deporte, command=mostrar_seleccion)
casilla3 = tk.Checkbutton(root, text="Música", variable=var_musica, command=mostrar_seleccion)
casilla1.pack()
casilla2.pack()
casilla3.pack()

seleccion = tk.Label(root)
seleccion.pack(pady=15)

root.mainloop()
