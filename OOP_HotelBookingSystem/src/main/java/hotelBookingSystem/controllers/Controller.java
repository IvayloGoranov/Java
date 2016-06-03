package hotelBookingSystem.controllers;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.reflections.Reflections;

import hotelBookingSystem.exceptions.AuthorizationFailedException;
import hotelBookingSystem.interfaces.IHotelBookingSystemData;
import hotelBookingSystem.interfaces.IUser;
import hotelBookingSystem.interfaces.IView;
import hotelBookingSystem.models.Role;
import hotelBookingSystem.views.View;

public abstract class Controller {

	private final String NamespaceSeparator = ".";
    private final String ControllerSuffix = "Controller";
    private final String ViewsFolder = "views";
    
    private IHotelBookingSystemData data;
    private IUser currentUser;
    
    public Controller(IHotelBookingSystemData data, IUser user) {
    	
        this.data = data;
        this.setCurrentUser(user);
    }

    public IUser getCurrentUser() {
		
    	return this.currentUser;
	}

	public void setCurrentUser(IUser currentUser) {
		
		this.currentUser = currentUser;
	}

	public boolean hasCurrentUser() { 

    	return this.currentUser != null; 
    }

	public IHotelBookingSystemData getData() {
		
    	return this.data;
	}

	/*Creates and returns an object of type view, that will output information about the result
	 * of the action performed by the system user.
	 * @param model Models are classes containing information about the real-world object the system works with. 
	 * @return Views contain the presentation logic in the system. They contain the results which are given to the user.
	 */
    protected IView view(Object model) 
    		throws InstantiationException, IllegalAccessException, IllegalArgumentException, 
    		InvocationTargetException, SecurityException {
    	
        String fullNamespace = this.getClass().getPackage().getName();
        int firstSeparatorIndex = fullNamespace.indexOf(NamespaceSeparator);
        String baseNamespace = fullNamespace.substring(0, firstSeparatorIndex);
        
        String controllerName = this.getClass().getName().replaceAll(ControllerSuffix, "");
        
        //The last element of the array represents the bottom of the stack, 
        //which is the least recent method invocation in the sequence.
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        String actionName = stackTraceElements[1].getMethodName();
        String capitalaziedActionName = actionName.substring(0, 1).toUpperCase() + actionName.substring(1);
        
        String fullPath = StringUtils.join(
        		new String[] { baseNamespace, ViewsFolder, controllerName, capitalaziedActionName }, NamespaceSeparator);

        Reflections reflections = new Reflections("hotelBookingSystem.views");
        Set<Class<? extends View>> allViewClasses = reflections.getSubTypesOf(View.class);
        Class<?> viewType = allViewClasses.stream().filter(v -> v.getName().equals(fullPath)).findFirst().get();
        
        View view = (View) viewType.getConstructors()[0].newInstance(model);
        
        return view;
    }

    /*Checks whether the current system user is authorized to perform a certain action.
     * @param roles The role of the current system user in the system.
     */
    protected void authorize(Role... roles) {
    	
        if (!this.hasCurrentUser()) {
        	
            throw new IllegalArgumentException("There is no currently logged in user.");
        }

        if (!Arrays.stream(roles).anyMatch(role -> this.getCurrentUser().isInRole(role))) {
        	
            throw new AuthorizationFailedException
                ("The currently logged in user doesn't have sufficient rights to perform this operation.", this.getCurrentUser());
        }
    }

    protected void ensureNoLoggedInUser() {
    	
        if (this.hasCurrentUser())
        {
            throw new IllegalArgumentException("There is already a logged in user.");
        }
    }
}
