# SafetyNet Alerts

Alerts is an API REST which expose data from a json file to emergency services.

## Installation

This application was developed with __Java openJDK 11.0.14__ and __Maven 3.8.3__

If you need to have a custom json file, it must be registered at the location ___src/main/resources___ and named __data.json__

You can compile the application in your prompt, from the project folder : 

```shell
mvn clean install
```

The jar file is generated in the folder ___target___ at the root of project.

It recommended copying it in a working folder and renamed it in alerts.jar for example.

You can run the application with the command : 

```shell
java -jar alerts.jar
```

Or you can use Docker.

To use docker, create a file named ___Dockerfile___ in your working directory and copy this content :

```dockerfile
FROM openjdk:11
WORKDIR /
COPY alerts.jar alerts.jar
EXPOSE 8080
CMD java -jar alerts.jar

```

Pay attention to use your own java file name instead of ___alerts.jar___

You can build the image with :

```shell
docker build -t alerts:v1.0 .
```

And run the application in a container like that : 

```shell
docker run -d -p 8080:8080 --name alerts alerts
```

## Usage

The application have an embedded Tomcat and listen to the port 8080.

### Fire stations

Fire stations endpoints and URLs API.

#### POST /firestation

Create a firestation.

 - Request boby : application/json
 - Example value :

```json
{
  "address": "my new address",
  "station": 50
}
```
- Responses

| code | Description                               |
|------|-------------------------------------------|
 | 201  | Created - The fire station was created    |
 | 409  | Conflict - The fire station already exist |
 
<br>

#### PUT /firestation

Update a fire station

 - Request body : application/json
 - Example value : 

```json
{
  "address": "1509 Culver St",
  "station": 10
}
```
 - Responses

| code | Description                           |
|------|---------------------------------------|
| 200  | Ok - The fire station was updated     |
| 409  | Conflict - The fire station not exist |
 

<br>

#### DELETE /firestation

Delete a fire station

 * Parameter :
   * address : ___string___
   * stationNumber : ___integer___
 * Examples values :

```
/firestion?address=644 Gershwin Cir&stationNumber=1
```

```
/firestation?address=644 Gershwin Cir
```

```
/firestation?stationNumber=1
```
 - Responses

| code | Description                           |
|------|---------------------------------------|
| 200  | Ok - The fire station was deleted     |
| 409  | Conflict - The fire station not exist |

<br>

#### GET /firestations

Get all fire stations.

Return the list of all fire stations

 - No parameters
 - Example value :

```
/firestations
```
 - Response

| code | Description |
|------|-------|
| 200  | Ok    |

<br>

<details>
<summary>Response body</summary>

```json
[
    {
        "address": "1509 Culver St",
        "station": 3
    },
    {
        "address": "29 15th St",
        "station": 2
    },
    {
        "address": "834 Binoc Ave",
        "station": 3
    },
    {
        "address": "644 Gershwin Cir",
        "station": 1
    },
    {
        "address": "748 Townings Dr",
        "station": 3
    },
    {
        "address": "112 Steppes Pl",
        "station": 3
    },
    {
        "address": "489 Manchester St",
        "station": 4
    },
    {
        "address": "892 Downing Ct",
        "station": 2
    },
    {
        "address": "908 73rd St",
        "station": 1
    },
    {
        "address": "112 Steppes Pl",
        "station": 4
    },
    {
        "address": "947 E. Rose Dr",
        "station": 1
    },
    {
        "address": "748 Townings Dr",
        "station": 3
    },
    {
        "address": "951 LoneTree Rd",
        "station": 2
    }
]
```
</details>

<br>

#### GET /firesation?stationNumber=<station_number>

Get persons and the number of adults and children covered by the corresponding fire station.

Return firstName, lastName, address, phone and the number of adults and children covered by the given station.

* Parameter :
  * stationNumber : ___integer___
* Example value :

```
/firestation?stationNumber=1
```

 - Response

| code   | Description  |
|--------|-------------|
| 200    | Ok     |
| 204    | No content|

<br>

<details>
<summary>Response boby</summary>

```json
{
  "personInfoList": [
    {
      "firstName": "Peter",
      "lastName": "Duncan",
      "address": "644 Gershwin Cir",
      "phone": "841-874-6512"
    },
    {
      "firstName": "Reginold",
      "lastName": "Walker",
      "address": "908 73rd St",
      "phone": "841-874-8547"
    },
    {
      "firstName": "Jamie",
      "lastName": "Peters",
      "address": "908 73rd St",
      "phone": "841-874-7462"
    },
    {
      "firstName": "Brian",
      "lastName": "Stelzer",
      "address": "947 E. Rose Dr",
      "phone": "841-874-7784"
    },
    {
      "firstName": "Shawna",
      "lastName": "Stelzer",
      "address": "947 E. Rose Dr",
      "phone": "841-874-7784"
    },
    {
      "firstName": "Kendrik",
      "lastName": "Stelzer",
      "address": "947 E. Rose Dr",
      "phone": "841-874-7784"
    }
  ],
  "nbAdult": 5,
  "nbMinor": 1
}
```
</details>

<br>

#### GET /phoneAlert?firestation=<station_number>

Get all phone number covered by given fire station.

Return a list of all phone number.

* Parameter :
  * firestation : ___integer___
* Example value : 

```
/childAlert?firestation=1
```

 - Response

| code   | Description  |
|--------|-------------|
| 200    | Ok     |
| 204    | No content|

<br>

<details>
<summary>Response body</summary>

```json
[
    "841-874-6512",
    "841-874-8547",
    "841-874-7462",
    "841-874-7784"
]
```
</details>

<br>

#### GET /flood/stations?stations=< a list of station_numbers>

Get list of homes covered by station.

Return firstName, lastName,phone, age, medications and allergies from each person groups by given station

* Parameter :
  * stations : ___integer___
* Example value :

```
/flood/stations?stations=1,3
```

 - Response

| code   | Description  |
|--------|-------------|
| 200    | Ok     |
| 204    | No content|

<br>

<details>
<summary>Response body</summary>

```json
[
  {
    "address": "644 Gershwin Cir",
    "personInfoList": [
      {
        "firstName": "Peter",
        "lastName": "Duncan",
        "age": 21,
        "phone": "841-874-6512",
        "medicament": [],
        "allergies": [
          "shellfish"
        ]
      },
      {
        "firstName": "Reginold",
        "lastName": "Walker",
        "age": 42,
        "phone": "841-874-8547",
        "medicament": [
          "thradox:700mg"
        ],
        "allergies": [
          "illisoxian"
        ]
      },
      {
        "firstName": "Jamie",
        "lastName": "Peters",
        "age": 40,
        "phone": "841-874-7462",
        "medicament": [],
        "allergies": []
      },
      {
        "firstName": "Brian",
        "lastName": "Stelzer",
        "age": 46,
        "phone": "841-874-7784",
        "medicament": [
          "ibupurin:200mg",
          "hydrapermazol:400mg"
        ],
        "allergies": [
          "nillacilan"
        ]
      },
      {
        "firstName": "Shawna",
        "lastName": "Stelzer",
        "age": 41,
        "phone": "841-874-7784",
        "medicament": [],
        "allergies": []
      },
      {
        "firstName": "Kendrik",
        "lastName": "Stelzer",
        "age": 8,
        "phone": "841-874-7784",
        "medicament": [
          "noxidian:100mg",
          "pharmacol:2500mg"
        ],
        "allergies": []
      }
    ]
  },
  {
    "address": "908 73rd St",
    "personInfoList": [
      {
        "firstName": "Peter",
        "lastName": "Duncan",
        "age": 21,
        "phone": "841-874-6512",
        "medicament": [],
        "allergies": [
          "shellfish"
        ]
      },
      {
        "firstName": "Reginold",
        "lastName": "Walker",
        "age": 42,
        "phone": "841-874-8547",
        "medicament": [
          "thradox:700mg"
        ],
        "allergies": [
          "illisoxian"
        ]
      },
      {
        "firstName": "Jamie",
        "lastName": "Peters",
        "age": 40,
        "phone": "841-874-7462",
        "medicament": [],
        "allergies": []
      },
      {
        "firstName": "Brian",
        "lastName": "Stelzer",
        "age": 46,
        "phone": "841-874-7784",
        "medicament": [
          "ibupurin:200mg",
          "hydrapermazol:400mg"
        ],
        "allergies": [
          "nillacilan"
        ]
      },
      {
        "firstName": "Shawna",
        "lastName": "Stelzer",
        "age": 41,
        "phone": "841-874-7784",
        "medicament": [],
        "allergies": []
      },
      {
        "firstName": "Kendrik",
        "lastName": "Stelzer",
        "age": 8,
        "phone": "841-874-7784",
        "medicament": [
          "noxidian:100mg",
          "pharmacol:2500mg"
        ],
        "allergies": []
      }
    ]
  },
  {
    "address": "947 E. Rose Dr",
    "personInfoList": [
      {
        "firstName": "Peter",
        "lastName": "Duncan",
        "age": 21,
        "phone": "841-874-6512",
        "medicament": [],
        "allergies": [
          "shellfish"
        ]
      },
      {
        "firstName": "Reginold",
        "lastName": "Walker",
        "age": 42,
        "phone": "841-874-8547",
        "medicament": [
          "thradox:700mg"
        ],
        "allergies": [
          "illisoxian"
        ]
      },
      {
        "firstName": "Jamie",
        "lastName": "Peters",
        "age": 40,
        "phone": "841-874-7462",
        "medicament": [],
        "allergies": []
      },
      {
        "firstName": "Brian",
        "lastName": "Stelzer",
        "age": 46,
        "phone": "841-874-7784",
        "medicament": [
          "ibupurin:200mg",
          "hydrapermazol:400mg"
        ],
        "allergies": [
          "nillacilan"
        ]
      },
      {
        "firstName": "Shawna",
        "lastName": "Stelzer",
        "age": 41,
        "phone": "841-874-7784",
        "medicament": [],
        "allergies": []
      },
      {
        "firstName": "Kendrik",
        "lastName": "Stelzer",
        "age": 8,
        "phone": "841-874-7784",
        "medicament": [
          "noxidian:100mg",
          "pharmacol:2500mg"
        ],
        "allergies": []
      }
    ]
  },
  {
    "address": "1509 Culver St",
    "personInfoList": [
      {
        "firstName": "John",
        "lastName": "Boyd",
        "age": 38,
        "phone": "841-874-6512",
        "medicament": [
          "aznol:350mg",
          "hydrapermazol:100mg"
        ],
        "allergies": [
          "nillacilan"
        ]
      },
      {
        "firstName": "Jacob",
        "lastName": "Boyd",
        "age": 33,
        "phone": "841-874-6513",
        "medicament": [
          "pharmacol:5000mg",
          "terazine:10mg",
          "noznazol:250mg"
        ],
        "allergies": []
      },
      {
        "firstName": "Tenley",
        "lastName": "Boyd",
        "age": 10,
        "phone": "841-874-6512",
        "medicament": [],
        "allergies": [
          "peanut"
        ]
      },
      {
        "firstName": "Roger",
        "lastName": "Boyd",
        "age": 4,
        "phone": "841-874-6512",
        "medicament": [],
        "allergies": []
      },
      {
        "firstName": "Felicia",
        "lastName": "Boyd",
        "age": 36,
        "phone": "841-874-6544",
        "medicament": [
          "tetracyclaz:650mg"
        ],
        "allergies": [
          "xilliathal"
        ]
      },
      {
        "firstName": "Tessa",
        "lastName": "Carman",
        "age": 10,
        "phone": "841-874-6512",
        "medicament": [],
        "allergies": []
      },
      {
        "firstName": "Foster",
        "lastName": "Shepard",
        "age": 42,
        "phone": "841-874-6544",
        "medicament": [],
        "allergies": []
      },
      {
        "firstName": "Clive",
        "lastName": "Ferguson",
        "age": 28,
        "phone": "841-874-6741",
        "medicament": [],
        "allergies": []
      },
      {
        "firstName": "Tony",
        "lastName": "Cooper",
        "age": 28,
        "phone": "841-874-6874",
        "medicament": [
          "hydrapermazol:300mg",
          "dodoxadin:30mg"
        ],
        "allergies": [
          "shellfish"
        ]
      },
      {
        "firstName": "Ron",
        "lastName": "Peters",
        "age": 56,
        "phone": "841-874-8888",
        "medicament": [],
        "allergies": []
      },
      {
        "firstName": "Allison",
        "lastName": "Boyd",
        "age": 57,
        "phone": "841-874-9888",
        "medicament": [
          "aznol:200mg"
        ],
        "allergies": [
          "nillacilan"
        ]
      },
      {
        "firstName": "Foster",
        "lastName": "Shepard",
        "age": 42,
        "phone": "841-874-6544",
        "medicament": [],
        "allergies": []
      },
      {
        "firstName": "Clive",
        "lastName": "Ferguson",
        "age": 28,
        "phone": "841-874-6741",
        "medicament": [],
        "allergies": []
      }
    ]
  },
  {
    "address": "834 Binoc Ave",
    "personInfoList": [
      {
        "firstName": "John",
        "lastName": "Boyd",
        "age": 38,
        "phone": "841-874-6512",
        "medicament": [
          "aznol:350mg",
          "hydrapermazol:100mg"
        ],
        "allergies": [
          "nillacilan"
        ]
      },
      {
        "firstName": "Jacob",
        "lastName": "Boyd",
        "age": 33,
        "phone": "841-874-6513",
        "medicament": [
          "pharmacol:5000mg",
          "terazine:10mg",
          "noznazol:250mg"
        ],
        "allergies": []
      },
      {
        "firstName": "Tenley",
        "lastName": "Boyd",
        "age": 10,
        "phone": "841-874-6512",
        "medicament": [],
        "allergies": [
          "peanut"
        ]
      },
      {
        "firstName": "Roger",
        "lastName": "Boyd",
        "age": 4,
        "phone": "841-874-6512",
        "medicament": [],
        "allergies": []
      },
      {
        "firstName": "Felicia",
        "lastName": "Boyd",
        "age": 36,
        "phone": "841-874-6544",
        "medicament": [
          "tetracyclaz:650mg"
        ],
        "allergies": [
          "xilliathal"
        ]
      },
      {
        "firstName": "Tessa",
        "lastName": "Carman",
        "age": 10,
        "phone": "841-874-6512",
        "medicament": [],
        "allergies": []
      },
      {
        "firstName": "Foster",
        "lastName": "Shepard",
        "age": 42,
        "phone": "841-874-6544",
        "medicament": [],
        "allergies": []
      },
      {
        "firstName": "Clive",
        "lastName": "Ferguson",
        "age": 28,
        "phone": "841-874-6741",
        "medicament": [],
        "allergies": []
      },
      {
        "firstName": "Tony",
        "lastName": "Cooper",
        "age": 28,
        "phone": "841-874-6874",
        "medicament": [
          "hydrapermazol:300mg",
          "dodoxadin:30mg"
        ],
        "allergies": [
          "shellfish"
        ]
      },
      {
        "firstName": "Ron",
        "lastName": "Peters",
        "age": 56,
        "phone": "841-874-8888",
        "medicament": [],
        "allergies": []
      },
      {
        "firstName": "Allison",
        "lastName": "Boyd",
        "age": 57,
        "phone": "841-874-9888",
        "medicament": [
          "aznol:200mg"
        ],
        "allergies": [
          "nillacilan"
        ]
      },
      {
        "firstName": "Foster",
        "lastName": "Shepard",
        "age": 42,
        "phone": "841-874-6544",
        "medicament": [],
        "allergies": []
      },
      {
        "firstName": "Clive",
        "lastName": "Ferguson",
        "age": 28,
        "phone": "841-874-6741",
        "medicament": [],
        "allergies": []
      }
    ]
  },
  {
    "address": "748 Townings Dr",
    "personInfoList": [
      {
        "firstName": "John",
        "lastName": "Boyd",
        "age": 38,
        "phone": "841-874-6512",
        "medicament": [
          "aznol:350mg",
          "hydrapermazol:100mg"
        ],
        "allergies": [
          "nillacilan"
        ]
      },
      {
        "firstName": "Jacob",
        "lastName": "Boyd",
        "age": 33,
        "phone": "841-874-6513",
        "medicament": [
          "pharmacol:5000mg",
          "terazine:10mg",
          "noznazol:250mg"
        ],
        "allergies": []
      },
      {
        "firstName": "Tenley",
        "lastName": "Boyd",
        "age": 10,
        "phone": "841-874-6512",
        "medicament": [],
        "allergies": [
          "peanut"
        ]
      },
      {
        "firstName": "Roger",
        "lastName": "Boyd",
        "age": 4,
        "phone": "841-874-6512",
        "medicament": [],
        "allergies": []
      },
      {
        "firstName": "Felicia",
        "lastName": "Boyd",
        "age": 36,
        "phone": "841-874-6544",
        "medicament": [
          "tetracyclaz:650mg"
        ],
        "allergies": [
          "xilliathal"
        ]
      },
      {
        "firstName": "Tessa",
        "lastName": "Carman",
        "age": 10,
        "phone": "841-874-6512",
        "medicament": [],
        "allergies": []
      },
      {
        "firstName": "Foster",
        "lastName": "Shepard",
        "age": 42,
        "phone": "841-874-6544",
        "medicament": [],
        "allergies": []
      },
      {
        "firstName": "Clive",
        "lastName": "Ferguson",
        "age": 28,
        "phone": "841-874-6741",
        "medicament": [],
        "allergies": []
      },
      {
        "firstName": "Tony",
        "lastName": "Cooper",
        "age": 28,
        "phone": "841-874-6874",
        "medicament": [
          "hydrapermazol:300mg",
          "dodoxadin:30mg"
        ],
        "allergies": [
          "shellfish"
        ]
      },
      {
        "firstName": "Ron",
        "lastName": "Peters",
        "age": 56,
        "phone": "841-874-8888",
        "medicament": [],
        "allergies": []
      },
      {
        "firstName": "Allison",
        "lastName": "Boyd",
        "age": 57,
        "phone": "841-874-9888",
        "medicament": [
          "aznol:200mg"
        ],
        "allergies": [
          "nillacilan"
        ]
      },
      {
        "firstName": "Foster",
        "lastName": "Shepard",
        "age": 42,
        "phone": "841-874-6544",
        "medicament": [],
        "allergies": []
      },
      {
        "firstName": "Clive",
        "lastName": "Ferguson",
        "age": 28,
        "phone": "841-874-6741",
        "medicament": [],
        "allergies": []
      }
    ]
  },
  {
    "address": "112 Steppes Pl",
    "personInfoList": [
      {
        "firstName": "John",
        "lastName": "Boyd",
        "age": 38,
        "phone": "841-874-6512",
        "medicament": [
          "aznol:350mg",
          "hydrapermazol:100mg"
        ],
        "allergies": [
          "nillacilan"
        ]
      },
      {
        "firstName": "Jacob",
        "lastName": "Boyd",
        "age": 33,
        "phone": "841-874-6513",
        "medicament": [
          "pharmacol:5000mg",
          "terazine:10mg",
          "noznazol:250mg"
        ],
        "allergies": []
      },
      {
        "firstName": "Tenley",
        "lastName": "Boyd",
        "age": 10,
        "phone": "841-874-6512",
        "medicament": [],
        "allergies": [
          "peanut"
        ]
      },
      {
        "firstName": "Roger",
        "lastName": "Boyd",
        "age": 4,
        "phone": "841-874-6512",
        "medicament": [],
        "allergies": []
      },
      {
        "firstName": "Felicia",
        "lastName": "Boyd",
        "age": 36,
        "phone": "841-874-6544",
        "medicament": [
          "tetracyclaz:650mg"
        ],
        "allergies": [
          "xilliathal"
        ]
      },
      {
        "firstName": "Tessa",
        "lastName": "Carman",
        "age": 10,
        "phone": "841-874-6512",
        "medicament": [],
        "allergies": []
      },
      {
        "firstName": "Foster",
        "lastName": "Shepard",
        "age": 42,
        "phone": "841-874-6544",
        "medicament": [],
        "allergies": []
      },
      {
        "firstName": "Clive",
        "lastName": "Ferguson",
        "age": 28,
        "phone": "841-874-6741",
        "medicament": [],
        "allergies": []
      },
      {
        "firstName": "Tony",
        "lastName": "Cooper",
        "age": 28,
        "phone": "841-874-6874",
        "medicament": [
          "hydrapermazol:300mg",
          "dodoxadin:30mg"
        ],
        "allergies": [
          "shellfish"
        ]
      },
      {
        "firstName": "Ron",
        "lastName": "Peters",
        "age": 56,
        "phone": "841-874-8888",
        "medicament": [],
        "allergies": []
      },
      {
        "firstName": "Allison",
        "lastName": "Boyd",
        "age": 57,
        "phone": "841-874-9888",
        "medicament": [
          "aznol:200mg"
        ],
        "allergies": [
          "nillacilan"
        ]
      },
      {
        "firstName": "Foster",
        "lastName": "Shepard",
        "age": 42,
        "phone": "841-874-6544",
        "medicament": [],
        "allergies": []
      },
      {
        "firstName": "Clive",
        "lastName": "Ferguson",
        "age": 28,
        "phone": "841-874-6741",
        "medicament": [],
        "allergies": []
      }
    ]
  },
  {
    "address": "748 Townings Dr",
    "personInfoList": [
      {
        "firstName": "John",
        "lastName": "Boyd",
        "age": 38,
        "phone": "841-874-6512",
        "medicament": [
          "aznol:350mg",
          "hydrapermazol:100mg"
        ],
        "allergies": [
          "nillacilan"
        ]
      },
      {
        "firstName": "Jacob",
        "lastName": "Boyd",
        "age": 33,
        "phone": "841-874-6513",
        "medicament": [
          "pharmacol:5000mg",
          "terazine:10mg",
          "noznazol:250mg"
        ],
        "allergies": []
      },
      {
        "firstName": "Tenley",
        "lastName": "Boyd",
        "age": 10,
        "phone": "841-874-6512",
        "medicament": [],
        "allergies": [
          "peanut"
        ]
      },
      {
        "firstName": "Roger",
        "lastName": "Boyd",
        "age": 4,
        "phone": "841-874-6512",
        "medicament": [],
        "allergies": []
      },
      {
        "firstName": "Felicia",
        "lastName": "Boyd",
        "age": 36,
        "phone": "841-874-6544",
        "medicament": [
          "tetracyclaz:650mg"
        ],
        "allergies": [
          "xilliathal"
        ]
      },
      {
        "firstName": "Tessa",
        "lastName": "Carman",
        "age": 10,
        "phone": "841-874-6512",
        "medicament": [],
        "allergies": []
      },
      {
        "firstName": "Foster",
        "lastName": "Shepard",
        "age": 42,
        "phone": "841-874-6544",
        "medicament": [],
        "allergies": []
      },
      {
        "firstName": "Clive",
        "lastName": "Ferguson",
        "age": 28,
        "phone": "841-874-6741",
        "medicament": [],
        "allergies": []
      },
      {
        "firstName": "Tony",
        "lastName": "Cooper",
        "age": 28,
        "phone": "841-874-6874",
        "medicament": [
          "hydrapermazol:300mg",
          "dodoxadin:30mg"
        ],
        "allergies": [
          "shellfish"
        ]
      },
      {
        "firstName": "Ron",
        "lastName": "Peters",
        "age": 56,
        "phone": "841-874-8888",
        "medicament": [],
        "allergies": []
      },
      {
        "firstName": "Allison",
        "lastName": "Boyd",
        "age": 57,
        "phone": "841-874-9888",
        "medicament": [
          "aznol:200mg"
        ],
        "allergies": [
          "nillacilan"
        ]
      },
      {
        "firstName": "Foster",
        "lastName": "Shepard",
        "age": 42,
        "phone": "841-874-6544",
        "medicament": [],
        "allergies": []
      },
      {
        "firstName": "Clive",
        "lastName": "Ferguson",
        "age": 28,
        "phone": "841-874-6741",
        "medicament": [],
        "allergies": []
      }
    ]
  }
]
```
</details>

<br>

### Medical Records

Medical records endpoints and URLs API.

#### POST /medicalRecord

Create a medical record.

- Request boby : application/json
- Example value :

```json
{
  "firstName":"John",
  "lastName":"Doe",
  "birthdate":"02/09/1980",
  "medications":[
    "aznol:350mg",
    "hydrapermazol:100mg"
  ],
  "allergies":["peanut"]
}
```
- Responses

| code | Description                                 |
|------|---------------------------------------------|
| 201  | Created - The medical record was created    |
| 409  | Conflict - The medical record already exist |

<br>

#### PUT /medicalRecord

Update a medical record.

- Request body : application/json
- Example value :

```json
{
  "firstName":"John",
  "lastName":"Boyd",
  "birthdate":"02/06/1984",
  "medications":[
    "aznol:350mg",
    "hydrapermazol:100mg"
  ],
  "allergies":[]
}
```
- Responses

| code | Description                             |
|------|-----------------------------------------|
| 200  | Ok - The medical record was updated     |
| 409  | Conflict - The medical record not exist |


<br>

#### DELETE /medicalRecord

Delete a medicalRecord

* Parameter :
    * firstName : ___string___
    * lastName : ___string___
* Examples values :

```
/medicalRecord?firstName=John&lastName=Boyd
```

- Responses

| code | Description                             |
|------|-----------------------------------------|
| 200  | Ok - The medical record was deleted     |
| 409  | Conflict - The medical record not exist |

<br>

#### GET /medicalrecords

Get all medical records.

Return the list of all medical records.

- No parameters
- Example value :

```
/medicalrecords
```
- Response

| code | Description |
|------|-------|
| 200  | Ok    |

<br>

<details>
<summary>Response body</summary>

```json
[
    {
        "firstName": "John",
        "lastName": "Boyd",
        "birthdate": "03/06/1984",
        "medications": [
            "aznol:350mg",
            "hydrapermazol:100mg"
        ],
        "allergies": [
            "nillacilan"
        ]
    },
    {
        "firstName": "Jacob",
        "lastName": "Boyd",
        "birthdate": "03/06/1989",
        "medications": [
            "pharmacol:5000mg",
            "terazine:10mg",
            "noznazol:250mg"
        ],
        "allergies": []
    },
    {
        "firstName": "Tenley",
        "lastName": "Boyd",
        "birthdate": "02/18/2012",
        "medications": [],
        "allergies": [
            "peanut"
        ]
    },
    {
        "firstName": "Roger",
        "lastName": "Boyd",
        "birthdate": "09/06/2017",
        "medications": [],
        "allergies": []
    },
    {
        "firstName": "Felicia",
        "lastName": "Boyd",
        "birthdate": "01/08/1986",
        "medications": [
            "tetracyclaz:650mg"
        ],
        "allergies": [
            "xilliathal"
        ]
    },
    {
        "firstName": "Jonanathan",
        "lastName": "Marrack",
        "birthdate": "01/03/1989",
        "medications": [],
        "allergies": []
    },
    {
        "firstName": "Tessa",
        "lastName": "Carman",
        "birthdate": "02/18/2012",
        "medications": [],
        "allergies": []
    },
    {
        "firstName": "Peter",
        "lastName": "Duncan",
        "birthdate": "09/06/2000",
        "medications": [],
        "allergies": [
            "shellfish"
        ]
    },
    {
        "firstName": "Foster",
        "lastName": "Shepard",
        "birthdate": "01/08/1980",
        "medications": [],
        "allergies": []
    },
    {
        "firstName": "Tony",
        "lastName": "Cooper",
        "birthdate": "03/06/1994",
        "medications": [
            "hydrapermazol:300mg",
            "dodoxadin:30mg"
        ],
        "allergies": [
            "shellfish"
        ]
    },
    {
        "firstName": "Lily",
        "lastName": "Cooper",
        "birthdate": "03/06/1994",
        "medications": [],
        "allergies": []
    },
    {
        "firstName": "Sophia",
        "lastName": "Zemicks",
        "birthdate": "03/06/1988",
        "medications": [
            "aznol:60mg",
            "hydrapermazol:900mg",
            "pharmacol:5000mg",
            "terazine:500mg"
        ],
        "allergies": [
            "peanut",
            "shellfish",
            "aznol"
        ]
    },
    {
        "firstName": "Warren",
        "lastName": "Zemicks",
        "birthdate": "03/06/1985",
        "medications": [],
        "allergies": []
    },
    {
        "firstName": "Zach",
        "lastName": "Zemicks",
        "birthdate": "03/06/2017",
        "medications": [],
        "allergies": []
    },
    {
        "firstName": "Reginold",
        "lastName": "Walker",
        "birthdate": "08/30/1979",
        "medications": [
            "thradox:700mg"
        ],
        "allergies": [
            "illisoxian"
        ]
    },
    {
        "firstName": "Jamie",
        "lastName": "Peters",
        "birthdate": "03/06/1982",
        "medications": [],
        "allergies": []
    },
    {
        "firstName": "Ron",
        "lastName": "Peters",
        "birthdate": "04/06/1965",
        "medications": [],
        "allergies": []
    },
    {
        "firstName": "Allison",
        "lastName": "Boyd",
        "birthdate": "03/15/1965",
        "medications": [
            "aznol:200mg"
        ],
        "allergies": [
            "nillacilan"
        ]
    },
    {
        "firstName": "Brian",
        "lastName": "Stelzer",
        "birthdate": "12/06/1975",
        "medications": [
            "ibupurin:200mg",
            "hydrapermazol:400mg"
        ],
        "allergies": [
            "nillacilan"
        ]
    },
    {
        "firstName": "Shawna",
        "lastName": "Stelzer",
        "birthdate": "07/08/1980",
        "medications": [],
        "allergies": []
    },
    {
        "firstName": "Kendrik",
        "lastName": "Stelzer",
        "birthdate": "03/06/2014",
        "medications": [
            "noxidian:100mg",
            "pharmacol:2500mg"
        ],
        "allergies": []
    },
    {
        "firstName": "Clive",
        "lastName": "Ferguson",
        "birthdate": "03/06/1994",
        "medications": [],
        "allergies": []
    },
    {
        "firstName": "Eric",
        "lastName": "Cadigan",
        "birthdate": "08/06/1945",
        "medications": [
            "tradoxidine:400mg"
        ],
        "allergies": []
    }
]
```
</details>

<br>

### Persons

Persons endpoints and URLs API.

#### POST /person

Create a person.

- Request boby : application/json
- Example value :

```json
{
  "firstName": "John",
  "lastName": "Doe",
  "address": "My address",
  "city": "Culver",
  "zip": 97451,
  "phone": "123-456-7890",
  "email": "jdoe@mail.me"
}
```
- Responses

| code | Description                         |
|------|-------------------------------------|
| 201  | Created - The person was created    |
| 409  | Conflict - The person already exist |

<br>

#### PUT /person

Update a person.

- Request body : application/json
- Example value :

```json
{
  "firstName": "John",
  "lastName": "Boyd",
  "address": "New address",
  "city": "Culver",
  "zip": 97451,
  "phone": "841-874-6512",
  "email": "jaboyd@email.com"
}
```
- Responses

| code | Description                     |
|------|---------------------------------|
| 200  | Ok - The person was updated     |
| 409  | Conflict - The person not exist |


<br>

#### DELETE /person

Delete a person

* Parameter :
    * firstName : ___string___
    * lastName : ___string___
* Examples values :

```
/person?firstName=John&lastName=Boyd
```

- Responses

| code | Description                     |
|------|---------------------------------|
| 200  | Ok - The person was deleted     |
| 409  | Conflict - The person not exist |

<br>

#### GET /persons

Get all persons.

Return the list of all persons.

- No parameters
- Example value :

```
/persons
```
- Response

| code | Description |
|------|-------|
| 200  | Ok    |

<br>

<details>
<summary>Response body</summary>

```json
[
    {
        "firstName": "John",
        "lastName": "Boyd",
        "address": "1509 Culver St",
        "city": "Culver",
        "zip": 97451,
        "phone": "841-874-6512",
        "email": "jaboyd@email.com"
    },
    {
        "firstName": "Jacob",
        "lastName": "Boyd",
        "address": "1509 Culver St",
        "city": "Culver",
        "zip": 97451,
        "phone": "841-874-6513",
        "email": "drk@email.com"
    },
    {
        "firstName": "Tenley",
        "lastName": "Boyd",
        "address": "1509 Culver St",
        "city": "Culver",
        "zip": 97451,
        "phone": "841-874-6512",
        "email": "tenz@email.com"
    },
    {
        "firstName": "Roger",
        "lastName": "Boyd",
        "address": "1509 Culver St",
        "city": "Culver",
        "zip": 97451,
        "phone": "841-874-6512",
        "email": "jaboyd@email.com"
    },
    {
        "firstName": "Felicia",
        "lastName": "Boyd",
        "address": "1509 Culver St",
        "city": "Culver",
        "zip": 97451,
        "phone": "841-874-6544",
        "email": "jaboyd@email.com"
    },
    {
        "firstName": "Jonanathan",
        "lastName": "Marrack",
        "address": "29 15th St",
        "city": "Culver",
        "zip": 97451,
        "phone": "841-874-6513",
        "email": "drk@email.com"
    },
    {
        "firstName": "Tessa",
        "lastName": "Carman",
        "address": "834 Binoc Ave",
        "city": "Culver",
        "zip": 97451,
        "phone": "841-874-6512",
        "email": "tenz@email.com"
    },
    {
        "firstName": "Peter",
        "lastName": "Duncan",
        "address": "644 Gershwin Cir",
        "city": "Culver",
        "zip": 97451,
        "phone": "841-874-6512",
        "email": "jaboyd@email.com"
    },
    {
        "firstName": "Foster",
        "lastName": "Shepard",
        "address": "748 Townings Dr",
        "city": "Culver",
        "zip": 97451,
        "phone": "841-874-6544",
        "email": "jaboyd@email.com"
    },
    {
        "firstName": "Tony",
        "lastName": "Cooper",
        "address": "112 Steppes Pl",
        "city": "Culver",
        "zip": 97451,
        "phone": "841-874-6874",
        "email": "tcoop@ymail.com"
    },
    {
        "firstName": "Lily",
        "lastName": "Cooper",
        "address": "489 Manchester St",
        "city": "Culver",
        "zip": 97451,
        "phone": "841-874-9845",
        "email": "lily@email.com"
    },
    {
        "firstName": "Sophia",
        "lastName": "Zemicks",
        "address": "892 Downing Ct",
        "city": "Culver",
        "zip": 97451,
        "phone": "841-874-7878",
        "email": "soph@email.com"
    },
    {
        "firstName": "Warren",
        "lastName": "Zemicks",
        "address": "892 Downing Ct",
        "city": "Culver",
        "zip": 97451,
        "phone": "841-874-7512",
        "email": "ward@email.com"
    },
    {
        "firstName": "Zach",
        "lastName": "Zemicks",
        "address": "892 Downing Ct",
        "city": "Culver",
        "zip": 97451,
        "phone": "841-874-7512",
        "email": "zarc@email.com"
    },
    {
        "firstName": "Reginold",
        "lastName": "Walker",
        "address": "908 73rd St",
        "city": "Culver",
        "zip": 97451,
        "phone": "841-874-8547",
        "email": "reg@email.com"
    },
    {
        "firstName": "Jamie",
        "lastName": "Peters",
        "address": "908 73rd St",
        "city": "Culver",
        "zip": 97451,
        "phone": "841-874-7462",
        "email": "jpeter@email.com"
    },
    {
        "firstName": "Ron",
        "lastName": "Peters",
        "address": "112 Steppes Pl",
        "city": "Culver",
        "zip": 97451,
        "phone": "841-874-8888",
        "email": "jpeter@email.com"
    },
    {
        "firstName": "Allison",
        "lastName": "Boyd",
        "address": "112 Steppes Pl",
        "city": "Culver",
        "zip": 97451,
        "phone": "841-874-9888",
        "email": "aly@imail.com"
    },
    {
        "firstName": "Brian",
        "lastName": "Stelzer",
        "address": "947 E. Rose Dr",
        "city": "Culver",
        "zip": 97451,
        "phone": "841-874-7784",
        "email": "bstel@email.com"
    },
    {
        "firstName": "Shawna",
        "lastName": "Stelzer",
        "address": "947 E. Rose Dr",
        "city": "Culver",
        "zip": 97451,
        "phone": "841-874-7784",
        "email": "ssanw@email.com"
    },
    {
        "firstName": "Kendrik",
        "lastName": "Stelzer",
        "address": "947 E. Rose Dr",
        "city": "Culver",
        "zip": 97451,
        "phone": "841-874-7784",
        "email": "bstel@email.com"
    },
    {
        "firstName": "Clive",
        "lastName": "Ferguson",
        "address": "748 Townings Dr",
        "city": "Culver",
        "zip": 97451,
        "phone": "841-874-6741",
        "email": "clivfd@ymail.com"
    },
    {
        "firstName": "Eric",
        "lastName": "Cadigan",
        "address": "951 LoneTree Rd",
        "city": "Culver",
        "zip": 97451,
        "phone": "841-874-7458",
        "email": "gramps@email.com"
    }
]
```
</details>

<br>

#### GET /childAlert?address=< address >

Get all children living at the same address.

Return firstName, lastName, age of children and the firstName and lastName from adults living with them.

* Parameter :
  * address : ___string___
* Example value :

```
/childAlert?address=1509 Culver St
```

 - Response

| code   | Description  |
|--------|-------------|
| 200    | Ok     |
| 204    | No content|

<br>

<details>
<summary>Response body</summary>

```json
{
    "childrenByAddressList": [
        {
            "firstName": "Tenley",
            "lastName": "Boyd",
            "age": 10
        },
        {
            "firstName": "Roger",
            "lastName": "Boyd",
            "age": 4
        }
    ],
    "adultByAddressList": [
        {
            "firstName": "John",
            "lastName": "Boyd"
        },
        {
            "firstName": "Jacob",
            "lastName": "Boyd"
        },
        {
            "firstName": "Felicia",
            "lastName": "Boyd"
        }
    ]
}
```
</details>

<br>

#### GET /fire?address=< address >

Get all people information living at the given address.

Return firstName, lastName, phone, age, medications and allergies for each person and the fire station number.

* Parameter :
  * address : ___string___
* Example value :

```
/fire?address=1509 Culver St
```

- Response

| code   | Description  |
|--------|-------------|
| 200    | Ok     |
| 204    | No content|

<br>

<details>
<summary>Response body</summary>

```json
{
    "childrenByAddressList": [
        {
            "firstName": "Tenley",
            "lastName": "Boyd",
            "age": 10
        },
        {
            "firstName": "Roger",
            "lastName": "Boyd",
            "age": 4
        }
    ],
    "adultByAddressList": [
        {
            "firstName": "John",
            "lastName": "Boyd"
        },
        {
            "firstName": "Jacob",
            "lastName": "Boyd"
        },
        {
            "firstName": "Felicia",
            "lastName": "Boyd"
        }
    ]
}
```
</details>

<br>

#### GET /personInfo?firstName=< firstName >&lastName=< lastName >

Get information from a specific person.

Return firstName, lastName, address, age, email, medications and allergies from given person.

* Parameter :
  * firstName : ___string___
  * lastName : ___string___
* Example value :

 ```
/personInfo?firstName=John&lastName=Boyd
```

- Response

| code   | Description  |
|--------|-------------|
| 200    | Ok     |
| 204    | No content|

<br>

<details>
<summary>Response body</summary>

```json
[
    {
        "firstName": "John",
        "lastName": "Boyd",
        "address": "1509 Culver St",
        "age": 38,
        "email": "jaboyd@email.com",
        "medicament": [
            "aznol:350mg",
            "hydrapermazol:100mg"
        ],
        "allergies": [
            "nillacilan"
        ]
    }
]
```
</details>

<br>

#### GET /communityEmail?city=< city >

Get email from person living in the same city.

Return the list of email from people living in the given city.

* Parameter : 
  * city : ___string___
* Example value : 

```
/communityEmail?city=Culver
```

 - Response

| code   | Description  |
|--------|-------------|
| 200    | Ok     |
| 204    | No content|

<br>

<details>
<summary>response body</summary>

```json
[
    "jaboyd@email.com",
    "drk@email.com",
    "tenz@email.com",
    "tcoop@ymail.com",
    "lily@email.com",
    "soph@email.com",
    "ward@email.com",
    "zarc@email.com",
    "reg@email.com",
    "jpeter@email.com",
    "aly@imail.com",
    "bstel@email.com",
    "ssanw@email.com",
    "clivfd@ymail.com",
    "gramps@email.com"
]
```
</details>

