# RestWebService_Messenger
This is the application generated using the walkthrough of youtube videos from Java Brain. This project basically covers all the basic concepts of Rest Web services using JaxRS and Jersey.
The application is a minimal skeleton of a social network and includes the basic objects like the Profile, Message and Comments.
The application has following layers:
1) Models or Beans
2) Service classes for every service that is accessible as the Rest Web Service
3) Resource classes for every service.

The models or beans are simple java beans. The service class are the classes that provide the actual service (for instance, accessing the dummy
or in memory database for the basic CRUD operations).
The resource classes are the java classes annotated with the JAXRS annotations and are responsible to handle all the requests.

Every request is handled via the path of the request, i.e. every resource class is annotated with @Path annotation that decides which resource
class should take care of which request.

The application also contains the exception handling module which is pretty easy and is self explanatory.

<b>TOOLS, IDE:</b>
The project was created using the Eclipse Mars Version: Mars.1 Release (4.5.1) Build id: 20150924-1200.
A recent version of eclipse for J2EE can be used to build a maven project and then add the relevant resource,service and beans to come up with
such basic projct.

To add, the chrome extension called Postman was used as a client interface. One can easily add the Postman plugin into chrome browser and navigate to the respective url to test/access the web service. A sample run of the Postman is attached (postman_interface_1.png and postman_interface_2.png). 

Thank you.
