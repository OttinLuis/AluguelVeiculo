# Sistema de Aluguel de Veículos

Sistema desenvolvido para gerenciar o aluguel de veículos, permitindo o cadastro de clientes, veículos e locações de forma prática e organizada.

## Funcionalidades

* Cadastro de clientes
* Cadastro de veículos
* Listagem de veículos disponíveis
* Realização de aluguel
* Devolução de veículos
* Consulta de histórico de locações
* Controle de disponibilidade dos veículos
* Validação de dados

## Tecnologias Utilizadas

* Java
* Spring Boot
* Spring MVC
* Spring Data JPA
* Thymeleaf
* HTML5
* CSS3
* Bootstrap
* PostgreSQL (ou MySQL)
* Maven

## Estrutura do Projeto

```text
src
├── main
│   ├── java
│   │   ├── controller
│   │   ├── model
│   │   ├── repository
│   │   ├── service
│   │   └── config
│   └── resources
│       ├── templates
│       ├── static
│       └── application.properties
```

## Como executar

### 1. Clone o repositório

```bash
git clone https://github.com/OttinLuis/AluguelVeiculo.git
```

### 2. Acesse a pasta do projeto

```bash
cd AluguelVeiculo
```

### 3. Configure o banco de dados

Edite o arquivo `application.properties` com as credenciais do seu banco de dados.

Exemplo:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/aluguel_veiculos
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 4. Execute a aplicação

Pela IntelliJ IDEA ou utilizando o Maven:

```bash
./mvnw spring-boot:run
```

ou

```bash
mvn spring-boot:run
```

## Telas do Sistema

* Página inicial
* Cadastro de clientes
* Cadastro de veículos
* Lista de veículos
* Realização de aluguel
* Devolução de veículo
* Histórico de locações

## Objetivo

Este projeto foi desenvolvido com o objetivo de praticar conceitos de desenvolvimento web utilizando Spring Boot, aplicando a arquitetura MVC, persistência de dados com JPA e boas práticas de programação.

## Melhorias Futuras

* Implementação de autenticação de usuários
* Perfis de administrador e funcionário
* Upload de imagens dos veículos
* Cálculo automático do valor da locação
* Pagamento online
* Dashboard com gráficos
* API REST
* Testes automatizados

## Autor

**Otto Luis**

GitHub: https://github.com/OttinLuis/AluguelVeiculo
