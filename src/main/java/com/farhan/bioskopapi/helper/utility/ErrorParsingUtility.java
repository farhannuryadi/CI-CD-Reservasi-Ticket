package com.farhan.bioskopapi.helper.utility;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

public class ErrorParsingUtility {

    private ErrorParsingUtility() {
        throw new IllegalStateException("Utility class");
    }

    public static List<String> parse(Errors errors){
        List<String> messages = new ArrayList<>();
        for (ObjectError error: errors.getAllErrors()) {
            messages.add(error.getDefaultMessage());
        }
        return messages;
    }
}
