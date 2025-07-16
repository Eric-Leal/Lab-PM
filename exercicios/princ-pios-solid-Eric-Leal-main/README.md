[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/4oilOx9r)


# Game Score Tracker - Relatório

Desenvolver um sistema em Java que permita:

- Registrar jogadores e diferentes tipos de jogos
- Registrar sessões de jogo
- Calcular e exibir rankings dos jogadores com base nas pontuações acumuladas
- Visualizar estatísticas individuais dos jogadores
- Adicionar novos tipos de jogos com regras de pontuação específicas

## Tipos de Jogos e Regras Implementadas

1. **Jogo de Futebol (FootballGame)**
   - Regra de pontuação: Vitória (3 pontos), Empate (1 ponto), Derrota (0 pontos)

2. **Jogo de Dados (DiceGame)**
   - Regra de pontuação: Vitória (2× pontuação da partida), Empate (pontuação da partida), Derrota (0 pontos)
   - Funcionalidade adicional: limite de tamanho do dado (até d20)

3. **Jogo de Cartas (CardGame)**
   - Adicionado para demonstrar o Princípio Aberto/Fechado
   - Regra de pontuação: Vitória (1 ponto), Derrota (0 pontos)

## Requisitos Definidos

1. **Cadastro de Jogadores**
   - O sistema permite cadastrar jogadores com nome
   - Cada jogador recebe um ID único automaticamente
   - É possível consultar jogadores por ID

2. **Cadastro de Jogos**
   - O sistema permite cadastrar diferentes tipos de jogos
   - Cada tipo de jogo possui suas próprias regras de pontuação
   - Suporta inicialmente três tipos de jogos:
     - Jogo de Futebol
     - Jogo de Dados
     - Jogo de Cartas

3. **Registro de Sessões de Jogo**
   - O sistema permite registrar sessões de jogo associando:
     - Um jogador
     - Um jogo
     - Um resultado (vitória, derrota, empate)
     - Uma pontuação específica da partida (quando aplicável)
   - Calcula e registra automaticamente os pontos do jogador conforme a regra do jogo

4. **Cálculo de Pontuação e Ranking**
   - O sistema calcula a pontuação dos jogadores para cada jogo
   - Gera rankings dos jogadores por jogo
   - Calcula a pontuação total de um jogador em todos os jogos

5. **Exibição de Estatísticas**
   - O sistema exibe estatísticas detalhadas dos jogadores
   - Mostra rankings para jogos específicos
   - Apresenta informações sobre pontos obtidos em cada jogo por jogador

6. **Extensibilidade para Novas Regras**
   - O sistema permite adicionar novos tipos de jogos sem modificar o código existente
   - Suporta diferentes regras de pontuação para cada jogo

## Princípios SOLID Aplicados

### S - Princípio da Responsabilidade Única

Cada classe no sistema tem uma responsabilidade bem definida:

- **Player**: Representa um jogador com seus dados básicos
- **PlayerScore**: Gerencia exclusivamente o armazenamento e cálculo das pontuações
- **Game** (e subclasses): Definem os diferentes tipos de jogos
- **ScoringRule**: Interface e implementações específicas para regras de pontuação
- **GameSession**: Gerencia uma sessão de jogo específica
- **PlayerManager**: Gerencia o conjunto de jogadores
- **GameManager**: Gerencia o conjunto de jogos
- **RankingService**: Fornece serviços de ranking e estatísticas
- **StatisticsReporter**: Responsável apenas pela formatação e apresentação das estatísticas

Cada classe tem uma única razão para mudar, seguindo o princípio SRP.

### O - Princípio do Aberto/Fechado

O sistema é aberto para extensão e fechado para modificação:

- Novos tipos de jogos podem ser adicionados estendendo a classe abstrata **Game**
- Novas regras de pontuação podem ser criadas implementando a interface **ScoringRule**
- O **CardGame** foi adicionado sem modificar nenhum código existente, demonstrando o OCP

### L - Princípio da Substituição de Liskov

As classes derivadas podem substituir suas classes base sem afetar o comportamento:

- **FootballGame**, **DiceGame** e **CardGame** podem ser usados onde um **Game** é esperado
- Todas as implementações de **ScoringRule** respeitam o contrato definido pela interface

### I - Princípio da Segregação de Interface

As interfaces são específicas para seus clientes:

- **ScoringRule** é uma interface coesa com apenas um método `calculateScore()`
- As classes não são forçadas a implementar métodos que não utilizam

### D - Princípio da Inversão de Dependência

O código depende de abstrações, não de implementações concretas:

- **Game** depende da abstração **ScoringRule**, não de implementações específicas
- **StatisticsReporter** depende da abstração **RankingService**
- **RankingService** depende da abstração **PlayerManager**
- **GameSession** trabalha com a abstração **Game**

