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
         }

         // TODO REQUIRED: Display the completed registry.
      } catch (FileNotFoundException exception) {
         // TODO REQUIRED: Report a missing input file.
      }
   }
}