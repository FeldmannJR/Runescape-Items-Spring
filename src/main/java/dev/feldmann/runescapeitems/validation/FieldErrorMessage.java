package dev.feldmann.runescapeitems.validation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldErrorMessage {
    private String field;
    private String message;
}
