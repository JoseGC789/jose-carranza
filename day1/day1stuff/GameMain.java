package day1stuff;

import java.util.Scanner;

// 21 sticks game.

public class GameMain {
    private State state;
    private Player player1;
    private Player player2;
    private SticksCounter sticks;

    public GameMain(){
        state = State.START;
        Player player = null;
        int i = 0;
        while (state != State.END){
            switch (state){
                case PLAYING:
                    //game's body
                    boolean hasSticks;
                    System.out.printf("\nNumber of sticks: %d.\n", sticks.getAmount());
                    System.out.printf("%s it's your turn \n", player.getName());
                    hasSticks = player.takeSticks();

                    //switch between player's references
                    if (hasSticks){
                        if (i == 1){
                            i = 2;
                            player = player2;
                        }else{
                            i = 1;
                            player = player1;
                        }
                    }else{
                        System.out.printf("%s lost.\n\n",player.getName());
                        state = State.CONCLUDED;
                        System.out.printf("Play again? (y/n) ");
                    }
                    break;

                case CONCLUDED:
                    //Game has conclusion. suggest a new game
                    char again;
                    boolean valid = false;
                    //check input
                    while (!valid) {
                        valid = true;
                        Scanner input = new Scanner(System.in);
                        again = input.next().charAt(0);
                        again = Character.toLowerCase(again);
                        switch (again){
                            case 'y':
                                state = State.START;
                                break;
                            case 'n':
                                state = State.END;
                                break;
                        }
                    }
                    break;

                case START:
                    //initialize game
                    //initialize classes
                    sticks = new SticksCounter();
                    player1 = new Player("Player 1", sticks);
                    player2 = new Player("Player 2", sticks);

                    player = player1;
                    i = 1;
                    state = State.PLAYING;
                    break;
            }
        }
        //success!
        System.out.printf("\nGame ended.");
    }

    public static void main(String[] args) {
        new GameMain();
    }
}
