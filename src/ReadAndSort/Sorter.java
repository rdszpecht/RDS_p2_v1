package ReadAndSort;

import java.util.concurrent.ForkJoinPool;

public abstract class Sorter {

    public static Score[] nonConcurrentSort(Score[] unsortedScores){
        sort(unsortedScores, 0, unsortedScores.length-1);

        // While returning unsortedScores is actually sorted tho.
        return unsortedScores;
    }

    public static Score[] concurrentSort(Score[] unsortedScores){
        ForkJoinPool forkJoinPool = new ForkJoinPool(8);
        int resul = forkJoinPool.invoke(new FJTask(unsortedScores,0,unsortedScores.length-1));
        forkJoinPool.shutdown();

        // While returning unsortedScores is actually sorted tho.
        return unsortedScores;
    }

    private static void merge(Object[] arr, int l, int m, int r){
        int n1 = m - l + 1;
        int n2 = r - m;

        Object L[] = new Object [n1];
        Object R[] = new Object [n2];

        for (int i=0; i<n1; ++i)
            L[i] = arr[l + i];
        for (int j=0; j<n2; ++j)
            R[j] = arr[m + 1+ j];

        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2){
            if (((Score)L[i]).compareTo((Score)R[j]) == 1){
                arr[k] = L[i];
                i++;
            }
            else{
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1){
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2){
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    private static void sort(Object[] arr, int l, int r){
        if (l < r){
            int m = (l+r)/2;

            sort(arr, l, m);
            sort(arr , m+1, r);

            merge(arr, l, m, r);
        }
    }
}