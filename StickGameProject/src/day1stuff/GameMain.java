package day1stuff;

import java.util.Scanner;

// 21 sticks game.

public class GameMain {
    private State state;
    private Player player1;
    private Player player2;
    private Player playing;
    private SticksCounter sticks;

    public GameMain(){
        initialize();
    }

    private void begin (){
        while (this.state != State.END){
            switch (this.state){
                case PLAYING:
                    resolvePlayerTurn();
                    break;

                case CONCLUDED:
                    resolveConclusion();
                    break;

                case START:
                    initialize();
                    break;
            }
        }
        //success!
        System.out.printf("\nGame ended.");
    }

    private void initialize (){
        //initialize game
        //initialize classes
        this.sticks = new SticksCounter();
        this.player1 = new Player("Player 1", sticks);
        this.player2 = new Player("Player 2", sticks);
        this.playing = this.player1;
        this.state = State.PLAYING;
    }

    private void resolvePlayerTurn(){
        //game's body
        boolean hasSticks;
        System.out.printf("\nNumber of sticks: %d.\n", this.sticks.getAmount());
        System.out.printf("%s it's your turn \n", this.playing.getName());
        hasSticks = this.playing.takeSticks();

        //switch between player's references
        if (hasSticks){
            if (this.playing.equals(player1)){
                this.playing = this.player2;
            }else{
                this.playing = this.player1;
            }
        }else{
            System.out.printf("%s lost.\n\n",this.playing.getName());
            this.state = State.CONCLUDED;
            System.out.printf("Play again? (y/n) ");
        }
    }

    private void resolveConclusion(){
        //Game has conclusion. suggest a new game
        char again;
        boolean valid = false;
        //check input
        while (!valid) {
            valid = true;
            Scanner input = new Scanner(System.in);
            again = input.next().charAt(0);
            again = Character.toLowerCase(again);

            if (again == 'y'){
                state = State.START;
            }else{
                if (again == 'n'){
                    state = State.END;
                }else{
                    valid = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        new GameMain().begin();
    }
}
