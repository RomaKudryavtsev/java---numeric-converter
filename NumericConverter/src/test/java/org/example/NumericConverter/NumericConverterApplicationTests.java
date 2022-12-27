package org.example.NumericConverter;

import org.example.NumericConverter.controller.ConvertController;
import org.example.NumericConverter.model.RequestForConversion;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class NumericConverterApplicationTests {
	private static ConvertController controller;
	@BeforeAll
	public static void beforeAll() {
		controller = new ConvertController();
	}

	@Test
	public void inputBase2OutputBase16Success() {
		RequestForConversion request = RequestForConversion.builder()
				.inputBase(2)
				.outputBase(16)
				.inputNumber("11111010101")
				.build();
		ResponseEntity<String> response = controller.convert(request);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(response.getBody(), "7D5");
	}

	@Test
	public void inputBase10OutputBase12Success() {
		RequestForConversion request = RequestForConversion.builder()
				.inputBase(10)
				.outputBase(12)
				.inputNumber("1121212121212")
				.build();
		ResponseEntity<String> response = controller.convert(request);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(response.getBody(), "16136B807224");
	}

	@Test
	public void inputBase16OutputBase10Success() {
		RequestForConversion request = RequestForConversion.builder()
				.inputBase(16)
				.outputBase(10)
				.inputNumber("7D5")
				.build();
		ResponseEntity<String> response = controller.convert(request);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(response.getBody(), "2005");
	}

	@Test
	public void invalidInputBaseFail() {
		RequestForConversion request = RequestForConversion.builder()
				.inputBase(1)
				.outputBase(10)
				.inputNumber("0")
				.build();
		ResponseEntity<String> response = controller.convert(request);
		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
		assertEquals(response.getBody(), "Input base has to be in the range between 2 and 16");
	}

	@Test
	public void invalidOutputBaseFail() {
		RequestForConversion request = RequestForConversion.builder()
				.inputBase(10)
				.outputBase(17)
				.inputNumber("404")
				.build();
		ResponseEntity<String> response = controller.convert(request);
		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
		assertEquals(response.getBody(), "Output base has to be in range between 2 and 16");
	}

	@Test
	public void invalidInputNumberFail() {
		RequestForConversion request = RequestForConversion.builder()
				.inputBase(2)
				.outputBase(16)
				.inputNumber("1012")
				.build();
		ResponseEntity<String> response = controller.convert(request);
		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
		assertEquals(response.getBody(), "Input number does not correspond input base");
	}
}
