package BucketSort;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class BucketSort {

    public static long iterations = 0;

    public static int[] bucketSort(int[] array) {

        long globalMin = array[0];
        long globalMax = array[0];
        for (int i = 0; i < array.length; i++) {
            iterations++;
            if (array[i] > globalMax) {
                globalMax = array[i];
            }
            if (array[i] < globalMin) {
                globalMin = array[i];
            }
        }

        if (array == null || array.length < 2) {
            return array;
        }

        int[] result = new int[array.length];

        int numberOfBuckets = array.length / 5;
        long range = (globalMax - globalMin) / numberOfBuckets + 1;
        long min = globalMin;
        long max = globalMin + range;

        int indexBuckets = 1;
        int indexResult = 0;

        boolean zero = false;
        int countZero = 0;

        while (indexBuckets <= numberOfBuckets) {
            int[] bucket = new int[32];
            int j = 0;
            for (int i = 0; i < array.length; i++) {
                iterations++;
                if (array[i] >= min && array[i] < max) {
                    if (array[i] == 0) {
                        zero = true;
                        countZero++;
                        bucket[j++] = array[i];
                    } else {
                        bucket[j++] = array[i];
                    }
                }
            }

            Arrays.sort(bucket);

            min += range;
            max += range;
            indexBuckets++;

            if (indexBuckets == numberOfBuckets) {
                max = globalMax + 1;
            }

            int unnecessaryZeros = -countZero;

            if (zero) {
                for (int i = 0; i < bucket.length; i++) {
                    iterations++;
                    if (bucket[i] == 0) {
                        unnecessaryZeros++;
                    }
                }
                for (int i = unnecessaryZeros; i < bucket.length; i++) {
                    iterations++;
                    result[indexResult] = bucket[i];
                    indexResult++;
                }
                zero = false;
            } else {
                for (int i = 0; i < bucket.length; i++) {
                    iterations++;
                    if (bucket[i] == 0) {
                        continue;
                    } else {
                        result[indexResult] = bucket[i];
                        indexResult++;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("src/BucketSort/Sets.txt");
        Scanner fileStream = new Scanner(file);

        File iterat = new File("src/BucketSort/iterations.txt");
        File time = new File("src/BucketSort/time.txt");
        PrintWriter itr = new PrintWriter(iterat);
        PrintWriter tm = new PrintWriter(time);


        while (fileStream.hasNextLine()) {
            String str = fileStream.nextLine();
            if (str.contains("Set")) {
                continue;
            } else {
                String[] numbers = str.split(" ");
                int[] array = new int[numbers.length];

                for (int i = 0; i < numbers.length; i++) {
                    array[i] = Integer.parseInt(numbers[i]);
                }

                long startTime = System.nanoTime();
                array = bucketSort(array);
                long estimatedTime = System.nanoTime() - startTime;

                itr.print(iterations + " ");
                tm.print(estimatedTime + " ");


                System.out.println(array.length);
                System.out.println(estimatedTime + " | " + iterations);
                System.out.println();
            }
        }
        fileStream.close();
        itr.close();
        tm.close();

    }
}







