package aptarget;

import fr.mleduc.annot.Factory;

/**
 * Created by mleduc on 29/03/17.
 */
@Factory(id = "Tiramisu", type = Meal.class)
public class Tiramisu implements Meal {
    @Override
    public float getPrice() {
        return 4.5f;
    }
}
