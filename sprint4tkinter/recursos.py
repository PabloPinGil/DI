import requests
from PIL import Image, ImageTk
import io


def descargar_imagen(url, size):
    try:
        # Descargar la imagen desde la URL
        response = requests.get(url)
        response.raise_for_status()

        # Cargar la imagen en formato PIL
        image_data = io.BytesIO(response.content)
        image = Image.open(image_data)

        # Redimensionar la imagen al tamaño especificado
        image = image.resize(size, Image.LANCZOS)

        # Convertir a formato Tkinter
        return ImageTk.PhotoImage(image)

    except requests.RequestException as e:
        print(f"Error al descargar la imagen desde {url}: {e}")
        return None
