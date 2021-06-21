# Finanteq Recruitment Task. Maksym Soshyn
## Description
Simple Spring WEB Service to perform simple currency conversion for next currencies: ["pln", "eur", "usd", "gbp"]

## Prerequirements
1. Java 8+
2. Gradle
3. Windows/Unix operating system

## How to use
Go to root project directory.

Build an artifact. Run:
```
gradlew build
```
Run the artifact via java:
```
java -jar ./build/libs/finanteq-0.0.1-SNAPSHOT.jar
```
Make request via curl, for example:
```
curl -X GET "localhost:8080/currency/convert?currencyCodeFrom=eur&currencyCodeTo=pln&amount=1"
```

## Example outputs
```json
{
  "currencyCode": "pln",
  "amount":4.414312000000001
}
```
