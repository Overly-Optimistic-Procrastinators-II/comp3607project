//Tyrell Lewis- 816036534

import java.util.Comparator;

public class AmountComparator implements Comparator
{
    public AmountComparator()
    {
        
    }
    
    public int compare(Object obj1, Object obj2)
    {
        if (obj1 instanceof Bill && obj2 instanceof Bill)
        {
            Bill b1 = (Bill) obj1;
            Bill b2 = (Bill) obj2;
            
            if (b1.getAmount() == b2.getAmount())
            {
                return 0;
            }
            if (b1.getAmount() > b2.getAmount())
            {
                return 1;
            }
            if (b1.getAmount() < b2.getAmount())
            {
                return -1;
            }
        }
        
        throw new ClassCastException("Object must be bills for comparison");
    }
}