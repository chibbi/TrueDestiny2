import java.util.Random;

public class temp {

    public static void main(String[] args) {
        int realAmount = 64;
        int[] allInts = new int[realAmount + 1];
        for (int i = 0; i < 1000000; i++) {
            int randy = new Random().nextInt(realAmount + 1);
            allInts[randy] = allInts[randy] + 1;

        }
        int i = 0;
        for (int into : allInts) {
            System.out.println(i + " = " + into);
            i++;
        }

    }

}
