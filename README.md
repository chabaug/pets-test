# PetStore Test Suite

## Overview
This is a Java solution provided for testing the PetStore App given by the Home Challenge. It uses RestAssured and TestNG to implement and perform such tests.

The tests selected cover what I understand are the key endpoints from the app. This is why enpoints like /pet/findByTags or /pet/{petId}/uploadImage were not covered. 
In other cases, like Update or Delete Users, there was not enough test data to provide a possible solution.

### To run (with Maven)

Given the app is running locally, follow the next steps to run the tests.

1. In order to install all dependencies run:

```
mvn install
```

2. Then, to run the tests execute:

```
mvn test
```
