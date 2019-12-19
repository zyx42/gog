# Guns of Grandeur
This is an application that will help you to get the Big'uns you deserve!

The application itself follow the Microservice Architecture Pattern and consists
of 9 modules, three functional services, five infrastructure services and a database.

## Functional Services:

#### Account Service
Sticking to its name, it contains user input logic and validation.

#### Statistics Service
Contains logic for calculating statistics parameters. Data is used to track
the overall progress of the user over time.

#### Notification Service
Stores user contact information and notification settings. Also sends
e-mail messages to the users.

## Infrastructure Services:

#### Config Service
Spring Cloud Config is a centralized configuration service for distributed systems.

#### Auth Service
Used for user authorization as well as for secure machine-to-machine communication
by granting OAuth2 tokens for resource services.

#### Gateway
A single entry point into the system, used to handle requests by routing them to the 
appropriate backend service or by aggregating the results from multiples backend services.
It's implemented with Netflix Zuul server.

#### Registry
The key part of Service discovery pattern that allows automatic detection of network
locations for service instances. It uses Netflix Eureka.

#### Monitoring
This is just a small Spring Boot application with Hystrix Dashboard.

## Other important parts:

#### Load balancer, Circuit breaker and Http Client
Load balancer is implemented with Netflix Ribbon, circuit breaker with Netflix Hystrix
and http client with Netflix Feign.

#### Database
A PostgreSQL to back up the whole system.