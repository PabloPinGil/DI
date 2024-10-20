import tkinter as tk


def dibujar():
    canvas.delete("all")
    size = float(entrada1.get())
    position = float(entrada2.get())
    canvas.create_rectangle(position, 0, position+(size*2), size, fill="red")
    canvas.create_oval(position, 150, position+size, 150+size, fill="red")


root = tk.Tk()
root.title("Ejercicio 7")
root.geometry("500x400")

etiqueta = tk.Label(root, text="Introducir tamaño y después posición")
etiqueta.pack()

entrada1 = tk.Entry(root)
entrada1.pack()
entrada2 = tk.Entry(root)
entrada2.pack()
aceptar = tk.Button(root, text="Aceptar", command=dibujar)
aceptar.pack()

canvas = tk.Canvas(root, width=300, height=300, bg="white")
canvas.pack(pady=20)

root.mainloop()
