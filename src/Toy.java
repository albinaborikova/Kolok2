public class Toy implements Comparable<Toy>{

    public String getName() {
        return name;
    }

    private final String name;
    private final int price;
    private final int lowerBound;
    private final int upperBound;

    public Toy(){
        this.name = null;
        this.price = 0;
        this.lowerBound = 0;
        this.upperBound = 99;
    }

    public Toy(String name, int price, int lowerBound, int upperBound) throws IllegalArgumentException {
        if (!rightBound(lowerBound, upperBound))
            throw new IllegalArgumentException("Upper bound is less than lower bound");
        this.name = name;
        if (price >= 0)
            this.price = price;
        else throw new IllegalArgumentException("Negative price");
        if (lowerBound >= 0)
            this.lowerBound = lowerBound;
        else throw new IllegalArgumentException("Negative lower bound");
        if (upperBound >= 0)
            this.upperBound = upperBound;
        else throw new IllegalArgumentException("Negative upper bound");
    }

    public int getLowerBound() {
        return lowerBound;
    }

    public int getUpperBound() {
        return upperBound;
    }

    public static boolean rightBound(int first, int second){
        return first <= second;
    }

    @Override
    public String toString() {
        return "name = '" + name + '\'' +
                ", price = " + price +
                ", ageFrom = " + lowerBound +
                ", ageTo = " + upperBound;
    }

    @Override
    public int compareTo(Toy o) {
        return price - o.price;
    }
}
