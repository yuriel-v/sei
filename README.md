# SEI

Sistema de Educação Intuitivo: Um sistema que incorpora um boletim e agenda acadêmica com um repositório de conteúdo. \
Atualmente, o projeto conta com um back-end em Java (OpenLiberty) e um front-end em Angular. Mais detalhes abaixo.

## Rodando o projeto como um todo
- Clone o repositório do projeto (seja por SSH, seja por HTTPS) e mude para esta branch (`uni`);
  - Caso esteja em Windows, utilize o WSL para fazer o clone do projeto.
  - Muita atenção para não clonar dentro do seu sistema de arquivos Windows, pois podem ocorrer problemas de permissão ou algo do tipo!
  - Seu sistema de arquivos Windows fica em `/mnt/c/`
    - Sugestão: Clone para o homedir do seu usuário Ubuntu (`~`, ou `/home/<username>`)
- Abra 2 terminais bash do Ubuntu (WSL caso esteja em Windows);
- Em um terminal, siga as [instruções de instalação e execução do front-end](#instalação-do-front-end);
  - Nota: Caso em WSL, fica aqui um [link de instalação do Node.JS](https://github.com/nodesource/distributions/blob/master/README.md#installation-instructions) - instale a versão 16.x.
- No outro terminal, siga as [instruções de instalação e execução do back-end](#instalação-do-back-end-em-ubuntu);
- Quando ambos front e back-end estiverem rodando, abra o seu navegador e vá até a página `http://localhost:4200`;
- Se tudo estiver OK, você verá o site rodando normalmente.

---

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
- Quando o front-end é rodado em Windows e o back-end em WSL, não há comunicação entre os dois. Isso se dá pelo fato de que o WSL é uma **máquina virtual** rodando em cima do Hyper-V do Windows, logo não é considerado "o mesmo dispositivo" para fins de integração.
  - Caso esteja rodando o projeto em Windows, **faça todas as etapas no WSL** - caso feito em Windows, vide segundo item da lista.
