#!/usr/bin/env bash
./gradlew clean
./gradlew build
./gradlew :multiplatform-bus-android:publishToMavenLocal
./gradlew :multiplatform-bus-ios:publishToMavenLocal
./gradlew publishToMavenLocal
./gradlew bintrayUpload
