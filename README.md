[![Release](https://jitpack.io/v/roadhouse-dev/LicenseHelper.svg)](https://jitpack.io/#roadhouse-dev/LicenseHelper)

#Main features
* Display open source licenses 
* Quick and easy integration

#Setup
```java 
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```

Add the dependencies:

```java 
dependencies {
    compile 'com.github.roadhouse-dev:LicenseHelper:1.0.1'
}
```

#Build
```java
$ git clone https://github.com/roadhouse-dev/LicenseHelper.git
$ ./gradlew build
```


#Usage

1- Create a folder in your apps assets directory called license 

2 - For each license you want to display, create a JSON file in your apps assets licence directory
```json
{
    "name":"Butter Knife",
    "link":"http://jakewharton.github.io/butterknife/",
    "license_link":"https://raw.githubusercontent.com/JakeWharton/butterknife/master/LICENSE.txt"
}
```


2 - Launch the LicenseHelper 

```java
    new LicenseHelper(Activity.this).displayLicenses(); 
```

#Bugs and Feedback
For bugs, questions, requests and discussions please use the [GitHub Issues](https://github.com/roadhouse-dev/LicenseHelper/issues).

#Pull Requests
All pull requests are welcome, however to make the whole process smoother please use the following guides

* All pull requests should be against the ```develop``` branch
* Code formatting should match the default Android Studio format
* Limit code changes to the scope of your implementation
* Provide standard JavaDoc for any public accessible members and classes