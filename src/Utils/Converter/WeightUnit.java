package Utils.Converter;

public enum WeightUnit implements IBaseUnit {

    POUND(16, "lb"),
    KILOGRAM(35.274, "kg"),
    GRAM(0.0353, "g"),
    OUNCE(1, "oz");

    private final double conversionFactor; // conversion to ounces

    private final String abbreviation;

    WeightUnit(double conversionFactor, String abbreviation) {
        this.conversionFactor = conversionFactor;
        this.abbreviation = abbreviation;
    }

    public static double convertWeightToOz(WeightUnit unit, double quantity) {
        return Math.floor((quantity * unit.conversionFactor) * 100) / 100;
    }

    public static double convertWeightToDestinationUnit(WeightUnit current, WeightUnit dest, double currentQuantity) {
        double ounceSize = convertWeightToOz(current, currentQuantity);
        return Math.floor((ounceSize / dest.conversionFactor) * 100) / 100;
    }

    public static boolean isWeightLarger(WeightUnit first, WeightUnit second) {
        if (first.conversionFactor > second.conversionFactor) {
            return true;
        }
        return false;
    }


    @Override
    public String getUnitString() {
        return abbreviation;
    }
}
