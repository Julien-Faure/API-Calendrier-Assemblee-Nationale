package fr.assemblee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ApiElementService {

    private final List<ApiElement> elements;

    public ApiElementService() {
        elements = new ArrayList<>();
        elements.add(new CalendarContent());
    }

    public ApiElement getByUrl(String url) {
        ApiElement element = null;

        int i = 0;

        while (i < elements.size()){
            ApiElement currentElement = elements.get(i);
            if(currentElement.getUrl().equalsIgnoreCase(url))
                element = currentElement;
        }

        return element;
    }

    public List<ApiElement> getAll() {
        return Collections.unmodifiableList(elements);
    }
}
