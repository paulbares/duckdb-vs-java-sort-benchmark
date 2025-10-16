package io.paulbares;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class Data {

    static final int DEFAULT_SIZE = 100_000;
    static final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static int getSize(String[] args) {
        if (args.length == 0) {
            return DEFAULT_SIZE;
        } else {
            return Integer.parseInt(args[0]);
        }
    }

    public static List<Integer> generateRandomIntegerList(int size) {
        return generateRandomList(size, Random::nextInt);

    }

    public static List<String> generateRandomStringList(int size) {
        return generateRandomList(size, random -> generateRandomString(random, random.nextInt(1, 17)));
    }

    public static <T> List<T> generateRandomList(int size, Function<Random, T> supplier) {
        List<T> list = new ArrayList<>(size);
        Random rand = new Random(1234);
        for (int i = 0; i < size; i++) {
            list.add(supplier.apply(rand));
        }
        return list;
    }

    public static String generateRandomString(Random random, int length) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }
}
