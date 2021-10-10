package KitchenAPI;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@SpringBootApplication
public class KitchenApiApplication {

	@GetMapping("/message")
	public String getMessage() {
		return "Hello me!!";
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(KitchenApiApplication.class, args);
//		try {
//			get();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		}
//	public static void get () throws Exception{
//		String uri = "http://localhost:8080";
//		HttpClient client = HttpClient.newHttpClient();
//		HttpRequest request =  HttpRequest.newBuilder()
//				.uri(URI.create(uri))
//				.build();
//		HttpResponse<String> response =
//				client.send(request, HttpResponse.BodyHandlers.ofString());
//		System.out.println(response.body());
//	}

}
