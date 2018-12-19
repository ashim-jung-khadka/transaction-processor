# Transaction Processor

## Technology Used

- [JUnit](https://junit.org/junit4/)
- [OpenCSV](http://opencsv.sourceforge.net/)
- [Java Xml Parser](https://docs.oracle.com/cd/B28359_01/appdev.111/b28394/adx_j_parser.htm#ADXDK19090)


## Implementation Changes

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