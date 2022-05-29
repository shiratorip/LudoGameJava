import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int PlayersNum=sc.nextInt();
        printField(PlayersNum);

    }
    static void printField(int PlayersNum){
        char[][] field = new char[13][27];


        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 27; j++) {
                boolean q1=((i == 11 || i == 1) && (j > 11 && j < 17)&&j%2==1),
                        q2=((j == 3 || j == 23) && (i > 4 && i < 7)),
                        q3=((i == 5 || i == 7) && (j > 2 && j < 24)&&j%2==1),
                        q4=((j == 11 || j == 15) && (i > 0 && i < 12)),
                        q5=(i==6&&(j==11||j==15)),
                        q6=((i==5||i==7)&&j==13);

                if (q1||q2||q3&&!q6||q4&&!q5) {
                    field[i][j] = 'x';
                }else {
                    field[i][j] = ' ';
                }
            }
        }
        switch (PlayersNum){
            case 2->{}
            case 3->{}
            case 4->{}
            default -> {
                System.out.println("wrong number of players");
                System.exit(0);//maybe change it
            }
        }
        field[0][15]='0';
        field[12][10]='2';field[12][11]='0';
        field[5][0]='3';field[5][1]='0';
        field[7][25]='1';field[7][26]='0';

        //27x13 WxH
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 27; j++) {
                System.out.print(field[i][j]);
            }
            System.out.println();
        }
    }
}
class pawn{
    int x, team;
    boolean isAtHome, hasFinished;

    pawn(int team){

    }
}
