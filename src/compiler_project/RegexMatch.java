package compiler_project;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class RegexMatch {

    public static String Regexcompare(String s) {
        
        String Regex = "if";
        if (Regex.equals("if")) {
            Pattern pattern = Pattern.compile(Regex);
            Matcher matcher = pattern.matcher(s);
            if (matcher.matches()) {
                return "IF";
            }
        }

        Regex = "then";
        if (Regex.equals("then")) {
            Pattern pattern = Pattern.compile(Regex);
            Matcher matcher = pattern.matcher(s);
            if (matcher.matches()) {
                return "THEN";
            }
        }

        Regex = "else";
        if (Regex.equals("else")) {
            Pattern pattern = Pattern.compile(Regex);
            Matcher matcher = pattern.matcher(s);
            if (matcher.matches()) {
                return "ELSE";
            }
        }

        Regex = "begin";
        if (Regex.equals("begin")) {
            Pattern pattern = Pattern.compile(Regex);
            Matcher matcher = pattern.matcher(s);
            if (matcher.matches()) {
                return "BEGIN";
            }
        }

        Regex = "end";
        if (Regex.equals("end")) {
            Pattern pattern = Pattern.compile(Regex);
            Matcher matcher = pattern.matcher(s);
            if (matcher.matches()) {
                return "END";
            }
        }

        Regex = "while";
        if (Regex.equals("while")) {
            Pattern pattern = Pattern.compile(Regex);
            Matcher matcher = pattern.matcher(s);
            if (matcher.matches()) {
                return "WHILE";
            }
        }

        Regex = "do";
        if (Regex.equals("do")) {
            Pattern pattern = Pattern.compile(Regex);
            Matcher matcher = pattern.matcher(s);
            if (matcher.matches()) {
                return "DO";
            }
        }

        Regex = "program";
        if (Regex.equals("program")) {
            Pattern pattern = Pattern.compile(Regex);
            Matcher matcher = pattern.matcher(s);
            if (matcher.matches()) {
                return "PROGRAM";
            }
        }

        Regex = "var";
        if (Regex.equals("var")) {
            Pattern pattern = Pattern.compile(Regex);
            Matcher matcher = pattern.matcher(s);
            if (matcher.matches()) {
                return "VAR";
            }
        }

        Regex = "as";
        if (Regex.equals("as")) {
            Pattern pattern = Pattern.compile(Regex);
            Matcher matcher = pattern.matcher(s);
            if (matcher.matches()) {
                return "AS";
            }
        }

        Regex = "int";
        if (Regex.equals("int")) {
            Pattern pattern = Pattern.compile(Regex);
            Matcher matcher = pattern.matcher(s);
            if (matcher.matches()) {
                return "INT";
            }
        }

        Regex = "bool";
        if (Regex.equals("bool")) {
            Pattern pattern = Pattern.compile(Regex);
            Matcher matcher = pattern.matcher(s);
            if (matcher.matches()) {
                return "BOOL";
            }
        }

        Regex = "writeint";
        if (Regex.equals("writeint")) {
            Pattern pattern = Pattern.compile(Regex);
            Matcher matcher = pattern.matcher(s);
            if (matcher.matches()) {
                return "WRITEINT";
            }
        }

        Regex = "readint";
        if (Regex.equals("readint")) {
            Pattern pattern = Pattern.compile(Regex);
            Matcher matcher = pattern.matcher(s);
            if (matcher.matches()) {
                return "READINT";
            }
        }

        Regex = "\\(";
        if (Regex.equals("\\(")) {
            Pattern pattern = Pattern.compile(Regex);
            Matcher matcher = pattern.matcher(s);
            if (matcher.matches()) {
                return "LP";
            }
        }

        Regex = "\\)";
        if (Regex.equals("\\)")) {
            Pattern pattern = Pattern.compile(Regex);
            Matcher matcher = pattern.matcher(s);
            if (matcher.matches()) {
                return "RP";
            }
        }

        Regex = "\\;";
        if (Regex.equals("\\;")) {
            Pattern pattern = Pattern.compile(Regex);
            Matcher matcher = pattern.matcher(s);
            if (matcher.matches()) {
                return "SC";
            }
        }

        Regex = "[1-9][0-9]*|0";
        if (Regex.equals("[1-9][0-9]*|0")) {
            Pattern pattern = Pattern.compile(Regex);
            Matcher matcher = pattern.matcher(s);
            if (matcher.matches()) {
                return "num";
            }
        }

        Regex = "false|true";
        if (Regex.equals("false|true")) {
            Pattern pattern = Pattern.compile(Regex);
            Matcher matcher = pattern.matcher(s);
            if (matcher.matches()) {
                return "boollit";
            }
        }

        Regex = "[A-Z][A-Z0-9]*";
        if (Regex.equals("[A-Z][A-Z0-9]*")) {
            Pattern pattern = Pattern.compile(Regex);
            Matcher matcher = pattern.matcher(s);
            if (matcher.matches()) {
                return "ident";
            }
        }

        Regex = "[*]|div|mod";
        if (Regex.equals("[*]|div|mod")) {
            Pattern pattern = Pattern.compile(Regex);
            Matcher matcher = pattern.matcher(s);
            if (matcher.matches()) {
                return "MULTIPLICATIVE";
            }
        }

        Regex = "[+]|[-]";
        if (Regex.equals("[+]|[-]")) {
            Pattern pattern = Pattern.compile(Regex);
            Matcher matcher = pattern.matcher(s);
            if (matcher.matches()) {
                return "ADDITIVE";
            }
        }

        Regex = "[=]|[!][=]|[<>]|[<][=]|[>][=]";
        if (Regex.equals("[=]|[!][=]|[<>]|[<][=]|[>][=]")) {
            Pattern pattern = Pattern.compile(Regex);
            Matcher matcher = pattern.matcher(s);
            if (matcher.matches()) {
                return "COMPARE";
            }
        }

        Regex = "[:][=]";
        if (Regex.equals("[:][=]")) {
            Pattern pattern = Pattern.compile(Regex);
            Matcher matcher = pattern.matcher(s);
            if (matcher.matches()) {
                return "ASGN";
            }
        }
        return "";
    }
}
