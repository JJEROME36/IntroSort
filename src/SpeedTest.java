public class SpeedTest
{
    public static void speedTest(int sizeOfArray)
    {
        int[] array = new int[sizeOfArray];
        long timeElapsedHeapSort = 0;
        long timeElapsedIntroSort = 0;
        long timeElapsedInsertionSort = 0;
        array = CreateRandomArray.createArray(sizeOfArray);

        for(int i = 0; i < 20;i++)
        {
            long start = System.currentTimeMillis();
            HeapSort heapSort = new HeapSort(0,sizeOfArray-1,array.clone());
            long finish = System.currentTimeMillis();
            timeElapsedHeapSort = timeElapsedHeapSort + finish - start;


            start = System.currentTimeMillis();
            InsertionSort insertionSort = new InsertionSort(0,sizeOfArray-1,array.clone());
            finish = System.currentTimeMillis();
            timeElapsedInsertionSort = timeElapsedInsertionSort + finish - start;

            start = System.currentTimeMillis();
            IntroSort introSort = new IntroSort(array.clone());
            finish = System.currentTimeMillis();
            timeElapsedIntroSort = timeElapsedIntroSort + finish - start;
        }

        System.out.println("IntroSort time elapsed = " + timeElapsedIntroSort + "ms");
        System.out.println("HeapSort time elapsed = " + timeElapsedHeapSort + "ms");
        System.out.println("InsertionSort time elapsed = " + timeElapsedInsertionSort + "ms");
    }
}
