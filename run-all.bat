@echo off
cd /d %~dp0

start "client-service" cmd /k ".\gradlew :client-service:bootRun"
start "menu-service" cmd /k ".\gradlew :menu-service:bootRun"
start "order-service" cmd /k ".\gradlew :order-service:bootRun"
start "kitchen-service" cmd /k ".\gradlew :kitchen-service:bootRun"

echo Все сервисы запускаются в отдельных окнах...
pause
