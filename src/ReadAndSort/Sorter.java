package ReadAndSort;

import java.util.concurrent.ForkJoinPool;

public abstract class Sorter {

    public static Score[] concurrentSort(Score[] unsortedScores){
        ForkJoinPool forkJoinPool = new ForkJoinPool(8);
        int resul = forkJoinPool.invoke(new FJTask(unsortedScores,0,unsortedScores.length-1));
        forkJoinPool.shutdown();

        // While returning unsortedScores is actually sorted tho.
        return unsortedScores;
    }
}