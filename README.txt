SimpleCrudApi

First Add departments

POST - http://localhost:{portNo}/SimpleCrudApi/addDepartment

Sample payload

{
"departmentId":1,
"departmentName":"Finance"
}

-----------------------------------------------------------------------------------------

Employee CRUD

GET - http://localhost:{portNo}/SimpleCrudApi/employee/{employeeId}
POST - http://localhost:{portNo}/SimpleCrudApi/employee/
PUT - http://localhost:{portNo}/SimpleCrudApi/employee/
DELETE - http://localhost:{portNo}/SimpleCrudApi/employee/{employeeId}

Sample payload

{
"employeeId":1,
"firstName":"Jone",
"lastName":"Jones",
"departmentId":1
}



