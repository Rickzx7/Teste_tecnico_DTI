# 📚 Sistema de Gerenciamento de Livros - Teste Técnico DTI

## 📋 Visão Geral

Sistema de gerenciamento de livros desenvolvido em **Java 21** com **Spring Boot 3.5.6**. A aplicação oferece uma interface de linha de comando (CLI) para gerenciar um catálogo de livros com operações CRUD completas, validações robustas e sistema de logs personalizados.

## 🎯 Recurso Principal: Gerenciamento de Livros

### 📖 Entidade Book

A aplicação gerencia livros através da entidade `Book` com as seguintes propriedades:

| Propriedade | Tipo | Obrigatório | Descrição |
|-------------|------|-------------|-----------|
| `id` | `Long` | ❌ | Identificador único (gerado automaticamente) |
| `title` | `String` | ✅ | Título do livro |
| `author` | `String` | ✅ | Nome do autor |
| `pages` | `Integer` | ✅ | Número de páginas (mínimo 1) |
| `publicationDate` | `LocalDate` | ✅ | Data de publicação (formato dd/MM/yyyy) |
| `description` | `String` | ❌ | Descrição opcional do livro |

### 🔍 Validações Implementadas

- **Título**: Obrigatório e não pode estar vazio
- **Autor**: Obrigatório e não pode estar vazio  
- **Páginas**: Obrigatório, deve ser um número inteiro maior que 0
- **Data de Publicação**: Formato obrigatório dd/MM/yyyy (ex: 15/03/2024)
- **Descrição**: Campo opcional, pode ser deixado em branco

## 🛠️ Tecnologias e Linguagem

### Linguagem Principal
- **Java 21** - Linguagem de programação principal

### Framework e Dependências
- **Spring Boot 3.5.6** - Framework principal
- **Spring Data JPA** - Persistência de dados
- **Spring Validation** - Validação de dados
- **Hibernate** - ORM (Object-Relational Mapping)
- **SQLite** - Banco de dados
- **Lombok** - Redução de código boilerplate
- **JUnit 5** - Framework de testes
- **Mockito** - Framework de mocking para testes
- **AssertJ** - Biblioteca de assertions fluentes

### Ferramentas de Build
- **Gradle** - Sistema de build e gerenciamento de dependências

## 📦 Pré-requisitos e Instalação

### 🔧 Dependências Necessárias

1. **Java Development Kit (JDK) 21**
   - **Download**: [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) ou [OpenJDK](https://openjdk.org/projects/jdk/21/)
   - **Instalação**: Execute o instalador e configure as variáveis de ambiente
   - **Verificação**:
     ```bash
     java --version
     # Deve mostrar: openjdk 21.x.x
     ```

2. **Docker e Docker Compose** (para execução em container)
   - **Windows/Mac**: [Docker Desktop](https://www.docker.com/products/docker-desktop/)
   - **Linux**: [Docker Engine](https://docs.docker.com/engine/install/) + [Docker Compose](https://docs.docker.com/compose/install/)
   - **Verificação**:
     ```bash
     docker --version
     docker compose --version
     ```

3. **Git** (para clonar o repositório)
   - **Download**: [Git Official](https://git-scm.com/downloads)
   - **Instalação**: Execute o instalador com configurações padrão
   - **Verificação**:
     ```bash
     git --version
     ```

### 📦 Dependências Automáticas

As seguintes dependências são baixadas automaticamente pelo Gradle:
- **Gradle Wrapper** (incluído no projeto)
- **Spring Boot 3.5.6** e todas as suas dependências
- **JUnit 5, Mockito, AssertJ** (para testes)
- **SQLite Driver** (banco de dados)
- **Lombok** (redução de código boilerplate)

### 📥 Instalação

1. **Clone o repositório**
   ```bash
   git clone <url-do-repositorio>
   cd teste_tecnico_dti
   ```

2. **Build do projeto**
   ```bash
   # Windows
   .\gradlew build -x test
   
   # Linux/Mac
   ./gradlew build -x test
   ```

## 🚀 Como Executar a Aplicação

### 🖥️ Execução Local

1. **Executar a aplicação**
   ```bash
   # Windows
   .\gradlew bootRun
   
   # Linux/Mac
   ./gradlew bootRun
   ```

2. **Verificar logs (em outro terminal)**
   ```bash
   # Windows PowerShell
   Get-Content logs\application-local.log -Wait
   
   # Linux/Mac
   tail -f logs/application-local.log
   ```

### 🐳 Execução com Docker

1. **Build da imagem Docker**
   ```bash
   docker compose build
   ```

2. **Executar no Docker**
   ```bash
   docker compose run --rm app
   ```

3. **Verificar logs do Docker**
   ```bash
   # Ver logs do container
   docker logs <container-id>
   
   # Ou ver arquivo de logs personalizados (se mapeado)
   Get-Content logs\personalized-logs.log -Wait
   ```

## 📚 Funcionalidades da Aplicação

### 🎮 Menu Principal

Ao executar a aplicação, você verá o seguinte menu:

```
Welcome to the System books

Choose an option:
1. List Books
2. Search Books for ID
3. Add Book
4. Update Book
5. Delete Book
0. Exit
Enter your choice:
```

### 📖 1. Listar Livros

**Descrição**: Exibe todos os livros cadastrados no sistema.

**Como usar**:
1. Digite `1` no menu principal
2. O sistema exibirá todos os livros no formato:
   ```
   ID: 1 | Title: Clean Code | Author: Robert Martin | Pages: 464 | Publication Date: 01/08/2008 | Description: A Handbook of Agile Software Craftsmanship
   ```

**Exemplo de uso**:
```
Enter your choice: 1
List of books in the system
ID: 1 | Title: Clean Code | Author: Robert Martin | Pages: 464 | Publication Date: 01/08/2008
ID: 2 | Title: Design Patterns | Author: Gang of Four | Pages: 395 | Publication Date: 21/10/1994
```

### 🔍 2. Buscar Livro por ID

**Descrição**: Busca um livro específico pelo seu ID.

**Como usar**:
1. Digite `2` no menu principal
2. Digite o ID do livro quando solicitado
3. O sistema exibirá o livro encontrado ou mensagem de "Book not found!"

**Exemplo de uso**:
```
Enter your choice: 2
Enter Book ID: 1
Book found: ID: 1 | Title: Clean Code | Author: Robert Martin | Pages: 464 | Publication Date: 01/08/2008
```

### ➕ 3. Adicionar Livro

**Descrição**: Adiciona um novo livro ao sistema com validações completas.

**Como usar**:
1. Digite `3` no menu principal
2. Preencha os dados solicitados:
   - **Título**: Obrigatório (não pode estar vazio)
   - **Autor**: Obrigatório (não pode estar vazio)
   - **Páginas**: Obrigatório (deve ser um número maior que 0)
   - **Data de Publicação**: Obrigatório (formato dd/MM/yyyy)
   - **Descrição**: Opcional (pressione Enter para pular)

**Exemplo de uso**:
```
Enter your choice: 3
=== Adding New Book ===
Enter Book Title: Effective Java
Enter Author: Joshua Bloch
Enter Number of Pages: 416
Enter Publication Date (dd/MM/yyyy): 28/05/2017
Enter Description (optional - press Enter to skip): Third Edition
Book added successfully! ID: 3
```

**Tratamento de Erros**:
```
Enter Book Title: 
Title is mandatory
Enter Book Title: Effective Java
```

### ✏️ 4. Atualizar Livro

**Descrição**: Atualiza os dados de um livro existente.

**Como usar**:
1. Digite `4` no menu principal
2. Digite o ID do livro a ser atualizado
3. Se o livro existir, preencha os novos dados
4. Os mesmos campos de validação da adição se aplicam

**Exemplo de uso**:
```
Enter your choice: 4
=== Updating Book ===
Enter Book ID: 1
Current book: ID: 1 | Title: Clean Code | Author: Robert Martin | Pages: 464 | Publication Date: 01/08/2008

Enter new information:
Enter Book Title: Clean Code - Updated Edition
Enter Author: Robert C. Martin
Enter Number of Pages: 464
Enter Publication Date (dd/MM/yyyy): 01/08/2008
Enter Description (optional - press Enter to skip): 
Book updated successfully!
```

### 🗑️ 5. Deletar Livro

**Descrição**: Remove um livro do sistema.

**Como usar**:
1. Digite `5` no menu principal
2. Digite o ID do livro a ser deletado
3. O sistema confirmará a exclusão ou informará se o livro não foi encontrado

**Exemplo de uso**:
```
Enter your choice: 5
Enter Book ID: 2
Book deleted successfully!
```

### 🚪 0. Sair

**Descrição**: Encerra a aplicação.

**Como usar**:
1. Digite `0` no menu principal
2. A aplicação será encerrada com a mensagem: "System shutting down... byee"

## 🧪 Executando Testes

### 🎯 Testes Unitários

```bash
# Executar todos os testes
.\gradlew test

# Executar teste específico
.\gradlew test --tests "DateUtilsTest"

# Executar testes com relatório de cobertura
.\gradlew test jacocoTestReport
```

### 📊 Cobertura de Testes

O projeto inclui testes unitários para:
- `DateUtils` - Utilitários de data
- `BookService` - Lógica de negócio
- `ValidationUtils` - Validações
- `BookDto` - Transfer Object
- `BookValidator` - Validação interativa
- `CommandLineApp` - Interface de linha de comando

## 📁 Estrutura do Projeto

```
src/
├── main/
│   ├── java/br/com/testedit/teste_tecnico_dti/
│   │   ├── cli/
│   │   │   └── CommandLineApp.java          # Interface de linha de comando
│   │   ├── entities/
│   │   │   └── Book.java                    # Entidade JPA
│   │   ├── repository/
│   │   │   └── BookRepository.java          # Repositório JPA
│   │   ├── service/
│   │   │   └── BookService.java             # Lógica de negócio
│   │   ├── dto/
│   │   │   └── BookDto.java                 # Data Transfer Object
│   │   ├── util/
│   │   │   ├── BookValidator.java           # Validação interativa
│   │   │   ├── DateUtils.java               # Utilitários de data
│   │   │   ├── ValidationResult.java        # Resultado de validação
│   │   │   └── ValidationUtils.java         # Utilitários de validação
│   │   └── TesteTecnicoDtiApplication.java  # Classe principal
│   └── resources/
│       ├── application.properties           # Configurações locais
│       ├── application-docker.properties    # Configurações Docker
│       └── schema.sql                       # Schema do banco
├── test/
│   └── java/br/com/testedit/teste_tecnico_dti/
│       ├── cli/
│       ├── service/
│       ├── dto/
│       └── util/
logs/                                        # Diretório de logs
├── application-local.log                    # Logs da execução local
└── personalized-logs.log                    # Logs personalizados da execução Docker
```

## 🔧 Configurações

### 📊 Banco de Dados

- **Tipo**: SQLite
- **Arquivo**: `database.db` (local) / `/app/data/database.db` (Docker)
- **Dialeto**: `org.hibernate.community.dialect.SQLiteDialect`

### 📝 Logs

- **Local**: `logs/application-local.log`
- **Docker**: `logs/personalized-logs.log`
- **Nível**: INFO para classes da aplicação
- **Console**: Desabilitado para execução local e Docker

#### 🔍 Logs Personalizados

A aplicação utiliza logs personalizados com `@Slf4j` para rastrear:
- Inicialização da aplicação
- Operações CRUD de livros
- Validações de dados
- Erros e exceções

**Para ver logs em tempo real:**
```bash
# Local
Get-Content logs\application-local.log -Wait

# Docker
Get-Content logs\personalized-logs.log -Wait
```

## 🐛 Troubleshooting

### ❌ Problemas Comuns

1. **Erro de Java Version**
   ```
   Solution: Verificar se Java 21 está instalado
   java --version
   ```

2. **Erro de Build**
   ```
   Solution: Limpar e rebuildar
   .\gradlew clean build -x test
   ```

3. **Erro de Docker**
   ```
   Solution: Verificar se Docker está rodando
   docker --version
   docker compose build
   ```

4. **Erro de Validação**
   ```
   Solution: Verificar formato da data (dd/MM/yyyy)
   Exemplo: 15/03/2024
   ```

5. **Problemas com Logs**
   ```
   Solution: Verificar se os diretórios de logs existem
   Local: logs/application-local.log
   Docker: logs/personalized-logs.log
   ```

## 📞 Suporte

Para dúvidas ou problemas:
1. Verificar os logs em `logs/application-local.log` (local) ou `logs/personalized-logs.log` (Docker)
2. Executar os testes para verificar integridade
3. Consultar a documentação das tecnologias utilizadas

---

