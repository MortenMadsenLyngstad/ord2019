package del1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class Doctor {
    private Patient patient; // 1a
    private Collection<String> treatmentList = new ArrayList<>();

    /**
     * Initialise this doctor with a set of competencies.
     * 
     * @param competencies
     */
    public Doctor(String... treatmentList) { // 2b
        this.treatmentList.addAll(Arrays.asList(treatmentList));
    }

    /**
     * Indicates to what extent this doctor can treat the provided patient.
     * The value is the number of the patient's conditions this doctor
     * can treat divided by the number of conditions the patient has.
     * Conditions and competences are matched using simple String comparison.
     * 
     * @param patient
     * @return the ratio of the patient's conditions that this
     *         doctor can treat.
     */
    public double canTreat(final Patient patient) { // 2b
        List<String> allConditions = new ArrayList<>();

        for (String string : patient) {
            allConditions.add(string);
        }
        
        int treatableConditions = (int) treatmentList.stream().filter(c -> allConditions.contains(c)).count();

        return ((double) treatableConditions) / allConditions.size();
    }

    /**
     * "Treats" the patient by removing all the patient's conditions
     * that this doctor can treat.
     */
    public void treat() { // 2b
        if (getPatient() == null) {
            throw new IllegalStateException("Has no patients");
        }
        getPatient().removeConditions(treatmentList);
    }

    public Patient getPatient() { // 1a
        return patient;
    }

    public boolean isAvailable() { // 1a
        return getPatient() == null;
    }

    public void setPatient(Patient patient) { // 1a
        this.patient = patient;
    }
}