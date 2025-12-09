package game;

public class TournamentPlayer {
    private final String name;
    private final Player player;
    private final int type;
    private int wins;
    private int losses;
    private int draws;

    public TournamentPlayer(String name, Player player, int type) {
        this.name = name;
        this.player = player;
        this.type = type;
        this.wins = 0;
        this.losses = 0;
        this.draws = 0;
    }

    public void addWin() {
        wins++;
    }

    public void addLoss() {
        losses++;
    }

    public void addDraw() {
        draws++;
    }

    public int getPoints() {
        return wins * 2 + draws;
    }

    public int getGamesPlayed() {
        return wins + losses + draws;
    }

    public String getName() {
        return name;
    }

    public Player getPlayer() {
        return player;
    }

    public int getType() {
        return type;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getDraws() {
        return draws;
    }

    public String getTypeString() {
        return switch (type) {
            case 1 -> "Human";
            case 2 -> "Random";
            case 3 -> "Sequence";
            default -> "Unknown";
        };
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - W:%d L:%d D:%d Pts:%d",
                name, getTypeString(), wins, losses, draws, getPoints());
    }
}