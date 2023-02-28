package Utils.Converter;

public enum WholeUnit implements IBaseUnit {

    WHOLE_UNIT("whole", ""),
    UNKNOWN("unknown", "");

    private final String name;
    private final String abbreviation;

    WholeUnit(String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }

    @Override
    public String getUnitString() {
        return abbreviation;
    }

    public String getName() {
        return name;
    }
}
