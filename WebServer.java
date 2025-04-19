import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class SimpleWebServer {

    public static void main(String[] args) throws IOException {
        // Create HTTP server on port 8000
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        
        // Set the default handler for the server
        server.createContext("/", new MyHandler());
        
        // Start the server
        server.start();
        
        System.out.println("Server started on http://localhost:8000");
    }

    // Handler class for processing requests
    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Prepare the response
            String response = "Hello, World!";
            
            // Set the response headers and status code
            exchange.sendResponseHeaders(200, response.getBytes().length);
            
            // Write the response
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
