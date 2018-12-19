# Transaction Processor

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