# parking-lot

# Task description

[Task initial description for this project](./PROBLEM_2_PARKING_LOT.md)

# Assemble
JDK and Maven expected to be installed in the system with proper paths configuration.

##### Reference JDK used:
```
> java -version

java version "10.0.2" 2018-07-17
Java(TM) SE Runtime Environment 18.3 (build 10.0.2+13)
Java HotSpot(TM) 64-Bit Server VM 18.3 (build 10.0.2+13, mixed mode)
```

##### Reference Maven used:
```
> mvn -version

Apache Maven 3.5.0 (ff8f5e7444045639af65f6095c62210b5713f426; 2017-04-03T22:39:06+03:00)
Maven home: C:\Bin\apache-maven-3.5.0\bin\..
Java version: 11.0.2, vendor: Oracle Corporation
Java home: C:\Program Files\Java\jdk-11.0.2
Default locale: en_US, platform encoding: Cp1251
OS name: "windows 8.1", version: "6.3", arch: "amd64", family: "windows"
```

To assemble jar file for distribute and execution:
```
> mvn clean package
```

Command to execute examples:
```
> java -jar ./target/parking-lot-1.0.jar 
> java -jar ./target/parking-lot-1.0.jar tests/provided.in.txt 
```

Command for distribution:
```
> git archive -o parking-lot.zip HEAD
```
