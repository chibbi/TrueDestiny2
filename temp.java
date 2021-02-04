import java.util.Random;

public class temp {

    public static void main(String[] args) {
        int nul = 0;
        int one = 0;
        int two = 0;
        int three = 0;
        int four = 0;
        for (int i = 0; i < 100000; i++) {
            if (new Random().nextInt(10 - 1 + 1) + 1 > 3) {
                one++;
            } else {
                nul++;
            }
        }
        System.out.println("Null: " + nul);
        System.out.println("One: " + one);
        System.out.println("Two: " + two);
        System.out.println("Three: " + three);
        System.out.println("Four: " + four);

    }

}
