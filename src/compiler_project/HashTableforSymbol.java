package compiler_project;

import java.util.HashMap;

public class HashTableforSymbol {

    private static int identOffset = 0;
    private static HashTableforSymbol sym = new HashTableforSymbol();
    private HashMap<String, RegisterRecordforSymbol> map;

    public HashTableforSymbol() {
        map = new HashMap<String, RegisterRecordforSymbol>();
    }

    public void addToHashTableforSymbol(String identName, String type) {
        RegisterRecordforSymbol register = new RegisterRecordforSymbol(identName, type);
        register.setIdentOffset(Integer.toString(identOffset));
        identOffset = identOffset - 4;
        map.put(identName, register);
    }

    public void addToHashTableforSymbol(String identName, String type, boolean check) {
        RegisterRecordforSymbol register = new RegisterRecordforSymbol(identName, type);
        register.arrVar = check;
        map.put(identName, register);
    }

    //get Identifier Type
    public String getIdentName(String identName) {
        if (map.containsKey(identName)) {
            return map.get(identName).identType;
        } else {
            return null;
        }
    }

    public HashMap<String, RegisterRecordforSymbol> hashedSymbol() {
        return map;
    }

    public static HashTableforSymbol getInsatnce() {
        return sym;
    }

    public String getIdOffset(String identName) {

        if (identName.contains("_")) {
            identName = identName.substring(1 + identName.indexOf("_"));
        }

        if (map.containsKey(identName)) {
            return map.get(identName).identOffeset;
        } else {
            return null;
        }

    }

    public String getIdBase(String identName) {
        if (map.containsKey(identName)) {
            return map.get(identName).identBase;
        } else {
            return null;
        }
    }


}
