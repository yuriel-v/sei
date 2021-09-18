class Product :

    def __init__(self,name,price):
        self.name = name
        self.price = price

    def prieceTag (self, name ,price):
        return(f"{name}; {price}")

class Importados(Product) :

    def taxas (self,taxa):
        self.taxa = taxa
        
    def prieceTag(self, name, price, taxa):
        return (f"{name}; {price}; {taxa}")

class Usados(Product):

    def date(self,dia,mes,ano):
        self.dia = dia
        self.mes = mes
        self.ano = ano

    def prieceTag(self, name, price,dia,mes,ano):
        return(f"{name}; {price}; {dia}-{mes}-{ano}")

    



