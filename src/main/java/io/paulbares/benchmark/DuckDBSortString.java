package io.paulbares.benchmark;

import io.paulbares.BenchmarkString;
import io.paulbares.DuckDBDriver;
import org.apache.arrow.vector.complex.reader.VarCharReader;
import org.duckdb.DuckDBAppender;

public class DuckDBSortString {

    static void main(String[] args) throws Exception {
        DuckDBDriver driver = new DuckDBDriver();

        new BenchmarkString(
                args,
                "DuckDB sort string",
                input -> driver.createAndFeedTable(input, "VARCHAR", DuckDBAppender::append),
                input -> driver.sort(input.size(), VarCharReader::readText))
                .run();
    }
}
