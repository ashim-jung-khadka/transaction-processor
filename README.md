# Transaction Processor

## Technology Used
- [Spring Boot](https://spring.io/projects/spring-boot)
- [JUnit](https://junit.org/junit4/)
- [OpenCSV](http://opencsv.sourceforge.net/)
- [Java Xml Parser](https://docs.oracle.com/cd/B28359_01/appdev.111/b28394/adx_j_parser.htm#ADXDK19090)


## Implementation Changes - Version 1.0-SNAPSHOT

- Added two implementation class under Transaction Processor.
```java
class CsvTransactionProcessor implements TransactionProcessor {}
class XmlTransactionProcessor implements TransactionProcessor {}
```

- Since default implementation is supported by an interface,
following methods implementation is in TransactionProcessor interface.
```java
interface TransactionProcessor {
	default List<Violation> validate() {}
	default boolean isBalanced() {}
}
```

- Introduce new enum called ViolationEnum for error handling.

- Added test scenario inside src/test/resources folder.

## Implementation Changes - Version 1.1-SNAPSHOT

- Upgraded to Spring Boot
- Moved class to their respective package
- Added TransactionProcessorController to handle request for both csv and xml
- Added integration test for Controller
    - CsvTransactionProcessorControllerTest
    - XmlTransactionProcessorControllerTest
- Added Docker Plugin

#### Create Docker Container
    /* It will build docker image as well */
    mvn clean install
    
    docker run -p 8080:8080 transaction-processor:latest
    OR
    docker-compose up

#### Pull from Docker Hub
Link : https://hub.docker.com/r/ashimjk/transaction-processor
    
    docker pull ashimjk/transaction-processor:1.1-SNAPSHOT
    docker run -p 8080:8080 ashimjk/transaction-processor:1.1-SNAPSHOT

#### Test Endpoints
    curl -F "file=@src/test/resources/xml/equalBalance.xml" http://localhost:8080/transaction-processor/upload-xml 

##### Future Changes :
- simplify transaction upload endpoint into single.
- pass content type via configuration.
- separate integration and unit testing.
 