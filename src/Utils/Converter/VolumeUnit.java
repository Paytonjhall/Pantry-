package Utils.Converter;

public enum VolumeUnit {
    GALLON (128.0),
    LITER (33.814),
    QUART (32.0),
    PINT (16.0),
    CUP (8.0),
    FLUID_OUNCE (1),
    TABLESPOON (0.5),
    TEASPOON (0.16667),
    MILLILITER (0.033814);

    private final double conversionFactor; // conversion to fl. oz.

    VolumeUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public static double convertToFlOz(VolumeUnit unit, int quantity) {
        return quantity * unit.conversionFactor;
    }
}
