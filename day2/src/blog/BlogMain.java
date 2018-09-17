package blog;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlogMain {
    private List<Entry> entries = new ArrayList<>();

    public BlogMain() {
        boolean valid = true;
        //loop
        while (valid) {
            valid = selectAction();
        }
    }

    public boolean selectAction(){
        //menu opciones. aca el usuario selecciona entre las opciones que hay en MenuOpciones
        System.out.printf(
                "Menu opciones: \n " +
                        "1: Crear nueva entrada. \n " +
                        "2: Borrar entrada existente. \n " +
                        "3: Mostrar entradas recientes. \n " +
                        "4: SALIR. \n");
        MenuOpciones opciones = MenuOpciones.values()[(enterInput(1,4)-1)];
        switch (opciones){
            case POST:
                postEntry();
                break;
            case DELETE:
                deleteEntry();
                break;
            case SHOW10:
                showRecent10();
                break;
            case SALIR:
                return false;
        }
        return true;
    }

    public void showRecent10 (){
        //Muestra las ultimos 10 entradas (Entry) en entries
        System.out.printf("Ultimas 10 entradas: \n");
        int entriesSize = entries.size() - 1;
        int recent10 = entriesSize - 9;
        if (recent10 < 0){recent10 = 0;}

        for (int i = entriesSize; i >= recent10 ; i--){
            System.out.printf("%s\n",entries.get(i));
        }
    }

    public void postEntry(){
        //Genero nuevo entrada (Entry) para entries
        EntryBuilder builder = new EntryBuilder();
        System.out.printf("Titulo de entrada: \n");
        builder.setTitle(enterInput());
        System.out.printf("Cuerpo de entrada: \n");
        builder.setText(enterInput());
        boolean loopFlag = true;
        while (loopFlag) {
            System.out.printf(
                    "Crear entrada opciones: \n " +
                            "1: Cambiar titulo: \"%s\" \n " +
                            "2: Cambiar texto: \"%s\" \n " +
                            "3: Agregar tag: \"%s\" \n " +
                            "4: Remover tag. \n " +
                            "5: Crear entrada. \n " +
                            "6: Cancelar entrada. \n",
                    builder.getTitle(),builder.getText(),builder.getTags());
            CrearOpciones opciones = CrearOpciones.values()[(enterInput(1, 6) - 1)];
            switch (opciones) {
                case TITULO:
                    System.out.printf("Titulo de entrada: \n");
                    builder.setTitle(enterInput());
                    break;
                case TEXTO:
                    System.out.printf("Cuerpo de entrada: \n");
                    builder.setText(enterInput());
                    break;
                case ADDTAGS:
                    System.out.printf("Ingresar tag: \n");
                    builder.addTags(enterInput());
                    break;
                case REMOVETAGS:
                    System.out.printf("Remover tag: \n");
                    builder.removeTags(enterInput());
                    break;
                case CREAR:
                    this.entries.add(builder.buildEntry());
                case CANCELAR:
                    loopFlag = false;
            }
        }
    }

    public void deleteEntry(){
        //borrar entry
        System.out.printf("Ingrese ID de entrada a remover: \n");
        int id = enterInput(1,200);
        Entry aux = null;
        for (Entry entry: entries){
            //buscador id
            if (entry.getId()==id){
                aux = entry;
            }
        }
        //esta parte del codigo borra la entrada si la encuentra.
        //en aux estara la entra si es encontrada
        //importante que este afuera del for loop o sino me genera la excepcion java.util.ConcurrentModificationException
        if (aux != null){
            entries.remove(entries.indexOf(aux));
        }
    }

    public static int enterInput(int min,int max){
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

    public static String enterInput(){
        //funcion para conseguir texto del usuario
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        return str;
    }

    public static void main (String[] args){
        new BlogMain();
    }
}
