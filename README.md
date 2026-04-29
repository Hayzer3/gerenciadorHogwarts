# 🏰 Gerenciador de Hogwarts API

Uma API REST desenvolvida em Spring Boot para o gerenciamento de dados do universo de Harry Potter, focando na organização de casas, bruxos e suas varinhas.

## 👥 Integrantes do Grupo
* **Lucas Nunes Soares** - RM: 566503
* **Camily Vitoria** - RM: 566520
* **Eduarda Weiss** - RM: 564434

## 🎯 Objetivos do Projeto
O objetivo principal é gerenciar o ecossistema de Hogwarts através de um CRUD completo, garantindo a integridade dos dados e seguindo as melhores práticas de desenvolvimento Java com Spring Framework.

### Principais Funcionalidades:
* **Gestão de Casas:** Cadastro e manutenção das casas de Hogwarts (Grifinória, Sonserina, etc).
* **Matrícula de Bruxos:** Registro de alunos vinculados a uma casa específica, com controle de idade e status de sangue.
* **Inventário de Varinhas:** Gerenciamento das varinhas mágicas, garantindo que cada varinha pertença a um bruxo e possua materiais validados.
* **Filtros Avançados:** Consultas customizadas por fundador da casa, material da varinha e paginação de bruxos por idade.

## 🛠️ Tecnologias Utilizadas
* **Java 17+**
* **Spring Boot 3.x**
* **Spring Data JPA** (Persistência de dados)
* **H2 Database** (Banco de dados em memória para testes rápidos)
* **Bean Validation** (Validações de regras de negócio)
* **Lombok** (Produtividade e código limpo)

## 🏗️ Arquitetura e Diferenciais
Para este projeto, implementamos padrões de projeto modernos para garantir segurança e performance:
* **DTOs (Data Transfer Objects):** Separamos as classes de entrada (`Request`) das de saída (`Response`), protegendo as entidades do banco de dados.
* **Projections:** Otimizamos as consultas de listagem para retornar apenas os campos necessários, melhorando o tempo de resposta.
* **Custom Validators:** Criamos anotações personalizadas para validar os materiais permitidos nas varinhas.

## 🚀 Como Executar
1. Clone o repositório.
2. Certifique-se de ter o Maven instalado.
3. Execute o comando: `mvn spring-boot:run`
4. A API estará disponível em `http://localhost:8080`
5. O console do banco de dados H2 pode ser acessado em `http://localhost:8080/h2-console`
