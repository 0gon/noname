package com.uca.hrm;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class HrmApplicationTests {
	
	@Autowired
	private DataSource dataSource;

	@Test
	void contextLoads() {
	}

	@Test
	void testDatabaseConnection() {
		// Act
		try (Connection connection = dataSource.getConnection()) {
			// Assert
			assertNotNull(connection, "Database connection should not be null");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to connect to the database", e);
		}
	}
}
