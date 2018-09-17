package day1stuff;

import java.util.Scanner;

public class Player {
    private String name;
    private ContadorPalitos palitos;

    public Player (String name, ContadorPalitos palitos){
        this.name = name;
        this.palitos = palitos;
    }

    public String getName() {
        return name;
    }

    public Boolean takePalito (){
        int numero ;
        System.out.printf("Enter the number of sticks to take. Number must be either 1 or 2, %s: ", this.name);
        numero = enterInput(1,2);

        switch (numero){
            case 1:
                return palitos.removeOne();
            case 2:
                return palitos.removeTwo();
        }
        return true;
    }

    private int enterInput(int min,int max){
        //funcion para conseguir entero del usuario
        //min = rango inferior / max rango superior
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
