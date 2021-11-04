# SEI Back-end

Back-end do projeto SEI. Escrito em Java usando JAX-RS, Maven para compilação + gerência de dependências, JUnit para testes unitários e OpenLiberty como ambiente de _runtime_.

## Rodando um servidor de desenvolvimento

Navegue até esta pasta no terminal (`/src/sei-back` referente à raiz do projeto) e rode `mvn liberty:dev`. \
Pressione enter para rodar testes (unitários e de integração) em demanda.

- Nota: O comando `mvn clean package` irá retornar erros nos testes e **não irá compilar!**
  - A maneira certa de se rodar testes é rodando o servidor de desenvolvimento e rodando os testes com enter!
  - Isso poderia ser corrigido (provavelmente) simulando a API com Mockito ou outra framework similar, mas francamente, não temos tempo nem paciência para isso.
- Nota 2: Caso já tenha compilado o projeto anteriormente (antes de mudarmos de Tomcat para OpenLiberty), favor rodar `mvn clean` antes de executar o comando acima.
  - Excluir a pasta `/src/sei-back/target` também funciona.

## Para os desenvolvedores

Algumas informações pertinentes aos desenvolvedores do software:

- O IntelliSense do VSCode utiliza o `pom.xml` (sim o XML) para resolver imports.
  - Caso adicionem alguma dependência extra, rodem o script `resync-pom.sh` (ou o comando contido nele, tanto faz) para converter o `pom.yml` para `pom.xml` se não quiserem lidar com esses alarmes falsos.
  - O arquivo lido efetivamente é o `pom.yml` (Maven polyglot assegura isso). O XML está aqui **somente para este fim** (resolução de imports pelo VSCode).
  - Vale ressaltar também que se alguma dependência for adicionada ao POM, **ela não será resolvida até que a janela seja recarregada.** Para tal, abra a paleta de comandos (CTRL + Shift + P) > Recarregar janela.
- Lembrando que o Insomnia ou Postman são as ferramentas ideais para teste desses artefatos.
- ~~Sim, cada endpoint é um servlet. Triste, mas é o que acontece.~~ Conforme confirmado pelo professor, como já planejamos uma API REST, podemos usar JAX-RS. É o que faremos.

## Autores

- Leonardo "Yuriel" Valim
- Gabriel Fidelis Souza
- Julio Cesar de Souza Soares
- Roger Pinheiro Bernardino
