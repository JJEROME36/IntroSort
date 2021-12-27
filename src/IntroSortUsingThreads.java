import java.util.Arrays;

public class IntroSortUsingThreads
{
    private  int[] array;
    private  int numberOfThreads;

    public IntroSortUsingThreads(int[] arrayToSort, int numberOfThreads)
    {
        this.array = arrayToSort;
        this.numberOfThreads = numberOfThreads;
        if(numberOfThreads != 1 && numberOfThreads != 2 && numberOfThreads != 4 && numberOfThreads != 8)
        {
            System.out.println("You can only use 1, 2, 4 or 8 threads.");
            System.exit(1); // wrong amount of threads
        }
        division();         // division array to smaller arrays and lunching IntroSort
    }

    private void division()
    {
        int size  = array.length / numberOfThreads;
        if(size < 1 || numberOfThreads == 1)        // small amount of numbers to sort use only one thread
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
        else    // lunching introSort on threads
        {
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

            mergeAllCutOutArrays(numberOfThreads,array,size);
        }
    }


    private static void mergeAllCutOutArrays(int numberOfThreads, int[] array, int size)
    {
        if(numberOfThreads == 2)
        {
            int[] result = new int[array.length];
            mergeCutOutArrays(0,size,size, array.length,array,result);   // hole array sorted
            for(int i = 0; i < array.length; i++)
            {
                array[i] = result[i];
            }
        }
        else if(numberOfThreads == 4)
        {
            int[] result1 = new int[2*size];
            int[] result2 = new int[array.length - 2*size];
            mergeCutOutArrays(0,size,size,2*size ,array,result1);     // first half array sorted
            mergeCutOutArrays(2*size,3*size,3*size,array.length,array,result2); // second half array sorted
            mergeArrays(result1,result2,array);
        }
        else if(numberOfThreads == 8)
        {
            int[] result1 = new int[2*size];
            int[] result2 = new int[2*size];
            int[] result3 = new int[2*size];
            int[] result4 = new int[array.length - 6*size];

            mergeCutOutArrays(0,size,size,2*size ,array,result1);
            mergeCutOutArrays(2*size,3*size,3*size,size*4,array,result2);
            mergeCutOutArrays(4*size,5*size,5*size,size*6,array,result3);
            mergeCutOutArrays(6*size,7*size,7*size, array.length, array, result4);

            int[] result5 = new int[result1.length + result2.length];
            int[] result6 = new int[result3.length + result4.length];

            mergeArrays(result1,result2,result5);
            mergeArrays(result3,result4,result6);
            mergeArrays(result5,result6,array);
        }
    }


    private static void mergeCutOutArrays(int start1, int end1, int start2, int end2, int[] array, int[] result)
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

    private static void mergeArrays(int[] result1, int[] result2, int[] array)
    {
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
