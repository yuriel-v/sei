import produto
import produtoimp
import produtousa

padrão = produto.Produto("","")
importados = produtoimp.Importados("","")
usados = produtousa.Usados("","")
nota = []
num = input("Qual o número de itens a serem adicionados ? ")

for i in range(0, int(num)):
    
    padrão.nome = input("diga o nome do produto: ")
    padrão.preço = input("e qual seria seu valor: ")
    resposta = input("se seu produto for de via importada digite i, se ele for usado digite u, caso nenhum dos dois digite n: ")
    if resposta == "i":
        importados.taxa = input("digite o valor da taxa de alfândega: ")
        c = importados.getTudo(nome=padrão.nome,preço=padrão.preço,taxa=importados.taxa)
        importados.Total
        nota.append(c)
    elif resposta == "u":
        usados.data = input("digite a data de expedição do produto com o formato dd-mm-aaaa: ")
        b = usados.getTudo(nome=padrão.nome,preço=padrão.preço,data=usados.data)
        nota.append(b)
    else:
        a = padrão.getTudo(nome=padrão.nome,preço=padrão.preço)
        nota.append(a)
    
    for produt in nota:
        print(produt)