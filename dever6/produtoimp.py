from produto import Produto

class Importados(Produto) :

        def taxas (self,taxa):
            self.taxa = taxa

        def getTudo(self,nome,preço,taxa):
            return (f"{nome}; {preço}; {taxa}")
            
        def Total(self,preço,taxa,total):
            (total == preço+taxa)
            return({total})