package day1stuff;

import java.util.Objects;
import java.util.Scanner;

public class Player {
    private String name;
    private SticksCounter sticks;

    public Player (String name, SticksCounter sticks){
        this.name = name;
        this.sticks = sticks;
    }

    public String getName() {
        return name;
    }

    public Boolean takeSticks(){
        int amount ;
        System.out.printf("Enter the number of sticks to take. Number must be either 1 or 2, %s: ", this.name);
        amount = enterInput(1,2);
        return sticks.removeSticks(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    private int enterInput(int min, int max){
        //get a number from user function
        //min = inferior range/ max superior range
        Scanner input = new Scanner(System.in);
        int number;
        do {
            while (!input.hasNextInt()) {
                input.next();
            }
            number = input.nextInt();
        } while ((number < min) || (number > max));
        return number;
    }
}
