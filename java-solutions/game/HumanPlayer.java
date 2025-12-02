package game;

import java.io.PrintStream;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private Scanner in;
    private PrintStream out;

    public HumanPlayer(Scanner in, PrintStream out) {
        this.in = in;
        this.out = out;
    }

    public HumanPlayer() {
        this(new Scanner(System.in), System.out);
    }

    @Override
    public Move makeMove(Position position) {
        out.println("Your turn " + position.getTurn());
        while (true) {
            if (in.hasNextInt()) {
                int row = in.nextInt();
                if (in.hasNextInt()) {
                    int col = in.nextInt();
                    Move move = new Move(row - 1, col - 1, position.getTurn());
                    if (position.isValid(move)) {
                        return move;
                    }
                    out.println("Ход невозможен: клетка занята или вне поля");
                    continue;
                }
            }
            in.nextLine();
            out.println("Неправильный ввод, нужны 2 цифры");
        }
    }
    //исправил проверку HumanPlayer на неверный ход - (вводить цифры и не промахиваться по клеткам)
    }

