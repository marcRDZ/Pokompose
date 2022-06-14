
# Another interpretation of Clean Architecture for Android

This project wants to provide a practical approach to this well-known architecture  applied to Android development.

I pretend to include the lastest features of the framework like [Jetpack Compose](https://developer.android.com/jetpack/compose)  to learn about them by the way and also many other third party libraries, trying to create a kind of "state of the art" app.

To provide a wide domain for use case examples I added a forked version of [Pokekotlin](https://github.com/marcRDZ/pokekotlin) with additional features like **coroutines** and functional programming support through [Λrrow (arrow-kt.io)](https://arrow-kt.io/) library.

As we already know the basic diagram for this architecture pattern as you had seen in many sites is something like the following:
![Clean Architecture diagram](http://drive.google.com/uc?export=view&id=1eX61zh9UinkJEnvi1T3PvDpsJXy2FRT9)

So this architecture pattern has been translated to the the following base structure:

![Pokompose project architecture](http://drive.google.com/uc?export=view&id=175NcPUgKV-NA53HgOLQETY3v7IFLkA26)

Basically, its main goal is to try to decouple the **UI logic** from the framework implementation in the same way we usually do with the **Domain logic**, keeping separated the flows of actions (see `Event`), by user or OS means, and reactions (see `State`) from actual `Activity` and `ViewModel`, making more testable by doing so.

So as we work with the concepts of **UseCase** and **Repository** to abstract the business logic from the underlying use of whatever framework API or 3rd party library, this project will use a kind of intermediary between UI and Domain layers called `StateHandler`

Let's see how it goes!

> Written with [StackEdit](https://stackedit.io/).
