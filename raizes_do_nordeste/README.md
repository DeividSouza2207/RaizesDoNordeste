# RAÍZES DO NORDESTE - API
API REST criada para o projeto acadêmico, de uma rede de lanchonetes<br>
chamada "Raízes do Nordeste. Uma rede fictícia especializada em produtos<br>
da culinária nordestina. O projeto buscar resolver os principais problemas<br>
do cliente no gerenciamento de pedidos.


## Sobre o projeto

 **O projeto é um MVP desenvolvido com:**<br><br>
-API REST;<br>
-Autenticação e autorização com JWT;<br>
-Gerenciamento de pedidos, com atualização de status;<br>
-Controle do estoque<br>
-Pagamento MOCK;<br>
-Validação dos dados de entrada;<br>
-Documentação com Swagger/OpenAPI;<br>
-Testes com Postman.



## Tecnologias
Java 17  
Spring Boot 4.0.7  
Spring Data JPA  
Hibernate  
Spring Security  
Maven  
OAuth2 Resourse Server  
BCrypt  
JWT  
Bean Validation   
MySQL   
Swagger/OpenAPI  
Postman  
Eclipse  

## Arquitetura
Arquitetura do MVP

```text
src/main/java/com/example/raizes_do_nordeste/

├── api
│   ├── controller
│   ├── dto
│   └── exception
│
├── application
│   └── service
│
├── domain
│   ├── entity
│   ├── enums
│   └── repository
│
├── infrastructure
│
└── config
```
**RESPONSABILIDADES**<br>

**API**<br>
Contém os controllers REST, DTOs e os tratamentos de exceções http.

**application**<br>
Contém os services e as principais regras de negócio da aplicação.

**domain**<br>
Contém as entidades, enums e repositories do sistema.

**infrastructure**<br>
Reservado para futuras integrações relacionadas à infraestrutura da aplicação.

**config**<br>
Contém configurações da aplicação, incluindo segurança e documentação da API.



## Autenticação e autorização
A API utiliza autenticação baseada em token JWT.
Após o login, o usuário recebe um token<br> que deve ser enviado aos endpoints protegidos com:  <br>
*Authorization:* Bearer {token}

Roles da aplicação:

CLIENTE<br>
ATENDENTE<br>
COZINHEIRO<br>
GERENTE<br>
ADMIN<br>

Exemplos de autorização:

POST/pedidos<br>
->CLIENTE ou ATENDENTE

PATCH/pedidos/{id}/status<br>
->COZINHEIRO, GERENTE ou ADMIN

GET/usuarios<br>
->GERENTE ou ADMIN

As senhas dos usuários são protegidas utilizando BCrypt.

## Variáveis de ambiente
O projeto utiliza variáveis de ambiente para configurar o banco de dados   
e também o segredo usado pelo JWT.

DB_URL  
DB_USERNAME  
DB_PASSWORD  
JWT_SECRET  

## Banco de Dados  
O banco de dados utilizado na aplicação é o MySQL, a persistência é feita através<br> do 
Spring Data JPA/Hibernate. <br><br> 
Suas principais entidades são:<br>  
Usuario  
Unidade  
Produto  
Pedido  
ItemPedido  
Estoque  
Pagamento   



## Principais regras de negócio
Ao criar um pedido, a aplicação:<br>

-Identifica o usuário autenticado;<br>
-Verifica a existência da unidade;<br>
-Verifica se a unidade está ativa;<br>
-valida os itens enviados;<br>
-verifica a existência dos produtos;<br>
-Verifica o estoque da unidade;<br>
-Verifica se a quantidade solicitada está disponível;<br>
-Calcula o sobtotal dos itens;<br>
-Calcula o valor total;<br>
-Atualiza o estoque;<br>
-Cria o pedido com status AGUARDANDO_PAGAMENTO.<br><br>
O preço do pedido é obtido a partir do produto cadastrado.<br>

**Status do pedido**

AGUARDANDO_PAGAMENTO<br>
PAGAMENTO_APROVADO ou CANCELADO<br>
EM_PREPARACAO<br>
PRONTO<br>
ENTREGUE

**Fluxo principal**

AGUARDANDO_PAGAMENTO -> PAGAMENTO_APROVADO -> EM_PREPARACAO<br>
-> PRONTO -> ENTREGUE


## Pagamento MOCK
O pagamento é simulado, não possui integração com um gateway real.<br><br>
Endpoint:<br>
POST/pagamentos/{id do pedido}?resultado=APROVADO<br><br>

Para simlular pagamento aprovado:<br>

resultado=APROVADO<br>

Para simular uma recusa:<br>

resultado=NEGADO

**Pagamento aprovado**

Pagamento -> APROVADO<br>
Pedido -> PAGAMENTO_APROVADO

**Pagamento negado**

Pagamento -> NEGADO<br>
Pedido -> CANCELADO

## Principais endpoints

**Autenticação**<br>
POST/auth/login

**Usuários**<br>
POST/usuarios<br>
GET/usuarios<br>
GET/usuarios/{id}<br>

**Pedidos**<br>
POST/pedidos<br>
GET/pedidos<br>
GET/pedidos{id}<br>
PATCH/pedidos/{id}/status<br>

**Pagamentos**<br>
POST/pagamentos/{id}?resultado=APROVADO<br>
POST/pagamentos/{id}?resultado=APROVADO<br>


# Como executar a aplicação
## 1.Requisitos
Antes de excutar a aplicação, é necessário ter instalado:<br><br>
-Java 17<br>
-MySQL<br>
-Eclipse ou outra IDE compatível com projetos Maven

## 2. Clonar o repositório
clone o projeto:  <br>
git clone [https://github.com/DeividSouza2207/RaizesDoNordeste](https://github.com/DeividSouza2207/RaizesDoNordeste)

Entre no diretório:

cd raizes_do_nordeste

## 3.Configurar o banco de dados
O projeto utiliza MySQL.
Crie o banco de dados utilizado pela aplicação.<br>

Certique-se de que o MySQL esteja em execução.

## 4.Configurar as variáveis de ambiente

O projeto utiliza variáveis de ambiente para evitar que informações sensíveis,<br>
 como senha do banco de dados e segredo JWT, sejam armazenadas diretamente no código.

Crie as variávei de ambiente no sistema operacional

As variáveis utilizadas pela aplicação são:  

DB_URL<br>
DB_USERNAME  
DB_PASSWORD  
JWT_SECRET
 
## 5. Instalar as dependências
 As dependências do projeto foram baixadas pelo Spring initializr<br><br>
[https://start.spring.io](https://start.spring.io)  <br>
Nele foram baixados o Maven e as demais dependências necessárias, <br>
que você deve descompactar e importar no projeto.

## 6. Executar a aplicação
A aplicação pode ser iniciada pelo Eclipse através da classe principal:

RaizesDoNordesteApplication

## 7. Acessar o Swagger/OpenAPI 

Com a aplicação em execução, acessar:

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

A documentação permite consultar os endpoints e excutar requisições diretamente pelo Swagger.

Os endpoints protegidos utilizam autenticação Bearer JWT.

## 8. Testes do Postman

A coleção utilizada nos testes está disponível no arquivo:

*Projeto Raízes do Nordeste.postman_collection.json*   
dentro de src/main/resources

Para executar os testes:

1. Abrir o Postman.
2. Importar a Collection.
3. Configure a variável {baseUrl} como:
[http://localhost:8080](http://localhost:8080)
4. Criar um usuario.
5. Executar o login para gerar o JWT.
6. Executar o login do COZINHEIRO para os testes de atualização de status.<br>
OBS: Por segurança, todo usuário quando cadastrado recebe a role CLIENTE,<br> para evitar
que um usuario comum escolha a sua própria role. Para executar os<br> testes como COZINHEIRO
deve ser criado um usuario novo, ou alterar a role<br> de um usuário existente direto no banco de dados.<br>
7. Executar os testes.

**Variáveis utilizadas**

*baseUrl*<br>
*Idpedido*


O *Idpedido* é preenchido automaticamente após a criação de um pedido.

## Cenários de teste
```
| ID    | Cenário                         | Método | Resultado |
| ----- | ------------------------------- | ------ | --------: |
| T01   | Login válido                    | POST   | *200  |
| T01-N | Login com credenciais inválidas | POST   | *401  |
| T02   | Acesso sem token                | GET    | *401  |
| T03   | Perfil sem permissão            | GET    | *403  |
| T04   | Criar pedido válido             | POST   | *201  |
| T05   | Pagamento aprovado              | POST   | *201  |
| T06   | Pedido em preparação            | PATCH  | *200  |
| T07   | Pedido pronto                   | PATCH  | *200  |
| T08   | Pedido entregue                 | PATCH  | *200  |
| T09   | Campo obrigatório ausente       | POST   | *400  |
| T10   | Quantidade inválida             | POST   | *400  |
| T11   | Produto inexistente             | POST   | *404  |
| T12   | Unidade inexistente             | POST   | *404  |
| T13   | Estoque insuficiente            | POST   | *409  |
| T15   | Pagamento duplicado             | POST   | *409  |
| T16   | Pagamento recusado              | POST   | *201  |
| T17   | Transição de status inválida    | PATCH  | *409  |
```



## Limatações atuais
**Nesta versão do projeto:**<br><br>
-Auditoria persistente de ações sensíveis não foi implementada;<br>
-O pagamento é uma simulação e não possui integração com um gateway real;<br>
-Recursos adicionais, como programa de fidelidade, podem ser <br>
implementados em versões futuras.<br> 

##Autor
DEIVID LUAN DE SOUZA











