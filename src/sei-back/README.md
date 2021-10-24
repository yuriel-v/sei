# SEI Back-end

Back-end do projeto SEI. Escrito em Java usando Servlets, Maven para compilação + gerência de dependências, JUnit para testes unitários e Tomcat como container de servlets.

## Rodando um servidor de desenvolvimento

Simplesmente navegue até esta pasta e digite `mvn clean package` para compilar. \
Logo após, digite `mvn tomcat7:run-war` para rodar um servidor de desenvolvimento. O back-end ficará disponível em `http://localhost:8080`.

## Para os desenvolvedores

Algumas informações pertinentes aos desenvolvedores do software:

- O IntelliSense do VSCode utiliza o `pom.xml` (sim o XML) para resolver imports.
  - Caso adicionem alguma dependência extra, rodem o script `resync-pom.xml` (ou o comando contido nele, tanto faz) para converter o `pom.yml` para `pom.xml` se não quiserem lidar com esses alarmes falsos.
  - O arquivo lido efetivamente é o `pom.yml` (Maven polyglot assegura isso). O XML está aqui **somente para este fim** (resolução de imports pelo VSCode).
  - Vale ressaltar também que se alguma dependência for adicionada ao POM, **ela não será resolvida até que a janela seja recarregada.** Para tal, abra a paleta de comandos (CTRL + Shift + P) > Recarregar janela.
- Lembrando que o Insomnia ou Postman são as ferramentas ideais para teste desses artefatos.
- Sim, cada endpoint é um servlet. Triste, mas é o que acontece.

## Autores

- Leonardo "Yuriel" Valim
- Gabriel Fidelis Souza
- Julio Cesar de Souza Soares
- Roger Pinheiro Bernardino
