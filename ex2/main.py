from pessoas import Individual, Company

try:
    pag = int(input("Entre com o número de pagantes: "))

except:
    print("Algo foi digitado errado.")

total = 0

for i in range (0,pag):
    
        ho =input("Individual or Company? (i/c) ")

        if ho == "i":
            
            try:
                na = input("Name: ")
            
            except:
                print("Algo foi digitado errado.")
            
            try:    
                an = input("Anual Income: ")
            
            except:
                print("Algo foi digitado errado.")    
            
            try:    
                he = input("Health expenditures: ") 
            
            except:
                print("Algo foi digitado errado.")
            
            pers = Individual(he, ''+ na, an)

            print(pers.tax())

            total = total + pers.tax()

        elif ho == "c":

            try:
                na = input("Name: ")

            except:
                print("Algo foi digitado errado.")
            
            try:    
                an = input("Anual Income: ")

            except:
                print("Algo foi digitado errado.")

            try:    
                ne = input("Number of employees: ")
                
            except:
                print("Algo foi digitado errado.")

            empr = Company(ne,''+ na, an)

            print  ("{:.2f}".format(empr.tax()))

            total = total + empr.tax()

        else:

            print("Você digitou algo errado.")
            break

if total != 0:
    print("{:.2f}".format(total))
else:
    SystemExit(0)