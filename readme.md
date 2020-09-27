# Machine Ventures Android Developer Code Challenge

# Task
We need you to implement MyKuya’s pages like home and map:
  - Design: Please implement all designs of the screen folder of the zip file. We've also shared a screen recording for you to better understand the User Experience

  - API: Create API call with mock data
  - Test: The code must have acceptable test coverage
  - Architecture: Use MVP

# Implementation
- Kotlin
- Modular
- Clean Architecture
- MVP (Model View Presenter)
- RxJava
- Navigation Component
- Koin
- JUnit4
- MockK
_ Google Maps

## Description
This project is a Modular project. 
`base` module contains MVP files and `RxRule` for testing. every module can see the `base`.
`data` module contains Repos of the project. we use `Remote` prefix for repositories that implement
repos that calls API's (Mock API's).
`map` module is a feature module and contains fragment and presenter for the Google Map stuff.
app uses `navigation` module to decide where is the next destination of the application.
this is a Single activity project and uses `Navigation Component` to navigate between fragments.
`network` module is a place that you define your retrofit and services and make API calls.
`service` module is a feature module for the home page.
All the Presenters have `Unit Test`s. We use `MockK` because it is well suited for `Kotlin`. `MockK`
enables us to easily mock our dependencies. We use `JUnit4` for assertions. Also, you can see
some of the tests use `Stub` for testing.
