@echo off
echo Поиск и завершение всех процессов Gradle BootRun...

REM Убиваем все окна gradlew bootRun (если запущены)
tasklist /FI "IMAGENAME eq java.exe" /FO LIST | findstr "PID" > tmp_pids.txt

for /f "tokens=2 delims=:" %%a in ('findstr "PID" tmp_pids.txt') do (
    set "PID=%%a"
    setlocal enabledelayedexpansion
    taskkill /PID !PID! /F >nul 2>&1
    endlocal
)

del tmp_pids.txt

echo Все процессы Spring Boot завершены.
pause
