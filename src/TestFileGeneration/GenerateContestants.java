package TestFileGeneration;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class GenerateContestants {
    public static void main(String[] args) {
        try (PrintWriter pw = new PrintWriter("contestants.txt")) {
            int nContestants = 10000000;
            pw.println(nContestants);
            for (int i = 0; i < nContestants; i++) {
                pw.println("Contestant "+(i+1)+": "+new Random().nextInt(nContestants*10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}