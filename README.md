# Atipera Recruitment Task

A simple Spring Boot application that fetches repositories for a given GitHub user using the GitHub API.

The main rest controller is located in `com.example.recruitmenttaskatipera.controller.MainController`. \
The integration test is located in `com.example.recruitmenttaskatipera.RecruitmentTaskAtiperaApplicationTests`.

## Configuration

You must specify your GitHub API token to allow the app to access the API. This can be done in one of two ways:

### 1. `application.properties` file 
```properties
app.github.api.token=YOUR_GITHUB_TOKEN
```

### 2. Environment variable
```shell
export APP_GITHUB_API_TOKEN=YOUR_GITHUB_TOKEN
```

## Running the Application
### 1. Build the application:
```shell
./gradlew clean build
```

### 2. Run the application:
```shell
./gradlew bootRun
```

### 3. Access the API:
```
GET http://localhost:8080/{username}/repos
```
Replace `{username}` with the GitHub username you want to fetch repositories for.

> **Note**\
> You can make this request using (for example) curl:
> ```shell
> curl http://localhost:8080/{username}/repos
> ```