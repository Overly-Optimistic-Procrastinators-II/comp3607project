//Tyrell Lewis- 816036534

public class Bill implements Comparable
{
    private String billNumber;
    private static int nextBillNumber = 1000;
    private double amount;
    private String type;
    private boolean paid;
    
    public Bill(String type, double amount, boolean paid)
    {
        this.billNumber = Integer.toString(nextBillNumber);
        nextBillNumber +=1;
        
        this.amount = amount;
        this.type = type;
        this.paid = paid;
    }
    
    public String getBillNumber()
    {
        return this.billNumber;
    }
    
    public double getAmount()
    {
        return this.amount;
    }
    
    public String getType()
    {
        return this.type;
    }
    
    public String checkPaid()
    {
        if (this.paid == true)
        {
            return ("PAID");
        }
        else
        {
            return ("UNPAID");
        }
    }
    
    public boolean equals (Object obj)
    {
        
        if (obj instanceof Bill)
        {
            Bill b = (Bill) obj;
            return b.billNumber.equals(this.billNumber);
        }
        throw new IllegalArgumentException ("Object must be a bill for equality");
    }
    
    public String toString()
    {
        return (getBillNumber() + "  " + getType() + "  $" + getAmount() + "  " + checkPaid());
    }
    
    public int hashCode()
    {
        return this.toString().hashCode();
    }
    
    public int compareTo (Object obj)
    {
        if (obj instanceof Bill)
        {
            Bill b = (Bill) obj;
            return this.billNumber.compareTo(b.billNumber);
        }
        throw new ClassCastException ("Object must be a Bill for comparison");
    }
}