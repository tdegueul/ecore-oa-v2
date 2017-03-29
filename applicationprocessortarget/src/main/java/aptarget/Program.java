package aptarget;

/**
 * Created by mleduc on 29/03/17.
 */
public class Program {
    public static void main(String[] args) {
        MealFactory mf = new MealFactory();
        System.out.println(mf.create("Tiramisu").getPrice());

    }
}
