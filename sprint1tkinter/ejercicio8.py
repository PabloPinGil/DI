import tkinter as tk


def mostrar_contenido():
    contenido = entrada.get()
    etiqueta2.config(text=f"Resultado: {contenido}")


def borrar_contenido():
    entrada.delete(0, tk.END)
    etiqueta2.config(text="Resultado:")


root = tk.Tk()
root.title("Ejercicio 8")
root.geometry("400x300")

frame_superior = tk.Frame(root)
frame_superior.pack(pady=10)

etiqueta1 = tk.Label(frame_superior, text="Introduce una frase:")
etiqueta1.pack()
entrada = tk.Entry(frame_superior, width=30)
entrada.pack()
etiqueta2 = tk.Label(frame_superior, text="Resultado: ")
etiqueta2.pack(pady=10)


frame_inferior = tk.Frame(root)
frame_inferior.pack(pady=10)

boton_mostrar = tk.Button(frame_inferior, text="Mostrar contenido", command=mostrar_contenido)
boton_mostrar.pack()
boton_borrar = tk.Button(frame_inferior, text="Borrar contenido", command=borrar_contenido)
boton_borrar.pack()

root.mainloop()
