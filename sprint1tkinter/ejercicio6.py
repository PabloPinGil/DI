import tkinter as tk


def mostrar_seleccion():
    seleccion = listbox.curselection()
    elemento = listbox.get(seleccion[0])
    etiqueta.config(text=f"Seleccionaste: {elemento}")


root = tk.Tk()
root.title("Ejercicio 6")
root.geometry("300x300")

opciones = ["Manzana", "Banana", "Naranja"]

listbox = tk.Listbox(root, selectmode=tk.SINGLE, height=3)
for opcion in opciones:
    listbox.insert(tk.END, opcion)
listbox.pack(pady=15)

boton = tk.Button(root, text="Mostrar fruta seleccionada: ", command=mostrar_seleccion)
boton.pack()

etiqueta = tk.Label(root, text="Nada seleccionado")
etiqueta.pack()

root.mainloop()
