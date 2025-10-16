package io.paulbares;

import org.apache.arrow.memory.RootAllocator;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.ipc.ArrowReader;
import org.duckdb.DuckDBAppender;
import org.duckdb.DuckDBConnection;
import org.duckdb.DuckDBResultSet;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DuckDBDriver {

    private final DuckDBConnection conn;

    public DuckDBDriver() {
        try {
            this.conn = (DuckDBConnection) DriverManager.getConnection("jdbc:duckdb:");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> void createAndFeedTable(List<T> list, String sqlType, ThrowingBiConsumer<DuckDBAppender, T> onNewRowConsumer) throws SQLException {
        try (var stmt = this.conn.createStatement()) {
            stmt.execute("CREATE TABLE tbl (x " + sqlType + ")");
        }

        try (var appender = this.conn.createAppender(DuckDBConnection.DEFAULT_SCHEMA, "tbl")) {
            for (T e : list) {
                appender.beginRow();
                onNewRowConsumer.accept(appender, e);
                appender.endRow();
            }
        }
    }

    public <T> List<T> sort(int size, ThrowingFunction<FieldReader, T> columnReader) {
        try {
            var stmt = this.conn.prepareStatement("SELECT x FROM tbl ORDER BY x");
            var resultset = (DuckDBResultSet) stmt.executeQuery();
            var allocator = new RootAllocator();
            List<T> r = new ArrayList<>(size);
            try (var reader = (ArrowReader) resultset.arrowExportStream(allocator, 512)) {
                while (reader.loadNextBatch()) {
                    FieldVector vector = reader.getVectorSchemaRoot().getVector(0);
                    FieldReader vectorReader = vector.getReader();
                    for (int i = 0; i < vector.getValueCount(); i++) {
                        vectorReader.setPosition(i);
                        r.add(columnReader.apply(vectorReader));
                    }
                }
            }
            return r;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public interface ThrowingBiConsumer<T, U> {
        void accept(T t, U u) throws SQLException;
    }

    public interface ThrowingConsumer<T> {
        void accept(T t) throws SQLException;
    }

    public interface ThrowingFunction<T, U> {
        U apply(T t) throws SQLException;
    }
}
