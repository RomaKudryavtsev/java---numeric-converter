package org.example.NumericConverter.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestForConversion {
    int inputBase;
    int outputBase;
    String inputNumber;
}
