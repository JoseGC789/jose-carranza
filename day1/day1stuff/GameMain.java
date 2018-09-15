package day1stuff;

import java.util.Scanner;

// Juego de tomar 21 palitos.

public class GameMain {
    private Estado estado;
    private Player jugador1;
    private Player jugador2;
    private ContadorPalitos palitos;

    public GameMain(){
        estado = Estado.START;
        Player jugador = null;
        int i = 0;
        while (estado != Estado.END){
            switch (estado){
                case START:
                    //inicializar juego
                    //inicializar clases
                    palitos = new ContadorPalitos();
                    jugador1 = new Player("Jugador 1",palitos);
                    jugador2 = new Player("Jugador 2",palitos);

                    jugador = jugador1;
                    i = 1;
                    estado = Estado.PLAYING;

                case PLAYING:
                    //cuerpo del juego
                    boolean hasPalitos;
                    System.out.printf("Cantidad de palitos: %d.\n", palitos.getCantidad());
                    System.out.printf("%s su turno \n", jugador.getName());
                    hasPalitos = jugador.takePalito();

                    //aca cambio entre jugadores
                    if (hasPalitos){
                        if (i == 1){
                            i = 2;
                            jugador = jugador2;
                        }else{
                            i = 1;
                            jugador = jugador1;
                        }
                    }else{
                        System.out.printf("%s perdio.\n\n",jugador.getName());
                        estado = Estado.CONCLUDED;
                    }
                    break;

                case CONCLUDED:
                    //juego tiene resolucion. sugiero un nuevo juego.
                    char devuelta;
                    boolean valido = false;
                    //chequeo que el input
                    while (!(valido)) {
                        valido = true;
                        Scanner input = new Scanner(System.in);
                        System.out.printf("Jugar devuelta? (s/n) ");
                        devuelta = input.next().charAt(0);
                        devuelta = Character.toLowerCase(devuelta);
                        System.out.printf("\n");
                        switch (devuelta){
                            case 's':
                                estado = Estado.START;
                                break;
                            case 'n':
                                estado = Estado.END;
                                break;
                            default:
                                System.out.printf("Ingrese un caracter valido. \n");
                                valido = false;
                        }
                    }
            }
        }
        //exito!
        System.out.printf("Fin de juego.");
    }

    public static void main(String[] args) {
        new GameMain();
    }
}
