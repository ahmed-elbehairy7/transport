# Transportation Company System Project (Hard Mode)

This project is like the hardmode for another college project I made [Transport-EasyMode-](https://github.com/ahmed-elbehairy7/transport-EasyMode-), although the first one got the full marks, but there's no problem with improving the project.

## Running the program

### Prerequists

First of all you need the java tool-kit to compile the code and so on, make sure that you have javac and java installed by typing the following command anywere in the cmd

    javac --version

and

    java --version

if they throws an error or not recognized, please go ahead and install them

### Compiling

since I needed to compile and run them alot, I already had done some scripts for compiling and running,
to compile the code, just type compile to the cmd in the src directory or in the project root directory and it should work fine. Just like this!:

    compile

### Running

I had also made another script called transport, and if you want to go with the gui, just add the argument -g or --gui after it like the following examples

    transport
    transport --gui
    transport -g

### Compiling then running

Also there was sometimes when I needed to compile then run the applicatoin directory, in order to do that, just type run in the cmd

    run
    run --gui
    run -g

as you can see, the arguments of gui applies also to this method

## Changes

Here are the changes made after the first version of the project

### Gui and Cli

First, there's no more gui and cli! There's only flows! while developing the project for the first time I noticed that there were a lot of dublicated code like

    createButton(1, "Text of button one);
    createButton(2, "Text of button two");

That's when I started to make a function called generateUi that takes a list of texts and functions as input and then generate the buttons in a for loop.

Then also while building the gui, I noticed that I was developing the same functions of the cli with approximatly no changes at all! The only difference was that cli output function was System.out.println while the gui's one was outputArea.setText, and same goes for input functions.

Then I made flows, that take actions as arguments generate the gui or cli text and functionality based on those actions, each action have inputFunc and outputFunc as arguments so whatever it was cli or gui, it will end with the same results. So then what's happening is first initializing a flow, then this flow recognizes it's sets of actions and print them to the terminal or generate buttons for them. and if one is selected it execute the function startFlow of the action with the inputFunc and outputFunc as parameters.

### Savable

For savables, I thing I should change the stupid idea of passing like 8 arguments to a function. I should just pass the class and then gets whatever I need from the class.

## Unfinished

By the time of writing this, the savable change was not made yet to be honest, also the personal info action, since it depends on the savable change I want to make. Also, there's a problem with the login and registeration in the gui, it seems like the way I choose to handle the situation didn't match up well with the gui.
