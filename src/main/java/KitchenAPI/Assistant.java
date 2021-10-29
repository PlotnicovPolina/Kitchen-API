package KitchenAPI;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

class Assistant implements Runnable{
    private static final TimeUnit unit = KitchenApiApplication.getUnit();
    private final ArrayList<Order> orders = Handler.getOrders();
//    private static final  String POST_API_URL = "http://dinning-hall:8080/distribution";
    private static final  String POST_API_URL = "http://localhost:8080/distribution";

    @Override
    public void run() {
        while (true){
            for (int i = 0; i < orders.size(); i++){
                Order order = orders.get(i);
                if (order.getNumberFreeDishes() == 0 ){
                    post(order);
                    orders.remove(order);
                }
            }
            try {
                unit.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void post(Order order){
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(order);
            System.out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(json, httpHeaders);
        restTemplate.postForObject(POST_API_URL, httpEntity, String.class);
    }
}