README

**There has been some issues with docker installation on my computer, so I’ll proceed without docker for now**

**How to run the application**
1. Checkout the project, and nuild the package:
   run mvn package
2. And then execute the jar:
   java -jar target/hr-0.0.1-SNAPSHOT.jar

**Verification**
We’ll be using POSTMAN to test passing data in the application
To verify the data is being persisted, we can view the stored data in:
http://localhost:8080/h2-console

**For testing the endpoints**
*Uploading file
[METHOD: POST] http://localhost:8080/users/upload?file
form-data:
file:	sample_file.csv
*This is the sample csv data provided in the problem

	*Fetching List
	[METHOD: GET] http://localhost:8080/users?min-salary=1000&max-salary=20000&limit=2&offset=1

	Fetching object by id
	[METHOD: GET] http://localhost:8080/users?id=e0001

	Update object by id
	[METHOD: PUT] http://localhost:8080/users?id=e0001
	raw: 
	{
    		"login": "mary_poppins",
    		"name": "Mary Poppins",
    		"salary": 3000.00,
    		"startDate": "2020-12-15"
	}

	Delete object by id
	[METHOD: PUT] http://localhost:8080/users?id=e0001


**Suggestions for the System**
*When uploading the file, it’s always all-or-nothing and normally it just returns when one error occurred
- We can customise the processing by going through all of the data and compiling the errors back to the user.
In that case, they won’t need to fix the problems in their data one at a time
* Having a generic and incremental id is much easier to maintain, rather than using the employee_id.
* We can put the constants (ex. error message, default values, etc) in property files for easier management and update
* Adding unit and integration tests for better code quality
	

