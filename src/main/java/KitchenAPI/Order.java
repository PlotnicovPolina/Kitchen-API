package KitchenAPI;
import java.util.Comparator;

public class Order {
    private int order_id;
    private int[] items;
    private int priority;
    private float max_wait;
    private int table_id;
    private int waiter_id;
    private long pick_up_time;
    private int numberOfReadyDishes;
    private int endPriority;
    private boolean bln = true;

    public static Comparator<Order> endPriorityComparator = new Comparator<Order>() {
        @Override
        public int compare(Order o1, Order o2) {
            return (o2.getEndPriority() > o1.getEndPriority() ? -1 :
                    (o2.getEndPriority() == o1.getEndPriority() ? 0 : 1));
        }
    };

    @Override
    public String toString() {
        return "Order: id =" + order_id + ", priority = " + priority + ", numberOfReadyDishes = " + numberOfReadyDishes + ", endPriority = " + endPriority;
    }

    public Order() {
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int[] getItems() {
        return items;
    }

    public void setItems(int[] items) {
        this.items = items;
        numberOfReadyDishes = items.length;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public float getMax_wait() {
        return max_wait;
    }

    public void setMax_wait(float max_wait) {
        this.max_wait = max_wait;
    }

    public int getTable_id() {
        return table_id;
    }

    public void setTable_id(int table_id) {
        this.table_id = table_id;
    }

    public int getWaiter_id() {
        return waiter_id;
    }

    public void setWaiter_id(int waiter_id) {
        this.waiter_id = waiter_id;
    }

    public long getPick_up_time() {
        return pick_up_time;
    }

    public void setPick_up_time(long pick_up_time) {
        this.pick_up_time = pick_up_time;
    }

    public int getNumberOfReadyDishes() {
        return numberOfReadyDishes;
    }

    public void setNumberOfReadyDishes(int numberOfReadyDishes) {
        this.numberOfReadyDishes = numberOfReadyDishes;
    }

    public int getEndPriority() {
        return endPriority;
    }

    public void setEndPriority(int endPriority) {
        this.endPriority = endPriority;
    }

    public boolean isBln() {
        return bln;
    }

    public void setBln(boolean bln) {
        this.bln = bln;
    }
}