import tkinter as tk
from tkinter import simpledialog, Toplevel


class MainMenu:
    def __init__(self, root, start_game_callback, show_stats_callback, quit_callback):
        self.root = root
        self.start_game_callback = start_game_callback
        self.show_stats_callback = show_stats_callback
        self.quit_callback = quit_callback

        # Configuración de la ventana principal
        root.title("Menú Principal")
        root.geometry("400x300")

        # Botones principales
        tk.Button(root, text="Jugar", command=self.start_game_callback, width=20).pack(pady=20)
        tk.Button(root, text="Estadísticas", command=self.show_stats_callback, width=20).pack(pady=20)
        tk.Button(root, text="Salir", command=self.quit_callback, width=20).pack(pady=20)

    def ask_player_name(self):
        return simpledialog.askstring("Nombre del jugador", "Introduce tu nombre:", parent=self.root)

    def show_stats(self, stats):
        stats_window = Toplevel(self.root)
        stats_window.title("Estadísticas")
        stats_window.geometry("300x400")

        for difficulty, scores in stats.items():
            tk.Label(stats_window, text=f"{difficulty.capitalize()}").pack(anchor="w", pady=5)
            for score in scores:
                tk.Label(stats_window, text=f"{score['name']}: {score['moves']} movimientos").pack(anchor="w")

        tk.Button(stats_window, text="Cerrar", command=stats_window.destroy).pack(pady=10)


class GameView:
    def __init__(self, on_card_click_callback, update_move_count_callback, update_time_callback):
        self.move_label = None
        self.timer_label = None
        self.on_card_click_callback = on_card_click_callback
        self.update_move_count_callback = update_move_count_callback
        self.update_time_callback = update_time_callback

        self.window = None
        self.labels = []

    def create_board(self, model):
        self.window = Toplevel()
        self.window.title("Juego de Memoria")
        self.window.geometry(f"{model.board_size * model.cell_size + 100}x{model.board_size * model.cell_size + 100}")

        # Crear la cuadrícula de etiquetas (cartas)
        self.labels = []
        for row in range(model.board_size):
            for col in range(model.board_size):
                label = tk.Label(
                    self.window,
                    highlightbackground="black",  # Color del borde
                    highlightthickness=2,  # Grosor del borde
                    bd=0  # Ancho del borde interno (opcional)
                )
                label.config(image=model.hidden_image)
                label.grid(row=row, column=col, padx=2, pady=2)
                label.bind("<Button-1>", lambda event, pos=(row, col): self.on_card_click_callback(pos))
                self.labels.append(label)

        # Elementos adicionales (contador de movimientos y temporizador)
        self.move_label = tk.Label(self.window, text="Movimientos: 0")
        self.move_label.grid(row=model.board_size, column=0, columnspan=model.board_size // 2, sticky="w")

        self.timer_label = tk.Label(self.window, text="Tiempo: 0s")
        self.timer_label.grid(row=model.board_size, column=model.board_size // 2, columnspan=model.board_size // 2,
                              sticky="e")

    def update_board(self, pos, image_id):
        row, col = pos
        idx = row * int(len(self.labels) ** 0.5) + col
        self.labels[idx].config(image=image_id)
        self.labels[idx].image = image_id

    def reset_cards(self, pos1, pos2, hidden_image):
        self.update_board(pos1, hidden_image)
        self.update_board(pos2, hidden_image)

    def update_move_count(self, moves):
        self.move_label.config(text=f"Movimientos: {moves}")

    def update_time(self, time):
        self.timer_label.config(text=f"Tiempo: {time}s")

    def destroy(self):
        if self.window:
            self.window.destroy()
            self.window = None
