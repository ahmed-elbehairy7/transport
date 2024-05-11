@echo off

javac gui/UserRegistrationGUI.java -d ../dist

if not errorlevel 1 java -cp ../dist gui.UserRegistrationGUI