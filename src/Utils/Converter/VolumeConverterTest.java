package Utils.Converter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VolumeConverterTest {

    VolumeUnit testUnit;
    int quantity;

    @Test
    void convert() {
        testUnit = VolumeUnit.CUP;
        quantity = 1;
        double ounces = VolumeConverter.convert(testUnit, quantity);
        Assertions.assertEquals(8.0, ounces);

        testUnit = VolumeUnit.GALLON;
        quantity = 3;
        ounces = VolumeConverter.convert(testUnit, quantity);
        Assertions.assertEquals(384.0, ounces);

        testUnit = VolumeUnit.LITER;
        quantity = 4;
        ounces = VolumeConverter.convert(testUnit, quantity);
        Assertions.assertEquals(135.25, ounces);

        testUnit = VolumeUnit.QUART;
        quantity = 20;
        ounces = VolumeConverter.convert(testUnit, quantity);
        Assertions.assertEquals(640.0, ounces);

        testUnit = VolumeUnit.PINT;
        quantity = 2;
        ounces = VolumeConverter.convert(testUnit, quantity);
        Assertions.assertEquals(32.0, ounces);

        testUnit = VolumeUnit.FLUID_OUNCE;
        quantity = 22;
        ounces = VolumeConverter.convert(testUnit, quantity);
        Assertions.assertEquals(22.0, ounces);

        testUnit = VolumeUnit.TABLESPOON;
        quantity = 41;
        ounces = VolumeConverter.convert(testUnit, quantity);
        Assertions.assertEquals(20.5, ounces);

        testUnit = VolumeUnit.TEASPOON;
        quantity = 98;
        ounces = VolumeConverter.convert(testUnit, quantity);
        Assertions.assertEquals(16.33, ounces);

        testUnit = VolumeUnit.MILLILITER;
        quantity = 378;
        ounces = VolumeConverter.convert(testUnit, quantity);
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
        newQuantity = VolumeUnit.convertToDestinationUnit(currentUnit, destUnit, quantity);
        Assertions.assertEquals(0.97, newQuantity);
    }

    @Test
    void stringToUnit() {
        String baseUnit;
        baseUnit = "gal";
        testUnit = VolumeConverter.stringToUnit(baseUnit);
        Assertions.assertEquals(VolumeUnit.GALLON, testUnit);

        baseUnit = "ounces";
        testUnit = VolumeConverter.stringToUnit(baseUnit);
        Assertions.assertEquals(VolumeUnit.FLUID_OUNCE, testUnit);

        baseUnit = "bogusVolume";
        testUnit = VolumeConverter.stringToUnit(baseUnit);
        Assertions.assertEquals(VolumeUnit.UNKNOWN, testUnit);
    }
}