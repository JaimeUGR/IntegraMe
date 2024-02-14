# IntegraMe
IntegraMe is an accessible schedule for students in special education centers, developed for children with
different capabilities and capacities.

As such, the application is able to adapt its UI/UX to the user's needs, including special input devices
such as external buttons or special keyboards.

## Where it comes from
In the subject project leading & management, we had been taught about accesibility and software project's
development, which we put to use developing this application as the main subject's task.

This project has allowed us to learn and gain experience in the following fields:
- **Team work**.
- Software architecture (MVVM, Client-Server...).
- Programming (Kotlin, Javascript, NodeJS).
- Documentation (API Docs, Kotlin Docs).
- Agile methodologies: Scrum (rotating roles).
- Tools: Jira, Excel(nº working hours), Project Libre (for Gantt Charts).


## Documentation
### User Manual
[Manual (Spanish)](./docs/IntegraMe%20-%20Manual%20de%20usuario.pdf)

### Demo Video
[Video](https://drive.google.com/file/d/1_YsjN3gcTFMF-7AKU8YTBJ_OMu0nzLfL/view?usp=sharing "IntegraMe Demo")

### Other documentation
Internal documents such as class diagrams, user stories or Gantt charts wont be shared here for privacy and ownership reasons.


## The app
This application was developed for Android. 

Technology wise, it uses Kotlin and Jetpack Compose, as we believe that these are the best
tools that nowadays exist for Android development.

### Libraries
The main libraries we use are:
- Retrofit2 (API)
- Dagger Hilt (dependency injection)
- Room (local database)
- Coil (remote images)

### Running
Since the backend cloud server is down, the application cant be run once compiled, as it will crash when retrieving data from the API.
However, one can change the build configuration to build using the testing API interface, which will handle requests locally.


## The backend
The application's backend was made using NodeJS as the server, where we defined a REST json based
API that communicates the database and the frontend, which in this case is our Android app.

Furthermore, it also offers support for the administration tools, deployed in a web page.
