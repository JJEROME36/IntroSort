import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        System.out.print("How many elements: ");
        int sizeOfArray = input.nextInt();

        System.out.print("Do you wanna print sorted array (Y/N)? ");
        boolean printingArray = true;
        char inputIsPrinted = input.next().charAt(0);
        if(inputIsPrinted == 'N' || inputIsPrinted == 'n')
        {
            printingArray = false;
        }

        int[] array = CreateRandomArray.createArray(sizeOfArray);

        long start = System.currentTimeMillis();
        IntroSort introSort = new IntroSort(array);
        long finish = System.currentTimeMillis();

        if(printingArray) printArray(array);

        System.out.print("Do you wanna compare with other sorts (Y/N)? ");
        char inputIsCompare = input.next().charAt(0);
        if( inputIsCompare == 'y' || inputIsCompare == 'Y')
        {
            SpeedTest.speedTest(3000);
        }
    }

    private static void printArray(int[] array)
    {
        for (int i = 0; i < array.length; i++)
        {
            System.out.println(i+1 + ". " + array[i]);
        }
    }
}
