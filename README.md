This is an CURD base application for Employee Mangement System
Where Backend is written in java , used Spring boot framework version 3.2.2 
and For Front-End Ract is used 
For database MYsql is used 

To run the applcation
  Install docker and Docker compose in your system:
    and run command : 
          
	  docker-compose up --build
    
 YOur application will be running on localhost:3000

 To stop all the running containers 

      docker stop $(docker ps -q)

To remove all local docker images

     docker rmi -f curd_base_application_service1
     docker rmi -f curd_base_application_service2
     docker rmi -f curd_base_application_service3
     



    
