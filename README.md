# Wishlist Management API

Esta é uma API REST desenvolvida para o gerenciamento de listas de desejos. O projeto foi construído utilizando práticas modernas de desenvolvimento backend, focando em escalabilidade, segurança e portabilidade através de conteinerização.

## Demonstração e Interface

A documentação interativa da API pode ser acessada via Swagger UI após a inicialização do projeto. 



> **Nota:** Recomenda-se inserir aqui um GIF ou screenshot do seu Swagger funcionando, demonstrando uma requisição POST e o retorno do JSON com os campos de auditoria.

## Tecnologias Utilizadas

- **Linguagem:** Java 21 (LTS)
- **Framework:** Spring Boot 3.x
- **Persistência:** Spring Data JPA / Hibernate
- **Banco de Dados:** PostgreSQL 18
- **Documentação:** SpringDoc OpenAPI (Swagger)
- **Infraestrutura:** Docker e Docker Compose
- **Validação:** Bean Validation (Jakarta)

## Diferenciais Técnicos

### 1. Arquitetura de Dados e Segurança
O projeto utiliza o padrão **DTO (Data Transfer Object)** para garantir o desacoplamento entre a camada de banco de dados (Entity) e a camada de apresentação, evitando a exposição desnecessária de atributos internos da aplicação.

### 2. Auditoria Automática
Implementação de **JPA Auditing** para rastreabilidade, onde campos como `dataCriacao` e `dataAtualizacao` são preenchidos automaticamente pelo ciclo de vida do Hibernate, sem intervenção manual no código de negócio.

### 3. Tratamento de Erros Global
Utilização de `@RestControllerAdvice` para capturar e formatar exceções, garantindo que o cliente da API receba respostas padronizadas mesmo em cenários de erro.

### 4. Validação de Dados
Uso de **Bean Validation (Jakarta)** para assegurar que apenas dados íntegros entrem no sistema, retornando erros descritivos ao cliente em caso de payloads inválidos.

## Como Executar o Projeto

### Pré-requisitos
- Docker e Docker Compose instalados.

### Passos para Inicialização
#### 1. Clone o repositório:
   ```bash
   git clone [https://github.com/DerekMA15/spring-wishlist-api.git](https://github.com/DerekMA15/spring-wishlist-api.git)
   ```

#### 2. Acesse a pasta do projeto:

    cd spring-wishlist-api
#### 3. Inicie os containers (API + Banco de Dados):

    docker-compose up --build
    

#### 4. A API estará disponível em http://localhost:8080.
    
    docker-compose up --build
    

## Endpoints Principais
 ### Método - Endpoint - Descrição
- GET - /api/wishlist - Lista itens com suporte a paginação e ordenação.
- POST - /api/wishlist - Registra um novo item na lista.
- PATCH - /api/wishlist/{id}/comprar - Marca um item específico como comprado.

## Licença
```text 
Este projeto está licenciado sob a Licença MIT. Esta licença permite o uso, cópia, modificação e distribuição do software de forma livre, desde que os direitos autorais e o aviso de permissão sejam incluídos em todas as cópias. Para mais detalhes, consulte o arquivo LICENSE.