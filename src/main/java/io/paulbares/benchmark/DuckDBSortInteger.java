package io.paulbares.benchmark;

import io.paulbares.BenchmarkInteger;
import io.paulbares.DuckDBDriver;
import org.apache.arrow.vector.complex.reader.IntReader;
import org.duckdb.DuckDBAppender;

public class DuckDBSortInteger {

    static void main(String[] args) throws Exception {
        DuckDBDriver driver = new DuckDBDriver();

        new BenchmarkInteger(
                args,
                "DuckDB sort integer",
                input -> driver.createAndFeedTable(input, "INT", DuckDBAppender::append),
                input -> driver.sort(input.size(), IntReader::readInteger))
                .run();
    }
}
