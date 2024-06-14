# StudentRegistryBackend

SpringBoot backend for StudentRegistry

## Prerequisites

- Java 17 or higher
- Maven or Gradle

## Getting Started

### Installation

1. Clone the repository
`git clone git@github.com:Lisalin5813010/StudentRegistryBackend.git`
2. Navigate to the project directory 
3. Build the project
`mvn clean install`
### Running the Application

1. Run the application</br>
`mvn spring-boot:run`</br>
The application will start running at `http://localhost:8080`.

## API Documentation

### Available Endpoints
* Get all items
`GET /api/students`
* Get item by ID
`GET /api/students/{id}`
* Add a new item
`POST /api/student`
* Update an item
`PUT /api/students/{id}`
* Request body:
`{
  "name": "Doe John",
  "age": "12",
  "grade": "A
  }  `
* Delete an item
`DELETE /api/students/{id}`

## Contributing

If you'd like to contribute to this project, please follow these steps:

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Make your changes and commit them with descriptive commit messages.
4. Push your changes to your forked repository.
5. Create a pull request in the main repository.
