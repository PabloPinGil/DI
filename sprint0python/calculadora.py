#Programa que pide dos números y acto seguido la operación a realizar

from operaciones import suma, resta, multiplicacion, division


if __name__ == '__main__':

    while True:
        n1 = int(input("Introduce el primer número: "))
        n2 = int(input("Introduce el segundo número: "))

        print("\n¿Qué operación desea realizar?")
        print("1. Suma")
        print("2. Resta")
        print("3. Multiplicación")
        print("4. División")
        opcion = int(input("Seleccione una opción: "))

        if opcion == 1:
            print("Resultado: " + str(suma(n1,n2)))
        elif opcion == 2:
            print("Resultado: " + str(resta(n1,n2)))
        elif opcion == 3:
            print("Resultado: " + str(multiplicacion(n1, n2)))
        elif opcion == 4:
            print("Resultado: " + str(division(n1, n2)))
        else:
            print("Error: opción no válida")

        fin = input("Realizar otra operación (s/n): ")

        if fin == "n" or fin == "N":
            break