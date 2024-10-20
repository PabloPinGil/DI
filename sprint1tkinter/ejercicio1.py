import tkinter as tk


def cambiar_texto():
    etiqueta3.config(text="Texto cambiado")


root = tk.Tk()
root.title("Ejercicio 1")
root.geometry("300x200")

etiqueta1 = tk.Label(root, text="Bienvenido")
etiqueta2 = tk.Label(root, text="Pablo Piñeiro Gil")
etiqueta3 = tk.Label(root, text="Presiona el botón para cambiar el texto")
etiqueta1.pack()
etiqueta2.pack()
etiqueta3.pack()

boton = tk.Button(root, text="Click aquí", command=cambiar_texto)
boton.pack()

root.mainloop()
