public enum Manufacturer {
    HONDA("Honda"), YAMAHA("Yamaha"), TOYOTA("Toyota"), SUZUKI("Suzuki");

    private final String displayName;

    Manufacturer(String displayName) { this.displayName = displayName; }

    @Override public String toString() { return displayName; }
}
