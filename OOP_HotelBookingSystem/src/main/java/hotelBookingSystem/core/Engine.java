package hotelBookingSystem.core;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;

import org.reflections.Reflections;

import hotelBookingSystem.controllers.Controller;
import hotelBookingSystem.data.HotelBookingSystemData;
import hotelBookingSystem.interfaces.IEndpoint;
import hotelBookingSystem.interfaces.IUser;
import hotelBookingSystem.interfaces.IView;
import hotelBookingSystem.utilities.Constants;

public class Engine {

	public Engine() {
    }
    
    public void startOperation() {
    	
    	HotelBookingSystemData database = new HotelBookingSystemData();
        
    	IUser currentUser = null;
        
        while (true) {
        	
            Scanner scanner = new Scanner(System.in);
        	String url = scanner.nextLine();
            if (url == null) {
            	
                break;
            }

            IEndpoint executionEndpoint = new Endpoint(url);

            Reflections reflections = new Reflections("hotelBookingSystem.controllers");
            Set<Class<? extends Controller>> allControllerClasses = reflections.getSubTypesOf(Controller.class);
            Class<?> controllerType = allControllerClasses.stream().
            		filter(c -> c.getName().equals(executionEndpoint.getControllerName())).findFirst().get();
            
            Method[] controllerMethods = controllerType.getMethods();
            Method action = Arrays.stream(controllerMethods).
            		filter(m -> m.getName().equals(executionEndpoint.getActionName())).findFirst().get();
            
            Object[] parameters = mapParameters(executionEndpoint, action);
            Controller controller = (Controller) controllerType.getConstructors()[0].newInstance(database, currentUser);
            
            String viewResult = "";
            try {
            	
                IView view = (IView) action.invoke(controller, parameters);
                viewResult = view.display();
                currentUser = controller.getCurrentUser();
            } catch (Exception ex) {
            	
                Error errorView = new Error(ex.getCause().getMessage());
                viewResult = errorView.display();
            }

            System.out.println(viewResult);
        }
    }

    private static Object[] mapParameters(IEndpoint executionEndpoint, Method action)
    {
        Class<?>[] parameters = action.getParameterTypes();
        Object[] parsedResults = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
			
        	if (parameters[i] == Integer.class) {
        		
                parsedResults[i] = Integer.parseInt(executionEndpoint.getParameters().get(parameters[i].getName()));
            } else if (parameters[i] == BigDecimal.class) {
            	
            	parsedResults[i] = new BigDecimal(executionEndpoint.getParameters().get(parameters[i].getName()));
            } else if (parameters[i] == LocalDate.class) {
            	
            	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(Constants.DateFormat);
            	parsedResults[i] = LocalDate.parse(executionEndpoint.getParameters().get(parameters[i].getName()), dateFormat);
            } else {
            	
            	parsedResults[i] = executionEndpoint.getParameters().get(parameters[i].getName());
            }
		}		
        
       return parsedResults;
    }
}
