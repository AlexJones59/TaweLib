Requirements
- OpenJDK 11
  - sudo apt install openjdk-11-jdk # For Ubuntu 18.04 and similar systems
  - https://jdk.java.net/11/ # Others
- IntelliJ Idea 2018.3.5 (optional)

Tested on
- Ubuntu 18.04

How to compile
- ./gradlew run     # For UNIX based systems
- gradlew.bat run   # For Windows based systems

Hardcoded credentials (others are randomly generated)
- system.admin
- nice.customer

Should you have any problems performing this, please refer to this documentation:
- https://docs.gradle.org/4.8.1/userguide/command_line_interface.html
- https://docs.gradle.org/4.8.1/userguide/userguide.html

IDE integration (optional)
- Open the project root folder in IntelliJ and import is as a Gradle project
- Import Gradle project changes (Using IntelliJ by navigating to the build.gradle for example).
- Run the application/run Gradle task
