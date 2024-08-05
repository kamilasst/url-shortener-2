# Encurtador de URLs

O projeto é responsável por encurtar e gerenciar URLs, transformando URLs longas em URLs encurtadas. Além disso, oferece a funcionalidade de consultar estatísticas de acesso(s) das URLs encurtadas.

---
### 🛠 Tecnologias

#### Requisitos:
- Java 17 ou superior
- Spring Boot
- Maven (Gerenciador de dependências)
- Docker (Gerenciador de containers)
- Gerenciador de banco para Postgres. Ex.: DBeaver
- Swagger/OpenAPI (Documentação)
- JUnit (Framework de Testes para Java)

---
#### 📌 Funcionalidades
**Cadastrar URL:** Realiza o cadastro de uma URL longa, e retornar uma URL encurtada correspondente;


**Acessar URL encurtada:** Exibe a URL cadastrada (longa) através da URL encurtada;


**Visualizando estatísticas:** Exibe estatísticas de acesso das URLs encurtadas, incluindo média por dia e o total geral de acessos.

---

#### 🛠 Como Executar

**Passos para Executar:**

1 - Clone o repositório: git clone
https://github.com/kamilasst/url-shortener-2.git


2 - Execute o comando docker docker-compose up -d responsável por executar o arquivo url-shortener/docker-compose.yml que será responsável por baixar a imagem do banco de dados postgres que será utilizado no projeto


3 - Abra o DBeaver ou similar e configure a conexão de banco igual o arquivo url-shortener-2/src/main/resources/application.yml


6 - Execute o script responsável por criar as tabelas de banco de dados da aplicação localizado em: url-shortener-2/scripts/create_tables.sql


7 - Compile o projeto: mvn compile


8 - Execute a classe principal do projeto, responsável por colocar a aplicação disponível: Classe UrlShortenerApplication.java, botão direito Run, projeto deverá subir em http://localhost:8080


9 - Chamada dos endpoints: Se preferir fazer chamadas via postman, é só importar os curls dos endpoints  localizado em: url-shortener/curls/curls.postman. Se preferir consumir e visualizar pelo Swagger clicar em: http://localhost:8080/swagger-ui/index.html

---

#### 📌 Problemas e Sugestões
Se encontrar algum problema ou tiver alguma sugestão, por favor abra uma nova issue neste repositório ou entre em contato com kamilasantosdev@gmail.com

