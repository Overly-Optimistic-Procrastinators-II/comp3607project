//Tyrell Lewis- 816036534

import java.util.Collection;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.List;
import java.util.Collections;

public class Renter
{
    private String userName;
    private String password;
    
    private Collection<Bill> bills;
    private Collection<Bill> billsByAmount;
    private Collection<Bill> billsByPaid;
    
    public Renter(String userName, String password)
    {
        this.userName = userName;
        this.password = password;
        this.bills = new ArrayList<>();
    }
    
    public Renter (String userName, String password, Collection<Bill> bills)
    {
        
        this.userName = userName;
        this.password = password;
        
        this.bills = new ArrayList<>(bills);
        
    }
    
    public String getUserName()
    {
        return this.userName;
    }
    
    public String getPassword()
    {
        return this.password;
    }
    
    public boolean equals (Object obj)
    {
        
        if (obj instanceof Renter)
        {
            Renter r = (Renter) obj;
            
            boolean result=  (r.userName.equals(this.userName) && r.password.equals(this.password));
            return result;
        }
        
        throw new IllegalArgumentException ("Object must be a renter for equality");
    }
    
    public int hashCode()
    {
        return userName.hashCode();
    }
    
    public String[] getBills()
    {
        
        int x=0;
        
        List<Bill> billByBillNumber = new ArrayList<>(bills);
        
        Collections.sort(billByBillNumber); 
        
        int size = billByBillNumber.size();
        
        String[] result = new String[size];
         
        for (Bill b : billByBillNumber)
        {
            
            result[x] = b.toString();
            x++;
        }
        
        return result;
    }
    
    public String[] getSortedBillsByAmount()
    {
        AmountComparator amtComp = new AmountComparator();
        
        billsByAmount = new TreeSet<>(amtComp);
        
        billsByAmount.addAll(bills);
        
        int size = billsByAmount.size();
        
        String[] result = new String[size];
        
        int x=0;
        
        for (Bill b : billsByAmount)
        {
            result[x] = b.toString();
            x++;
        }
        
        return result;
        
    }
    
    public String[] getSortedBillsByPaid()
    {
        PaidComparator paidComp = new PaidComparator();
        
        List<Bill> billsByPaid = new ArrayList<>(bills);
        
        billsByPaid.sort(paidComp);
        
        int size = billsByPaid.size();
        
        String[] result = new String[size];
        
        int x=0;
        
        for (Bill b : billsByPaid)
        {
            result[x] = b.toString();
            x++;
        }
        
        return result;
    }
}