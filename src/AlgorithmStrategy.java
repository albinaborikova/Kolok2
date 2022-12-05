import java.util.ArrayList;

public class AlgorithmStrategy implements Strategy{
    @Override
    public ArrayList<Toy> getSortedArray(ArrayList<Toy> original, int lowerBound, int upperBound) {
        ArrayList<Toy> sorted = new ArrayList<>();

        for (Toy toy : original){
            if (toy.getLowerBound() <= lowerBound && toy.getUpperBound() >= upperBound){
                sorted.add(toy);
            }
        }
        sorted.sort(Toy::compareTo);
        return sorted;
    }
}
