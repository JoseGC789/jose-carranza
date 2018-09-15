package day1stuff;

public class ContadorPalitos {
    private int cantidad=21;

    public boolean removeOne(){
        cantidad--;
        if (cantidad < 1){
            return false;
        }else{
            return true;
        }
    }

    public boolean removeTwo(){
        cantidad --;
        return removeOne();
    }

    public Integer getCantidad() {
        return cantidad;
    }
}
