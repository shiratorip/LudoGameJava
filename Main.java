import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static char[][] field = new char[13][27];
    static int[][] path=new int[40][2];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int PlayersNum = sc.nextInt();
        fieldstdCreate();
        fillPath();
        addPlayers(PlayersNum);
        addPawnsToField(PlayersNum);
        printField();
        char plop='-';
        System.out.println();
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 27; j++) {
                for (int k = 0; k < 40; k++) {

                    if (path[k][0]==j &&path[k][1]==i){
                        plop='o';
                        if (k==0)plop='P';
                    }
                }System.out.print(plop);
                plop='-';
            }
            System.out.println();
        }

    }
    public static void move(){

    }

    private static void fillPath() {
        int number=0;
        //
        for (int i = 0; i < 4; i++) {
            path[number]= new int[]{15, 2+i};
            number++;
        }
        for (int i = 0; i < 4; i++) {
            path[number]= new int[]{17+(2*i), 5};
            number++;
        }
        for (int i = 0; i < 2; i++) {
            path[number]= new int[]{23, 6+i};
            number++;
        }
        //
        for (int i = 0; i < 4; i++) {
            path[number]= new int[]{21-(2*i), 7};
            number++;
        }
        for (int i = 0; i < 4; i++) {
            path[number]= new int[]{15, 8+i};
            number++;
        }
        for (int i = 0; i < 2; i++) {
            path[number]= new int[]{13-(2*i), 11};
            number++;
        }
        //
        for (int i = 0; i < 4; i++) {
            path[number]= new int[]{11, 10-i};
            number++;
        }
        for (int i = 0; i < 4; i++) {
            path[number]= new int[]{9-(2*i), 7};
            number++;
        }
        for (int i = 0; i < 2; i++) {
            path[number]= new int[]{3, 6-i};
            number++;
        }
        //
        for (int i = 0; i < 4; i++) {
            path[number]= new int[]{5+(2*i),5};
            number++;
        }
        for (int i = 0; i < 4; i++) {
            path[number]= new int[]{11, 4-i};
            number++;
        }
        for (int i = 0; i < 2; i++) {
            path[number]= new int[]{13+(2*i), 1};
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
        field[1][3] = 'a';
        field[1][5] = 'a';
        field[2][3] = 'a';
        field[2][5] = 'a';
        //
        field[1][23] = 'b';
        field[1][21] = 'b';
        field[2][23] = 'b';
        field[2][21] = 'b';
        //
        if (PlayersNum>2){
            field[10][3] = 'c';
            field[10][5] = 'c';
            field[11][3] = 'c';
            field[11][5] = 'c';
            //
            if (PlayersNum>3){
                field[10][23] = 'd';
                field[10][21] = 'd';
                field[11][23] = 'd';
                field[11][21] = 'd';
            }
        }
    }



    }


