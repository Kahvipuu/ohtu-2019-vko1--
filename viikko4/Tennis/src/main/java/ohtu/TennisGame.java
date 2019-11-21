package ohtu;

public class TennisGame {

    private int scorePlayer1 = 0;
    private int scorePlayer2 = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name) || playerName.equals("1")) {
            scorePlayer1 += 1;
        }
        if (playerName.equals(player2Name) || playerName.equals("2")) {
            scorePlayer2 += 1;
        }
    }

    public String scoreToString() {
        if (scorePlayer1 == scorePlayer2) {
            return equalScoreToString();
        }
        if (scorePlayer1 >= 4 || scorePlayer2 >= 4) {
            return scoreOver4ToString();
        }
        return playerScoreToString(scorePlayer1) + "-" + playerScoreToString(scorePlayer2);
    }

    public String equalScoreToString() {
        switch (scorePlayer1) {
            case 0:
                return "Love-All";
            case 1:
                return "Fifteen-All";
            case 2:
                return "Thirty-All";
            case 3:
                return "Forty-All";
            default:
                return "Deuce";
        }
    }

    public String scoreOver4ToString() {
        int minusResult = scorePlayer1 - scorePlayer2;
        if (minusResult == 1) {
            return "Advantage player1";
        } else if (minusResult == -1) {
            return "Advantage player2";
        } else if (minusResult >= 2) {
            return "Win for player1";
        } else {
            return "Win for player2";
        }
    }

    private String playerScoreToString(int score) {
        switch (score) {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
            default:
                return "Nothing to see here, move along";
        }
    }
}
