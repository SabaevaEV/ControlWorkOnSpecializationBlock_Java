import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
        ToyStore toyStore = new ToyStore();

        toyStore.addToy(new Toy(1, "Кукла", 5, 30));
        toyStore.addToy(new Toy(2, "Мяч", 10, 20));
        toyStore.addToy(new Toy(3, "Машинка", 8, 15));

        toyStore.updateWeight(1, 40);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Toy prizeToy = toyStore.selectPrizeToy();
                if (prizeToy != null) {
                    toyStore.savePrizeToyToFile(prizeToy);
                }

                toyStore.toysCount++;
                if (toyStore.toysCount >= 10) {
                    timer.cancel();
                }
            }
        }, 0, 1000);
    }
}