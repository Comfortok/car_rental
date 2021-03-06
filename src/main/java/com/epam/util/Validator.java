package com.epam.util;

import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import static com.epam.constant.ConstantField.*;

public class Validator {
    private static final Logger LOG = Logger.getLogger(Validator.class);
    private static final String EMAIL_REGEX = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{5,16}$";

    private Validator() {
    }

    public static boolean validateRegistrationInfo(String email, String password) {
        LOG.debug("validateRegistrationInfo: " + (checkPassword(password)) + "; " + (checkEmail(email)));
        return checkEmail(email) || checkPassword(password);
    }

    private static boolean checkEmail(String email) {
        LOG.debug("checkEmail" + !Pattern.matches(EMAIL_REGEX, email));
        return !Pattern.matches(EMAIL_REGEX, email);
    }

    private static boolean checkPassword(String password) {
        LOG.debug("checkPassword" + !Pattern.matches(PASSWORD_REGEX, password));
        return !Pattern.matches(PASSWORD_REGEX, password);
    }

    public static boolean checkEmptyAttributes(String...inputInfo) {
        LOG.debug("Checking input info for not being an empty.");
        boolean result = false;
        for (String field : inputInfo) {
            if (field.isEmpty()) {
                result = true;
                break;
            }
        }
        return result;
    }

    public static boolean checkImageFormat(String image) {
        LOG.debug("Checking input image type.");
        boolean result = false;
        if (image.endsWith(IMAGE_FORMAT_JPG) || image.endsWith(IMAGE_FORMAT_JPEG)
                || image.endsWith(IMAGE_FORMAT_PNG)) {
            result = true;
        }
        return result;
    }
}