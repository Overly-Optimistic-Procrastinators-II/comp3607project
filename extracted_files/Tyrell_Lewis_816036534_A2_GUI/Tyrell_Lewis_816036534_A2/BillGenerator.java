//Tyrell Lewis- 816036534

import java.util.Random;
import java.util.ArrayList;

public class BillGenerator
{
     private ArrayList<Bill> billrepo = new ArrayList<Bill>();
     private Random rand = new Random();
     
     public void generateBills()
     {
         String[] types = {"Electric", "Internet", "Water"};
         int typeIndex = rand.nextInt(3);
         double amount = Math.floor((rand.nextDouble()*1000) * 100) / 100;
         int paidIndex = rand.nextInt(2);
         boolean paid = false;
         
         for(int i = 0; i<40; i++)
         {
             billrepo.add(new Bill(types[typeIndex], amount, paid));
             paidIndex = rand.nextInt(2);
             if(paidIndex == 0) 
             {
                 paid = false;
             }
             else
             {
                 paid = true;
             }
             
             typeIndex =rand.nextInt(3);
             amount = Math.floor((rand.nextDouble()*1000) * 100) / 100;
         }
         
         java.util.Iterator<Bill> iter = billrepo.iterator();
         
         while(iter.hasNext())
         {
             System.out.println(iter.next());
         }
     } 
     
     public ArrayList<Bill> getBills()
     {
         
         int numBills = rand.nextInt(4);
         numBills = numBills + 4;
         int x=0;
         int randIndex;
        
        
         ArrayList<Bill> tempBills = new ArrayList<>();
            
            
            if (numBills >= getBillSize()) // Only executes if the number of bills randomly generated for the new user exceeeds the bills currently in billrepo.
            {
                generateBills();
            }
            
            
            for (x=0; x<numBills; x++)
            {
                randIndex = rand.nextInt(getBillSize());
                tempBills.add(this.billrepo.remove(randIndex));
            }
         
         
         return tempBills;
     }
     
     public int getBillSize()
     {
         return this.billrepo.size();
     }
     
     public String toString()
     {
         String message = "";
         
         int size = getBillSize();
         
         for (int x=0; x<size; x++)
         {
             message = message + "\n" + getBills().get(x).toString();
         }
         
         return (message);
     }
}