<h1 style="font-size:50px" align="center">Posto ABCD</h1>

## Descrição

Este projeto é uma aplicação back-end desenvolvida em Spring que implementa um sistema de gerenciamento de abastecimento de combustíveis. A arquitetura foi elaborada seguindo os princípios do SOLID, visando modularidade e facilidade de manutenção. Além disso, a aplicação utiliza Docker e Docker Compose para facilitar a execução em diferentes ambientes.

###Link para o [Frontend](https://github.com/joaoedson-dantas/frontend-posto)

## Sobre o desenvolvimento

Para que fosse possível o desenvolvimento desse sistema, utilizei alguns recursos disponíveis 
aqui mesmo no Github, no Google Drive e no Figma.

1. [Link](https://docs.google.com/document/d/1PnWNfzcBYsXSAIET_Hn1KgaTRsJkkjHfBYwFnQ0TMOQ/edit?usp=sharing) **Levantamento de requisítos** - Nesse link será possível visualizar os requisitos do projeto assim como as suas regras de negócio.
2. [Link](https://github.com/users/joaoedson-dantas/projects/3) **Quadro de acompanhamento do projeto** - Assim como em diversos time de desenvolvimento, foi utilizado um board para acompanhar o progresso do desenvolvimento da aplicação.
3. **Modelagem do banco de dados:** - Com o objetivo de ter um banco de dados mais funcional e confiável, desenhei um mini modelo de banco de dados no própio Figma, assim consegui ter mais clareza de como seria feita a API.
   <p align="center">
   <br>
 ![image](https://github.com/joaoedson-dantas/api-posto-abcd/assets/114243172/d13f7e30-cd1a-4ea3-ba00-008e4a938b3c)


</p>

## Tecnologias Utilizadas

- [Spring Boot](https://spring.io/projects/spring-boot): Framework Java para criar aplicativos robustos e escaláveis.
- [Flyway](https://flywaydb.org/): Controle de versão para o banco de dados.
- [Java Persistence API (JPA)](https://docs.oracle.com/javaee/7/tutorial/persistence-intro.htm): Especificação Java para gerenciamento de dados.
- [JSON Web Token (JWT)](https://jwt.io/): Padrão para autenticação e troca segura de informações entre partes.
- [Docker Compose](https://docs.docker.com/compose/): Ferramenta para definir e executar aplicativos Docker com vários contêineres.
- [PostgreSQL](https://www.postgresql.org/): Sistema de gerenciamento de banco de dados relacional.
- [Lombok](https://projectlombok.org/): Biblioteca Java que ajuda a reduzir a verbosidade do código.
- Testes E2E: Testes de ponta a ponta garantindo a integridade do sistema.
  
## Estrutura do projeto 
  ![estrutura](https://github.com/joaoedson-dantas/api-posto-abcd/assets/114243172/0b0a7f5d-caf1-4487-ac90-f1c4d73ff72f)


# Vamos começar?

Siga estas instruções para configurar e executar o backend do projeto.

### Pré-requisitos

Antes de começar, certifique-se de ter as seguintes ferramentas instaladas:

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)

## Instalação

1. Clone o repositório:

```bash
$ git clone https://github.com/joaoedson-dantas/api-posto-abcd.git
```

2. Instale as dependecias com o Maven

## Modo de usar

1. Inicie a aplicação com o Maven 
2. A API poderá ser acessada http://localhost:8080

## Docker

Você pode executar este projeto com Docker executando o seguinte comando:

```bash
$ docker-compose up
```
Execute o comando e acesse em:  http:///localhost:5432/dbposto

Entre com seu e-mail e senha configurada em: [Docker file](./docker-compose.yml).

## API Endpoints
Esses são os principais Endpoints, poderá visualizar todos os endpois na documentação da api.

```markdown
GET /global-settings - Busca todas as configurações globais

POST /global-settings - Cria uma configuração global

PUT /global-settings - Edita uma configuração global

POST /auth/user - Autenticar usuário através do token 

POST /supply/to-fuel - Rota para abastecer veículos

GET /supply/all-supplies - Buscar todos os abastecimentos

GET /fuel-tanks - Busca todos os tanques criados

POST /fill-tanks - Abastercer um tanque

GET /fuel-pumps - Busca todas as bombas

POST /fuel-pumps - Cadastra uma bomba


```

## Contato
<<<<<<< HEAD
Algum problema ou sugestões? Envie uma mensagem para [joaoedson.dantas@outlook.com].
=======
Algum problema ou sugestões? Envie uma mensagem para [joaoedson.dantas@outlook.com].
>>>>>>> efbfd73334f7e97e3ede7397abf9082ea80020a4
