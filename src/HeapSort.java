public class HeapSort
{
    private int[] array;
    public HeapSort(int begin, int end, int[] array)
    {
        this.array = array;
        int size = end - begin;
        for (int i = size/2; i >=0; i--)
        {

            heapify(size,i + begin);
        }

        for(int i = size; i > 0; i--)
        {
            int tmp = array[begin];
            array[begin] = array[begin + i];
            array[begin + i] = tmp;
            heapify(begin+i,begin);
        }
    }


    private void heapify(int sizeH, int index)
    {
        int largest = index;
        int left = 2*index + 1;
        int right = 2*index + 2;

        if(left < sizeH && array[left] > array[largest])
        {
            largest = left;
        }
        if(right < sizeH && array[right] > array[largest])
        {
            largest = right;
        }

        if(largest != index)
        {
            int tmp = array[index];
            array[index] = array[largest];
            array[largest] = tmp;

            heapify(sizeH,largest);
        }
    }
}
