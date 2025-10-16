package io.paulbares.benchmark;

import io.paulbares.BenchmarkString;

import java.util.ArrayList;
import java.util.Collections;

public class JdkSortString {

    static void main(String[] args) throws Exception {
        new BenchmarkString(
                args,
                "JDK sort string",
                input -> Collections.sort(new ArrayList<>(input)))
                .run();
    }
}
