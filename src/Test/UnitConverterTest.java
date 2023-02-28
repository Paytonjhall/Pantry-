package Test;

import Utils.Converter.IBaseUnit;
import Utils.Converter.UnitConverter;
import Utils.Converter.VolumeUnit;
import Utils.Converter.WholeUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UnitConverterTest {

    VolumeUnit testUnit;
    int quantity;

    @Test
    void convert() {
        testUnit = VolumeUnit.CUP;
        quantity = 1;
        double ounces = UnitConverter.convert(testUnit, quantity);
        Assertions.assertEquals(8.0, ounces);

        testUnit = VolumeUnit.GALLON;
        quantity = 3;
        ounces = UnitConverter.convert(testUnit, quantity);
        Assertions.assertEquals(384.0, ounces);

        testUnit = VolumeUnit.LITER;
        quantity = 4;
        ounces = UnitConverter.convert(testUnit, quantity);
        Assertions.assertEquals(135.25, ounces);

        testUnit = VolumeUnit.QUART;
        quantity = 20;
        ounces = UnitConverter.convert(testUnit, quantity);
        Assertions.assertEquals(640.0, ounces);

        testUnit = VolumeUnit.PINT;
        quantity = 2;
        ounces = UnitConverter.convert(testUnit, quantity);
        Assertions.assertEquals(32.0, ounces);

        testUnit = VolumeUnit.FLUID_OUNCE;
        quantity = 22;
        ounces = UnitConverter.convert(testUnit, quantity);
        Assertions.assertEquals(22.0, ounces);

        testUnit = VolumeUnit.TABLESPOON;
        quantity = 41;
        ounces = UnitConverter.convert(testUnit, quantity);
        Assertions.assertEquals(20.5, ounces);

        testUnit = VolumeUnit.TEASPOON;
        quantity = 98;
        ounces = UnitConverter.convert(testUnit, quantity);
        Assertions.assertEquals(16.33, ounces);

        testUnit = VolumeUnit.MILLILITER;
        quantity = 378;
        ounces = UnitConverter.convert(testUnit, quantity);
        Assertions.assertEquals(12.78, ounces);
    }

    @Test
    void convertToDestinationUnit() {
        VolumeUnit currentUnit = VolumeUnit.CUP;
        VolumeUnit destUnit = VolumeUnit.GALLON;
        quantity = 20;
        double newQuantity = VolumeUnit.convertToDestinationUnit(currentUnit, destUnit, quantity);
        Assertions.assertEquals(1.25, newQuantity);

        currentUnit = VolumeUnit.FLUID_OUNCE;
        destUnit = VolumeUnit.LITER;
        quantity = 33;
        newQuantity = VolumeUnit.convertToDestinationUnit(currentUnit, destUnit,  quantity);
        Assertions.assertEquals(0.97, newQuantity);
    }

    @Test
    void stringToUnit() {
        String baseUnit;
        baseUnit = "gal";
        testUnit = (VolumeUnit) UnitConverter.stringToUnit(baseUnit);
        Assertions.assertEquals(VolumeUnit.GALLON, testUnit);

        baseUnit = "ounces";
        testUnit = (VolumeUnit) UnitConverter.stringToUnit(baseUnit);
        Assertions.assertEquals(VolumeUnit.FLUID_OUNCE, testUnit);

        baseUnit = "bogusVolume";
        IBaseUnit testBaseUnit = UnitConverter.stringToUnit(baseUnit);
        Assertions.assertEquals(WholeUnit.UNKNOWN, testBaseUnit);
    }
}