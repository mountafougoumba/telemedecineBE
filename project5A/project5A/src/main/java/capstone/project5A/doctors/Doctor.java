package capstone.project5A.doctors;

import capstone.project5A.patients.Patient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "doctor")
public class Doctor {
    @Id
    @SequenceGenerator(
        name = "doctor_sequence",
        sequenceName = "doctor_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "doctor_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    @Column(
            name = "first_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String firstName;
    @Column(
            name = "last_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String lastName;
    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String email;
    @Column(
            name = "phone",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String phone;
    @Column(
            name = "specialty",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String specialty; //a list of multiple specialties?
    @Column(
            name = "office",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String office; //maybe make an object?
   /* @OneToMany
    @Column(
            name = "patients"           //wasn't ready to implement
    )
    private Set<Patient> patients; */

    public Doctor(String firstName, String lastName, String email,
                  String phone, String specialty, String office) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.specialty = specialty;
        this.office = office;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", specialty='" + specialty + '\'' +
                ", office='" + office + '\'' +
//                ", patients=" + patients +
                '}';
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public void setOffice(String office) {
        this.office = office;
    }

 //   public void setPatients(Set<Patient> patients) {
 //       this.patients = patients;
  //  }
}
