package fr.mleduc;

import javax.lang.model.element.Element;

/**
 * Created by mleduc on 29/03/17.
 */
public class ProcessingException extends Exception {

    Element element;

    public ProcessingException(Element element, String msg, Object... args) {
        super(String.format(msg, args));
        this.element = element;
    }

    public Element getElement() {
        return element;
    }
}
