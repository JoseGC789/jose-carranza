package day1stuff;

public class SticksCounter {
    private int amount=21;

    public boolean removeOne(){
        amount--;
        return !(amount < 1);
    }

    public boolean removeTwo(){
        amount --;
        return this.removeOne();
    }

    public Integer getAmount() {
        return amount;
    }
}
