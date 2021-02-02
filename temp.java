import java.util.Random;

public class temp {

    public static void main(String[] args) {
        int nul = 0;
        int one = 0;
        int two = 0;
        int three = 0;
        int four = 0;
        for (int i = 0; i < 1000; i++) {
            switch (new Random().nextInt(10 - 5 + 1) + 5) {
                case 0:
                    nul++;
                    break;
                case 1:
                    one++;
                    break;
                case 2:
                    two++;
                    break;
                case 3:
                    three++;
                    break;
                case 4:
                    four++;
                    break;
            }
        }
        System.out.println("Null: " + nul);
        System.out.println("One: " + one);
        System.out.println("Two: " + two);
        System.out.println("Three: " + three);
        System.out.println("Four: " + four);

    }

}
