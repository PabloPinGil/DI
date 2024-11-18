import threading
import time
import random
from datetime import datetime
from recursos import descargar_imagen


class GameModel:
    def __init__(self, difficulty, player_name, cell_size=100):
        self.difficulty = difficulty
        self.player_name = player_name
        self.cell_size = cell_size

        # Configuración del tamaño del tablero según la dificultad
        self.board_size = {"facil": 4, "medio": 6, "dificil": 8}[difficulty]

        # Variables del juego
        self.board = []
        self.hidden_image = None
        self.images = {}  # Mapa de ID de imagen a PhotoImage
        self.images_loaded = threading.Event()  # Para indicar que las imágenes están listas
        self.start_time = None
        self.moves = 0
        self.pairs_found = 0

        # Inicializar tablero e imágenes
        self._generate_board()
        self._load_images()

    def _generate_board(self):
        total_pairs = (self.board_size * self.board_size) // 2
        card_ids = list(range(total_pairs)) * 2
        random.shuffle(card_ids)
        self.board = [card_ids[i:i + self.board_size] for i in range(0, len(card_ids), self.board_size)]

    def _load_images(self):
        def load_images_thread():
            try:
                # Descarga la imagen oculta
                self.hidden_image = descargar_imagen("https://raw.githubusercontent.com/PabloPinGil/DI/refs/heads/main/sprint4tkinter/imagenes/hidden.png", (self.cell_size, self.cell_size))
                if not self.hidden_image:
                    raise Exception("Fallo al descargar la imagen oculta.")

                # Descarga las imágenes para las cartas
                for card_id in range((self.board_size * self.board_size) // 2):
                    url = f"https://raw.githubusercontent.com/PabloPinGil/DI/refs/heads/main/sprint4tkinter/imagenes/{card_id}.png"
                    self.images[card_id] = descargar_imagen(url, (self.cell_size, self.cell_size))
                    if not self.images[card_id]:
                        raise Exception(f"Fallo al descargar la imagen con ID {card_id}.")

                # Marcar como completado
                self.images_loaded.set()
            except Exception as e:
                print(f"Error al cargar imágenes: {e}")

        threading.Thread(target=load_images_thread, daemon=True).start()

    def images_are_loaded(self):
        return self.images_loaded.is_set()

    def get_image_id(self, pos):
        row, col = pos
        card_id = self.board[row][col]  # Obtén el ID de la carta según la posición.
        return self.images.get(card_id, self.hidden_image)  # Devuelve la imagen asociada o la oculta.

    def start_timer(self):
        self.start_time = time.time()

    def get_time(self):
        return int(time.time() - self.start_time) if self.start_time else 0

    def check_match(self, pos1, pos2):
        self.moves += 1
        r1, c1 = pos1
        r2, c2 = pos2
        if self.board[r1][c1] == self.board[r2][c2]:
            self.pairs_found += 1
            return True
        return False

    def is_game_complete(self):
        return self.pairs_found == (self.board_size * self.board_size) // 2

    def save_score(self):
        try:
            # Cargar las puntuaciones existentes
            scores = self.load_scores()
            new_score = {
                "name": self.player_name,
                "moves": self.moves,
                "date": datetime.now().strftime("%Y-%m-%d")
            }
            scores[self.difficulty].append(new_score)

            # Ordenar y mantener las tres mejores
            scores[self.difficulty] = sorted(scores[self.difficulty], key=lambda x: x["moves"])[:3]

            # Guardar las puntuaciones
            with open("ranking.txt", "w") as file:
                for difficulty, scores_list in scores.items():
                    file.write(f"{difficulty}:\n")
                    for score in scores_list:
                        file.write(f"{score['name']},{score['moves']},{score['date']}\n")
        except Exception as e:
            print(f"Error al guardar puntuaciones: {e}")

    def load_scores(self):
        scores = {"facil": [], "medio": [], "dificil": []}
        try:
            with open("ranking.txt", "r") as file:
                difficulty = None
                for line in file:
                    line = line.strip()
                    if line.endswith(":"):
                        difficulty = line[:-1].lower()
                    elif difficulty:
                        name, moves, date = line.split(",")
                        scores[difficulty].append({"name": name, "moves": int(moves), "date": date})
        except FileNotFoundError:
            pass
        except Exception as e:
            print(f"Error al cargar puntuaciones: {e}")
        return scores
    