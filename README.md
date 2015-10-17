# Schedule Console

### Package it
mvn clean package -DskipTests

### Run it
nohup java -Xms128m -Xmx128m -jar schedule-console-0.1.0-SNAPSHOT.jar >console.log 2>&1 &

### APIs

Host: http://localhost:8080/

```
    POST    /v1/schedules
            Content-Type: application/json
            REQUEST:
            {
            	"name": "test2",
            	"cron": "0/2 * * * * ?",
            	"command": "ll"
            }
            RESPONSE:
            {
                "id" : 2
            }
```