import java.util.Arrays;

public class IntroSortUsingThreads
{
    private  int[] array;
    private  int numberOfThreads = 4;

    public IntroSortUsingThreads(int[] arrayToSort)
    {
        this.array = arrayToSort;
        division();
    }

    private void division()
    {
        int size  = array.length / numberOfThreads;
        if(size < 1)
        {
            IntroSortRunnable introSortRunnable = new IntroSortRunnable(0,array.length-1,array);
            Thread introSortThread = new Thread(introSortRunnable);
            introSortThread.start();
            try
            {
                introSortThread.join();
            }
            catch (InterruptedException e)
            {
                System.out.println("error intro sort: " + e);
            }
        }
        else
        {
            // lunching introSort
            IntroSortRunnable[] introSortRunnable = new IntroSortRunnable[numberOfThreads];
            Thread[] threads  = new Thread[numberOfThreads];
            for (int i = 0; i < numberOfThreads; i++)
            {
                if(i != numberOfThreads - 1)
                {
                    introSortRunnable[i] = new IntroSortRunnable(size*(i),size*(i+1) - 1,array);
                    threads[i] = new Thread(introSortRunnable[i]);
                    threads[i].start();
                }
                else
                {
                    introSortRunnable[i] = new IntroSortRunnable(size*(i),array.length-1,array);
                    threads[i] = new Thread(introSortRunnable[i]);
                    threads[i].start();
                }
            }

            for(int i = 0; i < numberOfThreads; i++)
            {
                try
                {
                    threads[i].join();
                }
                catch (InterruptedException e)
                {
                    System.out.println("join exception: " + e);
                }
            }

            merge(0,size-1,size,2*size,array);
            merge(2*size,3*size-1,3*size,array.length,array);
            merge(0,size * 2 -1,size*2, array.length,array);

        }
    }

    // merging O(n+m)
    public static void merge(int start1, int end1, int start2, int end2, int[] array)
    {
        int size1 = end1 - start1;
        int size2 = end2 - start2;
        for (int i = size2-1; i >= 0; i--)
        {
            int j, last = array[end1];
            for (j = size1-1; j >= 0 && array[start1 + j] > array[start2 + i]; j--)
            {
                array[start1+j + 1] = array[start1+j];
            }

            // If there was a greater element
            if (j != end1-1 || last >array[start2+i])
            {
                array[start1+j + 1] = array[start2+i];
                array[start2+i] = last;
            }
        }
    }

}
