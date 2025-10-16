# Fowl Play: I ditched Java's Sort for DuckDB's because it's quacker
This is the code source I used to compare Java's sort vs DuckDB's sort. Refer to my article for the results.

## Prerequisites
In order to build and run the benchmarks, you will need:

- Java JDK >= 25
- Latest stable Apache Maven

## Build
```bash
mvn package
```

## Run
Benchmark a located at `src/main/java/io/paulbares/benchmark`. To run `JdkSortString` i.e. sorting with `Collections.sort()` a list of random strings of 100000 elements:
```bash
java -Xmx20g -Xms20g --add-opens=java.base/java.nio=ALL-UNNAMED --class-path target/duckdbsort-1.0-SNAPSHOT.jar io.paulbares.benchmark.JdkSortString 100000
```