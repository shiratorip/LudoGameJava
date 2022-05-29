import java.util.Scanner;

public class Main {
    static char[][] field = new char[13][27];
    static Team[] teams = new Team[4];
    static int[][] path=new int[1][40];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int PlayersNum = sc.nextInt();
        fieldstdCreate();
        addPlayers(PlayersNum);
        addPawnsToField(PlayersNum);
        printField();


    }


    static void addPlayers(int PlayersNum) {
        switch (PlayersNum) {
            case 2, 3, 4 -> {
                for (int i = 0; i < PlayersNum; i++) {
                    teams[i] = new Team(i + 1);
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

    /*
        public static boolean isPathBlocked(int x, int Moves,char aChar) {
            for (int i = 0; i < Moves; i++) {
                if (place[Moves+x]!='x'){
                    if (place[Moves+x]==aChar){
                        //can pass
                    }else{
                       //beat or  path is blocked move on Moves+x
                    }
                }
            }
        }
    }
    */
    static class Team {
        Pawn[] pawns = new Pawn[4];
        int team,
                isAtHome = 4, hasFinished;


        Team(int team) {
            this.team = team;
            for (int i = 0; i < pawns.length; i++) {
                pawns[i] = new Pawn(team);
                pawns[i].x = -1;
            }


        }

    }

    static class Pawn {
        char aChar;
        int x;

        Pawn(int team) {
            aChar = (char) (96 + team);
        }

        public void find(int X) {

        }

        public void move(int Moves) {
            //  if(!Main.isPathBlocked(this.x,Moves,this.aChar))
            this.x += Moves;
        }

        public void goHome() {
            this.x = -1;
        }
    }
}