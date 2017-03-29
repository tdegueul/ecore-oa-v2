package aptarget;

import fr.mleduc.annot.Factory;

/**
 * Created by mleduc on 29/03/17.
 */
@Factory(id = "Calzone", type = Meal.class)
public class CalzonePizza implements Meal {
    @Override
    public float getPrice() {
        return 8.5f;
    }
}
