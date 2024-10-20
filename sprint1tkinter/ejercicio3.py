import tkinter as tk


def mostrar_saludo():
    texto = entrada.get()
    saludo = tk.Label(text=f"Buenos días, {texto}")
    saludo.pack()


root = tk.Tk()
root.title("Ejercicio 3")
root.geometry("300x200")

entrada = tk.Entry(root)
entrada.pack()

boton = tk.Button(root, text="Pulsa para mostrar saludo", command=mostrar_saludo)
boton.pack(pady=10)

root.mainloop()

