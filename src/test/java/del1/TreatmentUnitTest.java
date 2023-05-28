package del1;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Used to test TreatmentUnit
 */
public class TreatmentUnitTest {

   private TreatmentUnit treatmentUnit;

   @BeforeEach
   public void setUp() {
      this.treatmentUnit = new TreatmentUnit2();
   }

   @Test
   public void testAddDoctorsPatient() {
      final Doctor doctor1 = new Doctor("flu"); // new doctor can treat "flu"
      final Doctor doctor2 = new Doctor("noseblead", "pneumonia"); // new doctor can treat "noseblead" and "pneumonia"
      treatmentUnit.addDoctor(doctor1);
      treatmentUnit.addDoctor(doctor2);
      // Test that both doctors are available.
      assertTrue(doctor1.isAvailable(), "doctor1 should be available");
      assertTrue(doctor2.isAvailable(), "doctor2 should be available");

      final Patient patient = new Patient();
      patient.addConditions("flu", "noseblead"); // patient has conditions "flu" and "noseblead"
      // 2e) start sequence diagram
      treatmentUnit.addPatient(patient);
      // Test that only one of the doctors are available:
      assertTrue(doctor1.isAvailable() || doctor2.isAvailable(), "One of the doctors are available");
      assertTrue(!(doctor1.isAvailable() && doctor2.isAvailable()), "Both should not be availble");
      Doctor patientDoctor = treatmentUnit.getDoctor(patient);
      patientDoctor.treat();
      treatmentUnit.treatmentFinished(patientDoctor);
      // 2e) end sequence diagram
      // Test that the previous doctor is available and that a
      // new doctor has been assigned to the patient.
      assertTrue(patientDoctor.isAvailable(), "This doctor should be finished treating the patient");
      assertTrue((doctor1.getPatient() == patient || doctor2.getPatient() == patient), "The patient has a new doctor");

      patientDoctor = treatmentUnit.getDoctor(patient);
      patientDoctor.treat();
      treatmentUnit.treatmentFinished(patientDoctor);
      // Test that both doctors are available:
      assertTrue((doctor1.isAvailable() && doctor2.isAvailable()), "Both doctors should be available");
   }
}
