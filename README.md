# Alerts

<p align="center">
  <img src=https://user-images.githubusercontent.com/95872501/226969311-56836b82-0257-4def-87d4-3a84c14e70f9.png>
</p>

Alerts is an API developed to provide first aid information on people in distress based on different types of alerts : fire, hurricane or flood. It also allows people to be warned in case of danger.

# Possible Requests

```
Create a person, a medical record or a firestation
Update a person, a medical record or a firestation
Delete a person, a medical record or a firestation
```
```
Get the list of all inhabitants covered by a fire station
Get the list and the number of children of a given address
Get the list of telephone numbers of the inhabitants covered by a fire station
Get the list of all inhabitants sorted by household
Get the list of all inhabitants sorted by fire station
Get the personal information of a resident
Get the list of emails of all the inhabitants of a city
```

# Goals achieved

- Read/Write data on a json file named `data.json`
- Develop different endpoints to send requests
- Produce a JSON response from the URLs
- Return an empty object if no match
- Perform unit tests to cover 80% of the code
- Log each request and each response
- Develop other endpoints to add, modify or delete objects from the data file
- Follow an architecture following the `model-view-controller` pattern

# Run the API

There is 2 possibilities : 
- Open the project with your IDE and run the main method of `AlertsApplication` class.
- Or open a prompt :
    - move to the root of the project you cloned on your local machine
    - enter `mvn clean install` command to compile, test, package and install properly the project
    - enter `mvn spring-boot:run`command to start the API

# Send request to the API

1. Install [Postman](https://www.postman.com/downloads/)
2. Save this [file](https://gist.githubusercontent.com/HashTucE/8f89d2baff367100c6b5cbf59480e3c7/raw/f9452eab9d4b09de2478d7ea8e5d4e07692b0bff/P5%2520Endpoints.json) as `collection1.json`
3. Save this other [file](https://gist.githubusercontent.com/HashTucE/d271db756bfbaf22fc3cae7d5522454a/raw/37ff884dc32be35e9ef63f31bd6c44bbd1e204fb/P5%2520URLs.json) as `collection2.json`
4. Import both HTTP request collections into `Postman`

Now you should be able to access to 2 differents collections :
- P5 Endpoints will give you the possibility to create, update or delete objects. Check the body section before sending the request.
- P5 URLs will give you the possibility to get differents informations depending on the params section.

    - For exemple as you can see below, if you replace the parameter firestation by another number between 1 and 4, when you click on the `Send` button, you will get another phone list as json body.

<p align="center">
  <img src=https://user-images.githubusercontent.com/95872501/227168576-d8790adb-d998-451d-8d8d-ac4d655255ea.png>
</p>

    
    
# Class Diagram

<p align="center">
  <img src=https://user-images.githubusercontent.com/95872501/226991795-a9beba2c-faef-4710-a5af-53d6337764fc.png>
</p>


# MVC Architecture

<p align="center">
  <img src=https://user-images.githubusercontent.com/95872501/226992898-6cde9d0b-4d7b-428f-9c95-4b8099da4bec.png>
</p>


# SureFire Report

<p align="center">
  <img src=https://user-images.githubusercontent.com/95872501/226993240-8222558b-a0b1-4389-bf0f-e372dfe0064a.png>
</p>


# Jacoco Coverage

<p align="center">
  <img src=https://user-images.githubusercontent.com/95872501/226993258-211d5dc1-05ac-451b-a6d3-b0946348f8c3.png>
</p>


# Technical Stack 

<p align="center">
  <img src=https://user-images.githubusercontent.com/95872501/226993279-7c7b6e60-327b-4078-a2be-f1f6e56fa87e.png>
</p>


