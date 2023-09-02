// correct substituteAccentMarks


import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JOptionPane;
class generadorDeCurp{
    static int isInStringArray(String[] arr, String toCheck){
        // If a string is in the string array it returns where it is, else, -1
        int i;
        for (i = 0; i<(arr.length); i++){
                if (toCheck.equals(arr[i]) ){return i;}
        }
        return -1;
    }
    static String getFirstVocal(String str){
        String[] vocals = {"A", "I", "U", "E", "O"};
        String[] huhu = str.toUpperCase().split("");
        int dummy;
        for (String letter : huhu){
                dummy = isInStringArray(vocals, letter);
                if (dummy >-1){return vocals[dummy];}  
        }
        return "A";  //A is a pretty nice Vocal
    }
    static String substituteAccentMarks(String withMarks)
    {
        String[] withAccents = {"á","é","í","ó","ú","Á","É","Í","Ó","Ú"};
        String[] unnaccented = {"A","E","I","O","U"};
        String toReturn = "";
        int dummy = 0;
        for (String string : withMarks.split("")) {
                dummy = isInStringArray(withAccents, string);
                if (dummy>-1){toReturn += unnaccented[dummy%5];}  //this gets the unnaccented version of the letter and gets it there.
                else {toReturn += string.toUpperCase();}
        }
        return toReturn;
    }
    static String nameGetter(int nameIndex, int nameOrLastName) //a generic prompt to get either a first or a last name
    {
        String[] names = {"primer", "segundo"};
        String[] lastFirst = {"nombre de pila", "apellido"};
        String messageString = "Captura el " + names[nameIndex] + " " + lastFirst[nameOrLastName];
        String name = JOptionPane.showInputDialog(null, "Input", messageString, 0);
        name = name.toUpperCase();
        return nameHandler(name);
    }
    static int getIntInRange(int input, int down, int up){ //checks if a number is in a range. If it is, it gives it back If its not, it gives back the lowest bound of the range.
        if (input>=down && input<=up){return input;}return down;}
    static String getEitherDMY(int dayMonthYearIdentifier){
        int lower, upper;
        String comp = "";
        String dummy;
        int dumber = -1;
        boolean flag = false;
        switch (dayMonthYearIdentifier){
                case 0:         {lower = 1; upper = 31; comp = "día";break;}
                case 1:         {lower = 1; upper = 12; comp = "mes"; break;}
                case 2:         {lower = 1920; upper = 2024; comp = "año"; break;}
            default:
                {return "There was a mistake somewhere";}
        }
        do {
                // validating that what we get is actually a date
                dummy = JOptionPane.showInputDialog(null, "Captura el " + comp + " de nacimiento", "Input", JOptionPane.INFORMATION_MESSAGE);
                if (dummy !=null){
                    try { dumber =  Integer.parseInt(dummy);  }
                    catch (NumberFormatException error){        continue;   }
                    flag = true;  }
        } while (!flag);
        String intermediateValidation = Integer.toString(getIntInRange(dumber, lower, upper));
        dumber = Integer.parseInt(intermediateValidation);
        if (dumber < 10){ intermediateValidation = "0" + intermediateValidation;} //this makes sure we dont have formatting issues down the line
        return intermediateValidation;
    }
    static int reduceStringModule10(String toReduce){
        // this gets a string that contains some digits
        // adds its digits to a dummy variable, and returns that modulo ten
        int len = toReduce.length(); 
        int i ;
        int total = 0;
        for (i = 0; i < len; i+=1){
            total += (int) (toReduce.charAt(i)) - 48;
        }
        if (total >= 10){
            return reduceStringModule10(Integer.toString(total));
        }
        return total;
    }
    static String getVerifierDigit(int year, String formatedDates){
        return String.valueOf(reduceStringModule10(formatedDates));
    }

    static String nameHandler(String aPossibleName){
        String toReturn ="";
        try
        { aPossibleName.equals(toReturn);}
        catch (NullPointerException e){
                return toReturn;
        }
        return aPossibleName;

    }
    static void deleteMariaJose(String[] otherwiseSanitizedString){
        // edits the string in place
        if (otherwiseSanitizedString[0].equals("JOSE") && !otherwiseSanitizedString[1].equals("PLACEHOLDER")){
            otherwiseSanitizedString[0] = otherwiseSanitizedString[1];
        }
        if (otherwiseSanitizedString[0].equals("MARIA") && !otherwiseSanitizedString[1].equals("PLACEHOLDER")){
            otherwiseSanitizedString[0] = otherwiseSanitizedString[1];
        }
    }
    static String deletePrepositions (String aPossibleName) {
        if (aPossibleName.equals("")){
            return "PLACEHOLDER";
        }
        String aNewName =  "";
        int len = aPossibleName.length();
        int position = 0;
        boolean dumbBool;
        while (position < len){
            dumbBool = deletePrepositionHelper(aPossibleName, position);
            if (dumbBool){
                position += 3;
            }
            else {
                aNewName += aPossibleName.charAt(position);
                position += 1;
            }
        }
        return aNewName;
    } 
    static boolean deletePrepositionHelper(String aPossibleName, int position){
        // This is the regex I had originally planned to use. Oh well. ((DE|LA|LE|Y)\s)*
        if (position == aPossibleName.length() -1 ){
            return false;
        }
        char first, second, third;
        first = aPossibleName.charAt(position);
        second = aPossibleName.charAt(position + 1);
        if (second == ' '){
            if (first == 'Y') { return true;}
            return false;
        }
        try { third = aPossibleName.charAt(position + 2);} 
        catch (Exception e) {
            return false;
        }
        if (first == ' '){  
            
            //if this are our last three characters we'd like to remove this preposition too. Its otherwise a normal name
            //  if it isnt, it will be caught in a next run
            
            return  equalsPreposition(second + third + "") && (position == aPossibleName.length());
        }
        
        return equalsPreposition("" + first + second);
    }
    static boolean equalsPreposition(String aString){
        switch(aString){
            case "DE":
            case "EL":
            case "LA":
            case "Y ":
            case "LE":
                return true;
            default:
                {return false;}
        }
    }
    static String getStateId(String aPossibleState )
    {
        String possibleState = substituteAccentMarks(aPossibleState.toUpperCase().replaceAll(" ", ""));
        if (possibleState.length() < 4){
            return "NE";
        } 
        String dummy = "NE";
        String i = "" + possibleState.charAt(0);
        String ii = "" + possibleState.charAt(1);
        String iii = "" + possibleState.charAt(2);
        switch (i){
        case "A" :{ if(possibleState.equals("AGUASCALIENTES")){return "AS";}return dummy;}
        case "B" :{ if(possibleState.equals( "BAJACALIFORNIA" )){return "BC";} if(possibleState.equals( "BAJACALIFORNIASUR" )){return "BS";}return dummy;}
        case "C" :{ switch (ii){
                        case "A" :{if(possibleState.equals( "CAMPECHE" )){return "CC";}return dummy;}	
                        case "H" :{if(possibleState.equals( "CHIHUAHUA" )){return "CH";}return dummy;}	
                        case "I" :{if(possibleState.equals( "CIUDADDEMEXICO" )){return "DF";}return dummy;}	
                        case "O" :{ switch (iii){
                                        case "A" :{if(possibleState.equals( "COAHUILADEZARAGOZA" )){return "CL";}return dummy; }
                                        case "L" :{if(possibleState.equals( "COLIMA" )){return "CM";}return dummy;  }
                                        default : {return dummy;}}}}}
        case "D" :{ if(possibleState.equals( "DURANGO" )){return "DG";} return dummy;}	
        case "G" :{ if(possibleState.equals( "GUANAJUATO" )){return "GT";}if(possibleState.equals( "GUERRERO" )){return "GR";}return dummy;}
        case "H" :{ if(possibleState.equals( "HIDALGO" )){return "HG";}return dummy; }
        case "J" :{ if(possibleState.equals( "JALISCO" )){return "JC";}return dummy; }
        case "M" :{ switch (ii){
                        case "E" :{ if(possibleState.equals( "MEXICO" )){return "MC";}return dummy; }
                        case "I" :{ if(possibleState.equals( "MICHOACANDEOCAMPO" )){return "MN";}return dummy; }
                        case "O" :{ if(possibleState.equals( "MORELOS" )){return "MS";}return dummy; }
                        default: {return dummy;}        
                        }}
        case "N" :{switch (ii){
                        case "A" :{ if(possibleState.equals( "NAYARIT" )){return "NT";}return dummy; }
                        case "U" :{ if(possibleState.equals( "NUEVOLEON" )){return "NL";}return dummy; }
                        }}
        case "O" :{ if(possibleState.equals( "OAXACA" )){return "OC";}return dummy; }
        case "P" :{ if(possibleState.equals( "PUEBLA" )){return "PL";}return dummy; }
        case "Q" :{ if (!ii.equals("U")){return dummy;}  if(possibleState.equals( "QUERETARO" )){return "QT";}if(possibleState.equals( "QUINTANAROO" )){return "QR";} return dummy; }
        case "S" :{switch (ii){
                        case "A" :{ if(possibleState.equals( "SANLUISPOTOSI" )){return "SP";}return dummy; }
                        case "I" :{ if(possibleState.equals( "SINALOA" )){return "SL";}return dummy; }
                        case "O" :{ if(possibleState.equals( "SONORA" )){return "SR";}return dummy; }
                        default : {return dummy;}}}
        case "T" :{ switch (ii){
                        case "A" :{switch (iii){
                                        case "B" : { if(possibleState.equals( "TABASCO" )){return "TC";}return dummy; }
                                        case "M" :{ if(possibleState.equals( "TAMAULIPAS" )){return "TS";}return dummy; }
                                        default : {return dummy;}    }}
                        case "L" :{ if(possibleState.equals( "TLAXCALA" )){return "TL";}return dummy; }
                        default : {return dummy;}}}
        case "V" :{ if(possibleState.equals( "VERACRUZDEIGNACIODELALLAVE" )){return "VZ";}return dummy; }
        case "Y" :{ if(possibleState.equals( "YUCATAN" )){return "YN";}return dummy; }
        case "Z" :{ if(possibleState.equals( "ZACATECAS" )){return "ZS";} return dummy; }
        default: {return dummy;}
        }
}

    
    static String getGender(){
        String def = "M";
        String gender = JOptionPane.showInputDialog(null, "Captura genero", "def", 0);
        gender = gender.toUpperCase();
        if (gender.charAt(0)!='M' && gender.charAt(0)!='H'){
                return def;
        }
        return Character.toString(gender.charAt(0));
    }   
    static String getState(){
        return JOptionPane.showInputDialog(null, "Captura Estado", "", 0);
    }
    static String getRandomDigit(int year){
        if(year > 1999){
            return "" + (char) ThreadLocalRandom.current().nextInt(65,93);
        }
        return "" + ThreadLocalRandom.current().nextInt(0,10);
    }
    static String namePrinter(String[] nombreApellido){
        String dummy = "";
        dummy += nombreApellido[0] + " ";
        if (!nombreApellido[0].equals(nombreApellido[1])){
            dummy+=  nombreApellido[1] + " " ;
        }
        dummy += nombreApellido[2] + " ";
        if (!nombreApellido[2].equals(nombreApellido[3])){
            dummy+= nombreApellido[3]  + " ";
        }
        return dummy  + " ";
    }
    public static void main(String[] args){
        
        String curp = "";
        String fechaNacimiento, genero, entidadFederativa, digitoAleatorio, digitoVerificador;
        String dummy;
        String[] rawName = {(nameGetter(0, 0)), nameGetter(1, 0),
                            nameGetter(0,1), nameGetter(1,1)};
       
        String[] nombreApellido = {substituteAccentMarks(rawName[0]), substituteAccentMarks(rawName[1]), substituteAccentMarks(rawName[2]), substituteAccentMarks(rawName[3])};
        
        nombreApellido[0] = deletePrepositions(nombreApellido[0]);
        nombreApellido[1] = deletePrepositions(nombreApellido[1]);
        nombreApellido[2] = deletePrepositions(nombreApellido[2]);
        nombreApellido[3] = deletePrepositions(nombreApellido[3]);
        if (nombreApellido[1].equals("PLACEHOLDER")){ nombreApellido[1] = nombreApellido[0];};
        if (nombreApellido[3].equals("PLACEHOLDER")){ nombreApellido[3] = nombreApellido[2];};
        // Manejo del caso María/(José) como primer nombre
        deleteMariaJose(nombreApellido);

        // Gets Y M D
        String[] nacimiento = {getEitherDMY(2) , getEitherDMY(1) ,getEitherDMY(0)};
        // and flips it for I made all of my code work with D M Y
        dummy = nacimiento[0];
        nacimiento[0] = nacimiento[2];
        nacimiento[2] = dummy;

        // fecha en año més y dia. Como se pidio. Im sory if I did the rest of the comments in english. I found it to be better but accidentally used spanish here anyways
        fechaNacimiento = nacimiento[2].substring(2,4)  + nacimiento[1] + nacimiento[0];
        genero = getGender();
        entidadFederativa = getStateId(getState());
        digitoAleatorio = getRandomDigit(Integer.parseInt(nacimiento[2]));
        digitoVerificador = getVerifierDigit(Integer.parseInt(nacimiento[2]), fechaNacimiento);


        // Primera  Letra del Primer apellido , primera vocal del primer apellido y primera letra del segundo apellido
        curp = Character.toString(nombreApellido[2].charAt(0) ) + getFirstVocal(nombreApellido[2]) + Character.toString(nombreApellido[3].charAt(0));
        // Primera letra del nombre de pila
        curp +=  Character.toString(nombreApellido[0].charAt(0));
        // fecha de nacimiento, género y entidadFederativa de nacimiento.
        curp += fechaNacimiento + genero + entidadFederativa;
        // tercer caracter del primer apellido, tercer caracter del segundo apellido y tercer caracter del nombre de pila
        curp += Character.toString(nombreApellido[2].charAt(2)) +
                Character.toString(nombreApellido[3].charAt(2)) + 
                Character.toString(nombreApellido[0].charAt(2));
        
        curp += digitoAleatorio + digitoVerificador;
        System.out.println(curp);
        
        JOptionPane.showMessageDialog(null, "El CURP  de " + namePrinter(rawName) + "es: \n " + curp, "Generador de CURP", 0);
    }
}
// 