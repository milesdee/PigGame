/**
 * This is the skeleton of the Pig game. It is your job to fill in the empty
 functions to create a functional game of pig.
 * @author Adam Smith. Modified by Brian Wilkinson
 * @version 1.2
 */
class Pig {
    /**
     * The score needed to win a round.
     */
    public static final int WINNING_SCORE = 100;
    public static void main(String[] args) {
// intro, initialize players
        System.out.println("Welcome to Pig!");
        PigPlayer human = new HumanPigPlayer("Human");
        PigPlayer opponent = new ComputerPigPlayer("Skynet"); // could be human too
        int[] roundsWon = new int[2];
// round 1
        System.out.println("Round 1!");
        if (playRound(human, opponent)) roundsWon[0]++;
        else roundsWon[1]++;
        System.out.println();
// round 2
        System.out.println("Round 2!");
        if (playRound(opponent, human)) roundsWon[1]++;
        else roundsWon[0]++;
// report the final results
        reportFinalTally(roundsWon, human, opponent);
    }
/**
 * Do one round, crediting the winner.
 * @param player1 the first player
 * @param player2 the second player
 * @return true if player1 won, false if player2
 */
    private static boolean playRound(PigPlayer player1, PigPlayer player2)
    {
        // This function must do the following:
        // 1. Enter a loop, with player 1 taking a turn, then player 2.
        // 2. Keep track of each player's score and the turn number.
        // 3. When a player wins, print the winner, and break out of the loop.
        // 4. Return a boolean value
        int player1Score = 0;
        int player2Score = 0;
        int turn = 1;
        while (player1Score < 100 && player2Score < 100) {
            player1Score += playTurn(player1, turn, player1Score, player2Score);
            if (player1Score >= 100) {
                break;
            }
            else {
                player2Score += playTurn(player2, turn, player2Score, player1Score);
            }
            turn++;
        }
        if (player1Score >= 100) {
            System.out.println(player1.getName() + " wins!");
            return true;
        }
        else {
            System.out.println(player2.getName() + " wins!");
            return false;
        }
    }
/**
 * Play a single turn, returning how many points the player got.
 * @param player the player whose turn it is
 * @param turn the turn number (0-indexed)
 * @param playerScore the player's score
 * @param opponentsScore the player's adversary's score
 * @return the points that the player won
 */
    private static int playTurn(PigPlayer player, int turn, int playerScore, int opponentsScore) {
        int rolls = 1;
        int pool = 0;
        player.beginTurn(playerScore, opponentsScore);
        while(player.decideIfShouldRoll(turn, rolls, pool, playerScore, opponentsScore)) {
            int rollValue = (int)(Math.random()*6) + 1;
            if (rollValue == 1) {
                System.out.println(player.getName() + " rolled a " + rollValue + ".");
                System.out.println("Their pool is now empty.");
                return 0;
            }
            else {
                System.out.println(player.getName() + " rolled a " + rollValue + ".");
                pool += rollValue;
                rolls++;
            }
        }
        return pool;
    }
    /**
     * Deliver a final report, indicating the overall winner after all
     rounds
     * have been played.
     * @param wins an array of <code>int</code>s indicating the
    number of rounds each player won
     * @param player1 the first player
     * @param player2 the second player
     */
    private static void reportFinalTally(int[] wins, PigPlayer player1, PigPlayer player2) {
        System.out.println("So far, " + player1.getName() + " has won " + wins[0] + " rounds " +
                "and " + player2.getName() + " has won " + wins[1] + " rounds.");
        if (wins[0] > wins[1]) {
            System.out.println(player1.getName() + " has won the game!");
        }
        else if (wins[0] < wins[1]) {
            System.out.println(player2.getName() + " has won the game!");
        }
        else {
            System.out.println(player1.getName() + " and " + player2.getName() + "have tied!");
        }
    }
}