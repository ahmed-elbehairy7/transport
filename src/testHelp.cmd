@echo off

javac main/Main.java -d ../dist

if not errorlevel 1 java -cp ../dist Main -h