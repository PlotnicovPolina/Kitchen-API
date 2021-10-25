package KitchenAPI;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class CookManager implements Runnable {
    private final ArrayList<Order> orders = Handler.getOrders();
    private final ArrayList<Cook> cooksRank3 = KitchenApiApplication.getCooksRank3();
    private final ArrayList<Cook> cooksRank2 = KitchenApiApplication.getCooksRank2();
    private final ArrayList<Cook> cooksRank1 = KitchenApiApplication.getCooksRank1();
    private static final TimeUnit unit = KitchenApiApplication.getUnit();
    private static final ArrayList<Dish> dishes = new ArrayList();
    private int cookProf = Cook.getCooksProf();
    private int numberOfItems = 0;

    @Override
    public void run() {
        Cook cook;
        new Thread(new Assistant()).start();
        while (true){
            while ( !(orders.isEmpty())){
                Order maxPriorityOrder = findMaxPriority();
                if (maxPriorityOrder==null) continue;
                numberOfItems+=maxPriorityOrder.getNumberFreeDishes();
                for (int item: maxPriorityOrder.getItems()) {
                    Dish dish = new Dish(maxPriorityOrder.getOrder_id(), item, maxPriorityOrder.getPriority(), maxPriorityOrder.getEndPriority());
                    switch (dish.getComplexity()){
                        case 3:
                        {
                            if(cooksRank3.isEmpty()){
                                System.out.println("There is no cook, who can take order!");
                            }
                            else {
                                cook =findMinQueue(cooksRank3);
                                cook.queueGenerator(dish);
                                dish.setCookId(cook.getCook_id());
                                Cook.reduceCooksProf(1);
                            }
                            break;
                        }
                        case 2:
                        {
                            if(cooksRank2.isEmpty()){
                                if(cooksRank3.isEmpty()){
                                    System.out.println("There is no cook, who can take order!");
                                }
                                else {
                                    cook =findMinQueue(cooksRank3);
                                    cook.queueGenerator(dish);
                                    dish.setCookId(cook.getCook_id());
                                    Cook.reduceCooksProf(1);
                                }
                            }
                            else {
                                if (cooksRank3.isEmpty()){
                                    cook =findMinQueue(cooksRank2);
                                }
                                else{
                                    if(findMinQueue(cooksRank2).getQueue().size() < findMinQueue(cooksRank3).getQueue().size()) cook =findMinQueue(cooksRank2);
                                    else cook =findMinQueue(cooksRank3);
                                }
                                cook.queueGenerator(dish);
                                dish.setCookId(cook.getCook_id());
                                Cook.reduceCooksProf(1);
                            }
                            break;
                        }
                        case 1:
                        {
                            if (cooksRank1.isEmpty()){
                                if(cooksRank2.isEmpty()){
                                    if(cooksRank3.isEmpty())  System.out.println("There is no cook, who can take order!");
                                    else {
                                        cook =findMinQueue(cooksRank3);
                                        cook.queueGenerator(dish);
                                        dish.setCookId(cook.getCook_id());
                                    }
                                }
                                else {
                                    if (cooksRank3.isEmpty()) {
                                        cook =findMinQueue(cooksRank2);
                                    }
                                    else{
                                        if (findMinQueue(cooksRank2).getQueue().size() < findMinQueue(cooksRank3).getQueue().size()) cook =findMinQueue(cooksRank2);
                                        else cook =findMinQueue(cooksRank3);
                                    }
                                    cook.queueGenerator(dish);
                                    dish.setCookId(cook.getCook_id());
                                }
                            }
                            else {
                                if (cooksRank2.isEmpty()){
                                    if(cooksRank3.isEmpty()) cook = findMinQueue(cooksRank1);
                                    else {
                                        if(findMinQueue(cooksRank1).getQueue().size() < findMinQueue(cooksRank3).getQueue().size())  cook = findMinQueue(cooksRank1);
                                        else cook = findMinQueue(cooksRank3);
                                    }
                                }
                                else {
                                    if (cooksRank3.isEmpty()){
                                        if(findMinQueue(cooksRank1).getQueue().size() < findMinQueue(cooksRank2).getQueue().size()) cook = findMinQueue(cooksRank1);
                                        else cook = findMinQueue(cooksRank2);
                                    }
                                    else {
                                        if(findMinQueue(cooksRank1).getQueue().size() < findMinQueue(cooksRank2).getQueue().size() && findMinQueue(cooksRank1).getQueue().size() < findMinQueue(cooksRank3).getQueue().size() )
                                            cook = findMinQueue(cooksRank1);
                                        else if(findMinQueue(cooksRank2).getQueue().size() < findMinQueue(cooksRank1).getQueue().size() && findMinQueue(cooksRank2).getQueue().size() < findMinQueue(cooksRank3).getQueue().size())
                                            cook = findMinQueue(cooksRank2);
                                        else cook = findMinQueue(cooksRank3);
                                    }
                                }
                                cook.queueGenerator(dish);
                                dish.setCookId(cook.getCook_id());
                            }
                            Cook.reduceCooksProf(1);
                            break;
                        }
                    }
                }
                maxPriorityOrder.setBln(false);
            }

            try {
                unit.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private Order findMaxPriority(){
        Order order = null;
        if (!orders.isEmpty() && orders != null) {
            for (int i = 0; i < orders.size(); i++) {
                Order toCompare = orders.get(i);
                if (toCompare == null) continue;
                if (toCompare.isBln() && (order == null || toCompare.getEndPriority() > order.getEndPriority())) { order = toCompare; }
            }
        }
        return order;
    }

    private Cook findMinQueue(ArrayList<Cook> cooks){
        int min = Integer.MAX_VALUE;
        Cook minCook = null;
        for (Cook cook : cooks)
        {
            if(cook.getQueue().size() < min) {
                min = cook.getQueue().size();
                minCook = cook;
            }
        }
        return minCook;
    }
}
