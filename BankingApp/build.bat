@echo off
cd /d "%~dp0"

if not exist "out" mkdir out

javac -d out -cp "..\lib\juanparser.jar" *.java

if errorlevel 1 (
    echo.
    echo Compile failed.
    exit /b 1
)

java -cp "..\lib\juanparser.jar;out" program

if errorlevel 1 (
    echo Program exited with an error.
)
