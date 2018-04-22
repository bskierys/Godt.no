# Godt.no

<p align="center">
    <a href="marketing/promocyjne/eng/recommended.png">
        <img src="app/src/main/ic_launcher-web.png" style='width:100%;'/>
    </a>
</p>

## About this project

Godt.no is a simple project that demonstrates usage of clean architecture with MVP pattern. To see my approach to clean architecure with MVVM pattern see [Daily Set Solver](https://github.com/bskierys/DailySetSolver).

## What does this app do?

[Godt.no](https://www.godt.no/) is Norways most popular food website. The site contains food recipes, restaurant reviews, customer blogs/opinions and food articles.

This app consist of a simple list of the first 50 recipes in the godt api, containing title, image, description and ingredient names. It is possible to search for a recipe based on the ingredients or title of the recipe and the app works offline after parsing the feed for the first time.

## Architecture

Project is using clean architecture along with MVP pattern. It's also written entirely in kotlin with support from RxJava.

**What is clean architecture?**
The Clean Architecture demonstrates strategies to help solve or avoid common problems. It is designed to be testable, independent from UI, database or any external data source. Read more in articles below:

[Architecting Android...The clean way?](https://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/)

[What is all this Clean Architecture jibber-jabber about?](http://pguardiola.com/blog/clean-architecture-part-1/)

[Android Architecture Blueprints](https://github.com/googlesamples/android-architecture)

## Languages, libraries and tools used

* [Kotlin](https://kotlinlang.org/) - future of Android developement<br/>
* [RxJava2](https://github.com/ReactiveX/RxJava/wiki/What's-different-in-2.0) - for data manipulation<br/>
* [RxAndroid](https://github.com/ReactiveX/RxAndroid) - for schedulers and more<br/>
* [Dagger 2](https://github.com/google/dagger) - for dependency injection<br/>
* [Pine](https://github.com/bskierys/Pine),[Timber](https://github.com/JakeWharton/timber) - for simple logging<br/>
* [Retrofit2](http://square.github.io/retrofit/) - for REST Api
* [Realm](https://realm.io/products/realm-database/) - for caching data for offline use

**Application is also build upon two boilerplate projects:**

* [Android Architecture Blueprints](https://github.com/googlesamples/android-architecture/tree/todo-mvp-clean) - Official Google view on Clean Architecture
* [Kotlin-MVP-Example](https://github.com/Yalantis/kotlin-mvp-example) - MVP Boilerplate in kotlin<br/>
* [Android Clean Architecture Boilerplate](https://github.com/bufferapp/android-clean-architecture-boilerplate) - Clean Architecture Boilerplate in kotlin<br/>

## License

    Copyright 2018 Bartłomiej Kierys

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
