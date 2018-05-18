@echo off
call gradlew build
set /p version="Enter Version: "
xcopy build\libs\modid-1.0.jar releases\ /Y /I
ren releases\modid-1.0.jar minersdream-v%version%-mc1.12.jar
rm -R build\classes
rm -R build\dependency-cache
rm -R build\libs
rm -R build\resources
rm -R build\retromapping
rm -R build\sources
rm -R build\taskLogs
pause