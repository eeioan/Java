# Java Solutions — Учебные проекты по Java

[![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=flat&logo=openjdk&logoColor=white)](https://www.java.com)
[![GitHub](https://img.shields.io/badge/GitHub-eeioan%2FJava-181717?style=flat&logo=github)](https://github.com/eeioan/Java)

> Репозиторий содержит решения учебных заданий по курсу **«Программирование на Java»** (ИТМО / КТ).  
> Автор: **Ivan Chumakov** (eeioan)

---

## 📂 Структура проекта

```
java-solutions/
└── expression/
    ├── *.java                    # Базовые классы выражений
    ├── parser/
    │   ├── BaseParser.java       # Абстрактный парсер
    │   ├── ExpressionParser.java # Парсер арифметических выражений
    │   ├── StringSource.java     # Источник символов для парсера
    │   └── Operations.java       # Набор операций (ADD, SUB, MUL, DIV, ...)
    ├── exceptions/
    │   ├── DivisionByZero.java   # Исключение деления на ноль
    │   └── OverflowException.java# Исключение переполнения
    └── generic/
        ├── GenericExpression.java # Обобщённый интерфейс выражения
        ├── GenericTabulator.java  # Табулятор для вычисления по сетке
        ├── ExpressionParser.java  # Обобщённый парсер выражений
        ├── Operation.java         # Интерфейс типизированных операций
        ├── IntegerType.java       # Реализация для int (с/без overflow-check)
        ├── DoubleType.java        # Реализация для double
        ├── FloatType.java         # Реализация для float
        ├── ShortType.java         # Реализация для short
        ├── BigIntegerType.java    # Реализация для BigInteger
        ├── BinaryExpression.java  # Абстрактный бинарный узел AST
        ├── Add.java / Subtract.java / Multiply.java / Divide.java
        ├── Min.java / Max.java    # Операции min/max
        ├── Negate.java            # Унарный минус
        ├── Const.java             # Константный узел
        └── Variable.java          # Переменная (x, y, z)
```

---

## 🛠 Реализованные задания

### Задание 10 — Парсер выражений со сдвигами
- Разбор арифметических выражений: `+`, `-`, `*`, `/`
- Поддержка битовых сдвигов: `<<`, `>>`, `>>>`
- Собственный рекурсивный спуск на базе `BaseParser` + `CharSource`
- Корректная обработка пробелов и унарного минуса

### Задание 11 — Выражения с исключениями
- Обработка деления на ноль (`DivisionByZero`)
- Обнаружение арифметического переполнения (`OverflowException`)
- Полноценный парсер в пакете `expression.parser`

### Задание 12 — Обобщённые выражения (Generics)
- Параметрический интерфейс `GenericExpression<T>` и `Operation<T>`
- Поддержка 6 числовых режимов через `GenericTabulator`:
  - `"i"` — `int` с проверкой переполнения
  - `"u"` — `int` без проверки переполнения
  - `"d"` — `double`
  - `"f"` — `float`
  - `"s"` — `short`
  - `"bi"` — `BigInteger`
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
| OOP / Паттерны | Composite (AST), Strategy (Operation<T>) |

---

## 🚀 Запуск

```bash
# Компиляция
javac -d out java-solutions/expression/generic/*.java java-solutions/expression/parser/*.java

# Пример использования GenericTabulator
# mode: "i" | "u" | "d" | "f" | "s" | "bi"
```

---

## 📎 Ссылки

- 🔗 [Репозиторий на GitHub](https://github.com/eeioan/Java)
- 📘 Курс: Программирование на Java, ИТМО КТ 2025

---

*Все решения написаны самостоятельно в рамках учебного курса.*