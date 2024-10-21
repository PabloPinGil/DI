import tkinter as tk


def actualizar_valor(val):
    etiqueta.config(text=f"Valor: {val}")


root = tk.Tk()
root.title("Ejercicio 11")
root.geometry("300x200")

scale = tk.Scale(root, from_=0, to=100, orient="horizontal", command=actualizar_valor)
scale.pack(pady=20)

etiqueta = tk.Label(root, text="Valor: 0")
etiqueta.pack()

root.mainloop()
