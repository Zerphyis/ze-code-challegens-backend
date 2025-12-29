## 📌 Zé Delivery – Partner Service Backend

### 🎯 Problema que o projeto resolve

 O desafio propõe a construção de um serviço backend responsável por cadastrar parceiros, consultar parceiros por ID e retornar o parceiro mais próximo, respeitando regras de negócio e utilizando o formato GeoJSON para dados geográficos.

__O problema central não é apenas expor endpoints, mas garantir que:__ 

* Dados inválidos não entrem no sistema

* As regras de negócio sejam respeitadas

* Dados geográficos sigam o padrão GeoJSON

* A busca por proximidade funcione corretamente

* O código seja organizado e fácil de manter

Este projeto resolve esses pontos ao aplicar validações consistentes, centralizar regras de negócio e estruturar o código de forma previsível.

Desafio original:
https://github.com/ab-inbev-ze-company/ze-code-challenges/blob/master/backend.md

### 🧩 Contexto

__O desafio simula problemas comuns encontrados em sistemas reais, como:__

* Requisitos técnicos claros, mas regras de negócio implícitas

* Necessidade de trabalhar com dados geográficos no formato GeoJSON

* Separação entre domínio, aplicação e infraestrutura

* Código que precisa ser facilmente entendido e mantido

O projeto foi desenvolvido com foco em clareza, organização e aplicação correta das regras, indo além de apenas “passar no teste”.

### ✅ O que o sistema faz

* Cadastra parceiros com validação de dados obrigatórios

* Valida e persiste áreas de cobertura no formato GeoJSON (MultiPolygon)

* Consulta parceiros por identificador único

* Retorna o parceiro mais próximo a partir de coordenadas (longitude e latitude)

* Garante compatibilidade com o padrão GeoJSON exigido pelo desafio

* Centraliza regras de negócio na camada de aplicação

* Evita estados inválidos no domínio

* Controla requisições com rate limiting

* Trata erros de forma consistente


### 🧠 Decisões técnicas importantes

* Adotei Clean Architecture como decisão arquitetural, pois estou estudando esse modelo atualmente e quis aplicá-lo em um cenário próximo do mundo real

* Modelei dados geográficos seguindo rigorosamente o padrão GeoJSON, conforme exigido no desafio

* Separei regras de negócio em use cases, reduzindo acoplamento entre domínio, aplicação e infraestrutura

* Priorizei validação na entrada para evitar erros em etapas posteriores

* Mantive controllers finos, focados apenas em orquestração

* Preferi clareza e previsibilidade ao invés de “código inteligente”

* Aceitei mais código em troca de maior legibilidade e manutenção

### 🔄 Possíveis melhorias

* Padronizar ainda mais o formato de erros

* Aumentar a cobertura de testes, principalmente testes de integração

* Refinar validações de domínio

* Documentar regras de negócio mais complexas

* Evoluir o rate limiter para um cenário distribuído

### 🛠️ Como executar
#### Executando com Docker (recomendado)

Pré-requisitos:

* Docker
* Docker Compose

```
docker-compose up --build
````

__A aplicação será iniciada automaticamente e ficará disponível em:__
````
http://localhost:8080
````

As configurações de banco e ambiente já estão definidas no docker-compose.yml.


#### Executando localmente (sem Docker)

Pré-requisitos:

* Java 17+
*MySQL 8 (ou outro banco relacional de sua preferência)
* Maven
````
./mvnw spring-boot:run
````
Ou:
````
mvn spring-boot:run
````

A aplicação ficará disponível em:
````
http://localhost:8080
````

#### ⚠️ Importante
Para que a aplicação funcione corretamente em ambiente local, é obrigatório configurar o arquivo application.properties com as credenciais e informações do banco de dados antes de executar o projeto.

__Exemplo de application.properties (execução local)__
````
# Porta da aplicação
server.port=8080

# Configuração do banco de dados (MySQL)
spring.datasource.url=jdbc:mysql://localhost:3306/DB_NAME?useSSL=false&serverTimezone=UTC
spring.datasource.username=DB_USERNAME
spring.datasource.password=DB_PASSWORD

# JPA / Hibernate
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Jackson
spring.jackson.serialization.indent_output=true

# Rate limit
rate.limit.requests=10
rate.limit.duration.seconds=60
````

#### 📌 Nota
O banco de dados informado em DB_NAME deve existir previamente no MySQL.

### 📚 O que aprendi com esse projeto

Aprendi que resolver um desafio técnico não é apenas implementar endpoints, mas entender o problema de negócio e seus formatos de dados.
Trabalhar com GeoJSON e aplicar Clean Architecture reforçou a importância de separação de responsabilidades, validações corretas e previsibilidade para manter o sistema evolutivo e sustentável.