package com.example.nfcmedical;
import java.util.HashMap;
public class DataStructuring {

    //Receive data from the basic form and translate that to NFC-structured data
    public String condense(
            char[] multichars, //For non-bool answers
            boolean[] doDont,  //For bool answers
            String[] customTextInput) //For custom user text input
    {
        //This array will be what we use to write into the NFC tag; we initialize it filled with blanks
        char[] nfcBlock = new char[518];
        for(int i=0;i<517;i++) nfcBlock[i]=' ';
        //Contained in these curly brackets are the assignments to nfcBlock[]
        {
            //Blood Type
            nfcBlock[0] = multichars[0];
            //Sulfa Drugs Ag
            if (doDont[0]) nfcBlock[5] = '1';
            else nfcBlock[5] = '0';
            //Penicillin Ag
            if (doDont[1]) nfcBlock[6] = '1';
            else nfcBlock[6] = '0';
            //Amoxicillin Ag
            if (doDont[2]) nfcBlock[7] = '1';
            else nfcBlock[7] = '0';
            //NSAIDs Ag
            if (doDont[3]) nfcBlock[8] = '1';
            else nfcBlock[8] = '0';
            //General Anesthetics Ag
            if (doDont[4]) nfcBlock[9] = '1';
            else nfcBlock[9] = '0';
            //Peanut Ag
            if (doDont[5]) nfcBlock[10] = '1';
            else nfcBlock[10] = '0';
            //Bee Sting Ag
            if (doDont[6]) nfcBlock[11] = '1';
            else nfcBlock[11] = '0';
            //Latex Ag
            if (doDont[7]) nfcBlock[12] = '1';
            else nfcBlock[12] = '0';
            //Other Ag (t/f & custom entry)
            if (doDont[8]) {
                nfcBlock[29] = '1';
                for (int i = 0; i < customTextInput[3].length(); i++) {
                    nfcBlock[101 + i] = customTextInput[3].charAt(i);
                }
            } else nfcBlock[29] = '0';
            //Carries Epi-Pen?
            if (doDont[9]) nfcBlock[13] = '1';
            else nfcBlock[13] = '0';
            //Asthma
            if (doDont[10]) nfcBlock[14] = '1';
            else nfcBlock[14] = '0';
            //Diabetes
            nfcBlock[2] = multichars[2];
            //Atrial Fibrillation
            if (doDont[11]) nfcBlock[15] = '1';
            else nfcBlock[15] = '0';
            //Coronary Artery Disease
            if (doDont[12]) nfcBlock[16] = '1';
            else nfcBlock[16] = '0';
            //Congestive Heart Failure
            if (doDont[13]) nfcBlock[17] = '1';
            else nfcBlock[17] = '0';
            //Stroke
            if (doDont[14]) nfcBlock[18] = '1';
            else nfcBlock[18] = '0';
            //Hypertension
            if (doDont[15]) nfcBlock[19] = '1';
            else nfcBlock[19] = '0';
            //Bleeding Disorder (t/f & custom entry)
            if (doDont[16]) {
                nfcBlock[30] = '1';
                for (int i = 0; i < customTextInput[4].length(); i++) {
                    nfcBlock[121 + i] = customTextInput[4].charAt(i);
                }
            } else nfcBlock[30] = '0';
            //Epilepsy
            if (doDont[17]) nfcBlock[20] = '1';
            else nfcBlock[20] = '0';
            //Seizure Disorder
            if (doDont[18]) nfcBlock[21] = '1';
            else nfcBlock[21] = '0';
            //CKD
            nfcBlock[1] = multichars[1];
            //Dementia
            if (doDont[19]) nfcBlock[22] = '1';
            else nfcBlock[22] = '0';
            //Bipolar Disorder
            if (doDont[20]) nfcBlock[23] = '1';
            else nfcBlock[23] = '0';
            //Schizophrenia
            if (doDont[21]) nfcBlock[24] = '1';
            else nfcBlock[24] = '0';
            //PTSD
            if (doDont[22]) nfcBlock[25] = '1';
            else nfcBlock[25] = '0';
            //Active Cancer (t/f & custom entry)
            if (doDont[23]) {
                nfcBlock[31] = '1';
                for (int i = 0; i < customTextInput[5].length(); i++) {
                    nfcBlock[151 + i] = customTextInput[5].charAt(i);
                }
            } else nfcBlock[31] = '0';
            //Missing Organ (t/f & custom entry)
            if (doDont[24]) {
                nfcBlock[32] = '1';
                for (int i = 0; i < customTextInput[6].length(); i++) {
                    nfcBlock[181 + i] = customTextInput[6].charAt(i);
                }
            } else nfcBlock[32] = '0';
            //Transplanted Organ (t/f & custom entry)
            if (doDont[25]) {
                nfcBlock[33] = '1';
                for (int i = 0; i < customTextInput[7].length(); i++) {
                    nfcBlock[211 + i] = customTextInput[7].charAt(i);
                }
            } else nfcBlock[33] = '0';
            //Other Life-Threatening Conditions (t/f & custom entry)
            if (doDont[26]) {
                nfcBlock[34] = '1';
                for (int i = 0; i < customTextInput[8].length(); i++) {
                    nfcBlock[241 + i] = customTextInput[8].charAt(i);
                }
            } else nfcBlock[34] = '0';
            //Other Communicative Conditions (t/f & custom entry)
            if (doDont[27]) {
                nfcBlock[35] = '1';
                for (int i = 0; i < customTextInput[9].length(); i++) {
                    nfcBlock[301 + i] = customTextInput[9].charAt(i);
                }
            } else nfcBlock[35] = '0';
            //Pacemaker
            if (doDont[28]) nfcBlock[26] = '1';
            else nfcBlock[26] = '0';
            //Defibrillator
            if (doDont[29]) nfcBlock[27] = '1';
            else nfcBlock[27] = '0';
            //Insulin Pump
            if (doDont[30]) nfcBlock[28] = '1';
            else nfcBlock[28] = '0';
            //Other Implant (t/f & custom entry)
            if (doDont[31]) {
                nfcBlock[36] = '1';
                for (int i = 0; i < customTextInput[10].length(); i++) {
                    nfcBlock[361 + i] = customTextInput[10].charAt(i);
                }
            } else nfcBlock[36] = '0';
            //Anticoagulant (t/f & custom entry)
            if (doDont[32]) {
                nfcBlock[37] = '1';
                for (int i = 0; i < customTextInput[11].length(); i++) {
                    nfcBlock[391 + i] = customTextInput[11].charAt(i);
                }
            } else nfcBlock[37] = '0';
            //Anti-Seizure Med (t/f & custom entry)
            if (doDont[33]) {
                nfcBlock[38] = '1';
                for (int i = 0; i < customTextInput[12].length(); i++) {
                    nfcBlock[421 + i] = customTextInput[12].charAt(i);
                }
            } else nfcBlock[38] = '0';
            //Diabetic Med (t/f & custom entry)
            if (doDont[34]) {
                nfcBlock[39] = '1';
                for (int i = 0; i < customTextInput[13].length(); i++) {
                    nfcBlock[451 + i] = customTextInput[13].charAt(i);
                }
            } else nfcBlock[39] = '0';
            //Narcotic (t/f & custom entry)
            if (doDont[35]) {
                nfcBlock[40] = '1';
                for (int i = 0; i < customTextInput[14].length(); i++) {
                    nfcBlock[481 + i] = customTextInput[14].charAt(i);
                }
            } else nfcBlock[40] = '0';
            //Include Name?
            if (doDont[36]) nfcBlock[3] = '1';
            else nfcBlock[3] = '0';
            //Include ICE Number?
            if (doDont[37]) nfcBlock[4] = '1';
            else nfcBlock[4] = '0';
            //User ID
            for (int i = 0; i < 6; i++) {
                nfcBlock[511 + i] = customTextInput[15].charAt(i);
            }
        }
        return encode(nfcBlock);
    }

    //char array -> String, String written into NFC
    private String encode(char[] material)
    {
        //Concatenate the array of chars into a single string
        StringBuilder writable = new StringBuilder(String.valueOf(material[0]));
        for(int i=1;i<517;i++) {
            writable.append(material[i]);
        }
        return writable.toString();
    }

    //NFC data read into String, String -> char array
    public char[] decode(String nfcData)
    {
        String theReadData=nfcData;
        char[] nfcBlock = new char[517];

        /*
        Place the data read from the NFC into the String
        */

        //Convert the string to a char array
        for(int i=0;i<517;i++)
        {
            nfcBlock[i] = theReadData.charAt(i);
        }

        //Return the char array to the profile display
        return nfcBlock;
    }

    //Take the scanned data char array and translate into Strings for the basic profile display
    public String[] expandStrings(char[] material)
    {
        String[] stringsFor = new String[19];

        {
            //Blood Type
            switch (material[0]) {
                case 'A':
                    stringsFor[3] = "A+";
                    break;
                case 'a':
                    stringsFor[3] = "A-";
                    break;
                case 'B':
                    stringsFor[3] = "B+";
                    break;
                case 'b':
                    stringsFor[3] = "B-";
                    break;
                case 'C':
                    stringsFor[3] = "AB+";
                    break;
                case 'c':
                    stringsFor[3] = "AB-";
                    break;
                case 'O':
                    stringsFor[3] = "O+";
                    break;
                case 'o':
                    stringsFor[3] = "O-";
                    break;
            }
            //CKD
            switch (material[1]) {
                case '0':
                    stringsFor[4] = null;
                    break;
                case '1':
                    stringsFor[4] = "Stage I";
                    break;
                case '2':
                    stringsFor[4] = "Stage II";
                    break;
                case '3':
                    stringsFor[4] = "Stage III";
                    break;
                case '4':
                    stringsFor[4] = "Stage IV";
                    break;
                case '5':
                    stringsFor[4] = "Unspecified";
                    break;
            }
            //Diabetes
            switch (material[2]) {
                case '0':
                    stringsFor[5] = null;
                    break;
                case '1':
                    stringsFor[5] = "Type I";
                    break;
                case '2':
                    stringsFor[5] = "Type II";
                    break;
                case '3':
                    stringsFor[5] = "Unspecified";
                    break;
            }

            //Name
            stringsFor[0] = String.valueOf(material[41]);
            for (int i = 1; i < 25; i++)
                stringsFor[0] = stringsFor[0] + material[41 + i];
            //ICE Name
            stringsFor[1] = String.valueOf(material[66]);
            for (int i = 1; i < 25; i++)
                stringsFor[1] = stringsFor[1] + material[66 + i];
            //ICE Number
            stringsFor[2] = String.valueOf(material[91]);
            for (int i = 1; i < 10; i++)
                stringsFor[2] = stringsFor[2] + material[91 + i];
            //Other Allergy
            stringsFor[6] = String.valueOf(material[101]);
            for (int i = 1; i < 20; i++)
                stringsFor[6] = stringsFor[6] + material[101 + i];
            //Bleeding Disorder
            stringsFor[7] = String.valueOf(material[121]);
            for (int i = 1; i < 30; i++)
                stringsFor[7] = stringsFor[7] + material[121 + i];
            //Active Cancer
            stringsFor[8] = String.valueOf(material[151]);
            for (int i = 1; i < 30; i++)
                stringsFor[8] = stringsFor[8] + material[151 + i];
            //Missing Organ
            stringsFor[9] = String.valueOf(material[181]);
            for (int i = 1; i < 30; i++)
                stringsFor[9] = stringsFor[9] + material[181 + i];
            //Transplanted Organ
            stringsFor[10] = String.valueOf(material[211]);
            for (int i = 1; i < 30; i++)
                stringsFor[10] = stringsFor[10] + material[211 + i];
            //Other L-T Condition
            stringsFor[11] = String.valueOf(material[241]);
            for (int i = 1; i < 60; i++)
                stringsFor[11] = stringsFor[11] + material[241 + i];
            //Other Comm Condition
            stringsFor[12] = String.valueOf(material[301]);
            for (int i = 1; i < 60; i++)
                stringsFor[12] = stringsFor[12] + material[301 + i];
            //Other Implant
            stringsFor[13] = String.valueOf(material[361]);
            for (int i = 1; i < 30; i++)
                stringsFor[13] = stringsFor[13] + material[361 + i];
            //Anticoagulant
            stringsFor[14] = String.valueOf(material[391]);
            for (int i = 1; i < 30; i++)
                stringsFor[14] = stringsFor[14] + material[391 + i];
            //Anti-Seizure
            stringsFor[15] = String.valueOf(material[421]);
            for (int i = 1; i < 30; i++)
                stringsFor[15] = stringsFor[15] + material[421 + i];
            //Anti-Diabetic
            stringsFor[16] = String.valueOf(material[451]);
            for (int i = 1; i < 30; i++)
                stringsFor[16] = stringsFor[16] + material[451 + i];
            //Narcotic
            stringsFor[17] = String.valueOf(material[481]);
            for (int i = 1; i < 30; i++)
                stringsFor[17] = stringsFor[17] + material[481 + i];
            //User ID
            stringsFor[18] = String.valueOf(material[511]);
            for (int i = 1; i < 6; i++)
                stringsFor[18] = stringsFor[18] + material[511 + i];
        }
        for(int i=0;i<19;i++)
        {
            if("".equals(stringsFor[i])){stringsFor[i]=null;}
        }

        return stringsFor;
    }
    public HashMap<String, String> expandBooleans(char[] material)
    {
        boolean[] booleansFor = new boolean[38];
        HashMap<String, String> boolsMap = new HashMap<>();
        //Scroll through and copy values from NFC location 3-40
        for(int i=0;i<38;i++)
        {
            if (material[3 + i] == '1') booleansFor[i] = true;
            else booleansFor[i] = false;
        }
        boolsMap.put("Show Name",String.valueOf(booleansFor[0]));
        boolsMap.put("Show ICE Number",String.valueOf(booleansFor[1]));
        boolsMap.put("Sulfa Drugs",String.valueOf(booleansFor[2]));
        boolsMap.put("Penicillin",String.valueOf(booleansFor[3]));
        boolsMap.put("Amoxicillin",String.valueOf(booleansFor[4]));
        boolsMap.put("NSAIDs",String.valueOf(booleansFor[5]));
        boolsMap.put("General Anesthetic",String.valueOf(booleansFor[6]));
        boolsMap.put("Nuts",String.valueOf(booleansFor[7]));
        boolsMap.put("Bee Stings",String.valueOf(booleansFor[8]));
        boolsMap.put("Latex",String.valueOf(booleansFor[9]));
        boolsMap.put("Epi-Pen",String.valueOf(booleansFor[10]));
        boolsMap.put("Asthma",String.valueOf(booleansFor[11]));
        boolsMap.put("AFIB",String.valueOf(booleansFor[12]));
        boolsMap.put("Coronary Artery",String.valueOf(booleansFor[13]));
        boolsMap.put("Congestive Heart",String.valueOf(booleansFor[14]));
        boolsMap.put("CVA",String.valueOf(booleansFor[15]));
        boolsMap.put("Hypertension",String.valueOf(booleansFor[16]));
        boolsMap.put("Epilepsy",String.valueOf(booleansFor[17]));
        boolsMap.put("Seizure",String.valueOf(booleansFor[18]));
        boolsMap.put("Dementia",String.valueOf(booleansFor[19]));
        boolsMap.put("Bipolar",String.valueOf(booleansFor[20]));
        boolsMap.put("Schizophrenia",String.valueOf(booleansFor[21]));
        boolsMap.put("PTSD",String.valueOf(booleansFor[22]));
        boolsMap.put("Pacemaker",String.valueOf(booleansFor[23]));
        boolsMap.put("Defibrillator",String.valueOf(booleansFor[24]));
        boolsMap.put("Insulin Pump",String.valueOf(booleansFor[25]));
        boolsMap.put("Other Allergy",String.valueOf(booleansFor[26]));
        boolsMap.put("Bleeding Disorder",String.valueOf(booleansFor[27]));
        boolsMap.put("Active Cancer",String.valueOf(booleansFor[28]));
        boolsMap.put("Missing Organ",String.valueOf(booleansFor[29]));
        boolsMap.put("Transplanted Organ",String.valueOf(booleansFor[30]));
        boolsMap.put("Other L-T",String.valueOf(booleansFor[31]));
        boolsMap.put("Other Comm",String.valueOf(booleansFor[32]));
        boolsMap.put("Other Implant",String.valueOf(booleansFor[33]));
        boolsMap.put("Anticoagulant",String.valueOf(booleansFor[34]));
        boolsMap.put("Anti-Seizure",String.valueOf(booleansFor[35]));
        boolsMap.put("Diabetic",String.valueOf(booleansFor[36]));
        boolsMap.put("Narcotic",String.valueOf(booleansFor[37]));

        return boolsMap;
    }

}