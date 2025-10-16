package io.paulbares.benchmark;

import io.paulbares.BenchmarkString;

public class JdkParallelSortString {

    static void main(String[] args) throws Exception {
        new BenchmarkString(
                args,
                "JDK Parallel sort string",
                input -> input.stream().parallel().sorted().toList())
                .run();
    }
}
