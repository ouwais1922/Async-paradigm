## RATpp

In the first homework, we had to create a remote access tool system based on the client/server communication paradigm. In this sense, we had to manage connections between client and server using sockets and manage the streams ect... plus designing the protocol that would govern the communication between these two parties (client & server). However, we got in touched with the tidous side of this paradigm because developing and designing a protocol would not allow us to reach the luxury of invoking functions as if they are working locally. Therefore, ## RATpp (RAT++) ## is a remote access tool that allow the consumer invoking or calling functions as if they are local, so at the end of this homework, we will get the main use of integration as paradigm.(THE LUXURY)

## MAIN MENU :

    1 - Homework Objective
    2 - Technologies
    3 - Development approach
    4 - Steps to run the application
    5 - API
    6 - More description (Development approach details)
    7 - Conclusion
    8 - Resources

## 1 - Homework Objective:

In this app we will use RPC, so we can allow the consumer access the remote system by invoking three functions, and they are as following:

                1 - Reboot the remote system
                2 - Take screenshots of the remote system
                3 - Get the list of processes running in the remote system

## 2 - Technologies:

    1 - Web Services Description Language:

    WSDL is an XML format for describing network services as a set of endpoints. The
    operations and messages are described abstractly, and then bound to a concrete
    network protocol and message format to define an endpoint. In overall, A WSDL
    definition tells a client how to compose a web service request and describes the
    interface that is provided by the provider.

    2 - Protocol: SOAP over HTTP:

    An XML-based messaging protocol for sending and receiving messages, enabling
    remote procedure calls to be transported via HTTP. SOAP is language independent.

    3 - Provider programming language: (java)

    Java API for XML Web Services (JAX-WS) is a standardized API for creating and consuming SOAP (Simple Object Access Protocol.

    ## NB, we used:
    wsgen: command-line tool generates the necessary artifacts required for Java API for XML Web Services (JAX-WS).This tool used for generating WSDL document and skeleton code on provider’s side.


    4 - Consumer programming language: (python)

    Zeep module:  Zeep inspects the WSDL document and generates the
    corresponding code to use the service and types in the document. The client stub
    code will be generated dynamically when running the consumer code.

    5- Consumer programing language: (javascript)

    SOAP module: It enables us to connect to web services using SOAP. We used
    essentially soap.createClientAsync(url) to create a new SOAP client from the
    WSDL url (In our case the url specifies a local filesystem path).

    Node.js: JavaScript runtime environment on which we execute our JavaScript
    code (consumer.js) outside a web browser

## 3- Development approach:

    ## First code approach:

    We used the first code approach by following these steps:

    ==> Service provider side:

        - We started coding the service business implementation (the three functions) using java. Then, generate the WSDL file and java skeleton (using wsgen).

    ==> Consumer side: (python)

        - By parsing the WSDL document created on the provider side, we created the consumer code in Python and used the Zeep module to generate the client stub.

    ==> Consumer side: (javasciprt)

        - On this side, we created the promise-based consumer who will utilize the asynchronous service provided by the service provider. We started by stating the URL for the WSDL file, which provides the prototypes of the functions the provider offers, and where the provider may be contacted.

        - In order to build the SOAP client (Asynchronous), which will be the outcome of the promise given by this method, we then called soap.createClientAsync(url). The asynchronous functions shown by the provider can then be called using the SOAP client.

        - The client knows only the protoype of functions, he/she doesn't need to know the implementation of
        these function rather than just call it.
## 4- Steps to run the application: (user interface)

    1 - Service provider:

         1-1 - Open the terminal to use the command line.
         1-2 - Make sure that you are in the project folder. Otherwsi cd to the folder (ex: cd RAT).
         1-3 - Build the project by running : ./gradlew build (check the pic in utils/build)
         1-4 - Run the project by running   : ./gradlew run   (check the pic in utils/run)

    2 - Service consumer: (pyhton)

        2-1 -  Open the terminal to use the command line.
        2-2 -  Make sure that you are under python/consumer, then run : python Consumer.py
        2-3 - If you aren't under python/consumer, but you are under RAT/, then run : python src/main/python/consumer/Consumer.py
        2-4 - Once the application is running a menu will display of 4 options (check pic under utils/options)

            option 1 :  Get the list of processes running in the remote system (List of running processes display in terminal)
            option 2 :  Take screenshots of the remote system that will be stored under consumerShare (check the screenShot under consumerShare)
            option 3 :  Reboot the system
            option 4 :  Quit

    3 - Service consumer (javascript)

        3-1 -  Open the terminal to use the command line.
        3-2 -  Make sure that you are under javascript/consumer, then run : node Consumer.js
        3-3 - If you aren't under javascript/consumer, but you are under RAT/, then run : javascript src/main/javascript/Consumer.py
        2-4 - Once the application is running a menu will display of 4 options (check pic under utils/options)

            option 1 :  Get the list of processes running in the remote system (List of running processes display in terminal)
            option 2 :  Take screenshots of the remote system that will be stored under javascript/remote
            option 3 :  Reboot the system
            option 4 :  Quit


## API:

The Web Service Definition Language (WSDL), which describes the prototypes of the methods/functions that the service provider provides and the service consumer can invoke as if they were local methods, is used to describe our API.

- the consumer consumes from wsdl through accessing : http://localhost:7000/RAT?wsdl
  -- (accessTool = Client('http://localhost:7000/RAT?wsdl').service)
  -- check pics wsdl1 && wsdl2 under utils/

- if the consumer wants to consume from wsdl file. Then the service provider should generate the wsdl file
  by running : wsgen -wsdl -cp build/classes/java/main/ -d build/classes/java/main/ -r src/main/resources/

( I chose to consume from wsdl through "http://localhost:7000/RAT?wsdl" due to some errors that occured while generating the wsdl file)

    ## the four operations :

            - getScreenShot() : return xsd:base64Binary

                if the opretion done successfully then it will return the screenshot, otherwise it will return NULL

            - reboot() : return xsd:Boolean

                if the opretion done successfully then it will return true, otherwise it will return false

            - getRunningProcess() : return String[]

## More description (Development approach details):

    1 - Service provider:

            On this side, we first implemented the real and business implementation of the functions and methods the API describes (applying the code-first approach). The appropriate tool is then used to generate the WSDL document and the skeleton code.

            We run the service provider side. Then, the service is ready to get the consumer requests. The high level of abstraction (conntections details ...) is hidden from the consumer.For sure, the parameters unmarshalling and result marshalling are done by the stub/skeleton.


    2 - Service consumer:

            The consumer knows the prototype of the functions invoked without knowing the deatils of the functions in provider side. By using the stub function which is a fake function, it gives the impression of as if it were local.

## Conclusion:

At the end of this homework we get the importance of the integration paradigm. Therefore, as a consumer, I will not care of the bussiness implementation of the function in the provider side, but I will just invoke it as if it works local. Moreover, thanks to this homework we got in touch with the luxury of invoking functions comparing to the first homework.


## Resources:

NB: All the operations that rely on the execute command are mainly from stackoverflow

https://stackoverflow.com/questions/13523256/wsgen-is-not-recognized-as-an-internal-or-external-command-operable-program-o

https://www.geeksforgeeks.org/java-program-take-screenshots/

https://stackoverflow.com/questions/4490454/how-to-take-a-screenshot-in-java

https://stackoverflow.com/questions/54686/how-to-get-a-list-of-current-open-windows-process-with-java

https://stackoverflow.com/questions/41303159/is-it-possible-to-shutdown-computer-from-java-code

https://www.w3schools.com/xml/xml_soap.asp

https://github.com/oiraqi/paradigms/tree/main/P2-Integration
