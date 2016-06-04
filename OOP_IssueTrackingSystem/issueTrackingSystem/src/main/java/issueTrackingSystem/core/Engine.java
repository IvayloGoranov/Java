package issueTrackingSystem.core;

import issueTrackingSystem.interfaces.IDispatcher;
import issueTrackingSystem.interfaces.IEndpoint;
import issueTrackingSystem.interfaces.IEngine;
import issueTrackingSystem.interfaces.IInputHandler;
import issueTrackingSystem.interfaces.IRenderer;

public class Engine implements IEngine {

	private IDispatcher dispatcher;
    
    private IRenderer renderer;
    private IInputHandler inputHandler;
    
    public Engine(IDispatcher dispatcher, IRenderer renderer, IInputHandler inputHandler) {
    	
        this.dispatcher = dispatcher;
        this.renderer = renderer;
        this.inputHandler = inputHandler;
    }

    public Engine(IRenderer renderer, IInputHandler inputHandler){
    	
    	this(new Dispatcher(), renderer, inputHandler);
    }
    
    public void run() {
    	
        while (true) {
            
        	String url = this.inputHandler.nextLine();
            if (url == null) {
            	
                break;
            }
            
            url = url.trim();

            if (url != "") {
            	
                try {
                	
                    IEndpoint endPoint = new Endpoint(url);
                    String viewResult = this.dispatcher.dispatchAction(endPoint);
                    this.renderer.println(viewResult);
                } catch (IllegalStateException ex) {
                	
                    this.renderer.println(ex.getMessage());
                }
                catch (IllegalArgumentException ex) {
                	
                    this.renderer.println(ex.getMessage());
                }
            }
        }
    }
}
