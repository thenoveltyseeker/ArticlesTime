# ArticlesTime

Articles time is an Android app that will fetch and display the most popular articles for the past 7 days from the New York times API.


## App Architecture

The app is designed using MVVM architecture for uni directional data flow and Clean architecture for proper separation of concerns usind layered structure

## Basic working 

The app follows a single activity structure
There are two screens - List screen and Details Screen

[List screen](https://user-images.githubusercontent.com/14359651/193552645-ebb770f6-d639-488a-a3b7-72926077be20.png)
[Details screen](https://user-images.githubusercontent.com/14359651/193552386-b14c6b6d-6763-4b12-80bd-9f198b290ff7.png)

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
- Clone the project using the link

## How to run tests

- Choose the edit configuration at the top (from where we run the app)
- Click on the "+" button (highlighted in image) and select Gradle

![image](https://user-images.githubusercontent.com/14359651/193554736-9be9aa32-e016-4809-8a63-b63a716b937b.png)

- Now give a name for the suite
- Add the Run configurations highlighted in the screenshot in the Run field
- Select the project root folder for Gradle project field
- Now Apply, then OK.
- Now run the configuration like we run the app

![image](https://user-images.githubusercontent.com/14359651/193555003-522230fc-700f-441a-be86-418677cd14ec.png)

- Once you run configuration the test result will be shown like above
- Click on the highlighted vertical up arrow to export the test report. (You can also choose the gradle test report)

![image](https://user-images.githubusercontent.com/14359651/193556157-a4e5ed44-68a5-4986-8ad7-c4a0c7151e11.png)

- For coverage run the config using the highlighted sheild icon.
- Once the suite is updated, click the vertical up arrow (highlighted) to generate the coverage report.

