# bug project - SpringBoot with embedded MQTT server (HiveMQ CE)

## important note

**THIS IS NO EXAMPLE CODE, THIS IS FOR DEBUGGING/FINDING THE PROBLEM**

## description

For a project I wanted to embed the HiveMQ Community Edition MQTT server, but having the convenience of having all dependencies bundled in a fat-jar by using Spring Boot. There is no webserver included, so I only need the "Spring Boot Starter", which should take care of the application being able to run without further configuration.

In addition this is implemented using JDK 11 (and the ugly java module system), so maybe this is one source of the problems.

## the issue

**HiveMQ will behave unexpectedly!**

When starting the embedded MQTT server, some strange error message is shown on start:

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.5.4)

2021-09-07 13:37:47,641 INFO  - Starting Launcher v1.0.0-SNAPSHOT using Java 11.0.12 on somesystem with PID 14896 (C:\tmp\issuemqttembeddedspringboot\target\issuemqttembeddedspringboot-1.0.0-SNAPSHOT.jar started by someuser in C:\tmp\issuemqttembeddedspringboot)
2021-09-07 13:37:47,645 INFO  - No active profile set, falling back to default profiles: default
2021-09-07 13:37:49,548 INFO  - Started Launcher in 3.128 seconds (JVM running for 4.773)
>>>>>>>>
currentWorkingDirectory: C:\tmp\issuemqttembeddedspringboot
basePath: C:\tmp\issuemqttembeddedspringboot\mqtt-embedded
dataPath: C:\tmp\issuemqttembeddedspringboot\mqtt-embedded\data
configPath: C:\tmp\issuemqttembeddedspringboot\mqtt-embedded\conf
extensionsPath: C:\tmp\issuemqttembeddedspringboot\mqtt-embedded\extensions
configFile: C:\tmp\issuemqttembeddedspringboot\mqtt-embedded\conf\config.xml
<<<<<<<<
2021-09-07 13:37:49,576 INFO  - Setting default authentication behavior to ALLOW ALL
2021-09-07 13:37:49,596 INFO  - HiveMQ version: Development Snapshot
2021-09-07 13:37:49,600 WARN  - Not able to create folder file:\C:\tmp\issuemqttembeddedspringboot\target\issuemqttembeddedspringboot-1.0.0-SNAPSHOT.jar!\BOOT-INF\lib\hivemq-community-edition-embedded-2021.2.jar!\data, HiveMQ will behave unexpectedly!
2021-09-07 13:37:50,675 INFO  - Starting EmbeddedHiveMQ.
2021-09-07 13:37:51,445 INFO  - Starting with file persistence mode.
WARNING: An illegal reflective access operation has occurred
WARNING: Illegal reflective access by com.google.inject.internal.cglib.core.$ReflectUtils$1 (jar:file:/C:/tmp/issuemqttembeddedspringboot/target/issuemqttembeddedspringboot-1.0.0-SNAPSHOT.jar!/BOOT-INF/lib/guice-4.2.3.jar!/) to method java.lang.ClassLoader.defineClass(java.lang.String,byte[],int,int,java.security.ProtectionDomain)
WARNING: Please consider reporting this to the maintainers of com.google.inject.internal.cglib.core.$ReflectUtils$1
WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
WARNING: All illegal access operations will be denied in a future release
2021-09-07 13:37:53,815 WARN  - Can't open directory channel. Log directory fsync won't be performed.
(...)
2021-09-07 13:37:54,403 WARN  - Can't open directory channel. Log directory fsync won't be performed.
2021-09-07 13:37:55,805 INFO  - Starting HiveMQ extension system.
2021-09-07 13:37:55,874 INFO  - Starting TCP listener on address 0.0.0.0 and port 1883
2021-09-07 13:37:57,036 INFO  - Started TCP Listener on address 0.0.0.0 and on port 1883
2021-09-07 13:37:57,036 INFO  - Started EmbeddedHiveMQ in 6361ms
2021-09-07 13:37:57,036 INFO  - Closing EmbeddedHiveMQ.
2021-09-07 13:37:59,084 INFO  - Shutting down extension system
2021-09-07 13:38:01,528 INFO  - Stopped EmbeddedHiveMQ in 4492ms
```

## steps to reproduce

```
mvn clean package
java -jar ./target/issuemqttembeddedspringboot-1.0.0-SNAPSHOT.jar
```

## used software

Operating System: Windows 10
```
Microsoft Windows [Version 10.0.19043.1165]
```

Maven: 3.6.3
```
Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
Maven home: C:\tools\maven\apache-maven-3.6.3\bin\..
Java version: 11.0.12, vendor: Oracle Corporation, runtime: C:\Program Files\OpenJDK\openjdk-11.0.12_7
Default locale: de_DE, platform encoding: Cp1252
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
```

Java: OpenJDK 11
```
openjdk version "11.0.12" 2021-07-20
OpenJDK Runtime Environment 18.9 (build 11.0.12+7)
OpenJDK 64-Bit Server VM 18.9 (build 11.0.12+7, mixed mode)
```
