import java.util.Scanner;

public class GameRunner {
    public static void intro(){
        System.out.println("Hello , stranger! Welcome to Minesweeper!");
        System.out.println("The object is to clear the fields of all safe tiles!");
        System.out.println("There are three levels based on difficulty!");
        System.out.println("Select a tile with a mine and you will lose!");
        System.out.println("GL&HF!");
    }

    public static void test(){
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();
        game.updating();

        System.out.println("choose difficulty:");
        System.out.println("Beginner –  10 Mines - press 1");
        System.out.println(" Intermediate –  40 Mines - press 2");
        System.out.println(" Advanced - 99 Mines - press 3");
        int option = scanner.nextInt();
       switch (option){
           case 1:
               game = new Game(9,9);
                game.minesGenerator(10);
                break;
           case 2:
                game = new Game (16,16);
               game.minesGenerator(40);
                break;
           case 3:
                game = new Game(24,24);
               game.minesGenerator(99);
               break;

       }


        System.out.println("Enter x coordinates: ");
        int x = scanner.nextInt();
        System.out.print("Enter y coordinates: ");
        int y = scanner.nextInt();

        if(game.getATile(x,y).equals("*")){  //ensuring that the player does not lose on the first pick
            game.minesGenerator(1);
            game.fields[x][y] = "#";
        }
        game.clearing(x,y);
        game.detector();
        game.updating();


        //and continuing the game until it ends
        while(true){
            if(game.getFinish().equals(true) && game.getVictory().equals(true)){
                System.out.println("You win! ^_^");  //If the player wins
                game.end();
                break;
            }else if(game.getFinish().equals(true) && game.getVictory().equals(false)){
                System.out.println("Sorry man! You lost...but you can try again!");
                game.end();
                break;
            }else if(game.getFinish().equals(false)){   //if the player continues the game
                x = -1;
                y = -1;       //reseting the values
                System.out.println("Enter x coordinate: ");
                x = scanner.nextInt();
                System.out.println("Enter y coordinate: ");
                y = scanner.nextInt();

                game.rounds(x,y);
                game.Victory();
                game.detector();
                game.updating();
            }
        }
    }
}
