class Produto :

    def __init__(self,nome,preço):
        self.nome = nome
        self.preço = preço

    def getTudo(self,nome,preço):
        return (f"{nome}; {preço}")