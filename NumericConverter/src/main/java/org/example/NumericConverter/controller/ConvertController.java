package org.example.NumericConverter.controller;
import org.example.NumericConverter.model.RequestForConversion;
import org.example.NumericConverter.service.Converter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class ConvertController {
    @GetMapping ("/convert")
    public String convert (@RequestBody RequestForConversion request) {
        return Converter.convertGeneral(request.getInputBase(), request.getOutputBase(), request.getInputNumber());
    }
}
