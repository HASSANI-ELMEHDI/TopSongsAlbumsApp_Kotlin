# Android Application - Top Songs/Albums List with Filtering

This is an Android application developed using Kotlin that utilizes the Apple RSS feed API to fetch and display the top songs/albums. The application provides the functionality to filter the results based on the user's preference, allowing them to view either the top 10 or top 25 songs/albums.

## Features

- Fetches data from the Apple RSS feed API.
- Displays a list of the top songs/albums with their relevant information.
- Allows the user to filter the results based on their preference (top 10 or top 25).
- Provides an option to switch between viewing songs and albums.

## Prerequisites

- Android Studio installed on your system.
- Kotlin programming language.

## Getting Started

1. Clone this repository to your local machine or download the source code as a zip file.
2. Open Android Studio and select "Open an existing Android Studio project."
3. Navigate to the project directory and select the root folder. Click "OK" to open the project.
4. Wait for the Gradle build to complete.

## Configuration

Ensure that you have an active internet connection to fetch data from the Apple RSS feed API.

## Usage

1. Open the project in Android Studio.
2. Build and run the application on an Android emulator or physical device.

## Implementation Details

The application is built using the Model-View-ViewModel (MVVM) architecture pattern. Here is a brief overview of the project structure:

- `app` module: Contains the main code and resources for the Android application.
  - `src/main/java`: Contains the Kotlin source code.
    - `data`: Contains the data-related classes and repositories.
    - `model`: Contains the data models representing songs/albums.
    - `network`: Contains the network-related classes for API communication.
    - `ui`: Contains the user interface components such as activities, fragments, and adapters.
    - `viewmodel`: Contains the ViewModel classes for the UI components.
  - `src/main/res`: Contains the XML layout files, strings, and other resources.

## Mockups
Here are some mockups to provide you with an idea of the application's user interface:
1. Top Songs List:

<img src="[image-url](https://github.com/HASSANI-ELMEHDI/TopSongsAlbumsApp_Kotlin/assets/105174552/cce56554-bb38-4390-8900-4ab802c5d735)" height="300" width="400">

3. Top Albums List:


![2](https://github.com/HASSANI-ELMEHDI/TopSongsAlbumsApp_Kotlin/assets/105174552/be2294d9-28d0-443a-909a-37fb0afc83f8)

4. Filter the results:
   
![3](https://github.com/HASSANI-ELMEHDI/TopSongsAlbumsApp_Kotlin/assets/105174552/81a70c05-164f-464a-b518-f1cdae6cc46d)


Enjoy using the application!
