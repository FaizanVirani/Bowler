import java.util.*;
public class Player
{
    private String name; // name obviously
    private int score; // score
    private int bonusScore; // no use, codehs just had it in the instructions so I put it
    //these 2 just make life easier in scoreFrame and extraRound
    private boolean spare;
    private boolean strike;
    ArrayList<Integer> turns = new ArrayList<Integer>(); // keeps the last 3 bowls on record for strike calcs
    
    public Player(String n, int s) {
        turns.add(-1);
        turns.add(-1);
        turns.add(-1);
        
        name = n;
        score = s;
    }
    
    public int getScore() {
        return score;
    }
    
    public String getName() {
        return name;
    }
    
    public boolean extraRound(int frame) { // extra round yes or no
        if((spare && (frame == 10)) || (strike && (frame>= 9))) {
            return true;
        }
        return false;
    }
    public void scoreFrame(int firstBall, int secondBall, int frame) {
        
        if(spare) { // if there WAS a spare, this handles that
            score += (10 + firstBall);
            spare = false;
        }
        
        if(firstBall == 10) { // this is if strike
            turns.remove(0);
            turns.add(10);
        }
        
        if(turns.get(0) == 10) { // if there was a spare 2 bowls ago that can now be accounted for
            score += (10 + turns.get(1) + turns.get(2));
        }
        
        if ((firstBall + secondBall == 10) && (firstBall != 10)) { // if its a spare
            spare = true;
            turns.remove(0);
            turns.remove(0);
            turns.add(firstBall);
            turns.add(secondBall);
        }
        
        if(firstBall + secondBall != 10) { // anything else
            turns.remove(0);
            turns.remove(0);
            turns.add(firstBall);
            turns.add(secondBall);
            
            score += (firstBall + secondBall);
        }
        
        if(turns.get(1) == 10 || turns.get(2) == 10) { // if there was a strike that still needs to be accounted for, used to decide if an extra round is needed or not
            strike = true;
        } else {
            strike = false;
        }
        
    }
    
    public void scoreBonus() {
        // this is just here because codehs said it has to be idk
    }
}
