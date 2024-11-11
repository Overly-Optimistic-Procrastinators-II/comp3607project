//Tyrell Lewis- 816036534

import java.util.Comparator;

public class PaidComparator implements Comparator
{
    public PaidComparator()
    {
        
    }
    
    public int compare(Object obj1, Object obj2)
    {
        if (obj1 instanceof Bill && obj2 instanceof Bill)
        {
            Bill b1 = (Bill) obj1;
            Bill b2 = (Bill) obj2;
            
        if (b1.checkPaid().equalsIgnoreCase(b2.checkPaid())) 
        {
            return 0;
        }
        if (b1.checkPaid().equalsIgnoreCase("paid")) 
        {
            return -1;
        }
        return 1;
    }
        
        throw new ClassCastException("Object must be bills for comparison");
    }
}