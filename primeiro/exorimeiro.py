from figuras import Rectangle, Circle

try:
    quant = int(input("Quantas figuras serão solicitadas?\n"))
except:
    print("Graças ao Roger é sexta-feira!")

lista=[]

for i in  range (0,quant):

    try:
        fig = input("Qual figura será solicitada? (r para retângulo e c para círculo)\n")
    except:
        print("Algo de errado foi digitado.")
        break

    if fig == "r":

        try:
            l = float(input("Digite o lado do retângulo:"))
        except:
            print("Algo de errado foi digitado.")
            break

        try:
            a = float(input("Digite a altura do retângulo:"))
        except:
            print("Algo de errado foi digitado.")
            break

        r = Rectangle(l,a)
        lista.append(r.area())

    elif fig == "c":

        try:
            ra = float(input("Digite o raio do círculo:"))
        except:
            print("Algo de errado foi digitado.")
            break

        c = Circle(ra)
        lista.append(c.area())
        
    else:
        print("Voce digitou algo errado, tente novamente.")
        
if lista == []: 
    print("Nenhum valor foi digitado.")
else:
    print(lista)