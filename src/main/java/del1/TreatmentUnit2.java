package del1;

public class TreatmentUnit2 extends TreatmentUnit {

    @Override
    protected boolean startTreatment(final Doctor doctor) {
        if (doctor == null || doctor.getPatient() != null) {
            return false;
        }
        Patient patient = patientQue.stream().filter(p -> doctor.canTreat(p) > 0.0).findFirst().orElse(null);
        if (patient == null)
            return false;

        doctor.setPatient(patient);
        patientQue.remove(patient);
        return true;
    }

    @Override
    protected boolean startTreatment(final Patient patient) {
        if (patient == null) {
            return false;
        }
        Doctor doctor = doctors.stream().filter(d -> d.canTreat(patient) > 0.0).findFirst().orElse(null);
        if (doctor == null)
            return false;

        if (patientQue.contains(patient)) {
            patientQue.remove(patient);
        }

        doctor.setPatient(patient);
        return true;
    }

    @Override
    public void treatmentFinished(final Doctor doctor) {
        Patient patient = doctor.getPatient();
        if (patients.contains(patient) && !(patient.requiresTreatment())) {
            doctor.treat();
            patients.remove(patient);
        }
        doctor.setPatient(patientQue.stream().filter(p -> doctor.canTreat(p) > 0).findFirst().orElse(null));
        if (patient.requiresTreatment())
            startTreatment(patient);
    }
}
