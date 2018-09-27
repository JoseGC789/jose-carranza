package day1stuff;

public class SticksCounter {
    private int amount=21;

    public boolean removeSticks(int amount){
        this.amount -= amount;
        return !(this.amount < 1);
    }
    public Integer getAmount() {
        return amount;
    }
}
