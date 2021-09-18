import produto

prod = produto.Product(" ", " ")
prodi = produto.Importados(" "," ")
produ = produto.Usados(" "," ")
list = []
modelo = " "

gay = input("Digite o número de numero de produtos:\n")

for n in range(0 , int(gay)):

    modelo = input("Digite 'i' se o produto for importado, 'u' se for usado oue 'p' se não for nunhuma das outras opções:\n ")

    prod.name = input("Digite o nome do produto:\n")

    prod.price= input("Digite o preço do produto:\n")

   

    if modelo == "i":

        prodi.taxa=input("Digite a taxa aplicada sobre o produto:\n")

        list.append(prodi.prieceTag(name=prod.name, price=prod.price, taxa=prodi.taxa))

    elif modelo == "u":

        produ.dia = input("Digite o dia de fabricação:\n")
        produ.mes = input("Digite o mês de fabricação:\n")
        produ.ano = input("Digite o ano de fabricação:\n")

        list.append(produ.prieceTag(name=prod.name, price=prod.price, dia=produ.dia, mes=produ.mes, ano=produ.ano))

    else:

        list.append(prod.prieceTag(name=prod.name, price=prod.price,))

for produt in list:
    print(produt)



