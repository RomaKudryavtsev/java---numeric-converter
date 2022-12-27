package org.example.NumericConverter.controller;
import lombok.extern.slf4j.Slf4j;
import org.example.NumericConverter.exception.InvalidBaseExeption;
import org.example.NumericConverter.exception.InvalidInputNumberException;
import org.example.NumericConverter.model.RequestForConversion;
import org.example.NumericConverter.service.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping
@Slf4j
public class ConvertController {
    @GetMapping ("/convert")
    public ResponseEntity<String> convert (@RequestBody RequestForConversion request) {
        try {
            return new ResponseEntity<>(Converter.convertGeneral(request.getInputBase(), request.getOutputBase(),
                    request.getInputNumber()), HttpStatus.OK);
        } catch (InvalidBaseExeption e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (InvalidInputNumberException e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
