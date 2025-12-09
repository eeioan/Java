package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputRead {
    private final Scanner sc;

    public InputRead(Scanner sc) {
        this.sc = sc;
    }

    BoardShape shape;

    public BoardShape readBoardShape() {
        System.out.println("Choose board shape: 1-Rhomb, 2-Rectangle");
        while (true) {
            try {
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        shape = new Rhomb();
                        break;
                    case 2:
                        shape = new Square();
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
                return shape;
            } catch (Exception e) {
                System.out.println("Invalid input! Enter 1 or 2:");
                sc.nextLine();
            }
        }
    }

    public GameConfig readGameConfig(BoardShape shape) {
        System.out.println("Enter rows, cols, win (e.g., 3 3 3):");
        while (true) {
            try {
                int rows = sc.nextInt();
                int cols = sc.nextInt();
                int win = sc.nextInt();
                if (shape instanceof Rhomb) {
                    if (rows > 0 && cols > 0 && win > 0 && win <= Math.max(2 * rows - 1, 2 * cols - 1)) {
                        return new GameConfig(rows, cols, win, shape);
                    }
                } else if (shape instanceof Square) {
                    if (rows > 0 && cols > 0 && win > 0 && win <= Math.max(rows, cols)) {
                        return new GameConfig(rows, cols, win, shape);
                    }
                }
                System.out.println("Invalid parameters! win must be <= max(rows,cols). Try again:");
            } catch (Exception e) {
                System.out.println("Invalid input! Enter three positive numbers:");
                sc.nextLine();
            }
        }
    }

    public int readInt(int min, int max) {
        while (true) {
            try {
                int value = sc.nextInt();
                if (value >= min && value <= max) return value;
                System.out.printf("Enter number between %d and %d:%n", min, max);
            } catch (Exception e) {
                System.out.println("Invalid input! Enter a number:");
                sc.nextLine();
            }
        }
    }

    public int readPlayerCount() {
        System.out.println("Enter number of players:");
        while (true) {
            try {
                int count = sc.nextInt();
                if (count >= 2) return count;
                System.out.println("Must be at least 2 players:");
            } catch (Exception e) {
                System.out.println("Invalid input! Enter a number:");
                sc.nextLine();
            }
        }
    }

    public List<TournamentPlayer> readTournamentPlayers(int count) {
        List<TournamentPlayer> players = new ArrayList<>();
        System.out.println("Choose player types (1-Human, 2-Random, 3-Sequence):");

        for (int i = 0; i < count; i++) {
            System.out.println("Player " + (i + 1) + " (Player" + (i + 1) + ") type:");
            int type = readPlayerType();
            String name = "Player" + (i + 1);
            players.add(new TournamentPlayer(name, createPlayer(type), type));
        }
        return players;
    }

    private int readPlayerType() {
        while (true) {
            try {
                int type = sc.nextInt();
                if (type >= 1 && type <= 3) return type;
                throw new IllegalArgumentException();
            } catch (Exception e) {
                System.out.println("Invalid type! Enter 1, 2 or 3:");
                sc.nextLine();
            }
        }
    }

    private Player createPlayer(int type) {
        return switch (type) {
            case 1 -> new HumanPlayer();
            case 2 -> new RandomPlayer();
            case 3 -> new SequencePlayer();
            default -> throw new IllegalArgumentException();
        };
    }

    public boolean readShowSteps() {
        return readYesNo("Show tournament steps?");
    }

    public boolean readShowAllGames() {
        return readYesNo("Show all games?");
    }

    public boolean readYesNo(String prompt) {
        System.out.println(prompt + " (y/n):");
        while (true) {
            String input = sc.next().trim().toLowerCase();
            if (input.equals("y") || input.equals("yes")) return true;
            if (input.equals("n") || input.equals("no")) return false;
            System.out.println("Please enter 'y' or 'n':");
        }
    }
}