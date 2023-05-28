package del1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;


/** * A patient has a set of (health) conditions (of type String) that needs to be treated.
 * Supports iterating over the patient's conditions.
 */
public class Patient implements Iterable<String> {
    
    // Add fields, constructors, and methods here: // 2a
     // Support iteration // 2a
    private Collection<String> conditions = new ArrayList<>();

    public Iterator<String> iterator() {
        return conditions.iterator();
    }

    public void removeConditions(Collection<String> conditions) {
        this.conditions.removeAll(conditions);
    }

    public void addConditions(String... conditions) {
        this.conditions.addAll(Arrays.asList(conditions));
    }

  /**
 * Indicates if this patient has conditions that needs to be treated.
 * @return true if this patient has conditions that needs to be treated,
 * false otherwise.
 */
    public boolean requiresTreatment() { // 2a
        return ! conditions.isEmpty();
    }
}
