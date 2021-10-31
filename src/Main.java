import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        System.out.print("How many elements: ");
        Scanner input = new Scanner(System.in);
        int sizeOfArray = input.nextInt();
        int[] array = CreatRandomArray.creatArray(sizeOfArray);

       // printArray(array);
        IntroSort introSort = new IntroSort(array);
        System.out.println("after sort");
       // printArray(array);
    }

    private static void printArray(int[] array)
    {
        for (int i = 0; i < array.length; i++)
        {
            System.out.println(i+1 + ". " + array[i]);
        }
    }
}
