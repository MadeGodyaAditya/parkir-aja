# REST API Parkiraja Documentation

This is Parkiraja application providing a REST
API.


## Install

    1. git clone to local repository
    2. create table using spring.jpa.hibernate.ddl-auto=update in application.properties
    3. match @id sequence in package com.mandiri.entity with sequence in table

## Run the app

    run in port 8080

## Run the tests

    open postman and run GET, POST, PUT, DELETE 
    using one of API bellow

# REST API

The REST API to the parkiraja app is described below.

## 1. Parking Owner
## Get all parking owner

### Request

`GET /parking_owner`

### Response
Display all Parking Owner in json format

    [{"id":"40288182800c21bf01800c28452c0000","idCard":"3201399312321","name":"Made G","address":"Cibinong Bogor","phoneNumber":"08239481984"},
    {"id":"40288182800c21bf01800c293d220001","idCard":"320182139115","name":"Bambang S","address":"Depok","phoneNumber":"0219312321"}],"pageNumber":0,"pageSize":5,"totalPages":1,"totalElement":2}

## Get Parking Owner using Parameter
Store accept 4 search parameter both individually or collectivelly used in a search
    
    ?idCard=xxxx
    ?name=xxxx
    ?address=xxxx
    ?phoneNumber=xxxx

It also accepts page and size

    ?page=1&size=5


### Request

`GET /parking_owner?name=ma&address=cib&phoneNumber=08`

### Response
Display all store in json format

    {"content":[{"id":"40288182800c21bf01800c28452c0000","idCard":"3201399312321","name":"Made G","address":"Cibinong Bogor","phoneNumber":"08239481984"}],"pageNumber":0,"pageSize":5,"totalPages":1,"totalElement":1}

## Register Parking Owner

### Request

`POST /parking_owner`

RequestBody as JSON:

    {
    "idCard":"320182139115",
    "name":"Bambang S",
    "address":"Depok",
    "phoneNumber":"0219312321"
    }

### Response

    HTTP/1.1 200 OK
    Status: 200 OK
    Connection: close
    Content-Type: application/json

    {
    "id":"40288182800c31b201800c31f5940000",
    "idCard":"320182139115",
    "name":"Bambang S",
    "address":"Depok",
    "phoneNumber":"0219312321"
    }

## Change Parking Owner Data

### Request

`PUT /parking_owner`

    {
    "id":"40288182800c31b201800c31f5940000",
    "idCard":"32014393481",
    "name":"Joko W",
    "address":"Cibinong",
    "phoneNumber":"21392131"
    }

### Response

    HTTP/1.1 200 OK
    Status: 200 OK
    Connection: close
    Content-Type: application/json

    {
    "id":"40288182800c31b201800c31f5940000",
    "idCard":"32014393481",
    "name":"Joko W",
    "address":"Cibinong",
    "phoneNumber":"21392131"
    }

## Delete Parking Owner

### Request

`DELETE /parking_owner?id=40288182800c31b201800c31f5940000`


### Response

    HTTP/1.1 200 OK
    Status: 200 OK
    Connection: close

## 2. Parking Lot
## Get all Parking Lot

### Request

`GET /parking_lot`

### Response
Display all product in json format

    [{"id":"8a63394f7ff95aae017ff95ac61a0000","productName":"Biskuit Mari","description":"Biskuit","price":20000,"stock":2,"storeId":"8a63394f7ff7de6b017ff7dec0b80000"},
    {"id":"402881837ffa1e97017ffa1f66350000","productName":"Biskuit Roma Kelapa","description":"Biskuit","price":17000,"stock":6,"storeId":"8a63394f7ff7de6b017ff7df8fcb0001"},
    {"id":"402881837ffa1e97017ffa203d140001","productName":"Sari Roti","description":"Roti","price":3000,"stock":16,"storeId":"8a63394f7ff7de6b017ff7df8fcb0001"},
    {"id":"402881837ffa1e97017ffa20c8ac0002","productName":"Roti Sobek Rasa Kopyor","description":"Roti","price":2500,"stock":21,"storeId":"8a63394f7ff7de6b017ff7df8fcb0001"}]

## Get Product using Parameter
Product accept 3 search parameter both individually or collectivelly used in a search

    ?productName=xxxx
    ?priceMin=2000
    ?priceMax=7000

It also accepts page and size

    ?page=1&size=5

priceMin is the lower limit of the prices, while priceMax is the upper limit of prices. For example:

    ?priceMin=2000

if priceMax is null, it will show all products with price above 2000
### Request

`GET /parking_lot?productName=ro&priceMin=3000&priceMax=5000`

### Response
Display all products in json format

    {
    "content": [
        {
            "id": "402881837ffa1e97017ffa203d140001",
            "productName": "Sari Roti",
            "description": "Roti",
            "price": 3000,
            "stock": 16,
            "storeId": "8a63394f7ff7de6b017ff7df8fcb0001"
        }
    ],
    "pageNumber": 0,
    "pageSize": 5,
    "totalPages": 1,
    "totalElement": 1
    }

## Register New Parking Lot

### Request

`POST /parking_lot`

RequestBody as JSON:

    {
    "ownerId":"40288182800c21bf01800c28452c0000",
    "name":"Parkir Kosong Dikit",
    "address":"Sudirman",
    "size":"15.25",
    "capacity":"50"
    }

### Response

    HTTP/1.1 200 OK
    Status: 200 OK
    Connection: close
    Content-Type: application/json

    {
    "id": "40288182800cc09701800cc68db60002",
    "ownerId": "40288182800c21bf01800c28452c0000",
    "name": "Parkir Kosong Dikit",
    "address": "Sudirman",
    "size": 15.25,
    "capacity": 50
    }

## Change Parking Lot Data

### Request

`PUT /parking_lot`

    {
    "id": "40288182800cc09701800ccb6c510003",
    "ownerId":"40288182800c21bf01800c28452c0000",
    "name":"Update parkiran",
    "address":"Sudirman",
    "size":"10.00",
    "capacity":"55"
    }

### Response

    HTTP/1.1 200 OK
    Status: 200 OK
    Connection: close
    Content-Type: application/json

    {
    "id": "40288182800cc09701800ccb6c510003",
    "ownerId":"40288182800c21bf01800c28452c0000",
    "name":"Update parkiran",
    "address":"Sudirman",
    "size":"10.00",
    "capacity":"55"
    }

## Delete Parking Lot Data

### Request

`DELETE /product?id=402881837ffa2134017ffa227bd70000`


### Response

    HTTP/1.1 200 OK
    Status: 200 OK
    Connection: close

## 3. Customer
## Get all Customer

### Request

`GET /customer`

### Response
Display all customer in json format

    {"content":[{"id":"8a6333877ffe5986017ffe599a480000","fullName":"I Made Godya Aditya","username":"godyaaditya123","birthDate":"1996-04-11T17:00:00.000+00:00","phoneNumber":"081387308472","email":"godyaaditya@gmail.com","gender":"M"},
    {"id":"402881837ffed5f8017ffed622c00000","fullName":"Bambang Sibambang","username":"bambang_ok","birthDate":"1987-01-16T17:00:00.000+00:00","phoneNumber":"0812920219123","email":"sibambangbambang@gmail.com","gender":"M"},
    {"id":"402881837ffeeb98017ffeec61a00000","fullName":"La Elson","username":"elson_spasial","birthDate":"1962-11-18T17:00:00.000+00:00","phoneNumber":"0888030101256","email":"la_elson123@gmail.com","gender":"M"},
    {"id":"402881837ffeeb98017ffeed581b0001","fullName":"Febiola Ola","username":"febiola_kesekretariatan","birthDate":"2001-05-22T17:00:00.000+00:00","phoneNumber":"081393111517","email":"ola_ola921@gmail.com","gender":"F"},
    {"id":"402881837ffeeb98017ffeee53900002","fullName":"Rizki Utami","username":"rbu_123_ok","birthDate":"1987-10-22T17:00:00.000+00:00","phoneNumber":"088710239113","email":"rbu_kesekretarian_ddp@gmail.com","gender":"F"}],
    "pageNumber":0,"pageSize":5,"totalPages":1,"totalElement":5}

## Get Customer using Parameter
Customer accept 7 search parameter both individually or collectively used in a search

    ?fullName=xxxx
    ?userName=xxxx
    ?birthDateStart=1800-01-01
    ?birthDateEnd=2022-12-12
    ?phoneNumber=0812
    ?email=xxxxx
    ?gender=M/F

It also accepts page and size

    ?page=1&size=5

### Request

`localhost:8080/customer?fullName=made&gender=M`

### Response
Display all customer in json format

    {
    "content": [
        {
            "id": "402881837ffa1e97017ffa203d140001",
            "productName": "Sari Roti",
            "description": "Roti",
            "price": 3000,
            "stock": 16,
            "storeId": "8a63394f7ff7de6b017ff7df8fcb0001"
        }
    ],
    "pageNumber": 0,
    "pageSize": 5,
    "totalPages": 1,
    "totalElement": 1
    }

## Register New Customer

### Request

`POST /customer`

RequestBody as JSON:

    {
    "fullName":"Bambang Sibambang",
    "username":"bambang_ok",
    "birthDate":"1987-01-17",
    "phoneNumber": "0812920219123",
    "email":"sibambangbambang@gmail.com",
    "gender":"M"
    }

### Response

    HTTP/1.1 200 OK
    Status: 200 OK
    Connection: close
    Content-Type: application/json

    {
    "id": "402881837ffed5f8017ffed622c00000",
    "fullName": "Bambang Sibambang",
    "username": "bambang_ok",
    "birthDate": "1987-01-17T00:00:00.000+00:00",
    "phoneNumber": "0812920219123",
    "email": "sibambangbambang@gmail.com",
    "gender": "M"
    }

## Change Customer Data

### Request

`PUT /customer`

    {
    "id": "402881837ffed5f8017ffed622c00000",
    "fullName": "Bambang Sibambang",
    "username": "bambang_ok",
    "birthDate": "1987-01-17T00:00:00.000+00:00",
    "phoneNumber": "0812920219123",
    "email": "sibambangbambang@gmail.com",
    "gender": "M"
    }

### Response

    HTTP/1.1 200 OK
    Status: 200 OK
    Connection: close
    Content-Type: application/json

    {
    "id": "402881837ffed5f8017ffed622c00000",
    "fullName": "Bambang Sibambang",
    "username": "bambang_ok",
    "birthDate": "1987-01-17T00:00:00.000+00:00",
    "phoneNumber": "0812920219123",
    "email": "sibambangbambang@gmail.com",
    "gender": "M"
    }

## Delete Customer Data

### Request

`DELETE /customer?id=402881837ffed5f8017ffed622c00000`


### Response

    HTTP/1.1 200 OK
    Status: 200 OK
    Connection: close

## Update or Delete a non-existent Data

### Request

`PUT /store`

    {
    "id":"123456"
    "storeName":"TokoPekora",
    "storeAddress":"Depok Baru",
    "phoneNumber":"0811203990189",
    "siupNumber":"167/DB/1985",
    "npwp":"239823112"
    }

### Response

    HTTP/1.1 404 Not Found
    Status: 404 Not Found
    Connection: close
    Content-Type: application/json

    {"status":404,"reason":"id Not Found"}




