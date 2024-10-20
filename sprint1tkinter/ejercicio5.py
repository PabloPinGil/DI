import tkinter as tk


def cambiar_color():
    if var_color.get() == 1:
        root.config(bg="red")

    elif var_color.get() == 2:
        root.config(bg="green")

    else:
        root.config(bg="cyan")


root = tk.Tk()
root.title("Ejercicio 5")
root.geometry("300x300")

var_color = tk.IntVar()
var_color.set(0)

radio1 = tk.Radiobutton(root, text="Rojo", variable=var_color, value=1, command=cambiar_color)
radio2 = tk.Radiobutton(root, text="Verde", variable=var_color, value=2, command=cambiar_color)
radio3 = tk.Radiobutton(root, text="Azul", variable=var_color, value=3, command=cambiar_color)
radio1.pack()
radio2.pack()
radio3.pack()

root.mainloop()

