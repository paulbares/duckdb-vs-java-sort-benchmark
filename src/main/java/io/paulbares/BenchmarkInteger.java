package io.paulbares;

import io.paulbares.DuckDBDriver.ThrowingConsumer;

import java.util.List;

import static io.paulbares.Data.generateRandomIntegerList;

public class BenchmarkInteger extends BenchmarkBase<Integer> {

    public BenchmarkInteger(String[] args, String name, ThrowingConsumer<List<Integer>> consumer) {
        super(args, name, null, consumer);
    }

    public BenchmarkInteger(String[] args, String name, ThrowingConsumer<List<Integer>> beforeRunConsumer, ThrowingConsumer<List<Integer>> consumer) {
        super(args, name, beforeRunConsumer, consumer);
    }

    @Override
    protected List<Integer> generateList(int size) {
        return generateRandomIntegerList(size);
    }
}
