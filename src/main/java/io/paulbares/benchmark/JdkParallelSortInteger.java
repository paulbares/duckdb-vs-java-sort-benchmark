package io.paulbares.benchmark;


import io.paulbares.BenchmarkInteger;

public class JdkParallelSortInteger {

    static void main(String[] args) throws Exception {
        new BenchmarkInteger(
                args,
                "JDK Parallel sort integer",
                input -> input.stream().parallel().sorted().toList())
                .run();
    }
}
