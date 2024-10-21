import tkinter as tk


def insertar_texto():
    for i in range(1,100):
        texto.insert(tk.END, f"Línea {i}\n")


root = tk.Tk()
root.title("Ejercicio 10")
root.geometry("200x200")

frame = tk.Frame(root)
frame.pack(fill="both", expand=True)

texto = tk.Text(frame, wrap="none")
texto.grid(row=0, column=0, sticky="nsew")

scroll = tk.Scrollbar(frame, orient="vertical", command=texto.yview)
scroll.grid(row=0, column=1, sticky="ns")
texto.config(yscrollcommand=scroll.set)

frame.grid_rowconfigure(0, weight=1)
frame.grid_columnconfigure(0, weight=1)

insertar_texto()

root.mainloop()




