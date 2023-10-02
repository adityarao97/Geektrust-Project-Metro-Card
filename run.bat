@echo off

./gradlew clean build -x test --no-daemon
java -jar .\GeeekTrustChallengeMetroCard\build\libs\geektrust.jar .\input1.txt