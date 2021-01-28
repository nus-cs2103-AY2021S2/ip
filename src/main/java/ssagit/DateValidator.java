package ssagit;

/*
DateTimeFormatter dateFormatter = DateTimeFormatter.BASIC_ISO_DATE;
DateValidator validator = new DateValidatorUsingLocalDate(dateFormatter);
assertTrue(validator.isValid("20190228"));
assertFalse(validator.isValid("20190230"));
*/

public interface DateValidator {
    boolean isValid(String dateStr);
}
