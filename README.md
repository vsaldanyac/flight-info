# flight-info

## How to run

### After compiling, start with docker

~~~~
docker-compose up -d --build
~~~~

### Structure

- There are three docker containers up. 

· One for redis (on the redis port) named _redis_

· One for the external service that provides the flights for the given tail number (on port 8081) named _plane-services_

· One for the service that we want to use (on port 8080) named _flight-services_

The data to use for this test, borns from an external json file, loaded as a resource of the external service.

The endpoint to get the info has the following structure

· `localhost:8080/v1/flight-information/{tailNumber}/{flight-number}`

Sample:

· `localhost:8080/v1/flight-information/EC-MYT/653`



### On Redis

- On Redis we've got a key for each airport with the next structure: `AIRPORT_{code}`

- On Redis we've got a key for each tail number and flight with the following structure: `TAIL_{tailNumber}_FLIGHT_{flightNumber}`

On every case we can see the info with redis_cli with _keys *_ to see the info saved on with the _hgetall_ command to see the content.

Sample:

`hgetall TAIL_EC-ITB_FLIGHT_2655`

- We can retrieve the info about a tail number with _keys TAIL_{tail_number}*_

Sample:

`keys TAIL_EC-ITB*`

### Security

To use the endpoint you need to provide a valid Auth token on header request on the key _X-Auth_

· To use this sample, the valid token has been stored as a property and it's *1234*

Sample:

`curl --location --request GET 'localhost:8080/v1/flight-information/EC-MYT/653' --header 'X-Auth: 1234'`
