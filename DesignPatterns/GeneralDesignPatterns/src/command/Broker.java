package command;

import java.util.ArrayList;
import java.util.List;

public class Broker {
   
   private List<Executable> orderList = new ArrayList<Executable>(); 

   public void takeOrder(Executable order){
      
	   this.orderList.add(order);		
   }

   public void placeOrders(){
   
      for (Executable order : orderList) {
         
    	  order.execute();
      }
      
      orderList.clear();
   }
}
