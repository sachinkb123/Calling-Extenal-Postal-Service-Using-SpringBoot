# spring-boot-calling-external-postal-service

This Spring Boot application fetches post office details from an external API based on city names and stores them in a MySQL database.

## Features

- Fetches post office details using an external API (Postal Pincode API)
- Saves fetched details into a MySQL database using Spring Data JPA
- Error handling and logging using SLF4J and Logback
- Integration with Gson for JSON parsing
- Uses RestTemplate for making HTTP requests
