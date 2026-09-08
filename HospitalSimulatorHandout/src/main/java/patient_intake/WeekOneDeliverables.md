# CSC226 Hospital/ER Simulator - Week 1

## Patient Intake System

You are building the first module of a larger Emergency Room simulator. This week focuses on modeling patients, storing them in an array-backed registry, reading sample data, and testing your work.

For Java, Maven, package, IDE, and troubleshooting guidance, see [DevelopmentEnvironment.md](../../../../DevelopmentEnvironment.md).

## Required Work: 80%

Complete all four core checkpoints for the required 80%. The optional extensions below are worth the remaining 20%.

| Checkpoint | Value | Requirements |
|---|---:|---|
| Patient and Person classes | 20% | Implement the fields, constructor, getters, setters, and `Patient.toString()`. Use `firstName` and `lastName` for the two parts of a patient's name. |
| Basic array registry | 20% | Initialize an array-backed `PatientRegistry`, add patients, return the patients currently stored, find a patient by ID, and implement `PatientRegistry.toString()`. The sample data fits within the initial capacity. |
| CSV input and program output | 20% | In `Main`, use `Scanner` to read `patients.csv`, skip the header, parse each row, create a `Patient`, add it to the registry, and display the registry. |
| Tests and documentation | 20% | Pass the provided core tests, add six unique JUnit tests, and add comments or a short note explaining what each test verifies and why it matters. |

## Optional Extensions: 20%

Each extension is worth 5%. Complete any combination of extensions.

| Extension | Value | Requirement |
|---|---:|---|
| Dynamic resizing | 5% | Expand the backing array when it becomes full and preserve every stored patient. |
| Patient updates | 5% | Implement `updatePatient()` using the patient ID and report whether an update occurred. |
| Patient removal | 5% | Implement removal by patient ID and index, shift remaining entries, and handle invalid requests safely. |
| Validation and encapsulation | 5% | Reject ages outside 0 through 120 and return a defensive copy from `getPatientRegistry()`. |

Optional checks are in `src/test/java/patient_intake/optional/`. They are excluded from the normal `mvn test` run. Run them after completing the optional work:

```text
mvn "-Dtest=patient_intake.optional.AdvancedPatientChecks,patient_intake.optional.AdvancedPatientRegistryChecks" test
```

The optional check classes cover all extensions together. A partial extension attempt may therefore leave some optional checks failing.

## Data Contract

`patients.csv` is comma-separated, has one header row, and uses this column order:

```text
patientID,name,age,chiefComplaint,triageLevel,currentStage,assignedRoom,arrivalHour,insuranceID
```

The full name should be split into `firstName` and `lastName`. The provided data uses a first and last name separated by one space.

## Test and Build Commands

Run these commands from the project directory containing `pom.xml`:

```text
mvn clean compile
```

This checks production-code compilation. The starter project is expected to fail assertions until the TODOs are completed.

```text
mvn test
```

This runs the required JUnit tests. To compile tests without running them:

```text
mvn test -DskipTests
```

After compiling, run the program with:

```text
java -cp target/classes patient_intake.Main
```

## Student Tests

Put your six tests in a clearly named class such as:

```text
src/test/java/patient_intake/StudentAdditionalTests.java
```

Each test must check a distinct behavior. Add a short comment or accompanying note explaining why the behavior matters. Your tests should include both normal behavior and meaningful edge cases.

## Submission Checklist

- All `REQUIRED` TODOs are complete.
- The required tests pass with `mvn test`.
- Six unique student-written tests pass.
- `Main` reads and displays the CSV data.
- `Patient` and `PatientRegistry` have useful `toString()` methods.
- Design decisions are documented.
- Optional work is clearly identified if submitted.
- Do not commit Maven's generated `target/` directory.
