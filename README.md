# RxJava Bug
Show cases a bug with the RxJava library

##Information
When using RxJava and OkHttp in conjunction, while a network call is being made by OkHttp, if the app is put into the background, the thread used to observe, if it is one of the threads that the Schedulers class manages, will be interrupted. This issue doesn't occur if a thread is manually specified by using ```Schedulers.from()```

##Bug Reproduction Steps

1. Build and install the application on a device.
2. Click the Schedulers.IO button
3. Immediately after pressing the button, press the Home button on your device
4. Wait a few seconds for your device to finish loading (varies based on your Internet connection)
5. Reopen the app and observe the Toast message that says "Got an Interrupted IO Exception!"

##Working Method Reproduction Steps

1. Build and install the application on a device.
2. Click the Single Thread Executor button
3. Immediately after pressing the button, press the Home button on your device
4. Wait a few seconds for your device to finish loading (varies based on your Internet connection)
5. Reopen the app and observe the Toast message that says "Got some items."
