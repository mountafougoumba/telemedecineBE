package capstone.project5A.patients;

import org.apache.catalina.LifecycleState;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/patients")
public class PatientController {

    private static final List<Patient> PATIENTS = Arrays.asList(
            new Patient(
                    "Dean",
                    "Hardee",
                    "deanHardee@gmail.com",
                    "123 Final Street",
                    "Developerville",
                    "GA",
                    31234,
                    "123-456-7890",
                    LocalDate.of(2004, Month.AUGUST, 21)
            ),
            new Patient(
                    "Sarah",
                    "Jones",
                    "s.jones@gmail.com",
                    "2345 Project Lane",
                    "Software Town",
                    "GA",
                    31234,
                    "987-654-3210",
                    LocalDate.of(1983, Month.DECEMBER, 12)
            ),
            new Patient(
                    "James",
                    "Bond",
                    "d07@gmail.com",
                    "12 Capstone Drive",
                    "Javaston",
                    "GA",
                    31234,
                    "456-123-0987",
                    LocalDate.of(1999, Month.JUNE, 30)
            )
    );

    @GetMapping(path = "{patientId}")
    public Patient getPatient(@PathVariable("patientId")Long patientId){
        return PATIENTS.stream()
                .filter(patient -> patientId.equals(patient.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "Patient " + patientId + "does not exist."
                ));
    }

    @GetMapping
    public List<Patient> getAllPatients(){
        System.out.println("getAllStudents");
        return PATIENTS;
    }
}
