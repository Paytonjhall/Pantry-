package Utils.Converter;

public enum VolumeUnit implements IBaseUnit {
    GALLON (128.0, "gal"),
    LITER (33.814, "l"),
    QUART (32.0, "qt"),
    PINT (16.0, "pt"),
    CUP (8.0, "c"),
    FLUID_OUNCE (1, "fl oz"),
    TABLESPOON (0.5, "tbsp"),
    TEASPOON (0.16667, "tsp"),
    MILLILITER (0.033814, "ml"),
    UNKNOWN(-1, "unknown");

    private final double conversionFactor; // conversion to fl. oz.
    private final String abbreviation;

    VolumeUnit(double conversionFactor, String abbreviation) {
        this.conversionFactor = conversionFactor;
        this.abbreviation = abbreviation;
    }

    public static double convertToFlOz(VolumeUnit unit, double quantity) {
        return Math.floor((quantity * unit.conversionFactor) * 100) / 100;
    }

    @Override
    public String getUnitString() {
        return abbreviation;
    }

}
