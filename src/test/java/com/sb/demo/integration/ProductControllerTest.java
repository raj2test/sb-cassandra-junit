package com.sb.demo.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

import org.cassandraunit.spring.CassandraDataSet;
import org.cassandraunit.spring.CassandraUnitDependencyInjectionTestExecutionListener;
import org.cassandraunit.spring.EmbeddedCassandra;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sb.demo.entity.Product;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@EmbeddedCassandra(timeout = 10000)
@CassandraDataSet(value = { "test_data.cql" })
@TestExecutionListeners( {   CassandraUnitDependencyInjectionTestExecutionListener.class,DependencyInjectionTestExecutionListener.class })
class ProductControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@DisplayName("Integration testing GET /product api")
	void getProducts() throws Exception {
		
		MockHttpServletResponse response = mockMvc.perform(get ("/product")).andReturn().getResponse();
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.findAndRegisterModules();
		
		List<Product> products = objectMapper.readValue(response.getContentAsString(), new TypeReference<List<Product>>() {});
		
		assertAll("response",
				() -> assertEquals(HttpStatus.OK.value(), response.getStatus()),
				() -> assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType()),
				() -> assertEquals("prd1", products.get(0).getProductId()),
				() -> assertEquals("Test Product 1", products.get(0).getProductName()),
				() -> assertEquals("pd-1", products.get(0).getCode()),
				() -> assertEquals(new BigDecimal("250.99"), products.get(0).getPrice()),
				() -> assertEquals("prd2", products.get(1).getProductId()),
				() -> assertEquals("Test Product 2", products.get(1).getProductName()),
				() -> assertEquals("pd-2", products.get(1).getCode()),
				() -> assertEquals(new BigDecimal("300.99"), products.get(1).getPrice()));
		
	}

}
