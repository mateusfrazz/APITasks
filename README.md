# 📝 TodoList API

API REST desenvolvida em **Java + Spring Boot** para gerenciamento de tarefas, com foco em segurança e boas práticas.

## 🚀 Tecnologias
- **Java 17+**
- **Spring Boot 3**
- **Spring Data JPA**
- **H2 Database** (ambiente de desenvolvimento)
- **JWT** para autenticação
- **BCrypt** para criptografia de senhas

## 🔐 Segurança
- Todas as senhas são criptografadas usando **BCrypt** antes de serem salvas no banco.
- Autenticação e autorização baseadas em **JWT** (JSON Web Token).
- Filtro personalizado garantindo que apenas usuários autenticados acessem suas próprias tarefas.

## 📌 Funcionalidades
- Registro de usuários com criptografia de senha
- Login e geração de token JWT
- Criação, listagem e atualização de tarefas
- Associação de tarefas ao usuário autenticado
- Banco em memória **H2** para testes rápidos

## ▶️ Como rodar o projeto
1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/todolist-api.git

**📂 Estrutura principal

UserModel → Representa o usuário (com senha criptografada).

TaskModel → Representa a tarefa.

FilterTaskAuth → Filtro que valida o token JWT em cada requisição.**

📌 Próximos passos

Implementar documentação com Swagger/OpenAPI

Suporte a bancos de dados relacionais (PostgreSQL)

Deploy em nuvem (Heroku/AWS)
