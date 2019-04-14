import java.util.Scanner;

public class MyTicTacToe {


    public static final char SYMBOL_X = 'x';
    public static final char SYMBOL_0 = '0';

    public static final int GAME_SIZE = 3;

    char[][] game = new char[GAME_SIZE][GAME_SIZE];

    Player player1;
    Player player2;

    public MyTicTacToe(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void showGame() {
        for (int i = 0; i < GAME_SIZE; i++) {
            for (int j = 0; j < GAME_SIZE; j++) {
                System.out.print(game[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void intBoard() {
        for (int i = 0; i < GAME_SIZE; i++) {
            for (int j = 0; j < GAME_SIZE; j++) {
                game[i][j] = '.';
            }
        }
    }

    public Move readMove() {
        Scanner s = new Scanner(System.in);
        System.out.println("Make move");
        String myMove = s.nextLine();
        String[] mySplit = myMove.split("");
        int myline = Integer.valueOf(mySplit[0]);
        int mycol = Integer.valueOf(mySplit[2]);
        Move move = new Move(myline, mycol);
        return move;
    }

    public void makeMove(Move move, char symbol) {
        game[move.line][move.col] = symbol;
    }

    public boolean isWinline(int line, char symbol) {
        boolean isWin = true;
        int i = 0;
        while (i < GAME_SIZE && isWin == true) {
            if (game[i][line] != symbol) {
                isWin = false;
            }
            i++;
        }
        return isWin;
    }

    public boolean isWinCol(int col, char symbol) {
        boolean isWin = true;
        int i = 0;
        while (i < GAME_SIZE && isWin == true) {
            if (game[i][col] != symbol) {
                isWin = false;
            }
            i++;
        }
        return isWin;
    }

    public boolean isWinDiagl(char symbol) {
        boolean isWin = true;
        int i = 0;
        while (i < GAME_SIZE && isWin == true) {
            if (game[i][i] != symbol) {
                isWin = false;
            }
            i++;
        }
        return isWin;
    }

    public boolean isWinDiag2(char symbol) {
        boolean isWin = true;
        int i = 0;
        while (i < GAME_SIZE && isWin == true) {
            if (game[i][GAME_SIZE - i - 1] != symbol) {
                isWin = false;
            }
            i++;
        }
        return isWin;
    }

    public boolean isWin(Move move, char symbol) {
        boolean isWin = false;

        //testez linii
        isWin = isWinline(move.line, symbol);
        if (isWin == false) {
            //testez coloane
            isWin = isWinCol(move.col, symbol);
        }

        //testez diagonale1
        if (isWin == false && move.line == move.col) {
            isWin = isWinDiagl(symbol);
        }

        //testez diagonale2
        if (isWin == false && move.line == GAME_SIZE - move.col - 1) {
            isWin = isWinDiag2(symbol);

        }
        return isWin;
    }

    public void playGame() {
        intBoard();
        System.out.println("incepe jocul");
        showGame();

        Player currentPlayer = player1;
        char currentsymbol = SYMBOL_X;

        int nrMoves = 0;
        boolean isWin = false;

        while (isWin == false && nrMoves < (GAME_SIZE * GAME_SIZE)) {

            //citesc mutarea
            Move move = readMove();
            System.out.println(move.line);
            System.out.println(move.col);

            //validez mutarea

            //efectuez mutarea
            makeMove(move, currentsymbol);
            showGame();

            //numar mutarea
            nrMoves++;

            if (nrMoves >= (2 * GAME_SIZE - 1)) {
                //testez daca avem stare de Win
                isWin = isWin(move, currentsymbol);
            }

            //daca nu e win sau mai am mutari -- schimb jucatorul
            if (currentPlayer == player1) {
                currentPlayer = player2;
                currentsymbol = SYMBOL_0;
            } else {
                currentPlayer = player1;
                currentsymbol = SYMBOL_X;
            }
        }

        //afisez mesaj corespunzator
        if (isWin == true) {
            System.out.println("castigatorul" + currentPlayer.name);
        } else {
            System.out.println("nu a castigat nimeni");
        }


    }
}

