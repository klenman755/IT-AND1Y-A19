package hello;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.ClosedBill;
import model.PLU;
import model.PluGroup;
import model.User;

@RestController
public class MobileWaiterController {

    
    
    @RequestMapping("/getPluGroups")
    public PluGroup[] getSoldItems(){
    	
    	
    	return LocalDataStorage.getInstance().getPluGroups();
    }
    
    @RequestMapping("/getPLUs")
    public PLU[] getPLUs(){
    	
    	
    	 return LocalDataStorage.getInstance().getPLUs();
    }
    
    @RequestMapping("/getUsers")
    public User[] getUsers(){
    	
    	
    	 return LocalDataStorage.getInstance().getUsers();
    }
    
    
    @RequestMapping(value = "/login")
    public User login(@RequestParam(value = "quickPass") String quickPass) {
    	   return LocalDataStorage.getInstance().login(quickPass);
    }
    
    @RequestMapping(value = "/getSales")
    public BigDecimal getSales(@RequestParam(value = "id") String employeeId) {
       try {
    	   return LocalDataStorage.getInstance().getSalesOfEmployee(employeeId);
	} catch (Exception e) {
		return BigDecimal.ZERO;
	}
    }
    
   
    @PostMapping(value = "/sendBill")
    public int sendBill(@RequestBody ClosedBill closedBill) {
       try {
    	   LocalDataStorage.getInstance().addClosedBill(closedBill);
    	   return 0;
	} catch (Exception e) {
		return 1;
	}
    }
}