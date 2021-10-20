package KitchenAPI;

import java.util.ArrayList;
import java.util.Collections;

public class CookManager implements Runnable {
    private ArrayList<Order> orders = Handler.getOrders();
    private ArrayList<Cook> cooks = KitchenApiApplication.getCooks();
    private ArrayList<Dish> dishes = new ArrayList();

    @Override
    public void run() {
        int cookProf = Cook.getCooksProf();
        int numberOfItems = 0;

        while (true){

            endPriority();
            while (numberOfItems < cookProf){
                Order maxPriorityOrder = findMaxPriority();
                numberOfItems+=maxPriorityOrder.getNumberOfReadyDishes();
                for (int item: maxPriorityOrder.getItems()) {
                    dishes.add(new Dish(maxPriorityOrder.getOrder_id(), item, maxPriorityOrder.getPriority(), maxPriorityOrder.getEndPriority()));
                }
                maxPriorityOrder.setBln(false);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void endPriority(){
        for (Order order: orders) {
            int endPriority = order.getPriority() - order.getOrder_id();
            order.setEndPriority(endPriority);
        }
    }

    private Order findMaxPriority(){
        Order order = null;
        if (!orders.isEmpty()) {
            order = orders.get(0);

            for (int i = 0; i < orders.size(); i++)
                if (orders.get(i).getEndPriority()>order.getEndPriority() && orders.get(i).isBln()) order = orders.get(i);

        }
        return order;
    }






}
