### Building

You can build project with standard mvn clean install command

### Installation

Put output war on some application server (I used default wildfly 12 for development)

### API

root path should be just: http://localhost:8080/rest

#### Posting

POST http://localhost:8080/rest/post/{username}?message=""

#### Wall

GET http://localhost:8080/rest/wall/{username}?start=0&length=10

#### Following

POST http://localhost:8080/rest/{username}/follow/{user-to-follow}

#### Timeline

GET http://localhost:8080/rest/timeline/{username}?start=0&length=10