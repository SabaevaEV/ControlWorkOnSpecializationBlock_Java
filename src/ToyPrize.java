import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ToyPrize {
    private List<Toy> toys;
    int toysCount = 0;

    public ToyPrize() {
        toys = new ArrayList<>();
        toysCount = 0;
    }

    public void addToy(Toy toy) {
        toys.add(toy);
    }

    public void updateWeight(int toyId, double newWeight) {
        for (Toy toy : toys) {
            if (toy.getId() == toyId) {
                toy.setWeight(newWeight);
                break;
            }
        }
    }

    public Toy selectPrizeToy() {
        double totalWeight = 0.0;
        for (Toy toy : toys) {
            totalWeight += toy.getWeight();
        }

        Random random = new Random();
        double randomNumber = random.nextDouble() * totalWeight;

        double cumulativeWeight = 0.0;
        for (Toy toy : toys) {
            cumulativeWeight += toy.getWeight();
            if (randomNumber <= cumulativeWeight) {
                Toy prizeToy = new Toy(toy.getId(), toy.getName(), 1, toy.getWeight());
                toy.setQuantity(toy.getQuantity() - 1);
                return prizeToy;
            }
        }

        return null;
    }

    public void savePrizeToyToFile(Toy prizeToy) {
        try (FileWriter writer = new FileWriter("prize.txt", true)) {
            writer.write("Призовая игрушка: " + prizeToy.getName() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
