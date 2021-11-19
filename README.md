# SEI

Sistema de Educação Intuitivo: Um sistema que incorpora um boletim e agenda acadêmica com um repositório de conteúdo. \
Atualmente, o projeto conta com um back-end em Java (OpenLiberty) e um front-end em Angular. Mais detalhes abaixo.

## Front-end
Projeto feito em **Angular 13**, utilizando **Node.JS 16**.

### Instalação do front-end: 
1. Instale o Node [diretamente do website.](https://nodejs.org/pt-br/download/)
2. Após instalado, navegue até a pasta do front-end em `sei/src/sei-front`
3. Utilize o comando `npm install` para instalar as dependências.
4. Após instalada as dependências, o projeto está pronto para ser rodado utilizando o comando `npm start`.

## Back-end
Projeto feito em Java 11, usando Maven para gerência de dependências e OpenLiberty como framework web. \
Consiste em uma API REST elaborada usando JAX-RS, contando com 3 endpoints:

- `login`: (POST) Verificação de login.
- `agenda`: (GET + POST) Manipulação da agenda de um usuário qualquer.
- `index`: (GET) Endpoint de teste.

### Instalação do back-end em Ubuntu
- Instale o Maven com o comando `sudo apt-get update && sudo apt-get install -y maven` (instala OpenJDK 11 como dependência!)
- Navegue até a pasta `sei/src/sei-back`
- Rode o comando `mvn liberty:run`.
  - O aplicativo estará disponível no endereço `http://localhost:9080`.
  - Caso queira rodar um servidor de desenvolvimento, com testes sob demanda e detecção/recompilação de arquivos modificados, rode `mvn liberty:dev`

### Avisos e problemas conhecidos
- Em Linux, o back-end foi testado somente em Ubuntu 20.04 LTS (Focal Fossa) e Linux Mint 20.2 (Uma). Não há garantia de funcionamento em outras distros!
- Em Windows, por algum motivo ainda desconhecido para nós, o _encoding_ das matérias vem errado. Fizemos tudo que sabíamos e pesquisamos para tentar resolver o problema, mas nada.
  - Como consequência, caso o back-end seja rodado em Windows, matérias com nome, por exemplo, "Álgebra Linear", virão com os caracteres especiais (acento, til e cedilha) "borrados".
  - Em Linux (inclusive WSL), isso não acontece.
- A parte de segurança não foi implementada. Tinhamos planos de incluir uma autenticação baseada em JWT inicialmente, mas por falta de tempo, isso não foi para frente.
  - Isso significa que, sabendo a matrícula de qualquer usuário, a API confere acesso completo à agenda dele! Isso é gravíssimo e será consertado pós-matéria.
- No momento, existem somente 3 usuários, todos com dados "mockados":
  - Usuário `lbval`, matrícula `2019101557` e senha `lbval`;
  - Usuário `gfids`, matrícula `2019101478` e senha `gfids`;
  - Usuário `admin`, matrícula `9999999999` e senha `R!c|<r0ll`.
- No momento, não há suporte para edição dos dados mockados. Isso será implementado pós-matéria.


## Rodando o projeto como um todo
1. Clone o repositório do projeto (seja por SSH, seja por HTTPS) e mude para esta branch (`uni`);
2. Abra 2 terminais, com 1 deles necessariamente sendo o bash do Ubuntu (WSL caso esteja em Windows);
3. Em um terminal, siga as [instruções de instalação e execução do front-end](#instalação-do-front-end);
4. No terminal bash, siga as [instruções de instalação e execução do back-end](#instalação-do-back-end-em-ubuntu);
5. Abra o seu navegador e vá até a página `http://localhost:4200`;
6. Se tudo estiver OK, você verá o site rodando normalmente.
