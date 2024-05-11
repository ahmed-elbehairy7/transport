@echo off

javac Main.java -d ../out

if not errorlevel 1 java -cp ../out Main