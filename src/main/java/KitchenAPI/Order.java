package KitchenAPI;

import java.util.ArrayList;

public class Order {
    private int order_id;
    private int table_id;
    private int waiter_id;
    private int[] items;
    private int priority;
    private float max_wait;
    private long pick_up_time;
    private int cooking_time;
    private ArrayList<Cooking_Detail> cooking_details = new ArrayList<>();

    private int numberFreeDishes;
    private int endPriority;
    private boolean bln = true;

    @Override
    public String toString() {
        return "Order: id =" + order_id + ", priority = " + priority + ", numberOfReadyDishes = " + numberFreeDishes + ", endPriority = " + endPriority;
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
        numberFreeDishes = items.length;
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

    public int getNumberFreeDishes() {
        return numberFreeDishes;
    }

    public void setNumberFreeDishes() {
        numberFreeDishes--;
    }

    public int getEndPriority() {
        return endPriority;
    }

    public void setEndPriority() {
        this.endPriority = priority - order_id;
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

    public int getCooking_time() {
        return cooking_time;
    }

    public void setCooking_time(int cooking_time) {
        this.cooking_time = cooking_time;
    }

    public ArrayList<Cooking_Detail> getCooking_details() {
        return cooking_details;
    }

    public void setCooking_details(ArrayList<Cooking_Detail> cooking_details) {
        this.cooking_details = cooking_details;
    }

    public  void addCookingDetails(Cooking_Detail cooking_detail){
        cooking_details.add(cooking_detail);
    }
}