<h1 align="center">MovieApp</h1>
<table>
  <tr>
    <td>
      <img src="https://github.com/emir-kaya/movieapp/blob/main/assets/MovieDetailScreen.gif" alt="Açıklama" height="400" width="200">
    </td>
    <td>
      <img src="https://github.com/emir-kaya/movieapp/blob/main/assets/ActorDetailScreen.gif" alt="Açıklama" height="400" width="200">
    </td>
  </tr>
</table>

## Project Overview 
Project aims to provide the movie and actor details to the user. User can search for movies and actors. And can add movies to the favorite list.



## Tech Stack 🛠
-Jetpack Compose: I used Jetpack Compose to design UI screens.

-Hilt: I used it to provide dependency injection.

-Retrofit: I used it to make API calls.

-Room: I created a local database for favorite movies list.

-MVVM Architecture: I used Model View Viewmodel for architecture.

-Glide: I used Glide to view movie and actor photos from web.

## Clean Architecture 

<table>
  <tr>
    <td>
      <img src="https://gitlab.com/emir-kaya/movieapp/-/raw/main/assets/Arch.PNG?ref_type=heads" alt="Açıklama" height="400" width="400">
    </td>
  </tr>
</table>

### Presentation Layer

 This layer include composable functions and viewmodels. MVVM architecture is provided by viewmodels

### Data Layer

 Includes repository implementations, retrofit and room structures.

### Domain Layer

 Domain layer is completely independent of the presentation and data layers. Creates business logic with repository abstractions and usecases.

## Architecture Components

 <table>
  <tr>
    <td>
      <img src="https://gitlab.com/emir-kaya/movieapp/-/raw/main/assets/archgraph.PNG?ref_type=heads" alt="Açıklama" height="400" width="300">
    </td>
  </tr>
</table>

### UI Controllers
 
  The only job of UI controllers is to know how to display data and pass on UI events, such as the user pressing a button.

### ViewModel and Flow

  These classes represent all of the data needed for the UI to display. Data flow is provided asynchronously with the flow structure.

### Repository

  The viewmodel requests data from the repository. The repository manages where the data should be requested from: database or network.

### Remote Data Source
  Remote data source is managed. Retrofit is used for this management.

### Model
  Local data base is managed. Room is used for this management.


## UIState and MutableStateFlow

  UiState is often used with reactive data types like MutableStateFlow, so the UI is automatically updated with any changes in the ViewModel. For example, when data is pulled from the network or the user makes a search, the UiState is updated and reflected in the UI.


