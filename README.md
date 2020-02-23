zadanie-sylwia
### Microservices with mysql DB
1.  Dowload app;
2. Build -jar (assume on Windows) `mvn '-Dmaven.test.skip=true package`
3. Run Docker Compose `docker-compose up` This will start up the application
4. See how it works: use *Postman* Go to [http://localhost:8301/credit/createcredit](http://localhost:8301/credit/createcredit) Create any json in this format  (use Post)

```json
 {
    	"creditName":"for a car credit",
    	"customer":{
    		"firstName":"Ivan",
        	"surname":"McCredit",
        	"pesel":"123456789"
    	},
    	"product":{
    		"value":100,
    		"productName":"car credit"
    	}
    }
```
This  will create object and send data to 3 chemas.
To vbring back all credits use *Postman* with get method [http://localhost:8301/credit/getcredits](http://localhost:8301/credit/getcredits)
