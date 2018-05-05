package ReadAndSort;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class InitReader{

    public static Score[] fileToList(String path){
        Score[] scores;
        Scanner s;

        try {
            s = new Scanner(new File(path));
            Score score;
            String[] splt;
            String line;
            int i = 0;
            scores = new Score[Integer.parseInt(s.nextLine())];

            while (s.hasNextLine()) {
                line = s.nextLine();
                splt = line.split(": ");
                scores[i] = new Score(splt[0], Integer.parseInt(splt[1]));
                i++;
            }
            s.close();
            return scores;

        }catch(FileNotFoundException ex){
            System.out.println("File not found.");
            return new Score[1];
        }

    }
}