## Java Command Line Interface Helper

This library helps to create a primitive command line interface 
using java. 

![status](https://travis-ci.org/kosbr/cli-helper.svg?branch=master) [![](https://jitpack.io/v/kosbr/cli-helper.svg)](https://jitpack.io/#kosbr/cli-helper)

### Example

The example below describes how to create CLI with one command 
(greeting):

```
greeting --name Bob
    Hello, Bob
```

#### Step 1: Describe command options

```
public class GreetingOptions implements CommandOptions {

    @Parameter(names = "--name", description = "Your name", required = true)
    private String name;

    public String getName() {
        return name;
    }
}
```

#### Step 2: Implement command handler

```
public class GreetingHandler implements CommandHandler<GreetingOptions> {
    @Override
    public boolean handle(GreetingOptions commandOptions, PrintStream printStream) {
        final String name = commandOptions.getName();
        printStream.println("Hello, " + name);
        // return false if exit is needed
        return true;
    }

    @Override
    public Class<GreetingOptions> getOptionsClass() {
        return GreetingOptions.class;
    }
}
```

#### Step 3: Use it in main class

```
public static void main(String args[]) {
    final CommandRegistry commandRegistry = new CommandRegistry();
    commandRegistry.registerCommand("greeting", new GreetingHandler());
    final ConsoleManager consoleManager = new ConsoleManager(System.out, System.in, commandRegistry);
    consoleManager.start();
}
```

### Example with dialog command

The example below describes how add a dialog command 
(talk):

```
talk
    What's your name?
Bob
    What's your age?
10
    You are Bob and your age is 10        
```

In other words, this command opens dialog and returns console only after
dialog is finished.

#### Step 1: Describe command options

No options. Empty class.
```
public class SimpleDialogOptions implements CommandOptions {
}
```

#### Step 2: Implement command handler

```
public class SimpleDialogCommandHandler implements DialogCommandHandler<SimpleDialogCommandOptions> {

    @Override
    public Class<SimpleDialogCommandOptions> getOptionsClass() {
        return SimpleDialogCommandOptions.class;
    }

    @Override
    public boolean handle(final SimpleDialogCommandOptions commandOptions,
                          final PrintStream printStream,
                          final BufferedReader bufferedReader) {
        printStream.println("What is your name?");
        final String name = readLineNotSafe(bufferedReader);
        printStream.println("What is your age?");
        final String age = readLineNotSafe(bufferedReader);
        printStream.println("You are " + name + " and your age is " + age);
        return true;
    }

    private String readLineNotSafe(BufferedReader reader) {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```

#### Step 3: Register it

```
commandRegistry.registerCommand("talk", new SimpleDialogCommandHandler());
```

### Important notes
* Commands are case sensitive
* Many lines commands are not supported
* Many commands in one line are not supported

### How to import

#### Maven

```
<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
</repositories>
<dependency>
	    <groupId>com.github.kosbr</groupId>
	    <artifactId>cli-helper</artifactId>
	    <version>2.1</version>
</dependency>
	
```

#### Gradle
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
dependencies {
        compile 'com.github.kosbr:cli-helper:2.0'
}

```