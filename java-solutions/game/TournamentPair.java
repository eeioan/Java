package game;

public record TournamentPair(TournamentPlayer player1, TournamentPlayer player2) {

    @Override
    public String toString() {
        return player1.getName() + " vs " + player2.getName();
    }
}