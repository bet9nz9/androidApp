package com.example.kursach.validator;

import com.example.kursach.exception.Messages;
import com.example.kursach.exception.ValidationException;
import com.example.kursach.model.VacancyDataModel;
import com.example.kursach.model.VacancyFields;

import java.math.BigDecimal;

public class VacancyValidator {

    public static boolean isValidVacancyToCreate(VacancyDataModel vacancy) throws ValidationException {

        validatePrice(vacancy.getCoast());
        validateFullDesc(vacancy.getFullDesc());
        validateShortDesc(vacancy.getShortDesc());
        validateVacancyEmployee(vacancy.getEmployeeId());

        return true;
    }

    public static void validateCoast(String coast) throws ValidationException {
        try {
            BigDecimal price = new BigDecimal(coast);
        } catch (RuntimeException exception) {
            throw new ValidationException(Messages.COAST_IS_NOT_NUMERIC);
        }
    }

    private static void validatePrice(BigDecimal price) {
        if (price == null) {
            throw new ValidationException(String.format(Messages.FIElD_IS_EMPTY, VacancyFields.COAST_FIELD));
        }
        if (price.equals(BigDecimal.ZERO)) {
            throw new ValidationException(Messages.COAST_EQUALS_ZERO);
        }
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidationException(Messages.COAST_LESS_THAN_ZERO);
        }
        if (price.compareTo(BigDecimal.valueOf(5000)) > 0) {
            throw new ValidationException(Messages.COAST_MORE_THAN_5000);
        }
    }

    private static void validateShortDesc(String shortDesc) {
        if (shortDesc == null || shortDesc.equals("")) {
            throw new ValidationException(String.format(Messages.FIElD_IS_EMPTY, VacancyFields.SHORT_DESC_FIELD));
        }
    }

    private static void validateFullDesc(String fullDesc) {
        if (fullDesc == null || fullDesc.equals("")) {
            throw new ValidationException(String.format(Messages.FIElD_IS_EMPTY, VacancyFields.FULL_DESC_FIELD));
        }
    }

    private static void validateVacancyEmployee(Long employeeId) {
        if (employeeId == null) {
            throw new ValidationException(Messages.YOU_ARE_NOT_LOGGINED);
        }
    }

}
