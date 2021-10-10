package KitchenAPI;

public class Order {
    private int id;
    private int[] items;
    private int priority;
    private int max_wait;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int[] getItems() {
        return items;
    }

    public void setItems(int[] items) {
        this.items = items;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getMax_wait() {
        return max_wait;
    }

    public void setMax_wait(int max_wait) {
        this.max_wait = max_wait;
    }
}
