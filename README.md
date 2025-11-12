Pipocando – App de Filmes com Room, Retrofit e MVVM

O Pipocando é um aplicativo Android desenvolvido em Kotlin com Jetpack Compose que permite:

- Buscar filmes em uma API pública (OMDb)
- Ver detalhes dos filmes  
- Avaliar (nota de 0 a 5) e marcar como “assistido” ou “quero ver” 
- Salvar avaliações localmente usando Room 
- Fazer login simples com usuário salvo no banco local  

O projeto foi desenvolvido com foco em:
- Arquitetura MVVM
- Persistência com Room
- Navegação com Navigation Compose
- Consumo de API com Retrofit


Integrantes e Contribuições

Gabriel Balan – RA: 29253039  
- Implementação da arquitetura principal (MVVM  
- Configuração e estruturação do Navigation Compose  
- Desenvolvimento da tela HomeScreen e fluxo de navegação  
- Organização da estrutura de pastas e boas práticas do projeto  

Luiz Felipe Hoffmann Kuklik – RA: 34000844  
- Implementação da interface de usuário com Jetpack Compose  
- Desenvolvimento das telas LoginScreen SearchScreen DetailScreen  
- Integração da interface com ViewModels (StateFlow, eventos, entradas de usuário)  
- Tratamento visual (LazyColumn, TextFields, Material3, responsividade)

Lucas Fontanelli Reinaldim – RA: 32702728  
- Implementação da camada de **persistência (Room) 
- Criação das entidades (`UserEntity`, `MovieEntity`)  
- Desenvolvimento dos DAOs com CRUD completo  
- Integração com Retrofit + OMDb API  
- Desenvolvimento dos repositórios (`AuthRepository`, `MovieRepository`)

> Todos os membros trabalharam conjuntamente na validação, testes e documentação final.


Tecnologias Utilizadas

- Linguagem: Kotlin  
- UI: Jetpack Compose + Material 3  
- Arquitetura: MVVM  
- Navegação: Navigation Compose  
- Persistência: Room  
- Network: Retrofit + Gson  
- Imagens: Coil  
- Reatividade: StateFlow + Coroutines  


Estrutura Geral do Código

app/src/main/java/com/example/pipocando_oficial
├── MainActivity.kt
├── ui
│   ├── navigation
│   │   └── AppNavGraph.kt         Rotas e NavHost
│   └── screens
│       ├── LoginScreen.kt
│       ├── HomeScreen.kt
│       ├── SearchScreen.kt
│       ├── DetailScreen.kt
│       └── ProfileScreen.kt
├── viewmodel
│   ├── LoginViewModel.kt
│   └── SearchViewModel.kt
├── data
│   ├── local
│   │   ├── AppDatabase.kt
│   │   ├── MovieDao.kt
│   │   ├── MovieEntity.kt
│   │   ├── UserDao.kt
│   │   └── UserEntity.kt
│   ├── network
│   │   ├── OmdbApi.kt
│   │   ├── OmdbService.kt
│   │   ├── OmdbShort.kt
│   │   └── OmdbDetail.kt
│   └── repository
│       ├── MovieRepository.kt
│       └── AuthRepository.kt
└── core
    ├── Prefs.kt                    Armazena sessão logada
    └── Security.kt                 Hash de senha (SHA-256)
