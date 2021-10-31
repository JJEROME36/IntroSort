import java.util.concurrent.ThreadLocalRandom;

public class CreatRandomArray
{
    public static int[] creatArray(int n)
    {
        int[] arrayToCreat = new int[n];

        for (int i = 0; i < arrayToCreat.length; i++)
        {
            int randomNumber = ThreadLocalRandom.current().nextInt(1, 100 + 1);
            arrayToCreat[i] = randomNumber;
        }
        return arrayToCreat;
    }
}
