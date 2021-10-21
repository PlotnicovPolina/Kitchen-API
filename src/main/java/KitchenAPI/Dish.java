package KitchenAPI;

import java.util.concurrent.locks.ReentrantLock;

public class Dish {
    private int dish_id = count++;
    private int order_id;
    private int cook_id;
    private int item;
    private int priority;
    private int endPriority;
    private String name;
    private int preparation_time;
    private int complexity;
    private String cooking_apparatus;
    private static int count = 0;
    private boolean dishBln = true;

    private ReentrantLock lock = new ReentrantLock();

    public Dish(int order_id, int item, int priority, int endPriority) {
        this.order_id = order_id;
        this.item = item;
        this.priority = priority;
        this.endPriority = endPriority;
        this.name = getName();
        this.preparation_time = getPreparation_time();
        this.complexity = getComplexity();
        this.cooking_apparatus = getCooking_apparatus();
    }

    public boolean tryLock(){
        return lock.tryLock();
    }

    public void unLock(){
        lock.unlock();
    }

    public String getName() {
        String name = "";
        switch (item){
            case 1: name = "Pizza"; break;
            case 2: name = "Salad"; break;
            case 3: name = "Zeama"; break;
            case 4: name = "Scallop Sashimi with Meyer Lemon Confit"; break;
            case 5: name = "Island Duck with Mulberry Mustard"; break;
            case 6: name = "Waffles"; break;
            case 7: name = "Aubergine"; break;
            case 8: name = "Lasagna"; break;
            case 9: name = "Burger"; break;
            case 10: name = "Gyros"; break;

        }
        return name;
    }

    public int getPreparation_time() {
        int preparation_time = 0;
        switch (item){
            case 1:
            case 7:
                preparation_time = 20; break;
            case 2:
            case 6:
                preparation_time = 10; break;
            case 3: preparation_time = 7; break;
            case 4: preparation_time = 32; break;
            case 5: preparation_time = 35; break;
            case 8: preparation_time = 30; break;
            case 9:
            case 10:
                preparation_time = 15; break;
        }
        return preparation_time;
    }

    public int getComplexity() {
        int complexity = 0;
        switch (item){
            case 1:
            case 7:
            case 8:
                complexity = 2; break;
            case 2:
            case 3:
            case 6:
            case 9:
            case 10:
                complexity = 1; break;
            case 4:
            case 5:
                complexity = 3; break;
        }
        return complexity;
    }

    public String getCooking_apparatus() {
        String cooking_apparatus = "";
        switch (item){
            case 1:
            case 5:
            case 8:
            case 9:
                cooking_apparatus = "oven"; break;
            case 2:
            case 4:
            case 7:
            case 10:
                cooking_apparatus = null; break;
            case 3:
            case 6:
                cooking_apparatus = "stove"; break;

        }
        return cooking_apparatus;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "dish_id=" + dish_id +
                ", order_id=" + order_id +
                ", cook_id=" + cook_id +
                ", item=" + item +
                ", priority=" + priority +
                ", endPriority=" + endPriority +
                ", name='" + name + '\'' +
                ", preparation_time=" + preparation_time +
                ", complexity=" + complexity +
                ", cooking_apparatus='" + cooking_apparatus + '\'' +
                '}';
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getEndPriority() {
        return endPriority;
    }

    public void setEndPriority(int endPriority) {
        this.endPriority = endPriority;
    }

    public int getCookId() {
        return cook_id;
    }

    public void setCookId(int cook_id) {
        this.cook_id = cook_id;
    }

    public boolean isDishBln() {
        return dishBln;
    }

    public void setDishBln(boolean dishBln) {
        this.dishBln = dishBln;
    }
}
