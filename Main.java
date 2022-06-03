import javax.crypto.spec.PSource;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static char[][] field = new char[13][27];
    static int[][] path = new int[40][2];

    static final Scanner sc = new Scanner(System.in);
    static int[][] pawns = new int[4][4];
    static int turnCounter = 0;
    static boolean gameEnd = false;
    static int PlayersNum;

    public static void main(String[] args) {

        PlayersNum = sc.nextInt();

        fieldstdCreate();
        fillPath();
        addPlayers(PlayersNum);
        addPawnsToField(PlayersNum);

        printField();
        while (!gameEnd) {
            playTurn();
            printField();

        }
    }

    public static void playTurn() {
        System.out.println((char) ((turnCounter % PlayersNum) + 'a') + "\'s turn");
        for (int i = 0; i < pawns.length; i++) {
            System.out.println(" "+pawns[turnCounter %4][i]);
        }
        System.out.println("enter pawn's coordinate(-1 - move pawn from home):");
        int coordinateIn = sc.nextInt();
        boolean checker = false;
        int rndm = ((int) (Math.random() * 6) + 1);
        if (coordinateIn >= -1 && coordinateIn <= 39) {
            if (coordinateIn == -1) {
                if (rndm == 6) {
                   //field[path[(turnCounter % PlayersNum) * 10][1]][path[(turnCounter % PlayersNum) * 10][0]] = (char) ((turnCounter % PlayersNum) + 'a');
                   //for (int i = 0; i <pawns[turnCounter %4].length ; i++) {
                   //        if (pawns[turnCounter %4][i]==-2){
                   //        pawns[turnCounter %4][i]=((turnCounter % PlayersNum) * 10);
                   //            for (int l = 0; l < pawns.length; l++) {
                   //                System.out.println(" "+pawns[turnCounter %4][l]);
                   //            }
                   //        break;
                   //        }
                   //}

                    for (int i = 0; i < PlayersNum; i++) {
                        for (int j = 0; j <pawns[i].length ; j++) {
                            if (pawns[i][j]==-2){
                                pawns[i][j]=i*10;
                                break;
                            }
                        }

                    }

                    System.out.println("pawn moved");
                } else {
                    System.out.println("your roll is: \'" + rndm + "\'");

                }
            } else {
                for (int i = 0; i < pawns[turnCounter % PlayersNum].length; i++) {

                    if (pawns[turnCounter % PlayersNum][i] == coordinateIn) {
                        checker = true;
                        field[path[(turnCounter % PlayersNum) * 10][1]][path[(turnCounter % 4) * 10][0]] = 'p';
                        //
                        pawns[turnCounter % PlayersNum][i] = move(coordinateIn);
                        field[path[pawns[turnCounter % PlayersNum][i]][1]][path[pawns[turnCounter % PlayersNum][i]][0]] = (char) ((turnCounter % PlayersNum) + 'a');

                    }
                }

                if (!checker) {
                    System.out.println("ERROR: no suitable pawn");
                    playTurn();
                }

            }
        }else {
            System.out.println("ERROR: wrong input");
            playTurn();
        }
        //if ()
        //boolean pathBlocked;
//
        //if (finishCell)
//
        System.out.println(turnCounter);
        turnCounter++;
    }

    public static int move(int coordinate) {
        int random = (int) (Math.random() * 6) + 1;
        System.out.println("your roll is: \'" + random + "\'");
        //if ()
        return coordinate + random;

    }

    private static void fillPath() {
        int number = 0;
        //
        for (int i = 0; i < 4; i++) {
            path[number] = new int[]{15, 1 + i};
            number++;
        }
        for (int i = 0; i < 4; i++) {
            path[number] = new int[]{15 + (2 * i), 5};
            number++;
        }
        for (int i = 0; i < 2; i++) {
            path[number] = new int[]{23, 5 + i};
            number++;
        }
        //
        for (int i = 0; i < 4; i++) {
            path[number] = new int[]{23 - (2 * i), 7};
            number++;
        }
        for (int i = 0; i < 4; i++) {
            path[number] = new int[]{15, 7 + i};
            number++;
        }
        for (int i = 0; i < 2; i++) {
            path[number] = new int[]{15 - (2 * i), 11};
            number++;
        }
        //
        for (int i = 0; i < 4; i++) {
            path[number] = new int[]{11, 11 - i};
            number++;
        }
        for (int i = 0; i < 4; i++) {
            path[number] = new int[]{11 - (2 * i), 7};
            number++;
        }
        for (int i = 0; i < 2; i++) {
            path[number] = new int[]{3, 7 - i};
            number++;
        }
        //
        for (int i = 0; i < 4; i++) {
            path[number] = new int[]{3 + (2 * i), 5};
            number++;
        }
        for (int i = 0; i < 4; i++) {
            path[number] = new int[]{11, 5 - i};
            number++;
        }
        for (int i = 0; i < 2; i++) {
            path[number] = new int[]{11 + (2 * i), 1};
            number++;
        }
    }


    static void addPlayers(int PlayersNum) {
        switch (PlayersNum) {
            case 2, 3, 4 -> {
                for (int i = 0; i < PlayersNum; i++) {

                }
            }
            default -> {
                System.out.println("wrong number of players");
                System.exit(0);//maybe change it
            }
        }
    }

    static void printField() {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 27; j++) {
                System.out.print(field[i][j]);
            }
            System.out.println();
        }
    }


    static void fieldstdCreate() {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 27; j++) {
                boolean q1 = ((i == 11 || i == 1) && (j > 11 && j < 17) && j % 2 == 1),
                        q2 = ((j == 3 || j == 23) && (i > 4 && i < 7)),
                        q3 = ((i == 5 || i == 7) && (j > 2 && j < 24) && j % 2 == 1),
                        q4 = ((j == 11 || j == 15) && (i > 0 && i < 12)),
                        q5 = (i == 6 && (j == 11 || j == 15)),
                        q6 = ((i == 5 || i == 7) && j == 13);

                if (q1 || q2 || q3 && !q6 || q4 && !q5) {
                    field[i][j] = 'x';
                } else {
                    field[i][j] = ' ';
                }
            }
        }

        field[0][15] = '0';
        field[12][10] = '2';
        field[12][11] = '0';
        field[5][0] = '3';
        field[5][1] = '0';
        field[7][25] = '1';
        field[7][26] = '0';

        //27x13 WxH
    }

    static void addPawnsToField(int PlayersNum) {
        for (int i = 0; i < pawns.length; i++) {
            for (int j = 0; j < pawns[i].length; j++) {
                pawns[i][j] = -2;
            }
        }

        field[1][23] = 'a';
        field[1][21] = 'a';
        field[2][23] = 'a';
        field[2][21] = 'a';
        //
        field[10][23] = 'b';
        field[10][21] = 'b';
        field[11][23] = 'b';
        field[11][21] = 'b';
        if (PlayersNum > 2) {
            field[10][3] = 'c';
            field[10][5] = 'c';
            field[11][3] = 'c';
            field[11][5] = 'c';
            if (PlayersNum > 3) {
                field[1][3] = 'd';
                field[1][5] = 'd';
                field[2][3] = 'd';
                field[2][5] = 'd';
            }
        }
    }


}


