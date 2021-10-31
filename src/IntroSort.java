public class IntroSort
{
    public static int[] array;
    public IntroSort(int[] arrayToSort)
    {
        this.array = arrayToSort;
        int maxDepthLimit = (int)Math.floor(2 * Math.log(arrayToSort.length) / Math.log(2));
        System.out.println(maxDepthLimit);
        sort(0, arrayToSort.length-1, maxDepthLimit);
    }

    private void sort(int begin, int end, int maxDepthLimit)
    {
        int size = end - begin;
        if(size > 16)
        {
            if (maxDepthLimit == 0)
            {
                HeapSort heapSort = new HeapSort(begin, end, array);
                return;
            }

            maxDepthLimit = maxDepthLimit - 1;

            int pivot = calculatePivot(begin, begin + ((end-begin)/2)+1, end);

            swap(pivot,end);

            int splitIndex = calculateSplitIndex(begin,end);

            sort(begin, splitIndex - 1,maxDepthLimit);
            sort(splitIndex + 1, end, maxDepthLimit);
        }
        else
        {
            InsertionSort insertionSort = new InsertionSort(begin,end,array);
        }
    }

    private int calculatePivot(int index1, int index2, int index3)
    {
        int maxValue = Math.max(Math.max(array[index1],array[index2]),array[index3]);
        int minValue = Math.min(Math.min(array[index1],array[index2]),array[index3]);

        int median = maxValue ^ minValue ^ array[index1] ^ array[index2] ^array[index3];

        if(median == array[index1])
        {
           return index1;
        }
        else if(median == array[index2])
        {
            return index2;
        }
        else
        {
            return index3;
        }
    }

    private int calculateSplitIndex(int lowerElement, int higherElement)
    {
        int pivot = array[higherElement];

        int indexSmallerElement = (lowerElement - 1);

        for(int i = lowerElement; i <= higherElement - 1; i++)
        {
            if(array[i] <= pivot)
            {
                indexSmallerElement++;
                swap(indexSmallerElement, i);
            }
        }

        swap(indexSmallerElement + 1, higherElement);
        return indexSmallerElement + 1;
    }
    private void swap(int a , int b)
    {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

}
