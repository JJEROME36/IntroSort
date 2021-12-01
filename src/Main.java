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

        IntroSortUsingThreads introSortUsingThreads = new IntroSortUsingThreads(array);

        if(printingArray) printArray(array);

        System.out.print("Do you wanna compare with other sorts (Y/N)? ");
        char inputIsCompare = input.next().charAt(0);
        if( inputIsCompare == 'y' || inputIsCompare == 'Y')
        {
            System.out.println("it can take some time...");
            SpeedTest.speedTest(10000000,10);
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
