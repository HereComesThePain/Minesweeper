import java.util.Scanner;
import java.util.StringJoiner;

public class Game {
    private int minesNumber ;
    private int row;
    private int column;
    private String free = "#";
    private String mine = "*";
    private String blank = " ";


    Scanner scanner = new Scanner(System.in);

    public String[][] fields;
    public String[][] displaying;



    public Boolean finish = false;
    public Boolean victory = false;



    public Game(int x, int y) {
        fields = new String[x][y];
        displaying = new String[x][y];

        for( x= 0; x< fields.length ; x++){
            for ( y = 0; y <fields[0].length ; y++) {
                if((x == 0 || x == fields.length-1) || (y == 0 || y == fields[0].length -1)){
                    fields[x][y] = blank;
                    displaying[x][y] = blank;
                }
                else{
                    fields[x][y] = free;
                    displaying[x][y] = free;

                }
            }
        }
    }

    public Game() {

    }

    public static void print(String[][] strings){
        for(String[] row : strings){
            StringJoiner sj = new StringJoiner(" ");
            for(String col: row){
                sj.add(col);
            }
            System.out.println(sj.toString());
        }
    }

    public void updating(){
        print(displaying);
        System.out.println("");
    }

    public void minesGenerator ( int minesNumber ){
        for (int m = 0; m < minesNumber ; m++) {
            while (true){
                int x,y = 0;
                x=(int)(10*Math.random());
                y=(int)(10*Math.random());

                if(x >= 1 && x<= 10){
                    if (y >= 1 && y <= 10){
                        if(!fields[x][y].equals(mine)){
                            fields[x][y] = mine;
                            break;
                        }
                    }
                }
            }
        }
    }
    public void clearing(int x , int y){
        for( int  k = (x - 1); k <(x + 1) ; k++ ){
            for (int j = (y - 1); j <(y + 1) ; j++) {
                if (fields[k][j].equals(free)){
                    displaying[k][j] = blank;
                    fields[k][j] = blank;
                }
            }
        }
    }


    public String getATile(int x , int y){
        return fields[x][y];
    }

    public void detector(){
        for (int x = 1; x <displaying.length - 2 ; x++) {
            for (int y = 1; y <displaying.length - 2 ; y++) {
                if (fields[x][y].equals(blank)){
                    int numbers = 0;
                    for (int i = (x - 1); i <(x + 1) ; i++) {
                        for (int j = (y - 1); j <(y + 1) ; j++) {
                            if (fields[i][j].equals(mine) == true) {
                                numbers++;
                            }
                        }
                    }
                    displaying[x][y] = " " + numbers + " ";
                }
            }
        }
    }

    public void rounds(int x , int y){
        if(fields[x][y].equals(free)){
            finish = false;
            displaying[x][y] = blank;
            fields[x][y] = blank;
        }else if(fields[x][y].equals(mine)){
            finish = true;
            victory = false;
            System.out.println("Game Over ! You Lost! Sorry! T_T");
        }else if(displaying[x][y].equals(blank) && fields[x][y].equals(blank)){
            finish = false;
            System.out.println("This tile has been cleared! You are safe...for now");
        }
    }


    public void Victory(){
        int tile = 0;
        for (int i = 0; i <fields.length ; i++) {
            for (int j = 0; j <fields[0].length ; j++) {
                if(fields[i][j].equals(free)){
                    tile++;
                }
            }
        }
        if ( tile != 0 ){
            victory = false;
        }else{
            victory = true;
            finish=true;
        }
    }

    public Boolean getFinish(){
        return finish;
    }
    public Boolean getVictory(){
        return victory;
    }
    public void end(){
        print(fields);
    }

    public Game(int minesNumber) {
        this.minesNumber = minesNumber;

    }

    public int getMinesNumber() {
        return minesNumber;
    }

    public void setMinesNumber(int minesNumber) {
        this.minesNumber = minesNumber;

    }
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }



}
