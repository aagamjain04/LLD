
# Tic Tac Toe - Low Level Design (LLD)

## Overview

This is a CLI-based implementation of the Tic Tac Toe game for two players. The design emphasizes OOP principles such as inheritance, abstraction, and polymorphism. 
It supports two types of players (`X` and `O`) making moves on a shared board until one wins or the game ends in a draw.

---

## Requirements
- The Tic-Tac-Toe game should be played on a 3x3 grid.
- Two players take turns marking their symbols (X or O) on the grid.
- The first player to get three of their symbols in a row (horizontally, vertically, or diagonally) wins the game.
- If all the cells on the grid are filled and no player has won, the game ends in a draw.
- The game should have a user interface to display the grid and allow players to make their moves.
- The game should handle player turns and validate moves to ensure they are legal.
- The game should detect and announce the winner or a draw at the end of the game.
---

## Class Design

### 1. **Board**
- **Responsibility**: Maintains game state and provides utility to print and check board conditions.
- **Key Fields**:
    - `int size`: board size (default 3x3)
    - `char[][] board`: current game state
- **Key Methods**:
    - `boolean isValidMove(int row, int col)`
    - `void makeMove(int row, int col, char symbol)`
    - `boolean checkWinner(char symbol)`
    - `boolean isBoardFull()`
    - `void printBoard()`

---

### 2. **Player**
- **Responsibility**: Represents a player in the game.
- **Key Fields**:
    - `String name`
    - `char symbol`
- **Key Method**:
    - `PlayerMove makeMove(Board board)` *(abstract)*

---

### 3. **PlayerMove (Abstract Class)**
- **Responsibility**: Represents a move made by a player.
- **Key Fields**:
    - `int row`, `int col`
    - `MoveType moveType` (`X` or `O`)
- **Subclasses**:
    - `PlayerMoveX`
    - `PlayerMoveO`

---

### 4. **PlayerMoveX / PlayerMoveO**
- **Responsibility**: Specialized move representations for player X and O.
- **Constructors**:
    - Accepts `row`, `col` and assigns `MoveType.X` or `MoveType.O` accordingly.

---

### 5. **MoveType (Enum)**
- **Values**:
    - `X`, `O`
- **Responsibility**: Represents the type of move.

---

### 6. **TicTacToeGame**
- **Responsibility**: Coordinates the game play loop.
- **Key Fields**:
    - `Board board`
    - `Player player1`, `player2`
- **Key Method**:
    - `void startGame()`: handles turns, checks for winners or draw.

---

### 7. **Main**
- **Responsibility**: Entry point of the application.
- **Creates**: players, game instance and starts the game.

---

## 📌 Class Diagram (Textual Representation)

```
        +------------------+
        |     Player       |<--------------+
        +------------------+               |
        | - name: String   |               |
        | - symbol: char   |               |
        +------------------+               |
        | +makeMove()      |               |
        +------------------+               |
               ^                          |
               |                          |
     +------------------+       +------------------+
     |  PlayerX         |       |  PlayerO         |
     +------------------+       +------------------+

        +------------------+
        |    Board         |
        +------------------+
        | - size: int      |
        | - board: char[][]|
        +------------------+

        +---------------------+
        |   PlayerMove        |<--------------+
        +---------------------+               |
        | - row, col: int     |               |
        | - moveType: MoveType|               |
        +---------------------+               |
               ^                              |
     +---------------------+      +---------------------+
     |   PlayerMoveX       |      |   PlayerMoveO       |
     +---------------------+      +---------------------+

        +---------------------+
        | TicTacToeGame       |
        +---------------------+
        | - board: Board      |
        | - player1, player2  |
        +---------------------+

        +------------+
        |   Main     |
        +------------+
```

---

## Sequence Flow

1. Main method initializes players and game.
2. Game starts, alternates between `player1` and `player2`.
3. Each player makes a move using `makeMove()`.
4. Board is updated and printed.
5. After every move:
    - Check if current player has won → End
    - If board is full → Draw
6. Continue until win/draw.

---

## ⚙Design Principles Used

- **OOP Principles**:
    - Abstraction (`PlayerMove`, `Player`)
    - Inheritance (`PlayerMoveX`, `PlayerMoveO`)
    - Polymorphism (makeMove method in `Player`)
- **Encapsulation**: Board state and validation is hidden inside `Board`.
- **Open/Closed Principle**: New types of players or boards can be added easily.
- **Single Responsibility**: Each class has a focused purpose.
