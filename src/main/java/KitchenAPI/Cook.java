package KitchenAPI;

public class Cook implements Runnable{
    private int rank;
    private int proficiency;
    private String name;
    private String catch_phrase;
    private static final String[] names = new String[]{"John", "Monkey", "Li", "Dinara", "Artem", "Elina", "Liam", "Olivia", "Noah", "Emma", "Oliver","Ava", "William", "Sophia", "Elijah", "Isabella", "James", "Charlotte", "Benjamin", "Amelia", "Lucas", "Mia", "Mason", "Harper", "Ethan", "Evelyn"};
    private static final String[] catch_phrases = new String[]{"Ciao!", "Salaam alei-kun!", "Comment ca va!", "Salve!", "Ave!", "Hello!", "Ola!", "Aloha!", "Shalom!", "Salut!", "Bone die!", "Pozdravljeni!", "Elo!", "Bonjour!", "Konnichiwa!"};

    private static int cooksProf;

    public static void setCooksProf() {
        for (Cook cook :KitchenApiApplication.getCooks())
            cooksProf += cook.proficiency;
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

    public Cook(int rank, int proficiency) {
        this.rank = rank;
        this.proficiency = proficiency;
        this.name = nameGenerator();
        this.catch_phrase = catchPhraseGenerator();
    }

    @Override
    public void run() {

    }

    public void getCook(){
        System.out.println("Cook: name = " + name + ", rank = " + rank + ", proficiency = " + proficiency + ", catch_phrase = " + catch_phrase);
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getProficiency() {
        return proficiency;
    }

    public void setProficiency(int proficiency) {
        this.proficiency = proficiency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatch_phrase() {
        return catch_phrase;
    }

    public void setCatch_phrase(String catch_phrase) {
        this.catch_phrase = catch_phrase;
    }

    private String nameGenerator(){
       return names[(int) (Math.random()*25)];
    }

    private String catchPhraseGenerator(){
        return catch_phrases[(int) (Math.random()*14)];
    }
}
