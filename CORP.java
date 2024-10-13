// Step 1: Define the abstract handler
abstract class SupportHandler {
    protected SupportHandler nextHandler;

    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleRequest(String requestType);
}

// Step 2: Define concrete handlers
class BasicSupportHandler extends SupportHandler {
    @Override
    public void handleRequest(String requestType) {
        if (requestType.equals("Basic")) {
            System.out.println("Basic Support: Handling basic request.");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(requestType);
        }
    }
}

class IntermediateSupportHandler extends SupportHandler {
    @Override
    public void handleRequest(String requestType) {
        if (requestType.equals("Intermediate")) {
            System.out.println("Intermediate Support: Handling intermediate request.");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(requestType);
        }
    }
}

class CriticalSupportHandler extends SupportHandler {
    @Override
    public void handleRequest(String requestType) {
        if (requestType.equals("Critical")) {
            System.out.println("Critical Support: Handling critical request.");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(requestType);
        }
    }
}

// Step 3: Client to trigger the chain of responsibility
public class ChainOfResponsibilityExample {
    public static void main(String[] args) {
        // Create handlers
        SupportHandler basicSupport = new BasicSupportHandler();
        SupportHandler intermediateSupport = new IntermediateSupportHandler();
        SupportHandler criticalSupport = new CriticalSupportHandler();

        // Set up the chain
        basicSupport.setNextHandler(intermediateSupport);
        intermediateSupport.setNextHandler(criticalSupport);

        // Test different request types
        basicSupport.handleRequest("Basic");
        basicSupport.handleRequest("Intermediate");
        basicSupport.handleRequest("Critical");
        basicSupport.handleRequest("Unknown");
    }
}
