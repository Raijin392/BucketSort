package BucketSort;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class CreateSets {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("src/BucketSort/Sets.txt");
        PrintWriter pw = new PrintWriter(file);
        Random random = new Random();

        int numberOfSets = 1;
        int amountOfElems = 100;
        int max = 1000000;
        int min = -1000000;

        while (numberOfSets <= 100) {
            pw.println("Set №" + numberOfSets + " Amount of elems: " + amountOfElems);
            for (int i = 0; i < amountOfElems; i++) {
                pw.print((random.nextInt(max - min) + min) + " ");
            }
            numberOfSets++;
            amountOfElems = 100 * numberOfSets;
            pw.println();
        }
        pw.close();




    }
}
