cd ..\

cd deepthought
call mvn clean install
copy target\deepthought-0.0.1-SNAPSHOT.jar ..\deploy\backend\deepthought.jar /Y
