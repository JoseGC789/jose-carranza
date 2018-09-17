package blog;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlogMain {
    private List<Entry> entries = new ArrayList<>();

    private BlogMain() {
        boolean valid = true;
        //loop
        while (valid) {
            valid = selectAction();
        }
    }

    private boolean selectAction(){
        //menu opciones. aca el usuario selecciona entre las opciones que hay en MenuOpciones
        System.out.printf(
                "Menu options: \n " +
                        "1: Post new entry. \n " +
                        "2: Delete existing entry. \n " +
                        "3: Show most recent entries. \n " +
                        "4: Exit program. \n");
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

    private void showRecent10 (){
        //Muestra las ultimos 10 entradas (Entry) en entries
        System.out.printf("Most recent 10 entries: \n");
        int entriesSize = entries.size() - 1;
        int recent10 = entriesSize - 9;
        if (recent10 < 0){recent10 = 0;}

        for (int i = entriesSize; i >= recent10 ; i--){
            System.out.printf("%s\n",entries.get(i));
        }
    }

    private void postEntry(){
        //Genero nuevo entrada (Entry) para entries
        EntryBuilder builder = new EntryBuilder();
        System.out.printf("Entry title: \n");
        builder.setTitle(enterInput());
        System.out.printf("Entry body: \n");
        builder.setText(enterInput());
        boolean loopFlag = true;
        while (loopFlag) {
            System.out.printf(
                    "Post new entry options: \n " +
                            "1: Change title: \"%s\" \n " +
                            "2: Change body: \"%s\" \n " +
                            "3: Add tag: \"%s\" \n " +
                            "4: Remove tag. \n " +
                            "5: Post entry. \n " +
                            "6: Cancel entry. \n",
                    builder.getTitle(),builder.getText(),builder.getTags());
            CrearOpciones opciones = CrearOpciones.values()[(enterInput(1, 6) - 1)];
            switch (opciones) {
                case TITULO:
                    System.out.printf("Entry title: \n");
                    builder.setTitle(enterInput());
                    break;
                case TEXTO:
                    System.out.printf("Entry body: \n");
                    builder.setText(enterInput());
                    break;
                case ADDTAGS:
                    System.out.printf("Add tag: \n");
                    builder.addTags(enterInput());
                    break;
                case REMOVETAGS:
                    System.out.printf("Remove tag: \n");
                    builder.removeTags(enterInput());
                    break;
                case CREAR:
                    this.entries.add(builder.buildEntry());
                case CANCELAR:
                    loopFlag = false;
            }
        }
    }

    private void deleteEntry(){
        //borrar entry
        System.out.printf("Enter entry's id: \n");
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
            entries.remove(aux);
        }
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

    private String enterInput(){
        //funcion para conseguir texto del usuario
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public static void main (String[] args){
        new BlogMain();
    }
}
