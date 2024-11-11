//Tyrell Lewis- 816036534

/*
 * 
/**
 *
 * @author phaedramohammed
 * @edited by: 
 */
import java.util.ArrayList;
import java.util.Random;

public class PaymentPortalApplication 
{
    public static void main(String[] args)
    {
        PaymentSystem paymentSystem = new PaymentSystem();
        PaymentPortal lennysPaymentPortal = new PaymentPortal(paymentSystem);
        lennysPaymentPortal.setVisible(true);
    }

}
