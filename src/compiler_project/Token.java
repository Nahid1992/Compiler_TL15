package compiler_project;

class Token {

    public String type;
    public String value;
    public String stringvalue;

    public Token(String Regexcompare, String lookup, String stringvalue) {
        this.type = Regexcompare;
        this.value = lookup;
        this.stringvalue = stringvalue;
    }

    String token_return() {
        String token = null;

        return token;
    }
}
