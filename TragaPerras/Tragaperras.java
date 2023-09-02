import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JOptionPane;

public class Tragaperras {
    static int generateBet(int MAX){
        // int toBet;      
        int prospectiveBet = MAX + 1;
        // The following runs at least once. It just makes sure that we are 
        // betting Integers, and a number that we can pay. 
        String dummy;
        do {
            dummy = JOptionPane.showInputDialog(null,"¿Cuanto desea apostar?","¿Cuanto desea apostar?",0);
            if (dummy !=null){
                try {  prospectiveBet =  Integer.parseInt(dummy);}
                catch (NumberFormatException error){  continue; }
            }
            // Handling when we are for some reason betting negative numbers, which is frankly daft.
            if (prospectiveBet < 0) {
                prospectiveBet = MAX + 1;
            }
        } while (prospectiveBet > MAX);
        return prospectiveBet;
        }
    static byte generateSlot(){
        // generates a random number between 1 nd 13
        // I'm using bytes for I neednt use 4 of them for numbers as small as 1101
        return (byte) ThreadLocalRandom.current().nextInt(1, 14);
    }
    static byte[] generateSlots()
    {
        // I use bytes cuz there
        byte[] dummy = {generateSlot(), generateSlot(), generateSlot()};
        return dummy ;
    }
    
    static void welcomeMessage() //None -> IO
    { // a basic welcomeMessage
        JOptionPane.showMessageDialog(null, "Sea usted muy bienvenido a esta Maquina Tragaperras", "Saludo", 0);
    }
    static void goodByeMessage()  // //None -> IO
    { //a Basic goodbye Message
        JOptionPane.showMessageDialog(null, "Gracias por haber jugado a la Maquina Tragaperras", "Despedida", 0);
    }
    
    static int generatePayRate(byte[] slots)  // int -> int
    {
        // this function generates the pay rates according to the HW document
        // the rest is self explanatory.
        boolean areAllEqual = (slots[0] == slots[1] &&  slots[1]== slots[2] );
        if (areAllEqual)
        {
            switch (slots[0]) {
                case 5:
                    return 5;
                case 1:
                    return 7;
                case 9:
                    return 10;
                case 7:
                    return 100;
                default:
                    return 2;
            }
        }
        if ((slots[0] == 7) || (slots[1] == 7) || (slots[2] == 7)){ return 1; }
        return 0;
    }
   
    static void winLoseMessage(byte[] slots, int payBack, int bet,  int credits){
        String res = "";

        String strSlots = "Slot1: " + slots[0] + "; Slot2: " + slots[1] + "; Slot3: " + slots[2] + "\n";
        String title = "Ganaste!!";
        int messageType = JOptionPane.PLAIN_MESSAGE;
        if (bet == 0){
            res = "NOS HAS HECHO PERDER EL TIEMPO";
            title = "¿POR QUE? ";
        }
        else {
            if (payBack == 0){
                messageType = JOptionPane.ERROR_MESSAGE;
                res = "LO SIENTO HAS PERDIDO " ;
                title = "Perdiste";
                res += bet;
            }
            else {  
                messageType = JOptionPane.INFORMATION_MESSAGE;
                res = "FELICIDADES HA GANADO " ;
                title = "Ganaste";
                res += payBack;
        }
        }
        res += "\n";
        res += "Ahora tienes " + credits + " creditos" + "\n";
        JOptionPane.showMessageDialog(null, strSlots + res , title, messageType);
    }
    static boolean obtainKeepPlaying () //
    {
        // Gets whether the user wants to keep playing or not
        // Pressing the Esc key is the only way for the user to stop playing.
        String dummy =  JOptionPane.showInputDialog(null, "¿Quieres jugar de nuevo? \n<Esc> para salir \n<Enter> para continuar", "Input", 0);
        if  (dummy == null){ return false;}
        else { return true; }
    }
    
    public static void main(String[] args){

        int credits = 1000;
        int toBet, payBack;
        byte[] slots ;
        boolean wantsToPlay;

        welcomeMessage();
        do{
            slots = generateSlots();                          //the slots we are currently playing with
            toBet = generateBet(credits);                     //What we wish to bet this round. 
            payBack = toBet * generatePayRate(slots);         //What the pay rate will be for this round
            credits -= toBet;
            credits += payBack ;    
            winLoseMessage(slots, payBack, toBet, credits);   //This just generates the message the user is to see after betting.
            wantsToPlay = obtainKeepPlaying();                //This asks the player if they wish to continue playing.
            if (credits <= 0){
                wantsToPlay = false;
                JOptionPane.showMessageDialog(null, "No queda dinero para apostar.", null, 0);
            }
        }
        while (wantsToPlay);
        goodByeMessage();

    }
}