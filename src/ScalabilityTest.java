public class ScalabilityTest
{
    public static void scalabilityTest(int sizeOfArray, int sample)
    {
        long timeElapsedOneThread = 0;
        long timeElapsedTwoThreads = 0;
        long timeElapsedFourThreads = 0;
        long timeElapsedEightThreads = 0;

        int[] array;

        array = CreateRandomArray.createArray(sizeOfArray);

        for(int i = 0; i < sample;i++)
        {
            long start = System.currentTimeMillis();
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
            long finish = System.currentTimeMillis();
            timeElapsedOneThread = timeElapsedOneThread + finish - start;

            start = System.currentTimeMillis();
            IntroSortUsingThreads introSortUsingThreadsTwo = new IntroSortUsingThreads(array.clone(),2);
            finish = System.currentTimeMillis();
            timeElapsedTwoThreads = timeElapsedTwoThreads + finish - start;

            start = System.currentTimeMillis();
            IntroSortUsingThreads introSortUsingThreadsFour = new IntroSortUsingThreads(array.clone(),4);
            finish = System.currentTimeMillis();
            timeElapsedFourThreads = timeElapsedFourThreads + finish - start;

            start = System.currentTimeMillis();
            IntroSortUsingThreads introSortUsingThreadsEight = new IntroSortUsingThreads(array.clone(),8);
            finish = System.currentTimeMillis();
            timeElapsedEightThreads = timeElapsedEightThreads + finish - start;
        }

        System.out.println();
        System.out.println("One thread = " + timeElapsedOneThread/sample + "ms");
        System.out.println("Two threads = " + timeElapsedTwoThreads/sample + "ms");
        System.out.println("Four threads = " + timeElapsedFourThreads/sample + "ms");
        System.out.println("Eight threads = " + timeElapsedEightThreads/sample + "ms");
    }
}
