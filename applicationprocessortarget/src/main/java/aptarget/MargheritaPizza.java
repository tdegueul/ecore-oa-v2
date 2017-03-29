package aptarget;

import fr.mleduc.annot.Factory;

/**
 * Created by mleduc on 29/03/17.
 */
@Factory(id = "Margherita", type = Meal.class)
public class MargheritaPizza implements Meal {

    @Override
    public float getPrice() {
        return 6.0f;
    }
}
