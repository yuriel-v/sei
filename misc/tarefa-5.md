# Tarefa dia 5

## O exercício

![image](https://cdn.discordapp.com/attachments/701311212893372456/887452968952152084/unknown.png)

## Os detalhes

A ideia do exercício é mais simples do que parece:

- Prestem atenção no diagrama de classes apresentado. Ele vai ser a base para vocês montarem seus objetos.
- O `manufacturingDate` deve ser uma string ao invés de um `datetime`. Por questões de simplicidade.
  - No caso, a string vai ser formatada como `dd-mm-yyyy`. Exemplo: `16-09-2021`
- O oráculo vai retornar um array de objetos no campo `values`.
  - A estrutura desses objetos espelha as 3 classes do diagrama. Exemplo no final deste documento.
- O método `priceTag()` vai necessariamente ser uma string, formatada da seguinte maneira:
  - `(nome do produto); (preço)[; <data de fabricação / taxa de alfândega>;]`
  - Onde:
    - Elementos são separados por ponto e vírgula - `;`
    - Elementos obrigatórios estão envolvidos em parênteses - `()`
    - Tudo envolvido em colchetes - `[]` - é **opcional** e depende da classe - atenção ao diagrama!
    - Elementos envolvidos em um losango - `<>` - são uma *escolha*, ou seja, deve ser uma das opções, que são separadas por uma barra - `/`
    - Ou seja: Nome e preço são obrigatórios, e caso seja uma subclasse, também deve ser incluso data de fabricação **OU** taxa de alfândega logo após
    - A ordem **é importante** pois a resposta deve ser ordenada corretamente também, de acordo com o que o exercício original propõe

## Exemplo de valores

```json
{
    "values": [
        {
            "name": "Arroz 1kg",
            "price": 5.0
        },
        {
            "name": "Barra de chocolate Swiss Sweetness",
            "price": 40.59,
            "customsFee": 23.2
        },
        {
            "name": "Smartphone Motorama H7 Plus",
            "price": 850.0,
            "manufactureDate": "14-08-2018"
        }
    ]
}
```

É importante perceber que, se um objeto não tem um campo `customsFee` nem um campo `manufactureDate`, ele é um objeto do tipo `Product`. \
Também é importante perceber que a ordem dos valores dentro de um desses objetos pode variar. O que importa é *a ordem em que o objeto em si aparece,* não a ordem dos seus elementos.

## Formato esperado de resposta

```json
{
    "answer": [
        "Produto comum; 420.0",
        "Produto usado; 666.69; 20-04-2021",
        "Produto importado; 351.21; 69.48"
    ]
}
```

O formato da resposta será um array de strings, onde cada string é a saída do método `priceTag()`, conforme indicado acima. \
Relembrando: **É importante** que a ordem seja a mesma que a ordem em que os elementos foram apresentados no JSON!