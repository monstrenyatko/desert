DESERT WEB APPLICATION TESTING FRAMEWORK
========================================

Prepare environment
===================

JDK
---
1. Download and Install `Oracle JDK 7`
2. Create system environment variable `JAVA_HOME` with the path to the installed JDK

Maven
-----
1. Download `Apache Maven 3.2.2` (or the latest) and extract all files from the archive
2. Create system environment variable `M2_HOME` with the path to the extracted files
3. Add `M2_HOME/bin` path to the system variable `PATH`

Compilation and Installation
============================
1. Go to the project directory
2. Call: `mvn install`

When everything is finished you must have the `desert.jar` and `desert-sources.jar`
have been installed to the local Maven repository.

Configuration
=============

Property files
--------------
The library has the built in property file with name `desert-base.properties`.

Any property could be overwritten by the user defined property file `desert.properties`.
The property file must be available in standard resources paths of your project.

Property search sequence:
<br/> `desert.properties` -> `desert-base.properties`

Properties types
----------------
- Time: Milliseconds
- Time-Out: Milliseconds
- Window dimensions: Pixels

Properties
----------
- `browser.name`: Browser name
<br/> Default: `firefox`
<br/> Values: `firefox`, `chrome`, `safari`, `internet explorer`
- `browser.window.width`: Browser window width
<br/> Default: 1280
- `browser.window.height`: Browser window height
<br/> Default: 1024
- `timeout.implicitlyWait`: Sets the amount of time the driver should
wait when searching for an element if it is not immediately present
<br/> Default: 15000
- `timeout.pageLoadTimeout`: Sets the amount of time to wait for a page load
to complete before throwing an error
<br/> Default: 120000
- `timeout.pageConstructorLatency`: Sets additional latency between Making
a new instance of `PageObject` class and Initialization of elements of the page.
It gives a chance to load some parts of the page.
<br/> Default: 300
