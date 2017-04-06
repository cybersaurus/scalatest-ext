#ScalaTest Extensions

## EnhancedWordSpecLike
Extends `WordLikeSpec` to allow ignoring a test until a specified date.

For example:
```scala
"run test when hoverboards have been invented".ignoreUntil("2100-12-15") {
  // test the hoverboard works...
}
```
For equivalent functionality in Java/JUnit, see https://github.com/cybersaurus/junit-ext.