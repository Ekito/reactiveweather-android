# Making your Android API reactive

> see article on ekito's website : https://www.ekito.fr/people/making-android-api-reactive/

> This is about a transformation: finding a way of writing a reactive android API with RxJava/RxAndroid from its imperative version. It could be easily applied to any project that work with multiple flux of data. 

Here is the source code for the weather app. 

The android project contains 2 modules :
- app (the weather app itself)
- weathersdk (the refactored sdk)


The following versions are available in branches:
- first_version: old/imperative way
- reactive_version: RxJava inside
- master/kotlin_version: RxJava and App written in kotlin

