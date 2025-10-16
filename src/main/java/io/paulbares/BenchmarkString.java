package io.paulbares;

import io.paulbares.DuckDBDriver.ThrowingConsumer;

import java.util.List;

import static io.paulbares.Data.generateRandomStringList;

public class BenchmarkString extends BenchmarkBase<String> {

    public BenchmarkString(String[] args, String name, ThrowingConsumer<List<String>> consumer) {
        this(args, name, null, consumer);
    }

    public BenchmarkString(String[] args, String name, ThrowingConsumer<List<String>> beforeRunConsumer, ThrowingConsumer<List<String>> consumer) {
        super(args, name, beforeRunConsumer, consumer);
    }

    @Override
    protected List<String> generateList(int size) {
        return generateRandomStringList(size);
    }
}
