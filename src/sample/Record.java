package sample;

public class Record {

    private final String Name;
    private final double Value;

    public Record(String name, double value) {
        this.Name = name;
        this.Value = value;
    }

    public String getName() {
        return Name;
    }

    public double getValue() {
        return Value;
    }
}

