package KitchenAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.ArrayList;

@SpringBootApplication
public class KitchenApiApplication {
	private static final ArrayList<Cook> cooks = new ArrayList<>();

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(KitchenApiApplication.class, args);

		cooksGenerator(1,1,2);
		Cook.setCooksProf();
		CookManager cookManager = new CookManager();
		new Thread(cookManager).start();
//		for (Cook cook : cooks) {
//			cook.getCook();
//			new Thread(cook).start();
//		}
	}

	public static void  cooksGenerator (int cookRankThree, int cookRankTwo, int cooksRankOne){
		while (cookRankThree != 0){
			cooks.add(new Cook(3,proficiencyGenerator(3)));
			cookRankThree --;
		}
		while (cookRankTwo != 0){
			cooks.add(new Cook(2,proficiencyGenerator(2)));
			cookRankTwo--;
		}
		while (cooksRankOne !=0){
			cooks.add(new Cook(1,proficiencyGenerator(1)));
			cooksRankOne--;
		}
	}

	public static int  proficiencyGenerator (int rank){
		int proficiency = rank;
		if (Math.random() > 0.5) proficiency++;
		return  proficiency;
	}

	public static ArrayList<Cook> getCooks(){
		return cooks;
	}

}

