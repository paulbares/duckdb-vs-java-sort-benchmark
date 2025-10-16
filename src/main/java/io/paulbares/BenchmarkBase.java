package io.paulbares;

import io.paulbares.DuckDBDriver.ThrowingConsumer;

import java.sql.SQLException;
import java.util.List;

public abstract class BenchmarkBase<T> {

    private final int size;
    private final String name;
    private final ThrowingConsumer<List<T>> consumer;
    private final ThrowingConsumer<List<T>> beforeRunConsumer;

    public BenchmarkBase(String[] args, String name, ThrowingConsumer<List<T>> beforeRunConsumer, ThrowingConsumer<List<T>> consumer) {
        this.size = Data.getSize(args);
        this.name = name;
        this.beforeRunConsumer = beforeRunConsumer;
        this.consumer = consumer;
    }

    public void run() throws Exception {
        // Setup
        List<T> input = generateList(this.size);

        if (this.beforeRunConsumer != null) {
            this.beforeRunConsumer.accept(input);
        }

        // Benchmark - run
        BenchmarkHelper.run(() -> {
            try {
                this.consumer.accept(input);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }, this.name + " with list of size " + this.size, 2, 10);
    }

    protected abstract List<T> generateList(int size);
}
