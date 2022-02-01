package capstone.project5A.doctors;

import capstone.project5A.patients.Patient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/doctors")
public class DoctorController {

    private static final List<Doctor> DOCTORS = Arrays.asList(
            new Doctor(
                    "Thomas",
                    "Parrot",
                    "thomas.parrot@gmail.com",
                    "445-324-2452",
                    "Orthopedic",
                    "Kennesaw Office"
            ),
            new Doctor(
                    "James",
                    "Dean",
                    "jamesD@gmail.com",
                    "754-829-9034",
                    "Pediatric",
                    "Marietta Office"
            )
    );

    @GetMapping(path = "{doctorId}")
    public Doctor getDoctor(@PathVariable("doctorId")Long doctorId){
        return DOCTORS.stream()
                .filter(doctor -> doctorId.equals(doctor.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "Patient " + doctorId + "does not exist."
                ));
    }

    @GetMapping
    public List<Doctor> getAllDoctors(){
        System.out.println("getAllDoctors");
        return DOCTORS;
    }
}
