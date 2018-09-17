package day1stuff;

public class ContadorPalitos {
    private int cantidad=21;

    public boolean removeOne(){
        cantidad--;
        return !(cantidad < 1);
    }

    public boolean removeTwo(){
        cantidad --;
        return this.removeOne();
    }

    public Integer getCantidad() {
        return cantidad;
    }
}
