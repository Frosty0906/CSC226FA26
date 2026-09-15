package patient_intake;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
   public static void main(String[] args) {
      String filePath = "src/main/java/patient_intake/patients.csv";
      PatientRegistry patients = new PatientRegistry();

      try (Scanner fileReader = new Scanner(new File(filePath))) {
         if (fileReader.hasNextLine()) {
            fileReader.nextLine(); // Skip the CSV header.
         }

         while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();
            String[] parts = line.split(",");

            // TODO REQUIRED: Parse the fields from parts.
            // TODO REQUIRED: Split the full name into firstName and lastName.
            // TODO REQUIRED: Create a Patient and add it to patients.

            String patientID = parts[0];

            // splits the full name into firstName and lastName
            String[] nameParts = parts[1].split(" ");
            String firstName = nameParts[0]; // goes to part[1] as full name
            String lastName = nameParts[1]; // goes to part[1] as full name

            int age = Integer.parseInt(parts[2]); // converts the str to int
            String chiefComplaint = parts[3];
            int triageLevel = Integer.parseInt(parts[4]); // converts the str to int
            String currentStage = parts[5];
            String assignedRoom = parts[6];
            int arrivalHour = Integer.parseInt(parts[7]); // converts the str to int
            String insuranceID = parts[8];

            // creates new patient w the info from parts and adds it to the patient registry
            Patient patient = new Patient(patientID, firstName, lastName, age, chiefComplaint, triageLevel, currentStage, assignedRoom, arrivalHour, insuranceID);
            patients.addPatient(patient);

         }

         // TODO REQUIRED: Display the completed registry.
         System.out.println(patients); // displays registry
      } catch (FileNotFoundException exception) {
         // TODO REQUIRED: Report a missing input file.
         System.err.println("Error: Input file not found: " + filePath); // no file or wrong file
      }
   }
}