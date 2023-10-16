# Flight Search Service Application

---


### The service provides an API
#### `FlightSearchAPI`
* A flight search app


### How does the application works?
* Application receives the requested via `/v1/api/flights/search` url with all optional `originAirportId`,`destinationAirportId`,`departureDate`,`returnDate` request parameters
* There is a validation for date parameters.
  * If the date parameters is not valid, api returns `400 - Http Bad Request` response
* There is a `Criteria Specification` when search with all optional search parameters.
  * If all parameters is empty, gets all Flights from db.
  * Otherwise a Criteria will be builded for given parameters.
* Existing airports are `cached` and brought from the cache when needed.
* There is scheduled jobs at midnight:
  * A request is made to another API to retrieve the new day's flight information and save it to the db.
  * All cache evicted.
* A `RateLimiter` implemented with `resilience4j`
  
On the swagger page you can find the relevant api endpoint. 
You can reach the openapi page by `http://localhost:8080/swagger-ui/index.html` url.

You can define <b>API_URL </b> in the `application.yml` file

## Technologies

---
- Java 17
- Spring Boot 3.1
- Open API Documentation
- Spring Data JPA
- H2 In Memory Database
- Restful API
- Maven
- Junit5
- Mockito
- Resilience4j
- Docker
- Docker Compose

## Prerequisites

---
- Maven or Docker
---

## Docker Run
The application can be built and run by the `Docker` engine. The `Dockerfile` has multistage build, so you do not need to build and run separately.

Please follow the below directions in order to build and run the application with Docker Compose;

```sh
$ cd flight
$ docker-compose up -d
```


#### You can reach the open-api-ui via  `http://{HOST}:{8080}/swagger-ui.html`

---
## Maven Run
To build and run the application with `Maven`, please follow the directions below;

```sh
$ cd flight
$ mvn clean install
$ mvn spring-boot:run
```
You can reach the swagger-ui via  `http://{HOST}:8080/swagger-ui.html`

---
