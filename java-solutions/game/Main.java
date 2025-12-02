package game;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        InputRead reader = new InputRead(sc);

        System.out.println("=== КРЕСТИКИ - НОЛИКИ ===");
        System.out.println("Choose mode: 1-Single Game, 2-Tournament");

        int mode = reader.readInt(1, 2);

        if (mode == 1) {
            playSingleGame(reader);
        } else {
            playTournament(reader);
        }
    }

    private static void playSingleGame(InputRead reader) {
        System.out.println("=== SINGLE GAME ");

        BoardShape shape = reader.readBoardShape();
        GameConfig config = reader.readGameConfig(shape);

        System.out.println("Choose players:");
        TournamentPlayer p1 = reader.readTournamentPlayers(1).get(0);
        TournamentPlayer p2 = reader.readTournamentPlayers(1).get(0);

        boolean showGame = reader.readYesNo("Show game moves?");

        Board board = new TicTacToeBoard(config.rows, config.cols, config.win, config.shape);
        Game game = new Game(board, p1.getPlayer(), p2.getPlayer(), showGame);

        System.out.println("=== GAME STARTED ===");
        int result = game.play();

        System.out.println("=== GAME RESULT ===");
        switch (result) {
            case 1 -> System.out.println(p1.getName() + " wins!");
            case 2 -> System.out.println(p2.getName() + " wins!");
            case 0 -> System.out.println("Ничья!");
            default -> System.out.println("Игра кончилась ошибкой");
        }
    }

    private static void playTournament(InputRead reader) {
        System.out.println("=== TOURNAMENT ===");

        BoardShape shape = reader.readBoardShape();
        GameConfig config = reader.readGameConfig(shape);
        int playerCount = reader.readPlayerCount();
        List<TournamentPlayer> players = reader.readTournamentPlayers(playerCount);
        boolean showSteps = reader.readShowSteps();
        boolean showAllGames = reader.readShowAllGames();

        System.out.println("=== TOURNAMENT SETUP ===");
        System.out.println("Board: " + config.shape.getClass().getSimpleName());
        System.out.println(config);
        System.out.println("Players:");
        for (TournamentPlayer player : players) {
            System.out.println("  " + player.getName() + " - " + player.getTypeString());
        }

        TournamentManager tournament = new TournamentManager(
                players, config, config.shape, showSteps, showAllGames);
        tournament.run();

        System.out.println("=== TOURNAMENT FINISHED ===");
    }
}