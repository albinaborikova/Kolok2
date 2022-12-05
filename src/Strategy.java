import java.util.ArrayList;

public interface Strategy {
    ArrayList<Toy> getSortedArray(ArrayList<Toy> original, int lowerBound, int upperBound);
}