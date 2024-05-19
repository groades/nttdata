# nttdata
NTT Data Exercise

## Requirements

The project makes use of Gradle and uses the [Gradle wrapper] which means you don't need Gradle installed.

## Solution Diagram

Please find the solution diagram in path /src/main/resources/static/NTT Diagram.png

### Build the project

Compiles the project, runs the test and then creates an executable JAR file

```console
$ ./gradlew build
```
### Run the application

Run the application which will be listening on port `8080`.

```console
$ ./gradlew bootRun
```

### Stop the application

Stop all daemons running

```console
$ ./gradlew -stop
```

## API

Below is the API endpoint with the input and output. Please note that the application needs to be
running for the following endpoints to work.

### User Registration

Endpoint

```text
POST /api/v1/authentication/register
```

Example of body

```json
{
  "name": "Juan Rodriguez",
  "email": "juan@rodriguez.org",
  "password": "Ha22",
  "phones": [
    {
      "number": "1234567",
      "citycode": "1",
      "countrycode": "57"
    }
  ]
}
```
Example output

```json
{
  "id": "7e79e0fa-a728-4915-83a5-bfa2c6a0fb72",
  "created": "2024-05-19T01:44:30.472584",
  "modified": "2024-05-19T01:44:30.472584",
  "last_login": "2024-05-19T01:44:30.1247962",
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqdWFuQHJvZHJpZ3Vlei5vcmciLCJpYXQiOjE3MTYwOTc0NzAsImV4cCI6MTcxNjE4Mzg3MH0.BbQurqBSU28XpOq6KtMala-2L2h0iUx9CKBQ7zkbW_g",
  "isactive": true,
  "refresh_token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqdWFuQHJvZHJpZ3Vlei5vcmciLCJpYXQiOjE3MTYwOTc0NzAsImV4cCI6MTcxNjcwMjI3MH0.7_zmm2K-_o26JfaCExPljs5Ly5bTINwrvqg7YMH4srE"
}
```
