package KitchenAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class KitchenApiApplication {
	private static final ArrayList<Cook> cooksRank3 = new ArrayList<>();
	private static final ArrayList<Cook> cooksRank2 = new ArrayList<>();
	private static final ArrayList<Cook> cooksRank1 = new ArrayList<>();
	private static final ArrayList<Cooking_Apparatus> ovens = new ArrayList<>();
	private static final ArrayList<Cooking_Apparatus> stoves = new ArrayList<>();
	private static final int  numberOfStove = 2;
	private static final int numberOfOven = 1;
	private static final TimeUnit unit = TimeUnit.SECONDS;

	public static void main(String[] args){
		SpringApplication.run(KitchenApiApplication.class, args);

		cooksGenerator(2,1,1);
		CookManager cookManager = new CookManager();
		new Thread(cookManager).start();
		for (Cook cook : cooksRank3) {
			new Thread(cook).start();
			cook.getCook();
		}
		for (Cook cook : cooksRank2) {
			new Thread(cook).start();
			cook.getCook();
		}
		for (Cook cook : cooksRank1) {
			new Thread(cook).start();
			cook.getCook();
		}
		for (int i = 0; i < numberOfOven; i++){
			ovens.add(new Cooking_Apparatus());
		}
		for (int i = 0; i < numberOfStove; i++){
			stoves.add(new Cooking_Apparatus());
		}

	}

	public static void  cooksGenerator (int cookRankThree, int cookRankTwo, int cooksRankOne){
		while (cookRankThree != 0){
			cooksRank3.add(new Cook(3,proficiencyGenerator(3)));
			cookRankThree --;
		}
		while (cookRankTwo != 0){
			cooksRank2.add(new Cook(2,proficiencyGenerator(2)));
			cookRankTwo--;
		}
		while (cooksRankOne !=0){
			cooksRank1.add(new Cook(1,proficiencyGenerator(1)));
			cooksRankOne--;
		}
	}

	public static int  proficiencyGenerator (int rank){
		int proficiency = rank;
		if (Math.random() > 0.5) proficiency++;
		return  proficiency;
	}

	public static TimeUnit getUnit() {
		return unit;
	}

	public static ArrayList<Cook> getCooksRank3(){
		return cooksRank3;
	}
	public static ArrayList<Cook> getCooksRank2(){
		return cooksRank2;
	}
	public static ArrayList<Cook> getCooksRank1(){
		return cooksRank1;
	}
	public static ArrayList<Cooking_Apparatus> getOvens() {
		return ovens;
	}
	public static ArrayList<Cooking_Apparatus> getStoves() {
		return stoves;
	}
}

