package ReadAndSort;

import java.util.concurrent.RecursiveTask;

public class FJTask extends RecursiveTask<Integer>{

    private Score[] array;
    private int l,r,m;

    public FJTask(Score[] array, int l, int r){
        this.array = array;
        this.l = l;
        this.r = r;
        this.m = (l+r)/2;
    }

    @Override
    protected Integer compute(){
        if (l < r){
            FJTask left = new FJTask(array, l, m);
            FJTask right = new FJTask(array, m+1, r);

            left.fork();
            int rres = right.compute();
            int lres = left.join();

            merge();
        }
        return 0;
    }

    private void merge(){
        int n1 = m - l + 1;
        int n2 = r - m;

        Score L[] = new Score [n1];
        Score R[] = new Score [n2];

        for (int i=0; i<n1; ++i)
            L[i] = array[l + i];
        for (int j=0; j<n2; ++j)
            R[j] = array[m + 1+ j];

        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2){
            if ((L[i].compareTo(R[j])) == 1){
                array[k] = L[i];
                i++;
            }else{
                array[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1){
            array[k] = L[i];
            i++;
            k++;
        }
        while (j < n2){
            array[k] = R[j];
            j++;
            k++;
        }
    }
}
