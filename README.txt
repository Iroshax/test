SimpleCrudApi

First Add departments

POST - http://localhost:8082/SimpleCrudApi/addDepartment

Sample payload

{
"departmentId":1,
"departmentName":"Finance"
}

-----------------------------------------------------------------------------------------

Employee CRUD

GET - http://localhost:8082/SimpleCrudApi/employee/{employeeId}
POST - http://localhost:8082/SimpleCrudApi/employee/
PUT - http://localhost:8082/SimpleCrudApi/employee/
DELETE - http://localhost:8082/SimpleCrudApi/employee/{employeeId}

Sample payload

{
"employeeId":"",
"firstName":"",
"lastName":"",
"departmentId":""
}



