# RAÍZES DO NORDESTE - API
Esta é uma API REST criada para o projeto acadêmico "Raízes do Nordeste.  
O projeto busca atender as necessidades de uma rede de lanchonetes<br>
especializada em produtos da culinária nordestina.

## Sobre o projeto
O projeto é um MVP que utiliza APIs Rest, possui controle de estoque,<br> gerenciamento de pedidos,
pagamento MOCK, atualização do status dos pedidos,<br> validação dos dados de entrada
 e autenticação e autorização com token JWT.<br> A documentação é feita com Swagger/OpenAPI.

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
A aplicação segue uma arquitetura em camadas:

## Autenticação e autorização
A API utiliza autenticação baseada em token JWT

## Variável de ambiente
É necessário configurar uma variável de ambiente para que o segredo no token<br>
não fique armazenado no código fonte.<BR>  
jwt.secret=${JWT_SECRET}<BR>  
Configurar a variável de ambiente:<br>  
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


## Documentação
**Swagger/OpenAPI**<br>
Com a aplicação em execução, a documentação pode ser acessada em:
http://localhost:8080/swagger-ui/index.html
A API usa autenticação Bearer JWT nos endpoints.


## Teste
**Postman**


# Como executar
## 1. Clone o repositório
git clone https://github.com/DeividSouza2207/RaizesDoNordeste

Entre no diretório:

cd raizes_do_nordeste

## 2.Configure o banco
Crie o banco de dados para persistir os dados da aplicação.
O banco utilizado no projeto foi o MySQL.

## 3.Configure as variáveis de ambiente
url=${DB_URL}  
username=${DB_USERNAME}  
password=${DB_PASSWORD}  
secret=${JWT_SECRET}  
## 4.Execute a aplicação  
Execute a classe principal(RaizesDoNordesteApplication)pelo Eclipse.

## 5.Acesse a documentação
http://localhost:8080/swagger-ui/index.html

## Limatações atuais
Nesta versão do projeto:<br><br>
-Auditoria persistente não foi implementada;  
-Alguns erros de regras de negócio retornam HTTP 500;    
-Produto e unidade inexistente ainda podem ser aprimorados para   
retornar códigos HTTP mais específicos.

## Evoluções futuras

Futuramente pode ser implementado um programa de fidelidade.








