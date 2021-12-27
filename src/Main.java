import java.util.Arrays;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        System.out.print("How many elements in array: ");
        int sizeOfArray = input.nextInt();

        System.out.print("Do you wanna print sorted array (Y/N)? ");
        boolean printingArray = true;

        char inputIsPrinted = input.next().charAt(0);
        if(inputIsPrinted == 'N' || inputIsPrinted == 'n')
        {
            printingArray = false;
        }

        int[] array = CreateRandomArray.createArray(sizeOfArray);

        IntroSortUsingThreads introSortUsingThreads = new IntroSortUsingThreads(array,2);

        if(printingArray) printArray(array);

        System.out.print("Do you wanna compare check scalability (Y/N)? ");
        char inputAnswer = input.next().charAt(0);
        if( inputAnswer == 'y' || inputAnswer == 'Y')
        {
            ScalabilityTest.scalabilityTest(3000000,10);
            System.out.println();
        }

        System.out.print("Do you wanna compare with other sorts (Y/N)? ");
        inputAnswer = input.next().charAt(0);
        if( inputAnswer == 'y' || inputAnswer == 'Y')
        {
            System.out.println("it can take some time...");
            SpeedTest.speedTest(100000,10);
            System.out.println();
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
