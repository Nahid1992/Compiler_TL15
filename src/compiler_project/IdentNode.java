package compiler_project;

public class IdentNode implements ASTtree {

    public String idName;
    public String idType;
    BNFGrammarAST bgAST;
    HashTableforSymbol sym;
    ExpressionNode expNode;
    public String listType; 
    public boolean typeOk;

    public IdentNode(String n, String t) {
        this.idName = n;
        this.idType = t;
        this.expNode = null;
        this.typeOk = true;
        this.listType = null;
    }

    IdentNode(String x) {
        this.idName = x;
        this.idType = "";
        this.typeOk = true;
        this.expNode = null;
        this.listType = null;

    }

    //PROBLEM 
    public IdentNode(String n, String t, ExpressionNode expNode) {
        this.idName = n;
        this.idType = t;
        this.expNode = expNode;
        this.typeOk = true;
        this.listType = null;
    }
    //PROBLEM
    public IdentNode(String n, String t, ExpressionNode expNode, String type) {
        this.idName = n;
        this.idType = t;
        this.expNode = expNode;
        this.typeOk = true;
        this.listType = type;
    }

    public void drawIdentNode(int seek_index, String fillcolor, String shape) {
        //System.out.println("CHECK = "+idName + " " + idType + " ");
        if (idType.equals("INT")) {
            idType = ": int";
        }
        if (idType.equals("BOOL")) {
            idType = ": bool";
        }
        bgAST = BNFGrammarAST.getInstance();
        int graph_first_index = bgAST.graph_indexAST;

        bgAST.graphAST.append("n" + graph_first_index + " [label=\"" + idName + idType + "\",fillcolor=\"" + fillcolor + "\",shape=" + shape + "]").append(System.getProperty("line.separator"));
        bgAST.graph_indexAST++;
        bgAST.graphAST.append("n" + seek_index + " -> n" + graph_first_index).append(System.getProperty("line.separator"));

    }

    public void drawReadIntNode(int seek_index) {
        bgAST = BNFGrammarAST.getInstance();
        sym = HashTableforSymbol.getInsatnce();
        String fillcolor = "/x11/white";
        if (sym.getIdentName(idName) == null) {
            //System.out.println(idName);
            fillcolor = "/x11/gray96";
        } else if (sym.getIdentName(idName).equals("BOOL")) {
            fillcolor = "/x11/gray96";
        } else if (sym.getIdentName(idName).equals("INT")) {
            fillcolor = "/x11/gray96";
        }

        if (idName.equals("READINT")) {
            idName = "readint";
        }
        if (idType.equals("INT")) {
            idType = "int";
        }
        if (idType.equals("BOOL")) {
            idType = "bool";
        }

        String shape = "box";
        int graph_first_index = bgAST.graph_indexAST;
        bgAST.graphAST.append("n" + graph_first_index + " [label=\"" + idName + ": " + idType + "\",fillcolor=\"" + fillcolor + "\",shape=" + shape + "]").append(System.getProperty("line.separator"));
        bgAST.graph_indexAST++;
        bgAST.graphAST.append("n" + seek_index + " -> n" + graph_first_index).append(System.getProperty("line.separator"));

    }
}
