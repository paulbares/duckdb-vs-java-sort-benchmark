package io.paulbares;

import java.time.Duration;

public class BenchmarkHelper {

    public static void run(Runnable r, String name, int warmup, int runs) {
        IO.println("Starting warm up...");
        for (int i = 0; i < warmup; i++) {
            r.run();
        }
        IO.println("Warm up done");

        long max = Long.MIN_VALUE;
        long min = Long.MAX_VALUE;
        long totalElapsed = 0;

        IO.print("Running '" + name + "' " + (runs + 2) + " times: ");
        for (int i = 0; i < runs + 2; i++) {
            System.gc();

            long start = System.nanoTime();
            r.run();
            long elapsed = System.nanoTime() - start;
            if (elapsed < min) {
                min = elapsed;
            }
            if (elapsed > max) {
                max = elapsed;
            }
            totalElapsed += elapsed;
            System.out.print(Duration.ofNanos(elapsed).toMillis() + "ms ");
        }
        IO.println();

        double avgElapsed = (double) (totalElapsed - max - min) / runs; // discard outliers
        IO.println("Average '" + name + "' time: " + Duration.ofNanos((long) avgElapsed).toMillis() + "ms");
    }
}

