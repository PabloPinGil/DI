import time
import tkinter as tk
from tkinter import messagebox, simpledialog
from modelo import GameModel
from vista import MainMenu, GameView

class GameController:
    def __init__(self, root):
        self.root = root
        self.model = None
        self.view = None
        self.main_menu = MainMenu(
            root,
            self.start_game_callback,
            self.show_stats_callback,
            self.quit_callback
        )
        self.player_name = ""
        self.selected = []
        self.timer_started = False
        self.loading_window = None

    def show_difficulty_selection(self):
        difficulty = simpledialog.askstring(
            "Dificultad",
            "Selecciona la dificultad: facil, medio, dificil",
            parent=self.root
        )
        if difficulty in ["facil", "medio", "dificil"]:
            self.player_name = self.main_menu.ask_player_name()
            if self.player_name:
                self.start_game(difficulty)
        else:
            messagebox.showerror("Error", "Dificultad inválida")

    def start_game(self, difficulty):
        self.show_loading_window("Cargando tablero...")
        self.model = GameModel(difficulty, self.player_name)
        self.check_images_loaded()

    def show_loading_window(self, message):
        self.loading_window = tk.Toplevel(self.root)
        self.loading_window.title("Cargando")
        tk.Label(self.loading_window, text=message).pack(pady=10)
        self.loading_window.grab_set()

    def check_images_loaded(self):
        if self.model.images_are_loaded():
            self.loading_window.destroy()
            self.view = GameView(
                self.on_card_click,
                self.update_move_count,
                self.update_time
            )
            self.view.create_board(self.model)
        else:
            self.root.after(100, self.check_images_loaded)

    def on_card_click(self, pos):
        if not self.timer_started:
            self.timer_started = True
            self.model.start_timer()
            self.update_time()
        self.selected.append(pos)
        self.view.update_board(pos, self.model.get_image_id(pos))
        if len(self.selected) == 2:
            self.handle_card_selection()

    def handle_card_selection(self):
        pos1, pos2 = self.selected
        if self.model.check_match(pos1, pos2):
            self.view.update_board(pos1, self.model.get_image_id(pos1))
            self.view.update_board(pos2, self.model.get_image_id(pos2))
        else:
            self.root.after(1000, lambda: self.view.reset_cards(pos1, pos2, self.model.hidden_image))

        self.update_move_count(self.model.moves)
        self.selected = []
        self.check_game_complete()

    def update_move_count(self, moves):
        self.view.update_move_count(moves)

    def update_time(self):
        if self.model and not self.model.is_game_complete():
            elapsed_time = self.model.get_time()
            self.view.update_time(elapsed_time)
            self.root.after(1000, self.update_time)

    def check_game_complete(self):
        if self.model.is_game_complete():
            messagebox.showinfo(
                "¡Felicidades!",
                f"Has completado el juego en {self.model.moves} movimientos."
            )
            self.model.save_score()
            self.return_to_main_menu()

    def return_to_main_menu(self):
        if self.view:
            self.view.destroy()
        self.view = None
        self.main_menu = MainMenu(
            self.root,
            self.show_stats_callback,
            self.quit_callback
        )

    def show_stats(self):
        stats = self.model.load_scores()
        self.main_menu.show_stats(stats)

    def quit_callback(self):
        self.root.quit()

    def start_game_callback(self):
        self.show_difficulty_selection()

    def show_stats_callback(self):
        self.show_stats()

