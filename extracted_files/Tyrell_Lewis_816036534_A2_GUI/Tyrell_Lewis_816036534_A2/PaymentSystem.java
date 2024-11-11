//Tyrell Lewis- 816036534

import java.util.Collection;
import java.util.ArrayList;
import java.util.HashSet;

public class PaymentSystem
{
    private Collection<Renter> renters;
    private BillGenerator billGenerator;
    
    public PaymentSystem()
    {
        this.renters = new HashSet<>();
        
        billGenerator = new BillGenerator();
        billGenerator.generateBills();
    }
    
    private Renter getRenter (Renter r)
    {
        
        
        if (renters.contains(r))
        {
            for (Renter renter: renters)
            {
                if (renter.equals(r))
                {
                    return renter;
                }
            }
        }
        
        return null;
    }
    
    public String[] getRenterBills(String userName, String password)
    {
        Renter temp = new Renter(userName, password);
        
        Renter r = getRenter(temp);
        
        if (r != null)
        {
            return r.getBills();
        }
        
        return new String[0];
    }
    
    public String[] getSortedRenterBillsByAmount(String userName, String password)
    {
        Renter temp = new Renter(userName, password);
        
        Renter r = getRenter(temp);
        
        if (r != null)
        {
            return r.getSortedBillsByAmount();
        }
        
        return new String[0];
    }
    
    public String[] getSortedRenterBillsByPaid (String userName, String password)
    {
        Renter temp = new Renter(userName, password);
        
        Renter r = getRenter(temp);
        
        if (r != null)
        {
            return r.getSortedBillsByPaid();
        }
        
        return new String[0];
    }
    
    public boolean registerRenter (String userName, String password)
    {
        Renter temp = new Renter (userName, password);
        
        if (renters.contains(temp))
        {
            return false;
        }
        else
        {   
            
            Renter newRenter = new Renter (userName, password, billGenerator.getBills());
            
            renters.add (newRenter);
   
            return true;
        }
        
    }
    
    public boolean validateRenter (String userName, String password)
    {
        Renter temp = new Renter (userName, password);
        
        for (Renter r : renters)
        {
            if (r.getUserName().equals(userName) && r.getPassword().equals(password))
            {
                return true;
            }
        }
        
        return false;
    }
    
}