package Utils.Converter;

public interface IBaseUnit {
    /**
     * Converts from weight or volume units to a standardized unit for each
     * For volume, converts to ounces
     * @param unit the unit enum
     * @param unitSize size of the unit (ex: 3 gallons)
     * @return the normalized unit quantity
     */
    static double convertUnits(IBaseUnit unit, double unitSize) {
        if (unit instanceof VolumeUnit) {
            return VolumeUnit.convertToFlOz((VolumeUnit) unit, unitSize);
        } else if (unit instanceof WholeUnit) {
            return unitSize;
        } else if (unit instanceof WeightUnit) {
            return WeightUnit.convertWeightToOz((WeightUnit) unit, unitSize);
        }

        return -1;
    }

    String getUnitString();
}
