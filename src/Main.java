import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
        ToyPrize toyPrize = new ToyPrize();

        toyPrize.addToy(new Toy(1, "Кукла", 5, 30));
        toyPrize.addToy(new Toy(2, "Мяч", 10, 20));
        toyPrize.addToy(new Toy(3, "Машинка", 8, 15));

        toyPrize.updateWeight(1, 40);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Toy prizeToy = toyPrize.selectPrizeToy();
                if (prizeToy != null) {
                    toyPrize.savePrizeToyToFile(prizeToy);
                }

                toyPrize.toysCount++;
                if (toyPrize.toysCount >= 10) {
                    timer.cancel();
                }
            }
        }, 0, 1000);
    }
}