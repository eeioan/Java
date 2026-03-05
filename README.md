# Java Solutions — Учебные проекты по Java

[![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=flat&logo=openjdk&logoColor=white)](https://www.java.com)
[![GitHub](https://img.shields.io/badge/GitHub-eeioan%2FJava-181717?style=flat&logo=github)](https://github.com/eeioan/Java)

> Репозиторий содержит решения учебных заданий по курсу **«Программирование на Java»** (ИТМО / КТ).  
> Автор: **Ivan Chumakov** · [@eeioan](https://github.com/eeioan)

> ⚠️ Весь код находится в ветке **[`master`](https://github.com/eeioan/Java/tree/master)**

---

## 📂 Структура проекта

```
java-solutions/
├── game/                          # Задание: Игра М,Н,К (крестики-нолики)
│   ├── Main.java                  # Точка входа, запуск турнира
│   ├── Game.java                  # Логика одной игры
│   ├── Board.java                 # Интерфейс доски
│   ├── AbstractBoard.java         # Абстрактная реализация доски
│   ├── TicTacToeBoard.java        # Доска для игры М,Н,К
│   ├── BoardShape.java            # Интерфейс формы доски
│   ├── Square.java                # Квадратная форма доски
│   ├── Rhomb.java                 # Ромбовидная форма доски
│   ├── Cell.java                  # Состояние клетки (X, O, EMPTY)
│   ├── Result.java                # Результат игры (WIN, LOSE, DRAW)
│   ├── Move.java                  # Ход игрока
│   ├── Position.java              # Интерфейс позиции
│   ├── Player.java                # Интерфейс игрока
│   ├── HumanPlayer.java           # Игрок-человек (ввод с консоли)
│   ├── RandomPlayer.java          # Случайный игрок
│   ├── SequencePlayer.java        # Игрок, ходящий по порядку
│   ├── CheaterPlayer.java         # Читерский игрок
│   ├── GameConfig.java            # Конфигурация игры (M, N, K)
│   ├── InputRead.java             # Утилита чтения ввода
│   ├── TournamentManager.java     # Менеджер турнира
│   ├── TournamentPair.java        # Пара игроков в турнире
│   └── TournamentPlayer.java      # Обёртка игрока для турнира
│
└── expression/                    # Задания 10–12: Арифметические выражения
    ├── AbstractBinary.java        # Абстрактный бинарный узел
    ├── Add.java                   # Сложение
    ├── Subtract.java              # Вычитание
    ├── Multiply.java              # Умножение
    ├── Divide.java                # Деление
    ├── Negate.java                # Унарный минус
    ├── Const.java                 # Константа
    ├── Variable.java              # Переменная (x, y, z)
    ├── ShiftLeft.java             # Битовый сдвиг влево <<
    ├── ShiftRight.java            # Битовый сдвиг вправо >>
    ├── ShiftLogical.java          # Логический сдвиг вправо >>>
    ├── BaseParser.java            # Абстрактный парсер символов
    ├── CharSource.java            # Интерфейс источника символов
    ├── StringSource.java          # Реализация CharSource
    ├── ExpressionParser.java      # Парсер выражений (пакет expression)
    ├── ExpressionM.java           # Интерфейс выражения
    ├── TripleExpression.java      # Интерфейс тройного выражения
    ├── ToMiniString.java          # Интерфейс для toString
    ├── parser/
    │   ├── BaseParser.java        # Базовый парсер (пакет parser)
    │   ├── StringSource.java      # StringSource (пакет parser)
    │   ├── ExpressionParser.java  # Парсер (пакет parser)
    │   ├── TripleParser.java      # Интерфейс парсера тройных выражений
    │   └── Operations.java        # Константы операций
    ├── exceptions/
    │   ├── DivisionByZero.java    # Исключение: деление на ноль
    │   └── OverflowException.java # Исключение: переполнение
    └── generic/
        ├── GenericExpression.java  # Интерфейс обобщённого выражения
        ├── Operation.java          # Интерфейс типизированных операций
        ├── GenericTabulator.java   # Табулятор (вычисление по 3D-сетке)
        ├── ExpressionParser.java   # Обобщённый парсер
        ├── BinaryExpression.java   # Абстрактный бинарный узел AST
        ├── Add.java                # Сложение (generic)
        ├── Subtract.java           # Вычитание (generic)
        ├── Multiply.java           # Умножение (generic)
        ├── Divide.java             # Деление (generic)
        ├── Min.java                # Минимум
        ├── Max.java                # Максимум
        ├── Negate.java             # Унарный минус (generic)
        ├── Const.java              # Константа (generic)
        ├── Variable.java           # Переменная (generic)
        ├── IntegerType.java        # int (с/без overflow-check)
        ├── DoubleType.java         # double
        ├── FloatType.java          # float
        ├── ShortType.java          # short
        ├── BigIntegerType.java     # BigInteger
        └── Tabulator.java          # Интерфейс табулятора
```

---

## 🛠 Реализованные задания

### 🎮 Задание: Игра М,Н,К (крестики-нолики)
> `java-solutions/game/`

- Обобщённая игра на доске размером **M×N** с победой при **K** в ряд
- Поддержка нескольких форм доски: **квадратная** (`Square`) и **ромбовидная** (`Rhomb`)
- Игроки: **человек** (консольный ввод), **случайный**, **последовательный**, **читер**
- **Турнирный режим**: менеджер турнира с парными матчами
- Обнаружение победы, ничьей и некорректных ходов
- Конфигурируемые параметры: M, N, K через `GameConfig`

---

### 📐 Задание 10 — Парсер выражений со сдвигами
> `java-solutions/expression/`

- Разбор арифметических выражений: `+`, `-`, `*`, `/`
- Поддержка битовых сдвигов: `<<`, `>>`, `>>>`
- Рекурсивный спуск на базе `BaseParser` + `CharSource`
- Корректная обработка пробелов и унарного минуса

---

### ⚠️ Задание 11 — Выражения с исключениями
> `java-solutions/expression/exceptions/`, `java-solutions/expression/parser/`

- Обработка деления на ноль (`DivisionByZero`)
- Обнаружение арифметического переполнения (`OverflowException`)
- Полноценный парсер в пакете `expression.parser`

---

### 🔣 Задание 12 — Обобщённые выражения (Generics)
> `java-solutions/expression/generic/`

- Параметрический интерфейс `GenericExpression<T>` и `Operation<T>`
- Поддержка **6 числовых режимов** через `GenericTabulator`:

| Режим | Тип | Особенности |
|-------|-----|-------------|
| "i" | `int` | С проверкой переполнения |
| "u" | `int` | Без проверки переполнения |
| "d" | `double` | — |
| "f" | `float` | — |
| "s" | `short` | — |
| "bi" | `BigInteger` | Произвольная точность |

- Операции `min` / `max` поверх обобщённого типа
- AST-узлы: `Add`, `Subtract`, `Multiply`, `Divide`, `Negate`, `Min`, `Max`, `Const`, `Variable`

---

## ⚙️ Технологии

| Инструмент | Описание |
|---|---|
| Java 17+ | Основной язык |
| Generics | Параметрический полиморфизм |
| BigInteger | Арифметика произвольной точности |
| Рекурсивный спуск | Разбор выражений без сторонних библиотек |
| OOP / Паттерны | Composite (AST), Strategy (Operation<T>), Template Method |

---

## 🚀 Запуск

```bash
# Компиляция игры МНК
javac -d out java-solutions/game/*.java

# Запуск игры
java -cp out game.Main

# Компиляция обобщённых выражений
javac -d out java-solutions/expression/generic/*.java java-solutions/expression/parser/*.java

# Пример режимов GenericTabulator:
# "i" | "u" | "d" | "f" | "s" | "bi"
```

---

## 📎 Ссылки

- 🔗 [Репозиторий на GitHub](https://github.com/eeioan/Java/tree/master)
- 📘 Курс: Программирование на Java, ИТМО КТ 2025

---

*Все решения написаны самостоятельно в рамках учебного курса.*