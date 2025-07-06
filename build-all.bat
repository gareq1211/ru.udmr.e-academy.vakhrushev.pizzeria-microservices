@echo off
echo --- Building JARs ---

cd menu-service
call gradlew.bat clean build
cd ..

cd client-service
call gradlew.bat clean build
cd ..

cd kitchen-service
call gradlew.bat clean build
cd ..

echo --- Building Docker images ---
docker-compose build

echo --- Running containers ---
docker-compose up
