
# Another interpretation of Clean Architecture for Android

This project wants to provide a practical approach to this well-known architecture applied to Android development.

As we already know the basic diagram for this architecture pattern as you had seen in many sites is something like the following:
![Clean Architecture diagram](http://drive.google.com/uc?export=view&id=1eX61zh9UinkJEnvi1T3PvDpsJXy2FRT9)

So this architecture pattern has been translated to the the following base structure:

![Pokompose project architecture](http://drive.google.com/uc?export=view&id=175NcPUgKV-NA53HgOLQETY3v7IFLkA26)

I use to work with a kind of modularized architecture separated on three layers for UI, domain and data, with a domain isolated of the framework as a pure kotlin project.

My idea with this project was to split the domain on three modules following the dependency inversion principle to decouple the side dealing with the framework for data from the other that use the UI.

Also I try to implement a pure kotlin Presentation layer that contains the UI logic in a agnostic way, so as the Data layer implements **repositories** and the Domain contains the **use cases**, I've created an `EventHandler` interface as a bridge between `ViewModels` and the **use cases**, it's main purpose is to contain the flow of reactions  from UI events to the resulting states.

Inside the UI module you could see an architecture closer to MVI pattern, using typed interfaces for views and viewmodels, sharing a `StateHolder` and a specified set of `ViewEvents`, which allows you to have declared a clear way of communication on both directions and the different `ViewStates` that a view could get.

I gather here all the things I've been learning through my latest working experience:

- Asynchronous handling with [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
- UI reactive state management with [Flows](https://kotlinlang.org/docs/flow.html)
- Functional programming support through [Λrrow](https://arrow-kt.io/)
- More features like [Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) for Gradle and other things...

Although I'm not working currently with [Jetpack Compose](https://developer.android.com/jetpack/compose) I've created this project on top of such feature as I'm learning by the way.

To provide a wide domain for use case examples the project deals with the well known Poke API through a forked version of [Pokekotlin](https://github.com/marcRDZ/pokekotlin) with additional features.

Let's see how it goes!

> Written with [StackEdit](https://stackedit.io/).
