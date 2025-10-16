package io.paulbares.benchmark;

import io.paulbares.BenchmarkInteger;

import java.util.ArrayList;
import java.util.Collections;

public class JdkSortInteger {

    static void main(String[] args) throws Exception {
        new BenchmarkInteger(
                args,
                "JDK sort integer",
                input -> Collections.sort(new ArrayList<>(input)))
                .run();
    }
}
