
## Build Instructions

1.  **Prerequisites:**
    *   Java 17 or higher must be installed.
    *   Maven must be installed.
2.  **Clone the Repository (if applicable):**

    ```bash
    git clone https://github.com/DarylPastidio/smartpark
    cd smartpark
    ```

3.  **Build the Project:**

    ```bash
    mvn clean install
    ```

    This command will:
    *   `clean`: Delete any previously compiled files.
    *   `install`: Compile the source code, run the tests, and package the application as a JAR file in your local Maven repository.

## Run Instructions

1.  **Navigate to the Project Directory:**

    ```bash
    cd smartpark
    ```

2.  **Run the Application:**

    ```bash
    mvn spring-boot:run
    ```

    This command will start the Spring Boot application. You should see log messages in the console indicating that the application has started successfully.  The application will typically run on port 8080.

## Test Instructions

1.  **Run Tests:**

    ```bash
    mvn test
    ```

    This command will execute all the JUnit tests in your project.  The results will be displayed in the console, and a test report will be generated in the `target/surefire-reports` directory.

    You can also run the tests directly from your IDE (e.g., right-click on the `SmartparkApplicationTests.java` file and select "Run").

2.  **Interpreting Test Results:**

    *   A successful test run will show that all tests have passed.
    *   If any tests fail, the output will indicate which tests failed and provide information about the cause of the failure (e.g., assertion errors, exceptions).

## API Endpoints

The application exposes the following REST API endpoints:

### Parking Lots

*   **POST /api/parking-lots:** Register a new parking lot.
    *   Request Body: JSON representation of a `ParkingLot` object.
    *   Response: `201 Created` with the created `ParkingLot` object in the response body.
*   **GET /api/parking-lots/{id}:** Get a parking lot by ID.
    *   Response: `200 OK` with the `ParkingLot` object in the response body.
*   **GET /api/parking-lots/lotId/{lotId}:** Get a parking lot by lot ID.
    *   Response: `200 OK` with the `ParkingLot` object in the response body.
*   **GET /api/parking-lots:** Get all parking lots.
    *   Response: `200 OK` with a JSON array of `ParkingLot` objects in the response body.
*   **PUT /api/parking-lots/{id}:** Update a parking lot.
    *   Request Body: JSON representation of a `ParkingLot` object.
    *   Response: `200 OK` with the updated `ParkingLot` object in the response body.
*   **DELETE /api/parking-lots/{id}:** Delete a parking lot.
    *   Response: `204 No Content`.
*   **GET /api/parking-lots/{id}/availability:** Get the number of available spaces in a parking lot.
    *   Response: `200 OK` with the number of available spaces in the response body.

### Vehicles

*   **POST /api/vehicles:** Register a new vehicle.
    *   Request Body: JSON representation of a `Vehicle` object.
    *   Response: `201 Created` with the created `Vehicle` object in the response body.
*   **GET /api/vehicles/{id}:** Get a vehicle by ID.
    *   Response: `200 OK` with the `Vehicle` object in the response body.
*   **GET /api/vehicles/license/{licensePlate}:** Get a vehicle by license plate.
    *   Response: `200 OK` with the `Vehicle` object in the response body.
*   **GET /api/vehicles:** Get all vehicles.
    *   Response: `200 OK` with a JSON array of `Vehicle` objects in the response body.
*   **PUT /api/vehicles/{id}:** Update a vehicle.
    *   Request Body: JSON representation of a `Vehicle` object.
    *   Response: `200 OK` with the updated `Vehicle` object in the response body.
*   **DELETE /api/vehicles/{id}:** Delete a vehicle.
    *   Response: `204 No Content`.
*   **POST /api/vehicles/checkin/{licensePlate}/{lotId}:** Check in a vehicle to a parking lot.
    *   Response: `200 OK`.
*   **POST /api/vehicles/checkout/{licensePlate}:** Check out a vehicle from a parking lot.
    *   Response: `200 OK`.
*   **GET /api/vehicles/lot/{parkingLotId}:** Get all vehicles parked in a specific parking lot.
    *   Response: `200 OK` with a JSON array of `Vehicle` objects in the response body.

## Testing with Postman

See the `Postman Collection.json` file for a pre-configured Postman collection to test the API endpoints. Or you can create request manually by following the above guide.
In general, when testing with Postman, remember to send a request body in JSON format to the routes with method `Post` and `Put`.
Then, observe the HTTP response status code and response body carefully.

## Assumptions

*   API authentication is out of scope. All APIs are no auth.
*   Design documents are not required.
*   Error handling is implemented to provide meaningful error messages.
*   The H2 in-memory database is used, so data will be lost when the application stops.