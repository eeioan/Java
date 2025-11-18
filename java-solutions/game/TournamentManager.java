package game;

import java.util.*;

public class TournamentManager {
    private final List<TournamentPlayer> players;
    private final GameConfig config;
    private final BoardShape shape;
    private final boolean showSteps;
    private final boolean showAllGames;
    private final List<TournamentPair> pairs;

    public TournamentManager(List<TournamentPlayer> players, GameConfig config,
                             BoardShape shape, boolean showSteps, boolean showAllGames) {
        this.players = players;
        this.config = config;
        this.shape = shape;
        this.showSteps = showSteps;
        this.showAllGames = showAllGames;
        this.pairs = generateRandomPairs();
    }

    private List<TournamentPair> generateRandomPairs() {
        List<TournamentPair> randomPairs = new ArrayList<>();
        List<TournamentPlayer> shuffledPlayers = new ArrayList<>(players);
        Collections.shuffle(shuffledPlayers);

        if (shuffledPlayers.size() % 2 != 0) {
            TournamentPlayer byePlayer = shuffledPlayers.remove(shuffledPlayers.size() - 1);
            byePlayer.addWin();
        }

        for (int i = 0; i < shuffledPlayers.size(); i += 2) {
            TournamentPair pair = new TournamentPair(shuffledPlayers.get(i), shuffledPlayers.get(i + 1));
            randomPairs.add(pair);
        }

        return randomPairs;
    }

    public void run() {
        playTournament();
        displayResults();
    }

    private void playTournament() {
        if (showSteps) {
            System.out.println("\n--- Playing Matches ---");
        }

        for (int i = 0; i < pairs.size(); i++) {
            TournamentPair pair = pairs.get(i);

            if (showSteps) {
                System.out.println("\nMatch " + (i + 1) + "/" + pairs.size() +
                        ": " + pair.getPlayer1().getName() + " vs " + pair.getPlayer2().getName());
            }

            playMatch(pair);
        }
    }

    private void playMatch(TournamentPair pair) {
        TournamentPlayer p1 = pair.getPlayer1();
        TournamentPlayer p2 = pair.getPlayer2();

        Board board = new TicTacToeBoard(config.rows, config.cols, config.win, shape);
        Game game = new Game(board, p1.getPlayer(), p2.getPlayer(), showAllGames);

        int result = game.play();

        if (result == 1) {
            p1.addWin();
            p2.addLoss();
            if (showSteps) System.out.println("Winner: " + p1.getName());
        } else if (result == 2) {
            p2.addWin();
            p1.addLoss();
            if (showSteps) System.out.println("Winner: " + p2.getName());
        } else if (result == 0) {
            p1.addDraw();
            p2.addDraw();
            if (showSteps) System.out.println("Result: Draw");
        } else {
            p1.addLoss();
            p2.addLoss();
            if (showSteps) System.out.println("Result: Both lose (error)");
        }
    }

    private void displayResults() {
        System.out.println("\n=== FINAL RESULTS ===");

        List<TournamentPlayer> rankedPlayers = new ArrayList<>(players);
        rankedPlayers.sort((a, b) -> {
            int pointsCompare = Integer.compare(b.getPoints(), a.getPoints());
            if (pointsCompare != 0) return pointsCompare;
            int winsCompare = Integer.compare(b.getWins(), a.getWins());
            if (winsCompare != 0) return winsCompare;
            return Integer.compare(a.getLosses(), b.getLosses());
        });

        displayFinalRanking(rankedPlayers);
    }

    private void displayFinalRanking(List<TournamentPlayer> rankedPlayers) {
        System.out.println("\nFinal Ranking:");
        System.out.println("--------------");
        for (int i = 0; i < rankedPlayers.size(); i++) {
            TournamentPlayer player = rankedPlayers.get(i);
            String place = getPlaceString(i + 1);
            System.out.println(place + ": " + player.getName() + " (" + player.getTypeString() +
                    ") - " + player.getPoints() + " points");
        }

        System.out.println("\nWinners by Type:");
        System.out.println("---------------");
        Map<String, TournamentPlayer> bestByType = new HashMap<>();
        for (TournamentPlayer player : rankedPlayers) {
            String type = player.getTypeString();
            if (!bestByType.containsKey(type)) {
                bestByType.put(type, player);
            }
        }

        for (Map.Entry<String, TournamentPlayer> entry : bestByType.entrySet()) {
            TournamentPlayer player = entry.getValue();
            System.out.println(entry.getKey() + ": " + player.getName() + " - " + player.getPoints() + " points");
        }
    }

    private String getPlaceString(int place) {
        if (place == 1) return "1st";
        if (place == 2) return "2nd";
        if (place == 3) return "3rd";
        return place + "th";
    }
}