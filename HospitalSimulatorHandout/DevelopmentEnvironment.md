# CSC226 Development Environment Guide

This guide explains the tools and conventions used by the Hospital Simulator projects. It is separate from the weekly assignment requirements.

## Required Software

Install:

1. Java Development Kit (JDK) 17 or newer: https://www.oracle.com/java/technologies/downloads/
2. Apache Maven: https://maven.apache.org/install.html
3. An IDE such as VS Code or Eclipse (optional, but recommended)

For VS Code, install the **Extension Pack for Java**.

## Project Layout

Maven uses this standard layout:

```text
project-folder/
├── pom.xml
└── src/
    ├── main/java/       production code
    └── test/java/       JUnit tests
```

The directory structure must match the Java package declaration. For example, a file containing:

```java
package patient_intake;
```

belongs in a directory named `patient_intake` beneath a Java source root.

Maven creates `target/` when it builds the project. Do not commit `target/` to GitHub.

## Build and Test Commands

Run commands from the directory containing `pom.xml`:

```text
mvn clean compile
mvn test
```

To compile tests without executing them:

```text
mvn test -DskipTests
```

The starter handout is expected to fail tests until its TODOs are implemented. The solution project should pass its required tests.

## Running the Program

After compiling:

```text
java -cp target/classes patient_intake.Main
```

The current Week 1 program reads the CSV using a path relative to the project directory:

```java
new File("src/main/java/patient_intake/patients.csv")
```

A relative path is resolved from the directory where Java is launched, not from the location of the `.java` file.

## VS Code

1. Open the cloned project folder.
2. Open a Java source file.
3. Use the **Run** link above `main`, or use the Testing view for JUnit tests.

The project should still build from Maven even when using a different IDE.

## Troubleshooting

- `Could not find or load main class`: run Java with `target/classes` as the classpath root.
- `package ... does not exist`: check the package declaration and source directory.
- `FileNotFoundException`: confirm the current working directory and relative CSV path.
- Maven cannot find a project: change into the directory containing `pom.xml`.

## Optional Cloud Environment

GitHub Codespaces can provide a Linux development environment in the browser: https://github.com/codespaces
