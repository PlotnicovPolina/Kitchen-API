package KitchenAPI;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Cook implements Runnable {
    private int cook_id = count++;
    private int rank;
    private int proficiency;
    private String name;
    private String catch_phrase;
    private static final String[] names = new String[]{"John", "Monkey", "Li", "Dinara", "Artem", "Elina", "Liam", "Olivia", "Noah", "Emma", "Oliver", "Ava", "William", "Sophia", "Elijah", "Isabella", "James", "Charlotte", "Benjamin", "Amelia", "Lucas", "Mia", "Mason", "Harper", "Ethan", "Evelyn"};
    private static final String[] catch_phrases = new String[]{"Ciao!", "Salaam alei-kun!", "Comment ca va!", "Salve!", "Ave!", "Hello!", "Ola!", "Aloha!", "Shalom!", "Salut!", "Bone die!", "Pozdravljeni!", "Elo!", "Bonjour!", "Konnichiwa!"};
    private static final ArrayList<Dish> queue = new ArrayList<>();
    private static int count = 0;

    private static int cooksProf;

    private static final TimeUnit unit = KitchenApiApplication.getUnit();

    public Cook(int rank, int proficiency) {
        this.rank = rank;
        this.proficiency = proficiency;
        this.name = nameGenerator();
        this.catch_phrase = catchPhraseGenerator();
    }

    @Override
    public void run() {
        for (int i = 0; i < proficiency; i++) {
            new Thread(new Proficiency()).start();
        }
    }

    public void queueGenerator(Dish dish) {
        queue.add(dish);
    }

    public static ArrayList<Dish> getQueue() {
        return queue;
    }

    public static void setCooksProf() {
        for (Cook cook : KitchenApiApplication.getCooksRank3()) cooksProf += cook.proficiency;
        for (Cook cook : KitchenApiApplication.getCooksRank2()) cooksProf += cook.proficiency;
        for (Cook cook : KitchenApiApplication.getCooksRank1()) cooksProf += cook.proficiency;
    }

    public static void reduceCooksProf(int value) {
        cooksProf -= value;
    }

    public static void increaseCooksProf(int value) {
        cooksProf += value;
    }

    public static int getCooksProf() {
        return cooksProf;
    }

    public void getCook() {
        System.out.println("Cook: name = " + name + ", rank = " + rank + ", proficiency = " + proficiency + ", catch_phrase = " + catch_phrase);
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    private String nameGenerator() {
        return names[(int) (Math.random() * 25)];
    }

    private String catchPhraseGenerator() {
        return catch_phrases[(int) (Math.random() * 14)];
    }

    public int getCook_id() {
        return cook_id;
    }

    public void setCook_id(int cook_id) {
        this.cook_id = cook_id;
    }

    class Proficiency implements Runnable {
        private final ArrayList<Dish> queue = Cook.queue;
        private final ArrayList<Order> orders = Handler.getOrders();
        private final ArrayList<Cooking_Apparatus> ovens = KitchenApiApplication.getOvens();
        private final ArrayList<Cooking_Apparatus> stoves = KitchenApiApplication.getStoves();

        @Override
        public void run() {
            while (true) {
                for (int i = 0; i < queue.size(); i++) {
                    Dish dish = queue.get(i);
                    if (dish.isDishBln() && dish.tryLock()) {
                        int time_preparation = dish.getPreparation_time()/ 2;
                        try {
                            unit.sleep(time_preparation);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(dish.getName() + ", order " + dish.getOrder_id() + " is preparing by " + cook_id + ". " + name);
                        Order order = findOrder(dish.getOrder_id());
                        if (order.getNumberFreeDishes()==order.getItems().length && order.getCooking_time()==0) order.setCooking_time(unit.convert(System.nanoTime(),TimeUnit.NANOSECONDS));
                        if (dish.getCooking_apparatus() != null) {
                            switch (dish.getCooking_apparatus()) {
                                case "oven": {
                                    prepareDIsh(time_preparation, ovens);
                                    break;
                                }
                                case "stove": {
                                    prepareDIsh(time_preparation, stoves);
                                    break;
                                }
                            }
                        } else {
                            prepareDIsh(time_preparation,null);
                        }
                        Cooking_Detail cooking_detail = new Cooking_Detail(dish.getItem(), dish.getCookId());
                        order.addCookingDetails(cooking_detail);
                        dish.setDishBln(false);
                        queue.remove(dish);
                        order.setNumberFreeDishes();
                        String tmp = "";
                        if (order.getNumberFreeDishes() == 0) {order.setCooking_time(unit.convert(System.nanoTime(),TimeUnit.NANOSECONDS)); tmp = " in "+order.getCooking_time()+" "+unit.name(); }
                        System.out.println(dish.getName() + ", order " + dish.getOrder_id() + " was prepared by " + cook_id + ". " + name+tmp);

                        dish.unLock();
                    }

                }

            }

        }

        private void prepareDIsh(int time_preparation, ArrayList<Cooking_Apparatus> apparatuses) {
            boolean locked = false;
            Cooking_Apparatus apparatus = null;
            if (apparatuses!=null){
            for (Cooking_Apparatus stove : apparatuses) {
                if (stove.tryLock()) {
                    apparatus = stove;
                    locked = true;
                    break;
                }
            }
            if (!locked) {
                apparatus = apparatuses.get((int) (Math.random() * (apparatuses.size() - 1)));
                apparatus.setLock();
            }
            try {
                unit.sleep(time_preparation);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            apparatus.unLock(); } else {

                try {
                    unit.sleep(time_preparation);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Cook.increaseCooksProf(1);
        }

        private Order findOrder(int order_id) {
            Order init = null;
            for (Order order : orders) {
                if (order.getOrder_id() == order_id) {
                    init = order;
                    break;
                }
            }
            return init;
        }
    }
}
