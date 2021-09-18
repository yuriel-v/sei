from produto import Produto

class Usados(Produto) :
    
        def data (self,datas):
            self.datas = datas
        
        def getTudo(self,nome,preço,data):
            return (f"{nome}; {preço}; {data}")
            