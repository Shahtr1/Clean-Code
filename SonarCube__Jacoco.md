# JaCoCo & SonarQube Installation Guide

Follow this guide to install and integrate JaCoCo and SonarQube for code coverage and quality analysis.

## 1. Installing SonarQube

### 1.1. Prerequisites:

- Java (preferably a recent version)
- Database (PostgreSQL, MySQL, or Oracle)

### 1.2. Installation Steps:

1. Download the latest version of SonarQube from the official website.
2. Extract the downloaded zip/tar file to a directory of your choice.
3. Configure the database by editing the `sonar.properties` file located in the `conf` directory of the SonarQube
   installation. Uncomment and set the appropriate database configuration.
4. Start SonarQube:
    - On Windows: Navigate to the `bin\windows-x86-xx` folder and execute `StartSonar.bat`.
    - On macOS/Linux: Navigate to the `bin/[OS]` folder and execute `./sonar.sh start`.
5. Once started, open a browser and go to `http://localhost:9000`. The default login is `admin` with the
   password `admin`.

## 2. Installing JaCoCo

JaCoCo is typically used as a plugin with build tools like Maven or Gradle.

### 2.1. Prerequisites:

- Maven (ensure it's installed and set up on your machine)

### 2.2. Installation Steps:

1. Open your Maven `pom.xml` file.
2. Add the JaCoCo plugin to the `<plugins>` section:

```xml

<plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <version>0.8.7</version>
    <executions>
        <execution>
            <goals>
                <goal>prepare-agent</goal>
            </goals>
        </execution>
        <execution>
            <id>report</id>
            <phase>prepare-package</phase>
            <goals>
                <goal>report</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

Now, when you run `mvn clean verify`, JaCoCo will generate a code coverage report in the `target/site/jacoco` directory.

## 3. Integrating JaCoCo with SonarQube

To send the JaCoCo code coverage report to SonarQube:

1. Add the SonarQube Maven plugin to your `pom.xml`:

```xml

<plugin>
    <groupId>org.sonarsource.scanner.maven</groupId>
    <artifactId>sonar-maven-plugin</artifactId>
    <version>3.9.0.2155</version>
</plugin>
```

## SonarQube Configuration

1. Configure the SonarQube server URL and other properties either in the `pom.xml` or in a `sonar-project.properties`
   file.

2. Run the following Maven command to analyze the project and send the results to SonarQube:

```bash
  mvn clean verify sonar:sonar
```

After running the command, your project's code quality and coverage report will be available on the SonarQube dashboard.
