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
            return VolumeConverter.convert((VolumeUnit) unit, unitSize);
        }
        // TODO:
        // We can add weight conversion here too so the ingredient doesn't need
        // to track if it is a weight unit or volume unit

        return -1;
    }

    static IBaseUnit stringToUnit(String unit) {
        IBaseUnit baseUnit = VolumeConverter.stringToUnit(unit);
        return baseUnit;

        // TODO: update weight conversions here too so it can convert from string to unit
    }

    String getUnitString();
}
