import tkinter as tk
from tkinter import messagebox


def leer_datos():
    nombre = entry.get()
    edad = scale.get()
    genero = var_genero.get()
    listbox.insert(tk.END, f"{nombre}, {edad}, {genero}")


def borrar_usuario():
    selected_index = listbox.curselection()[0]
    listbox.delete(selected_index)


def mensaje_guardado():
    messagebox.showinfo("Guardar lista", "Lista guardada")


def mensaje_cargado():
    messagebox.showinfo("Cargar lista", "Lista cargada")


root = tk.Tk()
root.title("Ejercicio 12")
root.geometry("300x400")

etiqueta_entry = tk.Label(root, text="Nombre:")
etiqueta_entry.grid(row=0, column=0, padx=10)
entry = tk.Entry(root)
entry.grid(row=0, column=1)

etiqueta_edad = tk.Label(root, text="Edad: ")
etiqueta_edad.grid(row=1, column=0, padx=10)
scale = tk.Scale(root, from_=0, to=100, orient="horizontal")
scale.grid(row=1, column=1, padx=10)

var_genero = tk.StringVar()
etiqueta_genero = tk.Label(root, text="Género: ")
radio_h = tk.Radiobutton(root, text="Masculino", variable=var_genero, value="Hombre")
radio_m = tk.Radiobutton(root, text="Femenino", variable=var_genero, value="Mujer")
radio_o = tk.Radiobutton(root, text="Otro", variable=var_genero, value="Otro")
etiqueta_genero.grid(row=2, column=0, padx=10)
radio_h.grid(row=2, column=1)
radio_m.grid(row=3, column=1)
radio_o.grid(row=4, column=1)

boton_add = tk.Button(root, text="Añadir usuario", command=leer_datos)
boton_add.grid(row=5, columnspan=2, pady=(1, 15))

listbox = tk.Listbox(root, selectmode=tk.SINGLE, height=6)
listbox.grid(row=6, columnspan=2, column=0, sticky="nsew")

boton_borrar = tk.Button(root, text="Borrar usuario", command=borrar_usuario)
boton_borrar.grid(row=7, columnspan=2)

scroll = tk.Scrollbar(root, orient="vertical", command=listbox.yview)
scroll.grid(row=6, column=2, sticky="nsw")
listbox.config(yscrollcommand=scroll.set)

boton_salir = tk.Button(root, text="Salir del programa", command=root.destroy)
boton_salir.grid(row=8, columnspan=2, pady=15)

menu = tk.Menu(root)
root.config(menu=menu)
menu.add_command(label="Guardar lista", command=mensaje_guardado)
menu.add_command(label="Cargar lista", command=mensaje_cargado)

root.mainloop()


