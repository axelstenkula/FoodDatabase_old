::Remove old files at Tomcat dir
set installDir="C:\FoodDatabase\dev"
del /Q /F %installDir%\webapps\ROOT\*.*
del /Q /F %installDir%\webapps\ROOT.war

set RUN_DIR=C:\Users\Jenny\SkyDrive\DokumentAxel\Matdatabas\git\java-archive
set FOOD_DIR=C:\Users\Jenny\SkyDrive\DokumentAxel\Matdatabas\git\foodmanager

::Build GUI project
cd %RUN_DIR%
call mvn clean package
ECHO Packaging finished, starting to move files and folders..

::Copy newly built files
copy %RUN_DIR%\target\java-archive-0.0.1-SNAPSHOT.war %installDir%\webapps\ROOT.war

::Startup web service
set CATALINA_BASE="C:\FoodDatabase\dev"
%CATALINA_HOME%\bin\startup.bat