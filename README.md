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

## 0 . FIND BY ID

parkiraja support find By ID in all of URI, to find the data using ID:

`GET /any_api/{id}`

for example:

    /parking_owner/xf0929299292

or

    /parking_lot/29839120388288

etc

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
Display all parking lot in json format

    {"content":
    [{"id":"40288182800cc09701800cc4dc8d0000","ownerId":"40288182800c21bf01800c28452c0000","name":"Parkir Jaya Abadi","address":"Senayan","size":20.25,"capacity":100,"category":"Car"},
    {"id":"40288182800cc09701800cc615cf0001","ownerId":"40288182800c21bf01800c28452c0000","name":"Parkir Penuh Terus","address":"Senayan","size":20.25,"capacity":100,"category":"Bike"},
    {"id":"40288182800cc09701800cc68db60002","ownerId":"40288182800c21bf01800c28452c0000","name":"Parkir Kosong Dikit","address":"Sudirman","size":15.25,"capacity":50,"category":"All"}],
    "pageNumber":0,"pageSize":5,"totalPages":1,"totalElement":3}

## Get Parking Lot using Parameter
Parking Lot accept 5 search parameter both individually or collectivelly used in a search

    ?name=xxxx
    ?address=xxxx
    ?capacityBottomLimit=10
    ?capacityTopLimit=200
    ?category=Car/Bike/All

It also accepts page and size

    ?page=1&size=5


if both capacityBottomLimit and capacityTopLimit are used, it will show all products with capacity between lower and top limit number
### Request

`GET /parking_lot?name=ja&address=se&capacityBottomLimit=20&capacityTopLimit=200&category=Car`

### Response
Display all products in json format

    {
    "content": [
        {
            "id": "40288182800cc09701800cc4dc8d0000",
            "ownerId": "40288182800c21bf01800c28452c0000",
            "name": "Parkir Jaya Abadi",
            "address": "Senayan",
            "size": 20.25,
            "capacity": 100,
            "category": "Car"
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
    "capacity":"50",
    "category":"Car"
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
    "capacity": 50,
    "category":"Car"
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
    "capacity":"55",
    "category":"Car"
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
    "capacity":"55",
    "category":"Car"
    }

## Delete Parking Lot Data

### Request

`DELETE /product?id=402881837ffa2134017ffa227bd70000`


### Response

    HTTP/1.1 200 OK
    Status: 200 OK
    Connection: close

## 3. Fee

## Register New Fee

### Request

`POST /fee`

RequestBody as JSON:

    {
    "parkingLotId":"40288182800cc09701800cc4dc8d0000",
    "category":"Car",
    "fee":"5000"
    }

### Response

    HTTP/1.1 200 OK
    Status: 200 OK
    Connection: close
    Content-Type: application/json

    {
    "id": "40288182800d03f901800d040d020000",
    "parkingLotId": "40288182800cc09701800cc4dc8d0000",
    "category": "Car",
    "fee": 5000
    }

## Change Fee Data

### Request

`PUT /fee`

    {
    "id": "40288182800d03f901800d0670750003",
    "parkingLotId": "40288182800cc09701800cc68db60002",
    "category": "Car",
    "fee": 5500
    }

### Response

    HTTP/1.1 200 OK
    Status: 200 OK
    Connection: close
    Content-Type: application/json

    {
    "id": "40288182800d03f901800d0670750003",
    "parkingLotId": "40288182800cc09701800cc68db60002",
    "category": "Car",
    "fee": 5500
    }

## Delete Fee Data

### Request

`DELETE /fee?id=40288182800d03f901800d0670750003`


### Response

    HTTP/1.1 200 OK
    Status: 200 OK
    Connection: close

## 4. Parking
## Get all Parking

### Request

`GET /parking`

### Response
Display all parking in json format

    {"content":
    [{"id":"40288182800cc09701800cc4dc8d0000","ownerId":"40288182800c21bf01800c28452c0000","name":"Parkir Jaya Abadi","address":"Senayan","size":20.25,"capacity":100,"category":"Car"},
    {"id":"40288182800cc09701800cc615cf0001","ownerId":"40288182800c21bf01800c28452c0000","name":"Parkir Penuh Terus","address":"Senayan","size":20.25,"capacity":100,"category":"Bike"},
    {"id":"40288182800cc09701800cc68db60002","ownerId":"40288182800c21bf01800c28452c0000","name":"Parkir Kosong Dikit","address":"Sudirman","size":15.25,"capacity":50,"category":"All"}],
    "pageNumber":0,"pageSize":5,"totalPages":1,"totalElement":3}

## Get Parking using Parameter
Parking accept 5 search parameter both individually or collectivelly used in a search

    ?name=xxxx
    ?address=xxxx
    ?capacityBottomLimit=10
    ?capacityTopLimit=200
    ?category=Car/Bike/All

It also accepts page and size

    ?page=1&size=5


if both capacityBottomLimit and capacityTopLimit are used, it will show all products with capacity between lower and top limit number
### Request

`GET /parking?name=ja&address=se&capacityBottomLimit=20&capacityTopLimit=200&category=Car`

### Response
Display all parking in json format

    {
    "content": [
        {
            "id": "40288182800cc09701800cc4dc8d0000",
            "ownerId": "40288182800c21bf01800c28452c0000",
            "name": "Parkir Jaya Abadi",
            "address": "Senayan",
            "size": 20.25,
            "capacity": 100,
            "category": "Car"
        }
    ],
    "pageNumber": 0,
    "pageSize": 5,
    "totalPages": 1,
    "totalElement": 1
    }

## Register New Parking

### Request

`POST /parking`

RequestBody as JSON:

    {
    "parkingLotId":"40288182800cc09701800cc615cf0001",
    "licensePlate":"F 3212 JL",
    "type":"Bike"
    }

### Response

    HTTP/1.1 200 OK
    Status: 200 OK
    Connection: close
    Content-Type: application/json

    {
    "id": "40288182800d59aa01800d5a7e130000",
    "parkingLotId": "40288182800cc09701800cc615cf0001",
    "licensePlate": "F 3212 JL",
    "type": "Bike",
    "entrance": "2022-04-09T08:04:35.945+00:00"
    }

## Delete Parking Lot Data

### Request

`DELETE /product?id=402881837ffa2134017ffa227bd70000`


### Response

    HTTP/1.1 200 OK
    Status: 200 OK
    Connection: close


## Update or Delete a non-existent Data

### Request

`PUT /parking_lot`

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




