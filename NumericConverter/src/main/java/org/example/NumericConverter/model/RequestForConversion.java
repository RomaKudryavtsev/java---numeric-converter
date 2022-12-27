package org.example.NumericConverter.model;

import lombok.Data;

@Data
public class RequestForConversion {
    int inputBase;
    int outputBase;
    String inputNumber;
}
