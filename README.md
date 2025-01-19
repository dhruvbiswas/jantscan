JAntScan - Java Annotation Scan is an application that scans a jar file and reports all annotations that it could discover in the file.

Currently it only supports run-time annotations that the application can discover by instantiating a class instance of the class in the jar.
The annotation added on the class should be included as a class file inside the jar.

There is a sample jar-with-all-dependencies that contains annotated classes at the following location that can be used to test the report generated

https://github.com/dhruvbiswas/jantscan/blob/master/src/test/resources/sample_application-1.0-SNAPSHOT-jar-with-dependencies.jar

The workspace can be built using the following command

```
mvn clean package
```

This would build an assembly jar under target folder. To run the application use the following command

```
java -jar ./target/jantscan-jar-with-dependencies.jar ./src/test/resources/sample_application-1.0-SNAPSHOT-jar-with-dependencies.jar
```

The scanned classes are reported on the console. The application can be extended to generate json output, but that is a TODO for now.

Here is sample output of a scanned class
```
Scanned: com.sample.app.lib.AppClass3
--Class Annotation--
com.sample.app.lib.AppClass3:[com.sprung.core.annotations.Component,]
--Field--
appClass2_instance2:[com.sprung.core.annotations.AutoWired,]
appClass2:[com.sprung.core.annotations.AutoWired,com.sprung.core.annotations.AutoWired,]
propvalue:[com.sprung.core.annotations.Value,]
someothervalue:[com.sprung.core.annotations.Value,]
--Constructor--
method-level annotated constructors, found none
only-method-parameter annotated constructors, found none
--Methods--
notify_0:[jdk.internal.HotSpotIntrinsicCandidate,]
getClass_0:[jdk.internal.HotSpotIntrinsicCandidate,]
notifyAll_0:[jdk.internal.HotSpotIntrinsicCandidate,]
hashCode_0:[jdk.internal.HotSpotIntrinsicCandidate,]
only-method-parameter annotated methods, found none
```

The application currently does not support executable jar file parsing. 
The application uses ```class.forName``` and passes in the
jar file that the user specifies as a classloader URL. If ```class.forName``` cannot instantiate a Class instance from the jar the 
application would exit with an Error.

The unit tests contain actual Spring annotations pulled in and a set of unit test beans that contain annotations at various
places within the class file. 
Kindly check the following unit test file for what annotation discovery the application currently supports.

https://github.com/dhruvbiswas/jantscan/blob/master/src/test/java/ca.jantscan.test/caprocessor/ClassAnnotationProcessorTest.java

To run all tests execute the following

```
mvn test
```

Unit test code coverage can be found at ```./target/site/jacoco/index.html```

