package day1stuff;

import java.util.InputMismatchException;
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
        boolean valido = false;
        int numero = 0;
        while (!(valido)) {
            try {
                Scanner input = new Scanner(System.in);
                System.out.printf("Ingrese la cantidad de palitos a tomar, entre 1 y 2, %s: ", this.name);
                numero = input.nextInt();
                if ((numero < 1) || (numero > 2)){
                    throw new InputMismatchException();
                }
                valido = true;
                System.out.printf("\n");

            }   catch (InputMismatchException e) {
                System.out.printf("Ingrese un numero valido porfavor. \n");
                valido = false;
            }   catch (Exception e) {
                System.out.printf("Input invalido. \n");
                valido = false;
            }
        }

        switch (numero){
            case 1:
                boolean b1 = palitos.removeOne();
                return b1;
            case 2:
                boolean b2 = palitos.removeTwo();
                return b2;
        }
        return true;
    }
}
