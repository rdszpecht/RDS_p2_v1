package ReadAndSort;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

class main{
    public static void main(String[] args) {
        Score[] unsortedScores;
        String path;

        System.out.println("Select the path of the file to sort: (by default ./src/txt/in.txt)");
        Scanner s = new Scanner(System.in);
        path = s.nextLine();
        if (path.equals("")){
            path = "./src/txt/in.txt";
        }
        System.out.println("Loading file...");
        unsortedScores = InitReader.fileToList(path);
        Score[] sortedScores = new Score[unsortedScores.length];

        long startTime = System.currentTimeMillis();
        sortedScores = Sorter.concurrentSort(unsortedScores);
        long elapsedTime = System.currentTimeMillis() - startTime;

        System.out.println("Elapsed time sorting list: " + (((double) elapsedTime) / 1000.0) + "s;\nNumber of scores: " + sortedScores.length);

        try {
            System.out.println("\n Writing result to file (./src/txt/out.txt)...");
            writeToFile(sortedScores);
        }catch(IOException ex){
            System.out.println("Can't write");
        }finally {
            System.out.println("The process is done.");
        }
        //END
    }

    private static void writeToFile(Score[] list) throws IOException{
        File file = new File("./src/txt/out.txt");
        file.createNewFile();
        PrintWriter pw = new PrintWriter(file);
        pw.println(list.length);
        String  test = "0";

        for (Score s: list){
            pw.println(s.getName() + ": " + s.getScore());
        }

        pw.close();
    }

}