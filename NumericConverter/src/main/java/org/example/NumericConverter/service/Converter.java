package org.example.NumericConverter.service;

import org.example.NumericConverter.exception.InvalidBaseExeption;
import org.example.NumericConverter.exception.InvalidInputNumberException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Converter {
    private final static int MIN_BASE = 2;
    private final static int MAX_BASE = 16;

    public static String convertGeneral(int inputBase, int outputBase, String inputNumber)
    throws InvalidBaseExeption {
        if(inputBase < MIN_BASE || inputBase > MAX_BASE) {
            throw new InvalidBaseExeption("Input base has to be in the range between 2 and 16");
        }
        if(outputBase < MIN_BASE || outputBase > MAX_BASE) {
            throw new InvalidBaseExeption("Output base has to be in range between 2 and 16");
        }
        if(!checkInputNumber(inputBase, inputNumber)) {
            throw new InvalidInputNumberException("Input number does not correspond input base");
        }
        long decimalNumber = convertToDecimal(inputBase, inputNumber);
        return iterativeConvertFromDecimal(outputBase, decimalNumber);
    }

    private static boolean checkInputNumber(int inputBase, String inputNumber) {
        for (int i = 0; i < inputNumber.length(); ++i) {
            if (Character.digit(inputNumber.charAt(i), inputBase) == -1) {
                return false;
            }
        }
        return true;
    }

    private static String iterativeConvertFromDecimal(int outputBase, long inputNumber) {
        StringBuilder result = new StringBuilder();
        while (inputNumber/outputBase != 0) {
            long remainder = inputNumber % outputBase;
            if(remainder >= 10) {
                result.append(numberToSymbol((int) remainder));
            } else {
                result.append(remainder);
            }
            inputNumber /= outputBase;
        }
        if(inputNumber / outputBase == 0) {
            long remainder = inputNumber % outputBase;
            if(remainder >= 10) {
                result.append(numberToSymbol((int) remainder));
            } else {
                result.append(remainder);
            }
        }
        return new StringBuilder(result.toString()).reverse().toString();
    }

    private static long convertToDecimal(int inputBase, String inputNumber) {
        StringBuilder input = new StringBuilder(inputNumber).reverse();
        long output = 0;
        for(int i = 0; i < input.length(); ++i) {
            Character currentSymbol = input.charAt(i);
            int digit;
            if(isSymbol(currentSymbol)) {
                digit = symbolToNumber(currentSymbol);
            } else {
                digit = Character.getNumericValue(currentSymbol);
            }
            output += digit * Math.pow(inputBase, i);
        }
        return output;
    }

    private static boolean isSymbol(Character c) {
        List<Character> symbols = List.of('A', 'B', 'C', 'D', 'E', 'F');
        return symbols.contains(c);
    }

    private static int symbolToNumber(Character symbol) {
        switch(symbol) {
            case 'A':
                return 10;
            case 'B':
                return 11;
            case 'C':
                return 12;
            case 'D':
                return 13;
            case 'E':
                return 14;
            case 'F':
                return 15;
        }
        return 0;
    }

    private static String numberToSymbol(int digit) {
        switch(digit) {
            case 10:
                return "A";
            case 11:
                return "B";
            case 12:
                return "C";
            case 13:
                return "D";
            case 14:
                return "E";
            case 15:
                return "F";
        }
        return null;
    }
}
