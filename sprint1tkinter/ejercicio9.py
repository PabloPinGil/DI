import tkinter as tk
from tkinter import messagebox


def salir():
    root.quit()


def mostrar_acerca_de():
    messagebox.showinfo("Acerca de", "Ejemplo de mensaje.")


root = tk.Tk()
root.title("Ejercicio 9")
root.geometry("400x400")

barra_menus = tk.Menu(root)
root.config(menu=barra_menus)

menu_archivo = tk.Menu(barra_menus, tearoff=0)
menu_archivo.add_command(label="Abrir")
menu_archivo.add_command(label="Salir", command=salir)
barra_menus.add_cascade(label="Archivo", menu=menu_archivo)

menu_ayuda = tk.Menu(barra_menus, tearoff=0)
menu_ayuda.add_command(label="Acerca de", command=mostrar_acerca_de)
barra_menus.add_cascade(label="Ayuda", menu=menu_ayuda)

root.mainloop()
