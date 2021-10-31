public class InsertionSort
{
    private int[] array;
    private int begin;
    private int end;
    public InsertionSort(int begin, int end, int[] array)
    {
        System.out.println("insertion");
        this.array = array;
        this.begin = begin;
        this.end = end;
        sort();
    }
    private void sort()
    {
        for(int i = begin; i <= end; i++)
        {
            int key  = array[i];
            int j = i;
            while (j > begin && array[j-1] > key)
            {
                array[j] = array[j-1];
                j--;
            }
            array[j] = key;
        }
    }
}
