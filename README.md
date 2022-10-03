# ArticlesTime

Articles time is an Android app that will fetch and display the most popular articles for the past 7 days from the New York times API.


## App Architecture

The app is designed using MVVM architecture for uni directional data flow and Clean architecture for proper separation of concerns using layered structure

## Basic working 

The app follows a single activity structure.
There are two screens - List screen and Details Screen

[List screen ](https://user-images.githubusercontent.com/14359651/193558039-18066a7d-e65e-4344-bfb2-bddf1b2b22a4.png)

[Details screen-1](https://user-images.githubusercontent.com/14359651/193558229-a736f814-7f10-4ceb-b1a9-7dd535491935.png)

[Details screen-2](https://user-images.githubusercontent.com/14359651/193552386-b14c6b6d-6763-4b12-80bd-9f198b290ff7.png)

#### Layers 

Data layer - This holds the Data source (Remote and Local), their DTOs and Repository implementation

Domain layer - DTO to domain mappers, Usecases and Repoistory interface (IoC)

Presentation layer - Viewmodels, adapters, Fragments and everything UI

#### Data flow
View -> ViewModel -> Usecase -> Repo -> Datasource 


## Tech stack

- Language : Kotlin
- DI framework : Hilt
- Networking : Retrofit and GSON
- Local data caching : Room database
- Asyncronous operation : Coroutines and Flow
- Jetpack components : AAC Viewmodel, Navigation component
- Test framework : MockK


## How to Build and Run the app
- Clone the project using the link available in the repo
![image](https://user-images.githubusercontent.com/14359651/193556947-bad02f6a-819d-44eb-9f44-4ede1b899f78.png)
- Import the cloned project in Android studio: File -> New -> Import project
- Choose the cloned repo directory
- Let Gradle download the necessary dependencies
- Once all the processes are completed, build and run the app using the Green Play button at the top bar of Android studio.

## How to run tests

- Choose the edit configuration at the top (from where we run the app)
- Click on the "+" button (highlighted in image) and select Gradle

![image](https://user-images.githubusercontent.com/14359651/193554736-9be9aa32-e016-4809-8a63-b63a716b937b.png)

- Now give a name for the suite
- Add the Run configurations highlighted in the screenshot in the Run field.
- Select the project root folder for Gradle project field
- Now Apply, then OK.
- Now run the configuration: instead of choosing app as configuration choose our test config and click the play button.

![image](https://user-images.githubusercontent.com/14359651/193555003-522230fc-700f-441a-be86-418677cd14ec.png)

- Once you run the configuration the test results will be shown like above.
- Click on the highlighted vertical up arrow to export the test report. (You can also choose the gradle test report - gradle icon)

![image](https://user-images.githubusercontent.com/14359651/193556157-a4e5ed44-68a5-4986-8ad7-c4a0c7151e11.png)

- For Test coverage, run the created test configuration using the highlighted sheild icon in the above image.
- Once the suite is updated, click the vertical up arrow (highlighted) to generate the coverage report.

