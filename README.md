Spring WebSocket Implementation in Spring Boot
This repository contains a basic example of implementing WebSocket in a Spring Boot application. WebSocket is a full-duplex communication protocol ideal for real-time applications such as chat applications, gaming, stock tickers, and more.

Features
Real-time messaging using WebSocket
Configurable WebSocket endpoint
Simple frontend for testing WebSocket messages
Scalable and efficient for real-time communication
Technologies Used
Java
Spring Boot
Spring WebSocket
STOMP (Simple Text Oriented Messaging Protocol)
Thymeleaf (for frontend UI)
Getting Started
Prerequisites
JDK 11 or higher
Maven or Gradle
IDE (IntelliJ IDEA, Eclipse, or similar)
Installation
Clone the repository:

bash
Copy code
git clone https://github.com/your-username/spring-websocket-implementation.git
cd spring-websocket-implementation
Build the application:

bash
Copy code
mvn clean install
Run the application:

bash
Copy code
mvn spring-boot:run
Access the application at http://localhost:8080.

WebSocket Configuration
The WebSocket endpoint is configured in WebSocketConfig.java, and it enables STOMP messages over WebSocket.

Example:

java
Copy code
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
    }
}
How It Works
Endpoint: /ws is used as the WebSocket endpoint.
Message Broker: /app is used as the prefix for messages bound for methods annotated with @MessageMapping.
Subscriptions: Messages from the server are broadcasted to clients subscribed to /topic.
Frontend Usage
The frontend is a simple HTML page with JavaScript, using SockJS and Stomp.js libraries for WebSocket connections.

Example:

html
Copy code
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1.5.0/dist/sockjs.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/stomp-websocket@2.3.3/lib/stomp.min.js"></script>

<script>
    const socket = new SockJS('/ws');
    const stompClient = Stomp.over(socket);

    stompClient.connect({}, (frame) => {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/messages', (message) => {
            showMessage(JSON.parse(message.body).content);
        });
    });

    function sendMessage() {
        stompClient.send("/app/chat", {}, JSON.stringify({'content': $("#message").val()}));
    }
</script>
Testing WebSocket
Run the application and navigate to the frontend.
Open multiple browser windows/tabs to simulate multiple users.
Send messages and see real-time updates across the open tabs.
License
This project is licensed under the MIT License. See the LICENSE file for details.

Contributing
Contributions are welcome! Please open an issue or submit a pull request for any improvements or fixes.

