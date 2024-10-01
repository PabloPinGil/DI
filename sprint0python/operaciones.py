# Programa con una función para cada una de las operaciones aritméticas básicas


def suma(x, y):
    return x + y


def resta(x, y):
    return x - y


def multiplicacion(x, y):
    return x * y


def division(x, y):
    return x / y


if __name__ == '__main__':
    n1 = int(input("Introduce un número: "))
    n2 = int(input("Introduce otro número: "))

    print("Suma: " + str(suma(n1,n2)))
    print("Resta: " + str(resta(n1, n2)))
    print("Multiplicacion: " + str(multiplicacion(n1, n2)))
    print("Division: " + str(division(n1, n2)))
