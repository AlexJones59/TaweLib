Requirements:
- OpenJDK 11
- Gradle
- IntelliJ Idea 2018.3.5

Tested on:
- Ubuntu 18.04

How to compile:
- Open the project root folder in IntelliJ and import is as a Gradle project
- Import Gradle project changes (Using IntelliJ by navigating to the build.gradle for example).
- Run the application/run Gradle task

Command line alternative:
  The project is a fully wrapped Gradle application. Depending on yor system, you should execute
  the gradle wrapper "gradlew" on unix based systems or "gradlew.bat" on windows. To launch the app
  use the following command:

  - "./gradlew run" on Linux, MacOS
  - "gradlew.bat run" on Windows

  Should you have any problems performing this, please refer to this documentation:

  - https://docs.gradle.org/4.8.1/userguide/command_line_interface.html
  - https://docs.gradle.org/4.8.1/userguide/userguide.html