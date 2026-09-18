# CSC226 Hospital/ER Simulator - Week 2

## Triage Efficiency

You are building a small search-analysis module for a hospital emergency room. This week focuses on comparing search strategies, measuring performance, and justifying the best approach for patient lookup.

For Java, Maven, package, IDE, and troubleshooting guidance, see [DevelopmentEnvironment.md](../../../../DevelopmentEnvironment.md).

## Required Work: 80%

Complete all four core checkpoints for the required 80%. The optional extension below is worth the remaining 20%.

| Checkpoint | Value | Requirements |
|---|---:|---|
| Linear search | 20% | Implement `linearSearch()` in `EfficiencyTester.java` so it scans the array in order and returns the matching `Patient` or `null`. |
| Binary search | 20% | Implement `binarySearch()` in `EfficiencyTester.java` for a sorted array of patients. This method must use the midpoint-halving strategy. |
| Search driver | 20% | Use `Main.java` to generate patient data, sort it when needed, and test all three search methods with both found and not-found IDs. |
| JUnit coverage | 20% | Add tests for the required search methods and explain why each test matters. |

## Optional Extension: 20%

| Extension | Value | Requirement |
|---|---:|---|
| Alternative O(log n) algorithm | 5% | Research and implement a second logarithmic-time search algorithm such as exponential search or jump search. |
| Timing comparison report | 5% | Measure how long each algorithm takes on arrays of different sizes and record the results in a table. |
| Analysis write-up | 5% | Explain why the recommended algorithm is best for emergency-room patient lookups and discuss the limitations of runtime measurements. |
| Documentation and reflection | 5% | Clearly cite your algorithm source and describe how the results relate to the Big O analysis. |

## Data and Search Contract

- Patient IDs are strings such as `P00001`.
- `linearSearch()` works on any array order.
- `binarySearch()` and `logNSearch()` assume the patient array is sorted by patient ID before searching.
- A not-found patient should return `null`.

## Timing Comparison

Create a short document or markdown file comparing the following search methods on data sets of different sizes:

| Dataset Size | Linear Search Time | Binary Search Time | Alternative O(log n) Time |
|--------------|-------------------|-------------------|-----------------------------|
| 100 patients | ___ ns | ___ ns | ___ ns |
| 1,000 patients | ___ ns | ___ ns | ___ ns |
| 10,000 patients | ___ ns | ___ ns | ___ ns |
| 100,000 patients | ___ ns | ___ ns | ___ ns |

Answer these questions in your write-up:

1. As the dataset grows by 10x each time, how does each algorithm's time change on average?
2. Carroll Memorial Hospital sees about 50,000 patients per year. Which search algorithm would you recommend? Why?
3. Is measuring time in this way the best way to assess algorithmic efficiency?

## Test and Build Commands

Run these commands from the project directory containing `pom.xml`:

```text
mvn clean compile
```

This checks that your production code compiles. The starter project is expected to fail assertions until the TODOs are completed.

```text
mvn test
```

This runs the required JUnit tests.

To compile tests without running them:

```text
mvn test -DskipTests
```

## Student Tests

Add your JUnit tests in a clearly named class such as:

```text
src/test/java/triage_efficiency/SearchAlgorithmTests.java
```

Each test should check a distinct behavior, such as:
- element found in the first position
- element found in the middle
- element found near the end
- no match returns null
- sorted array behavior
- not-found behavior for the alternative search method

## Submission Checklist

- All `TODO` comments are complete.
- The required tests pass with `mvn test`.
- `Main.java` demonstrates the search methods on sample patient data.
- The timing comparison report is included.
- Optional algorithm work is clearly identified if submitted.
- Do not commit Maven's generated `target/` directory.
