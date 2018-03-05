package compiler_project;

public class FactorNode {

    public IdentNode idFactorF;
    public NumNode numNodeF;
    public BoollitNode boolNodeF;
    public ExpressionNode expNodeF;
    HashTableforSymbol symTab;
    String type;
    BNFGrammarAST bgAST;
    public boolean typeOk;

    public FactorNode(IdentNode id) {
        this.idFactorF = id;
        symTab = HashTableforSymbol.getInsatnce();
        type = symTab.getIdentName(idFactorF.idName);
        this.boolNodeF = null;
        this.expNodeF = null;
        this.numNodeF = null;
        this.typeOk = idFactorF.typeOk;
    }

    public FactorNode(ExpressionNode exp) {
        this.expNodeF = exp;
        //symTab=HashTableforSymbol.getInsatnce();
        type = expNodeF.typeExp;
        this.boolNodeF = null;
        this.idFactorF = null;
        this.numNodeF = null;
        this.typeOk = expNodeF.typeOk;

    }

    public FactorNode(NumNode n) {
        this.numNodeF = n;
        //symTab=HashTableforSymbol.getInsatnce();
        type = numNodeF.typeNum;
        this.boolNodeF = null;
        this.expNodeF = null;
        this.idFactorF = null;
        this.typeOk = numNodeF.typeOk;
		//System.out.println(numNodeF.typeOk);

    }

    public FactorNode(BoollitNode bool) {
        this.boolNodeF = bool;
        //symTab=HashTableforSymbol.getInsatnce();
        type = boolNodeF.boolType;
        this.idFactorF = null;
        this.expNodeF = null;
        this.numNodeF = null;
        this.typeOk = boolNodeF.typeOk;

    }

    public void drawFactorNode(int seek_index) {
        String fillcolor = "/x11/gray96";
        String shape = "box";
        bgAST = BNFGrammarAST.getInstance();
        int graph_first_index = bgAST.graph_indexAST;

        if (boolNodeF != null) {
            boolNodeF.drawBoollitNode(seek_index);
        } else if (numNodeF != null) {
            numNodeF.drawNumNode(seek_index);
        } else if (expNodeF != null) {
            expNodeF.drawExpressionNode(seek_index);
        } else if (idFactorF != null) {

            if (type.equals("INT")) {
                type = "int";
                fillcolor = "/x11/gray96";
            }else {
                fillcolor = "/x11/gray96";
            }
            bgAST.graphAST.append("n" + graph_first_index + " [label=\"" + idFactorF.idName+": "+type + "\",fillcolor=\"" + fillcolor + "\",shape=" + shape + "]").append(System.getProperty("line.separator"));
            //int graph_first_index = bgAST.graph_indexAST;
            bgAST.graph_indexAST++;
            bgAST.graphAST.append("n" + seek_index + " -> n" + graph_first_index).append(System.getProperty("line.separator"));
        }
    }

    boolean TypeCheck() {
        boolean checkType = false;

        return checkType;

    }
}
