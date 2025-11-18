package game;

public class TournamentPair {
    private final TournamentPlayer player1;
    private final TournamentPlayer player2;

    public TournamentPair(TournamentPlayer player1, TournamentPlayer player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public TournamentPlayer getPlayer1() { return player1; }
    public TournamentPlayer getPlayer2() { return player2; }

    @Override
    public String toString() {
        return player1.getName() + " vs " + player2.getName();
    }
}