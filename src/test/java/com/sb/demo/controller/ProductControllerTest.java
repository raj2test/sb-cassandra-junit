package com.sb.demo.controller;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sb.demo.entity.Product;
import com.sb.demo.service.ProductService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class ProductControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProductService service;
	
	List<Product> prd = null;
	
	@BeforeEach
	void setup() {
		prd= Arrays.asList(new Product("prd1", "prd1", "prd1", new BigDecimal(120.50)), new Product("prd2", "prd2", "prd2", new BigDecimal(199.99)));
	}
	
	@Test
	@DisplayName("Unit testing GET /product")
	void getProducts() throws Exception {
		
		when(service.getProducts()).thenReturn(prd);
		
		MockHttpServletResponse response = mockMvc.perform(get ("/product")).andReturn().getResponse();
		
		List<Product> products = new ObjectMapper().readValue(response.getContentAsString(), new TypeReference<List<Product>>() {});
		
		assertAll("response",
				() -> assertEquals(HttpStatus.OK.value(), response.getStatus()),
				() -> assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType()),
				() -> assertEquals(prd.get(0).getProductId(), products.get(0).getProductId()),
				() -> assertEquals(prd.get(0).getProductName(), products.get(0).getProductName()),
				() -> assertEquals(prd.get(0).getCode(), products.get(0).getCode()),
				() -> assertEquals(prd.get(0).getPrice(), products.get(0).getPrice()),
				() -> assertEquals(prd.get(1).getProductId(), products.get(1).getProductId()),
				() -> assertEquals(prd.get(1).getProductName(), products.get(1).getProductName()),
				() -> assertEquals(prd.get(1).getCode(), products.get(1).getCode()),
				() -> assertEquals(prd.get(1).getPrice(), products.get(1).getPrice()));
		
	}

}

