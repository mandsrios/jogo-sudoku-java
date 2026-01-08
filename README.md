
# ☕  Sudoku Game em Java

Este projeto consiste em um jogo de Sudoku desenvolvido em Java, criado como parte do Bootcamp da DIO. A aplicação implementa toda a lógica do jogo, incluindo validações completas das regras do Sudoku e o controle do estado da partida durante a execução.

O jogo permite iniciar uma nova partida a partir de uma configuração inicial de tabuleiro, informada como argumento na execução do programa, possibilitando diferentes cenários de jogo.

O principal objetivo do projeto foi aplicar, na prática, os conceitos e princípios da Programação Orientada a Objetos (POO), como encapsulamento, separação de responsabilidades e organização do código, resultando em uma aplicação funcional e bem estruturada.

A aplicação pode ser executada de duas formas:

- Via terminal (CLI)
- Por meio de uma interface gráfica (GUI) desenvolvida com a biblioteca Swing

# 🛠️ Tecnologias Utilizadas
- Java 17+
- Java Swing (para a interface gráfica)
- Git e GitHub (para versionamento)
- Programação Orientada a Objetos
- Stream API

# 🎮 Funcionalidades

- **🧩 Motor completo de Sudoku:**
Implementa toda a lógica do jogo, respeitando o layout tradicional do Sudoku, facilitando a visualização do tabuleiro.

- **✏️ Preenchimento interativo das células:**
Validação automática dos números inseridos de acordo com as regras do jogo.

- **🔄 Botão “Resetar Jogo”:**
Reinicia a partida, limpando todas as jogadas realizadas pelo usuário.

- **🔍 Botão “Verificar Status”:**
Analisa o estado atual do tabuleiro e informa se o jogo está completo, incompleto ou contém inconsistências.

- **🏁 Botão “Finalizar Jogo”:**
Valida a solução final e fornece feedback ao usuário caso o Sudoku esteja corretamente resolvido.

- **🔒 Células fixas (pré-preenchidas):**
Pprotegidas contra edição, garantindo a integridade da configuração inicial do tabuleiro.

# 📌 Uso/Exemplos:

## Configuração inicial do tabuleiro

O comando abaixo inicializa o jogo com a configuração que foi especificada, criando um modelo tabuleiro.

```java
java -jar sudoku.jar "0,0;4,false 1,0;7,false 2,0;9,true 3,0;5,false 4,0;8,true 5,0;6,true 6,0;2,true 7,0;3,false 8,0;1,false 0,1;1,false 1,1;3,true 2,1;5,false 3,1;4,false 4,1;7,true 5,1;2,false 6,1;8,false 7,1;9,true 8,1;6,true 0,2;2,false 1,2;6,true 2,2;8,false 3,2;9,false 4,2;1,true 5,2;3,false 6,2;7,false 7,2;4,false 8,2;5,true 0,3;5,true 1,3;1,false 2,3;3,true 3,3;7,false 4,3;6,false 5,3;4,false 6,3;9,false 7,3;8,true 8,3;2,false 0,4;8,false 1,4;9,true 2,4;7,false 3,4;1,true 4,4;2,true 5,4;5,true 6,4;3,false 7,4;6,true 8,4;4,false 0,5;6,false 1,5;4,true 2,5;2,false 3,5;3,false 4,5;9,false 5,5;8,false 6,5;1,true 7,5;5,false 8,5;7,true 0,6;7,true 1,6;5,false 2,6;4,false 3,6;2,false 4,6;3,true 5,6;9,false 6,6;6,false 7,6;1,true 8,6;8,false 0,7;9,true 1,7;8,true 2,7;1,false 3,7;6,false 4,7;4,true 5,7;7,false 6,7;5,false 7,7;2,true 8,7;3,false 0,8;3,false 1,8;2,false 2,8;6,true 3,8;8,true 4,8;5,true 5,8;1,false 6,8;4,true 7,8;7,false 8,8;9,false"
```

## Execução do projeto

### Via Terminal (Console)

- **_Navegue até a pasta 'src' do projeto_**
```bash
cd Sudoko_Project/src
```

- **_Compile os arquivos Java_**
```bash
javac *.java model/*.java service/*.java util/*.java
```

- **_Execute a classe principal do modo console_**
```bash
java Main
```

### Com Interface Gráfica (Swing)

- **_Navegue até a pasta 'src' do projeto_**

```bash
cd Sudoko_Project/src
```

- **_Compile os arquivos Java, incluindo os da UI_**
```bash
javac *.java model/*.java service/*.java util/*.java ui_custom/screen/*.java ui_custom/panel/*.java ui_custom/input/*.java
```

- **_Execute a classe principal da UI_**
```bash
java UIMain
```

### Via IDE

- Importe o projeto em sua IDE (IntelliJ, Eclipse, etc.).
- Para a versão console, execute o método main da classe Main.java.
- Para a versão gráfica, execute o método main da classe UIMain.java.


