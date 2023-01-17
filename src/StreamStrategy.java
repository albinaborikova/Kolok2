import java.util.ArrayList;
public class StreamStrategy implements Strategy{
    @Override
    public ArrayList<Toy> getSortedArray(ArrayList<Toy> original, int lowerBound, int upperBound) {
        ArrayList<Toy> sorted = new ArrayList<>();
        original.stream().filter((toy) -> toy.getLowerBound() <= lowerBound && toy.getUpperBound() >= upperBound).sorted(Toy::compareTo).forEach(sorted::add);
        return sorted;
    }
}