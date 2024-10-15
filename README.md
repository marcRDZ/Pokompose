
# Another approach to Clean Architecture for Android


As we already know the basic diagram for this architecture pattern as you had seen in many sites is something like the following:
![Clean Architecture diagram](http://drive.google.com/uc?export=view&id=1eX61zh9UinkJEnvi1T3PvDpsJXy2FRT9)

So this architecture pattern has been translated to the the following base structure:

![Pokompose project architecture](http://drive.google.com/uc?export=view&id=175NcPUgKV-NA53HgOLQETY3v7IFLkA26)

I use to work with a kind of modularized architecture separated on three layers for UI, domain and data, with a domain isolated of the framework as a pure kotlin project.

My idea with this project was to split the domain on three modules following the dependency inversion principle to decouple the side dealing with the framework for data from the other that use the UI. 

Beside this I try to implement a pure kotlin Presentation layer that contains the UI logic in a agnostic and testable way, so as the Data layer implements **repositories** and the Domain contains the **use cases**, I've created an `EventFlowHandler` interface as a bridge between `ViewModels` and the **use cases**, its main purpose is to contain the flow of reactions from UI events to the resulting states.

Inside the UI module you could see an architecture closer to MVI pattern, using a typed interface for viewmodels with a `BaseStateHolder` containing the state of data, views communicate with them through a specified set of `ViewEvents`.

I gather here all the things I've been learning through my latest working experience:

- Asynchronous handling with [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
- UI reactive state management with [Flows](https://kotlinlang.org/docs/flow.html)
- Functional programming support through [Λrrow](https://arrow-kt.io/)
- UI fully designed on [Jetpack Compose](https://developer.android.com/jetpack/compose)

To provide a wide domain for use case examples the project deals with the well known Poke API through my own forked version of [Pokekotlin](https://github.com/marcRDZ/pokekotlin) with additional features.

Let's see how it goes!

[pokompose_master-detail.webm](https://github.com/user-attachments/assets/6f3a102b-213a-4aa8-9438-500c044dd707)


> Written with [StackEdit](https://stackedit.io/).
