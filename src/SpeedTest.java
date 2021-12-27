public class SpeedTest
{
    public static void speedTest(int sizeOfArray, int sample)
    {
        if(sample < 3)
        {
            System.out.println("Sample is small result can be different for bigger sample or size of array");
        }
        int[] array = new int[sizeOfArray];
        int currentSample = 0;
        int numberOfAlgorithms = 4;
        long timeElapsedHeapSort = 0;
        long timeElapsedIntroSort = 0;
        long timeElapsedInsertionSort = 0;
        long timeElapsedIntroSortOnFourThreads = 0;

        array = CreateRandomArray.createArray(sizeOfArray);



        for(int i = 0; i < sample;i++)
        {
            currentSample++;
            currentSamplePrint(currentSample,sample,numberOfAlgorithms);

            long start = System.currentTimeMillis();
            HeapSort heapSort = new HeapSort(0,sizeOfArray-1,array.clone());
            long finish = System.currentTimeMillis();
            timeElapsedHeapSort = timeElapsedHeapSort + finish - start;

            currentSample++;
            currentSamplePrint(currentSample,sample,numberOfAlgorithms);

            start = System.currentTimeMillis();
            InsertionSort insertionSort = new InsertionSort(0,sizeOfArray-1,array.clone());
            finish = System.currentTimeMillis();
            timeElapsedInsertionSort = timeElapsedInsertionSort + finish - start;

            currentSample++;
            currentSamplePrint(currentSample,sample,numberOfAlgorithms);

            start = System.currentTimeMillis();
            IntroSortRunnable introSortRunnable = new IntroSortRunnable(0,sizeOfArray-1,array.clone());
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
            finish = System.currentTimeMillis();
            timeElapsedIntroSort = timeElapsedIntroSort + finish - start;

            currentSample++;
            currentSamplePrint(currentSample,sample,numberOfAlgorithms);

            start = System.currentTimeMillis();
            IntroSortUsingThreads introSortUsingThreads = new IntroSortUsingThreads(array.clone(),4);
            finish = System.currentTimeMillis();
            timeElapsedIntroSortOnFourThreads = timeElapsedIntroSortOnFourThreads + finish - start;

        }

        System.out.println();
        System.out.println("IntroSort on four threads time elapsed = " + timeElapsedIntroSortOnFourThreads/sample + "ms");
        System.out.println("IntroSort time elapsed = " + timeElapsedIntroSort/sample + "ms");
        System.out.println("HeapSort time elapsed = " + timeElapsedHeapSort/sample + "ms");
        System.out.println("InsertionSort time elapsed = " + timeElapsedInsertionSort/sample + "ms");
    }

    private static void currentSamplePrint(int currentSample, int sample, int numberOfAlgorithms)
    {
        System.out.print("\r" + "[" + currentSample + "/" + sample * numberOfAlgorithms + "]");
    }


}
