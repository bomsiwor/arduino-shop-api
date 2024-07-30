package com.arduino.shop_api.util;

import com.arduino.shop_api.exception.ErrorMessage;
import com.arduino.shop_api.exception.ValidationException;

import java.util.ArrayList;
import java.util.List;

public class ValidationUtil {

    private List<String> errors = new ArrayList<>();

    /**
     * Validate if a field is required and present
     * @param data Field data
     * @param fieldName Field name
     * @return Valid flag
     * @param <Field> Field type
     */
    public <Field> Boolean required(Field data, String fieldName) {
        Boolean isPresent = null != data;

        if (!isPresent) {
            errors.add(String.format(ErrorMessage.FIELD_REQUIRED, fieldName));
        }

        return isPresent;
    }

    /**
     * Validate if a string has minimum length
     * @param data Field data
     * @param fieldName Field name
     * @param number Minimum length
     * @return Valid flag
     */
    public Boolean min(String data, int number, String fieldName) {
        Boolean isValid = (null != data) && (data.length() >= number);

        if (!isValid) {
            errors.add(String.format(ErrorMessage.FIELD_MIN, fieldName, number));
        }

        return isValid;
    }

    /**
     * Validate if a number is above offset
     * @param data Field data
     * @param fieldName Field name
     * @param number Minimum length
     * @return Valid flag
     */
    public Boolean min(Integer data, int number, String fieldName) {
        Boolean isValid = (null != data) && (data >= number);

        if (!isValid) {
            errors.add(String.format(ErrorMessage.FIELD_MIN,fieldName, number));
        }

        return isValid;
    }

    /**
     * Validate if a number is below offset
     * @param data Field data
     * @param fieldName Field name
     * @param number maximum length
     * @return Valid flag
     */
    public Boolean max(Integer data, int number, String fieldName) {
        Boolean isValid = (null != data) && (data <= number);

        if (!isValid) {
            errors.add(String.format(ErrorMessage.FIELD_MAX,fieldName, number));
        }

        return isValid;
    }

    /**
     * Validating All field. Throw exception if there is an error
     */
    public void validate() {
        if (!errors.isEmpty()) {
            throw new ValidationException(errors.get(0), errors);
        }
    }
}
