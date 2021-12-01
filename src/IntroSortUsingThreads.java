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
            IntroSortRunnable introSortRunnable;
            Thread[] threads  = new Thread[numberOfThreads];
            for (int i = 0; i < numberOfThreads; i++)
            {
                if(i != numberOfThreads - 1)
                {
                    introSortRunnable = new IntroSortRunnable(size*(i),size*(i+1) - 1,array);
                }
                else
                {
                    introSortRunnable = new IntroSortRunnable(size*(i),array.length-1,array);
                }
                threads[i] = new Thread(introSortRunnable);
                threads[i].start();
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

            int[] result1 = new int[2*size];
            int[] result2 = new int[array.length - 2*size];

            mergeArrays(0,size,size,2*size ,array,result1);

            mergeArrays(2*size,3*size,3*size,array.length,array,result2);


            int i = 0, j = 0, k = 0;

            while (i < result1.length && j < result2.length)
            {
                if (result1[i] < result2[j])
                {
                    array[k++] = result1[i++];
                }
                else
                {
                    array[k++] = result2[j++];
                }

            }

            while (i < result1.length)
            {
                array[k++] = result1[i++];
            }

            while (j < result2.length)
            {
                array[k++] = result2[j++];
            }

        }
    }


    public static void mergeArrays(int start1, int end1, int start2, int end2, int[] array, int[] result)
    {
        int i = 0, j = 0, k = 0;
        int size1 = end1 - start1;
        int size2 = end2 - start2;

        while (i < size1 && j < size2)
        {
            if (array[start1 + i] < array[start2 + j])
            {
                result[k++] = array[start1 + i++];
            }

            else
            {
                result[k++] = array[start2 + j++];
            }

        }

        while (i < size1)
        {
            result[k++] = array[start1 + i++];
        }

        while (j < size2)
        {
            result[k++] = array[start2 + j++];
        }
    }
}
