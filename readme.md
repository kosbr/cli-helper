## Java Command Line Interface Helper

This library helps to create a primitive command line interface 
using java. 

![status](https://travis-ci.org/kosbr/cli-helper.svg?branch=master)

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

### Important notes
* Commands are case sensitive
* Many lines commands are not supported
* Many commands in one line are not supported