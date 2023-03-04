package Utils.Converter;

public class UnitConverter {

    public static double convert(IBaseUnit unit, double quantity) {
        if (unit instanceof VolumeUnit) {
            return VolumeUnit.convertToFlOz((VolumeUnit) unit, quantity);
        } else if (unit instanceof WeightUnit) {
            return WeightUnit.convertWeightToOz((WeightUnit) unit, quantity);
        } else {
            return quantity;
        }
    }


    public static IBaseUnit stringToUnit(String unit) {
        if (unit == null|| unit.length() == 0) {
            return WholeUnit.UNKNOWN;
        }

        String currentUnit = unit.toLowerCase();
        int stringEnd = currentUnit.length() - 1;
        if (currentUnit.charAt(stringEnd) == 's') {
            currentUnit = currentUnit.substring(0, stringEnd);
        }

        IBaseUnit type = null;

        // most units can have multiple abbreviations, so this tests
        // for the most common ones
        switch (currentUnit) {
            case "gallon":
            case "gal":
                type = VolumeUnit.GALLON;
                break;
            case "liter":
            case "l":
                type = VolumeUnit.LITER;
                break;
            case "quart":
            case "qt":
                type = VolumeUnit.QUART;
                break;
            case "pint":
            case "pt":
                type = VolumeUnit.PINT;
                break;
            case "cup":
            case "c":
                type = VolumeUnit.CUP;
                break;
            case "tablespoon":
            case "tbsp":
            case "tb":
            case "tbs":
                type = VolumeUnit.TABLESPOON;
                break;
            case "teaspoon":
            case "tsp":
                type = VolumeUnit.TEASPOON;
                break;
            case "milliliter":
            case "ml":
                type = VolumeUnit.MILLILITER;
                break;
            case "fluid ounce":
            case "fl. oz.":
            case "fl oz":
                type = VolumeUnit.FLUID_OUNCE;
                break;
            case "single":
            case "whole":
                type = WholeUnit.WHOLE_UNIT;
                break;
            case "lb":
            case "lbs":
            case "lb.":
            case "lbs.":
            case "pound":
                type = WeightUnit.POUND;
                break;
            case "oz":
            case "oz.":
            case "ounce":
                type = WeightUnit.OUNCE;
                break;
            case "kilogram":
            case "kg":
            case "kilo":
                type = WeightUnit.KILOGRAM;
                break;
            case "g":
            case "gram":
            case "gm":
                type = WeightUnit.GRAM;
                break;
            default:
                type = WholeUnit.UNKNOWN;
                break;

        }

        return type;

    }

}