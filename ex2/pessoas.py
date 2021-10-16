from abc import ABC,abstractclassmethod

class TaxPayer(ABC):

    name = ''
    anualIncome = None

    @abstractclassmethod
    def tax(self):
        pass

class Individual(TaxPayer):

    def __init__(self,healthExpenditures,name,anualIncome):

        self.h = healthExpenditures
        self.n = name
        self.a = anualIncome
        

    def tax(self):


        if  float(self.a) < 20000 and float(self.h) > 0:

            return (float(self.a) * 0.15) - (float(self.h) * 0.5)

        elif float(self.a) < 20000:

            return (float(self.a) * 0.15)

        elif float(self.a) > 20000 and float(self.h) > 0:

           return (float(self.a) * 0.25) - (float(self.h) * 0.5)

        else:

            return self.a * 0.25

class Company(TaxPayer):

    def __init__(self,numberofemployees,name,anualIncome):

        self.nu = numberofemployees
        self.n = name
        self.a = anualIncome
    
    def tax(self):

        if int(self.nu) < 10:

            return float(self.a) * 0.16

        else:
            return float(self.a) * 0.14