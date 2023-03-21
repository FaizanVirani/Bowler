import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private ArrayList<Player> players = new ArrayList<Player>(); // players arraylist
    private int currentFrame; // the frame
    
    public Game() { // main game
        Scanner scan = new Scanner(System.in);
        currentFrame = 0;
        while(true) { // enter players loop
            System.out.print("Please enter a player name (exit when done): ");
            String name = scan.next();
            
            if(name.toLowerCase().equals("exit")) {
                break;
            } else {
                Player p = new Player(name, 0);
                players.add(p);
            }
        }
    }
    
    public void playGame() { // playing game
        currentFrame = 0;
        while(currentFrame <= 10) { // 10 rounds
            currentFrame++; // next round
            for(int j = 0; j < players.size(); j++) { // each player!
                playFrame(j);
                System.out.print("\n\nCurrent Frame: " + currentFrame + "\n" + players.get(j).getName() + ": " + players.get(j).getScore() + "\n"); // print stuff
            }
        }
        
        for(int i = 0; i < 2; i++) { // up to 2 rounds extra if necessary
            for(int j = 0; j < players.size(); j++) {
                if(players.get(j).extraRound(currentFrame)) {
                    playFrame(j);
                }
            }
        }
    }
    
    public void playFrame(int pos) {
        
        Scanner scan = new Scanner(System.in);
        System.out.print("Next Player: " + players.get(pos).getName());
        System.out.print("\nPlease enter the first ball score: ");
        int firstBall = scan.nextInt(); // first ball
        int secondBall = 0; // initialize to 0 in case first ball is 10
        
        if(firstBall != 10) {
            System.out.print("Please enter the second ball score: ");
            secondBall = scan.nextInt();//second ball if not strike
        }
        
        players.get(pos).scoreFrame(firstBall, secondBall, currentFrame); // scoreframe does all the calculations and stuff
        
    }
}
