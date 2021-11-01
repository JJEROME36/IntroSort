import java.util.concurrent.ThreadLocalRandom;

public class CreateRandomArray
{
    public static int[] createArray(int n)
    {
        int[] arrayToCreate = new int[n];

        for (int i = 0; i < arrayToCreate.length; i++)
        {
            int randomNumber = ThreadLocalRandom.current().nextInt(1, 100 + 1);
            arrayToCreate[i] = randomNumber;
        }
        return arrayToCreate;
    }
}
