# Schedule Console

### Package it
mvn clean package -DskipTests

### Run it
nohup java -Xms128m -Xmx128m -jar schedule-console-0.1.0-SNAPSHOT.jar >console.log 2>&1 &
