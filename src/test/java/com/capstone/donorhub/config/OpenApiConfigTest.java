package com.capstone.donorhub.config;

//Testing presence and properties on annotations

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpenApiConfigTest {

	@Test
	void testOpenAPIDefinitionAnnotation() {
		OpenAPIDefinition openAPIDefinition = OpenApiConfig.class.getAnnotation(OpenAPIDefinition.class);

		assertNotNull(openAPIDefinition);
		SecurityRequirement[] securityRequirements = openAPIDefinition.security();
		assertEquals(1, securityRequirements.length);
		SecurityRequirement securityRequirement = securityRequirements[0];
		assertTrue(securityRequirement.name().equalsIgnoreCase("bearerAuth"));
	}

	@Test
	void testSecuritySchemeAnnotation() {
		SecurityScheme securityScheme = OpenApiConfig.class.getAnnotation(SecurityScheme.class);

		assertNotNull(securityScheme);
		assertEquals("bearerAuth", securityScheme.name());
		assertEquals("JWT Auth Description", securityScheme.description());
		assertEquals(SecuritySchemeType.HTTP, securityScheme.type());
		assertEquals(SecuritySchemeIn.HEADER, securityScheme.in());
		assertEquals("bearer", securityScheme.scheme());
		assertEquals("JWT", securityScheme.bearerFormat());
	}
}
