import java.util.Scanner;

public class Main {
    static char[][] field = new char[13][27];
    static int[][] path = new int[40][2];

    static final Scanner sc = new Scanner(System.in);
    static int[][] pawns = new int[4][4];
    static int[][] distance = new int[4][4];
    static int turnCounter = 0;
    static boolean gameEnd = false;
    static int PlayersNum;

    public static void main(String[] args) {

        PlayersNum = sc.nextInt();
        fillPath();

        fieldstdCreate();
        addPlayers(PlayersNum);
        refreshPawns(PlayersNum);

        for (int i = 0; i < pawns.length; i++) {
            for (int j = 0; j < pawns[i].length; j++) {
                pawns[i][j] = -2;
            }
        }

        printField();
        while (!gameEnd) {
            playTurn();
            printField();
        }

    }

    public static void playTurn() {
        System.out.println("Turn: " + turnCounter);
        System.out.println((char) ((turnCounter % PlayersNum) + 'a') + "\'s turn");
        for (int i = 0; i < pawns.length; i++) {
            System.out.print(" " + pawns[turnCounter % PlayersNum][i]);
        }
        System.out.println();
        for (int i = 0; i < distance.length; i++) {
            System.out.print(" " + distance[turnCounter % PlayersNum][i]);
        }
        System.out.println("\nenter pawn's coordinate(-1 - move pawn from home):");
        int coordinateIn = sc.nextInt();
        int rndm = ((int) (Math.random() * 6) + 1);

        if (coordinateIn >= -1 && coordinateIn <= 39) {

            for (int j = 0; j < pawns[turnCounter % PlayersNum].length; j++) {
                if (coordinateIn != -1) {
                    if (move(coordinateIn, rndm) == 1) return;
                    System.out.println("1your roll is: \'" + rndm + "\'");
                    break;
                } else if (coordinateIn == -1 && rndm == 6) {


                    if (pawns[turnCounter % PlayersNum][j] == -2) {

                        for (int l = 0; l < pawns.length; l++) {
                            for (int k = 0; k < pawns[l].length; k++) {
                                if (pawns[l][k] == (turnCounter % PlayersNum * 10)) {
                                    pawns[l][k] = -2;
                                }
                            }
                        }
                        pawns[turnCounter % PlayersNum][j] = turnCounter % PlayersNum * 10;
                        System.out.println("pawn moved");
                        break;
                    }
                } else {
                    System.out.println("your roll is: \'" + rndm + "\'");
                    break;
                }
            }


        } else {
            System.out.println("ERROR: wrong input");
            playTurn();
            return;
        }
        turnCounter++;

    }

    public static int move(int coordinate, int rndm) {
        int random = rndm;
        int CountWinner=0;
        int x, y, delta = coordinate + random;
        x = path[coordinate][0];
        y = path[coordinate][1];

        if (field[y][x] == (char) ('a' + turnCounter % PlayersNum)) {

            for (int i = 0; i < pawns.length; i++) {
                for (int j = 0; j < pawns[i].length; j++) {
                    if (coordinate + random > 39) {
                        System.out.println("000000000000000");
                        delta=coordinate+random-40;
                        if ((pawns[i][j] > coordinate && pawns[i][j] <= 39) || (pawns[i][j] >= 0 && pawns[i][j] <= coordinate + random - 40)) {
                            System.out.println("delta" + delta);
                            delta = pawns[i][j];
                            pawns[i][j] = -2;
                            distance[i][j]=0;
                            System.out.println("delta" + delta);
                        }
                    }
                    if (pawns[i][j] > coordinate && pawns[i][j] <= (coordinate + random)) {
                        delta = pawns[i][j];
                        pawns[i][j] = -2;
                        distance[i][j]=0;
                        System.out.println("===");
                    }

                }
            }

            for (int i = 0; i < PlayersNum; i++) {
                if (pawns[turnCounter % PlayersNum][i] == coordinate) {
                    System.out.println("]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]");
                    pawns[turnCounter % PlayersNum][i] = delta;
                    distance[turnCounter % PlayersNum][i]+=random;
                    if (distance[turnCounter % PlayersNum][i]>=40){
                        pawns[turnCounter % PlayersNum][i]=100;
                    }
                }
            }

            for (int i = 0; i < PlayersNum; i++) {
                for (int j = 0; j < pawns[i].length; j++) {
                    if (pawns[i][j]==100)CountWinner++;
                }
                gameEnd=CountWinner==4;
                CountWinner=0;
            }

        } else {
            System.out.println("wrong pick-> enter good pick");
            playTurn();
            return 1;
        }
        return 0;
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
            }
            default -> {
                System.out.println("wrong number of players");
                System.exit(0);//maybe change it
            }
        }
    }

    static void printField() {
        fieldstdCreate();
        refreshPawns(PlayersNum);
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
                field[i][j] = ' ';

                for (int k = 0; k < path.length; k++) {
                    if (path[k][0] == j && path[k][1] == i) {
                        field[i][j] = 'x';
                    }
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
    }

    static void refreshPawns(int PlayersNum) {

        int count = 0;
        for (int i = 0; i < PlayersNum; i++) {
            for (int j = 0; j < pawns[i].length; j++) {
                if (pawns[i][j] == -2) {
                    count++;
                } else if(pawns[i][j]<40){
                    int x = path[pawns[i][j]][0];
                    int y = path[pawns[i][j]][1];
                    field[y][x] = (char) ('a' + (i % PlayersNum));

                }
            }
            switch (i) {
                case 0 -> {
                    field[1][23] = count > 0 ? 'a' : ' ';
                    field[1][21] = count > 1 ? 'a' : ' ';
                    field[2][23] = count > 2 ? 'a' : ' ';
                    field[2][21] = count > 3 ? 'a' : ' ';
                }
                case 1 -> {
                    field[10][23] = count > 0 ? 'b' : ' ';
                    field[10][21] = count > 1 ? 'b' : ' ';
                    field[11][23] = count > 2 ? 'b' : ' ';
                    field[11][21] = count > 3 ? 'b' : ' ';
                }
                case 2 -> {
                    if (PlayersNum > 2) {
                        field[10][3] = count > 0 ? 'c' : ' ';
                        field[10][5] = count > 1 ? 'c' : ' ';
                        field[11][3] = count > 2 ? 'c' : ' ';
                        field[11][5] = count > 3 ? 'c' : ' ';
                    }
                }
                case 3 -> {
                    if (PlayersNum > 3) {
                        field[1][3] = count > 0 ? 'd' : ' ';
                        field[1][5] = count > 1 ? 'd' : ' ';
                        field[2][3] = count > 2 ? 'd' : ' ';
                        field[2][5] = count > 3 ? 'd' : ' ';
                    }
                }
            }
            count = 0;
        }
    }


}


