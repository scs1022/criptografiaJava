#!/bin/bash
which java
java -version
echo "JAVA_HOME is currently set to: $JAVA_HOME"

which mvn
mvn -version

chmod +x mvnw
./mvnw clean package
