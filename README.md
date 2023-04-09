# FileServer #

### Introduction ###

FileServer is a file storage service. There are no user accounts or authentication — the files are
accessible to anybody. Author: Emma Belinda Semilarski

### Technologies used ###
* Java 17
* Spring Boot
* Hibernate H2 database
* Postman

### Features ###

* Creating a directory
* Creating and uploading a file
* Listing directory contents
* Downloading a file
* Removing a file
* Removing a directory

### Installation guide ###

* Clone this repository [here](https://emmasemilarski@bitbucket.org/emmasemilarski/fileserver.git)
* Run the app with gradle bootRun command

# REST API #

The REST API is described below. 

## Create a directory ##

**Request** `POST directories/create`
```
http://localhost:8080/directories/create?directoryName=Animals&locationDirectoryId=1
```

**Response**
```
Status: 200 OK
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sun, 09 Apr 2023 16:01:09 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
    "id": 2,
    "directoryName": "Animals",
    "creationDate": "2023-04-09",
    "locationDirId": 1
}
```


## Create and upload a file ##

**Request** `POST files/upload`
```
http://localhost:8080/files/upload
```

**Response**
```
Status: 200 OK
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sun, 09 Apr 2023 16:12:15 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
    "id": 1,
    "filename": "HelloWorld.txt",
    "creationDate": "2023-04-09",
    "lastAccessDate": "2023-04-09",
    "size": 9,
    "directoryId": 1
}
```

Disclaimer: A simple text file "HelloWorld.txt" was created and used to test this out, with content 'Hi there!'.

## List directory contents ##

**Request** `GET directories/content/{id}`
```
http://localhost:8080/directories/content/1
```

**Response**
```
Status: 200 OK
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sun, 09 Apr 2023 16:15:03 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
    [
    {
        "id": 2,
        "directoryName": "Animals",
        "creationDate": "2023-04-09",
        "locationDirId": 1
    },
    {
        "id": 1,
        "filename": "HelloWorld.txt",
        "creationDate": "2023-04-09",
        "lastAccessDate": "2023-04-09",
        "size": 9,
        "directoryId": 1
    }
]
}
```


## Downloading a file ##

**Request** `GET files/download/{id}`
```
http://localhost:8080/files/download/1
```

**Response**
```
Status: 200 OK
Accept-Ranges: bytes
Content-Type: application/json
Content-Length: 9
Date: Sun, 09 Apr 2023 16:18:44 GMT
Keep-Alive: timeout=60
Connection: keep-alive

Hi there!
```


## Removing a file ##

**Request** `DELETE files/delete/{id}`
```
http://localhost:8080/files/delete/1
```

**Response**
```
Status: 200 OK
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sun, 09 Apr 2023 16:21:41 GMT
Keep-Alive: timeout=60
Connection: keep-alive

true
```


## Removing a directory ##

**Request** `DELETE directories/delete/{id}`
```
http://localhost:8080/directories/delete/2
```

**Response**
```
Status: 200 OK
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sun, 09 Apr 2023 16:23:02 GMT
Keep-Alive: timeout=60
Connection: keep-alive

true
```


## Getting all directories ##

**Request** `GET directories`
```
http://localhost:8080/directories
```

**Response**
```
Status: 200 OK
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sun, 09 Apr 2023 16:24:25 GMT
Keep-Alive: timeout=60
Connection: keep-alive

[
    {
        "id": 1,
        "directoryName": "base",
        "creationDate": "2023-04-09",
        "locationDirId": null
    },
    {
        "id": 3,
        "directoryName": "Animals",
        "creationDate": "2023-04-09",
        "locationDirId": 1
    }
]
```


## Getting all files ##

**Request** `GET files`
```
http://localhost:8080/files/2
```

**Response**
```
Status: 200 OK
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sun, 09 Apr 2023 16:26:43 GMT
Keep-Alive: timeout=60
Connection: keep-alive

[
    {
        "id": 2,
        "filename": "HelloWorld.txt",
        "creationDate": "2023-04-09",
        "lastAccessDate": "2023-04-09",
        "size": 9,
        "directoryId": 1
    }
]
```

## Getting a file by id ##

**Request** `GET files/{id}`
```
http://localhost:8080/files
```

**Response**
```
Status: 200 OK
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sun, 09 Apr 2023 16:27:48 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
    "id": 2,
    "filename": "HelloWorld.txt",
    "creationDate": "2023-04-09",
    "lastAccessDate": "2023-04-09",
    "size": 9,
    "directoryId": 1
}
```