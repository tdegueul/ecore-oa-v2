package semantic.printeval.operation;

import javafx.util.Pair;

/**
 * Created by mleduc on 15/03/17.
 */
public interface ExpressionOperation extends semantic.print.operation.ExpressionOperation, expression.semantic.operation.ExpressionOperation {
    void printeval();
}
