# my-real-app

spring-boot-microservices
A real time microservices supply monitoring project utilizing spring cloud technologies framework. Services can connect/interact with each other or can also connect with external tools as shown in the Architectural layer below.

Services and their functionality
Event driven activity such as notifying customers when order has been made was implemented with kafka. The kafka broker was set up using Docker and Spring for Apache kafka.

Distributed Tracing was implemented with spring-cloud-sleuth and Zipkin for the UI. This helps to track the performance of our app by adding traces and spans id for every api call made to all the services.

Circuit breaker was implemented using Resilience 4j depency from Spring Cloud circuit breaker framework. This helps to degrade functionality when a method call fails. Since we are implementing a synchronous call to the inventory service, a circuit breaker would ensure clients are routed to a new page when a service fails or takes longer time to respond. More information here.

Service discovery was implemented with spring cloud discovery Eureka server/client. This helps to persist services on the eureka client side acting as a caching solution when the server is down. This can be seen as an SSG solution for rendering the browser DOM.

API gateway was implemented with spring cloud gateway to route and filter incoming requests to micro services.

Order service: Built on mySQL DB. Makes synchronous call to Inventory service to confirm if goods are available and asynchronous call to notification service notifying customers if order was successful.

Product service: Built on mongoDB. An endpoint to display goods. The frontend would be built on this end point.

Inventory service: Built on mySQL DB. Takes count of the quantity of goods in stock.

Notification service: Serveless API to notify cutomers about their orders.

Architecture Layer
architecture

Logic Layer for each service
logic layer
