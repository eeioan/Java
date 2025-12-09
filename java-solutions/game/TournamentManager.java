package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TournamentManager {
    private final List<TournamentPlayer> players;
    private final GameConfig config;
    private final BoardShape shape;
    private final boolean showSteps;
    private final boolean showAllGames;

    public TournamentManager(List<TournamentPlayer> players, GameConfig config,
                             BoardShape shape, boolean showSteps, boolean showAllGames) {
        this.players = players;
        this.config = config;
        this.shape = shape;
        this.showSteps = showSteps;
        this.showAllGames = showAllGames;
    }

    public void run() {
        System.out.println(" Начало Турнира ");
        List<TournamentPlayer> bracket = new ArrayList<>(players);
        Collections.shuffle(bracket);
        List<TournamentPlayer> finalRanking = playRoundRecursive(bracket);
        displayResults(finalRanking);
    }

    private List<TournamentPlayer> playRoundRecursive(List<TournamentPlayer> group) {
        if (group.size() <= 1) {
            return group;
        }
        if (showSteps) {
            System.out.println(" Играют  " + group.size() + " игроков ");
        }

        List<TournamentPlayer> winners = new ArrayList<>();
        List<TournamentPlayer> losers = new ArrayList<>();
        List<TournamentPair> pairs = new ArrayList<>();

        for (int i = 0; i < group.size(); i += 2) {
            if (i + 1 < group.size()) {
                pairs.add(new TournamentPair(group.get(i), group.get(i + 1)));
            } else {
                if (showSteps) {
                    System.out.println("Bye: " + group.get(i).getName());
                }
                winners.add(group.get(i));
            }
        }
        for (TournamentPair pair : pairs) {
            if (showSteps) {
                System.out.println("Match: " + pair);
            }
            TournamentPlayer winner = playMatch(pair.player1(), pair.player2());
            TournamentPlayer loser = (winner == pair.player1()) ? pair.player2() : pair.player1();
            winners.add(winner);
            losers.add(loser);
        }
        List<TournamentPlayer> rankedWinners = playRoundRecursive(winners);
        List<TournamentPlayer> rankedLosers = playRoundRecursive(losers);
        List<TournamentPlayer> result = new ArrayList<>(rankedWinners);
        result.addAll(rankedLosers);

        return result;
    }

    private TournamentPlayer playMatch(TournamentPlayer p1, TournamentPlayer p2) {
        Board board = new TicTacToeBoard(config.rows(), config.cols(), config.win(), shape);
        Game game = new Game(board, p1.getPlayer(), p2.getPlayer(), showAllGames);
        int result = game.play();
        if (result == 1) {
            p1.addWin();
            p2.addLoss();
            return p1;
        } else if (result == 2) {
            p2.addWin();
            p1.addLoss();
            return p2;
        } else {
            p1.addDraw();
            p2.addDraw();
            return Math.random() > 0.5 ? p1 : p2;
        }
    }

    private void displayResults(List<TournamentPlayer> rankedPlayers) {
        System.out.println("=== Результаты ===");
        for (int i = 0; i < rankedPlayers.size(); i++) {
            TournamentPlayer p = rankedPlayers.get(i);
            System.out.printf("%d место: %s (W:%d L:%d D:%d)%n",
                    i + 1, p.getName(), p.getWins(), p.getLosses(), p.getDraws());
        }
    }
}