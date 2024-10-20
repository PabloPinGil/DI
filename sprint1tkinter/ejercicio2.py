import tkinter as tk


def mostrar_etiqueta():
    etiqueta = tk.Label(root, text="Etiqueta mostrada")
    etiqueta.pack()


def cerrar_ventana():
    root.destroy()


root = tk.Tk()
root.title("Ejercicio 2")
root.geometry("300x200")

boton1 = tk.Button(root, text="Presiona para mostrar etiqueta", command=mostrar_etiqueta)
boton2 = tk.Button(root, text="Presiona para cerrar la ventana", command=cerrar_ventana)
boton1.pack()
boton2.pack()

root.mainloop()
