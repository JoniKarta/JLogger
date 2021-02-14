# JLogger
Library for creating and saving logs in any component in your application such as Activity, Fragment or Service.
The library is built using MVVM arhitecture and Live data which reflect and observe changes in room sql.
You can observe the changes using a pre-built UI, which display all the data from different component in the application,
or dispaly the data to the console without the pre-built ui using the Logger manager which has multiple live data observers.
enjoy.

[![](https://jitpack.io/v/JoniKarta/JLogger.svg)](https://jitpack.io/#JoniKarta/JLogger)


## 💻 Installation
Add this in your app's build.gradle file (Project & module):
## Setup
Step 1. Add it in your root build.gradle at the end of repositories:
```gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

Step 2. Add the dependency:
```gradle
dependencies {
        implementation 'com.github.JoniKarta:JLogger:1.0.1'
}
```
## ScreenShots 
![](Images/Jlogger.gif)


## ❔ Usage
**Basic Usage**
</br>
Minimum SDk version required: 24

###### Logger UI Initialization:
```java
public class MainActivity extends LoggerUI {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}              
```
###### Logger Manager Initialization:
```java
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoggerManager loggerManager = LoggerManager.getInstance(getApplication());
    }
}                
```

###### Logger Functions:

```java
// Delete all logs from the system
getLoggerViewModel().deleteAll();

// Insert new log to the system
getLoggerViewModel().insert(new Logger(Logger.DEBUG, "MESSAGE " + i, new Date()));
        
// Observe all logs in the system
getLoggerViewModel().getAllLogs().observe(this, loggers -> {
    Log.i(TAG, "onCreate: " + loggers);
});

// Observe number of logs in the system
getLoggerViewModel().loggerCount().observe(this, count -> {
    Log.i(TAG, "onCreate: " + count);
});
```

# 📃 License

    Copyright 2020 Jonathan Karta & Gil Glick

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

### If you like the library, please click on the ★ Star button at the top 😊
