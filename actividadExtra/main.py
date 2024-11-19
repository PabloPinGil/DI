import tkinter as tk
from tkinter import messagebox
import random


def seleccionar_modo():  # Elige si van a jugar 1 o 2 personas
    frame_seleccion.pack()

    etiqueta_modo = tk.Label(frame_seleccion, text="Selecciona un modo:")
    etiqueta_modo.pack()
    boton1 = tk.Button(frame_seleccion, text="1 jugador", command=un_jugador)
    boton2 = tk.Button(frame_seleccion, text="2 jugadores", command=dos_jugadores)
    boton1.pack(pady=(10, 0))
    boton2.pack()


def un_jugador():  # Empieza la partida para un jugador
    frame_seleccion.pack_forget()
    frame = tk.Frame(root)
    frame.pack()
    etiqueta_resultado = tk.Label(frame)

    victorias_jugador = 0
    victorias_cpu = 0

    etiqueta1 = tk.Label(frame, text="Elige: piedra, papel o tijeras")
    etiqueta1.pack()

    entry = tk.Entry(frame)
    global eleccion_j1
    boton_aceptar = tk.Button(frame, text="Aceptar", command=lambda: procesar_entrada(entry.get()))
    entry.pack()
    boton_aceptar.pack()

    eleccion_cpu = random.choice(["piedra", "papel", "tijeras"])
    if eleccion_j1 is None:
        pass
    elif eleccion_j1 == "error":
        messagebox.showinfo("Error", "Opción no válida, vuelve a introducirla")
    elif eleccion_j1 == eleccion_cpu:
        etiqueta_resultado.config(text=f"El jugador uno saca {eleccion_j1},"
                                       f" la CPU saca {eleccion_cpu}. Empate")
    elif ((eleccion_j1 == "piedra" and eleccion_cpu == "tijeras") or
          (eleccion_j1 == "papel" and eleccion_cpu == "piedra") or
          (eleccion_j1 == "tijeras" and eleccion_cpu == "papel")):

        etiqueta_resultado.config(text=f"El jugador uno saca {eleccion_j1},"
                                       f" la CPU saca {eleccion_cpu}. Gana el jugador 1.")
        victorias_jugador += 1
    else:
        etiqueta_resultado.config(text=f"El jugador uno saca {eleccion_j1},"
                                       f" la CPU saca {eleccion_cpu}. Gana la CPU.")
        victorias_cpu += 1
    etiqueta_resultado.pack()

    etiqueta_victorias = tk.Label(frame, text=f"Victorias jugador: {victorias_jugador}\n"
                                              f"Victorias CPU: {victorias_cpu}")
    etiqueta_victorias.pack()

    if victorias_jugador == 3:
        messagebox.showinfo("Resultado", "Ha ganado el jugador 1")
        frame.destroy()
        seleccionar_modo()
    elif victorias_cpu == 3:
        messagebox.showinfo("Resultado", "Ha ganado la CPU")
        frame.destroy()
        seleccionar_modo()


def dos_jugadores():  # Empieza la partida para dos jugadores
    pass


def procesar_entrada(entrada):  # Valida la entrada de una opcion
    global eleccion_j1
    entrada = entrada.lower()
    if entrada == "piedra" or entrada == "papel" or entrada == "tijeras":
        eleccion_j1 = entrada
    else:
        eleccion_j1 = "error"


# inicializamos la ventana y creamos el primer frame
root = tk.Tk()
root.title("Piedra, papel, tijeras")
root.geometry("500x500")
frame_seleccion = tk.Frame(root)

eleccion_j1 = None
eleccion_j2 = None
seleccionar_modo()

root.mainloop()
