Task Manager Case Study Build Notes:

Application Structure:
Parent maven project(taskmanager-parent) with two dependent modules - For building nd packaging both service and ui into a jar
1) task-manager-service - Maven Spring boot project for exposing rest endpoints + Mongo DB
2) task-manager-ui - Angular CLI for building UI and connects with rest endpoints exposed

Git Repository:
https://github.com/smadhankumar/taskmanager-parent.git


Maven Build Commands for the final artifacts:

1) clean install -e	 [run the command for taskmanager-parent project which will build UI and service project and create the final jar with required resources]
2) package docker:build	 [run the command for task-manager-service project which will copy the jar from target and create image in the remote docker container]

Commands to run the created image and validate the image:

1) Connect to the remote docker machine 
2) To check whether the image is created in docker container
   dockerx image ls
3) To run the created image in docker
   dockerx run -p 8085:8089 mks-task-manager:latest
4) To check whether the image is running in docker[open new cmd prompt and run the cmd]
   dockerx ps
5) To validate whether the application is working fine using curl command
     i)  connect to bash shell in the container. [take container id of the image created from dockers ps]
	      dockerx exec -it [CONTAINER_ID] bash
	 ii) check whether application is working [it will return custom techincal error from the service exposed since mongo db i not available in docker]
	      curl -d '{}' -H "Accept:*/*" -H "Content-Type:application/json" -X POST http://localhost:8085/task-manager/viewTasks

Commands for local development:

1) Make sure mongodb is running in localhost with port 27017 [mongodb://localhost:27017/test]
2) Run maven command spring-boot:run for the project task-manager-service
3) Check whether the endpoint is working fine in postman
    Endpoint : http://localhost:8089/task-manager/viewTasks
	Request: {}
	Method: POST
	Headers: Accept:*/*
             Content-Type:application/json
	Response:
	[
    {
        "_id": {
            "timestamp": 1540283478,
            "machineIdentifier": 15246859,
            "processIdentifier": 3124,
            "counter": 1753997,
            "time": 1540283478000,
            "date": 1540283478000,
            "timeSecond": 1540283478
        },
        "taskId": 1,
        "taskName": "task 1",
        "parentTaskDetail": null,
        "priority": "10",
        "startDate": "10/23/2018",
        "endDate": "10/23/2018"
    },
    {
        "_id": {
            "timestamp": 1540283553,
            "machineIdentifier": 15246859,
            "processIdentifier": 3124,
            "counter": 1753998,
            "time": 1540283553000,
            "date": 1540283553000,
            "timeSecond": 1540283553
        },
        "taskId": 2,
        "taskName": "task 2",
        "parentTaskDetail": {
            "parentId": 1,
            "parentTaskName": "task 1"
        },
        "priority": "28",
        "startDate": "10/23/2018",
        "endDate": "10/24/2018"
    }
	]
4) Open the folder src/main/web of project task-manager-ui in visual studio and then run below commands
   npm install
   npm start
5) Hit the url http://loalhost:4200 and see whether the page is getting loaded

Jenkins command:

1) Make sure Jenkins installed and running 
2) Configure Maven and JDK in jenkins with name maven3 and jdk1.8
3) Create Jenkins project with Pipeline option and configure the below information
    i)   Github repository with credentials
	ii)  Branch to build: */develop
	iii) Path to Jenkinsfile -> 
   
   
Note: 
1) All the configurations are available in pom.xml of respective projects
2) Before the maven build, change the port number in environment.prod.ts
   to change the port number in which the service is exposed.