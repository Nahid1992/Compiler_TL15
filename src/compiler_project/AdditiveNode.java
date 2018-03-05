package compiler_project;

class AdditiveNode {

    public String opName;
    public String opType;
    BNFGrammarAST bgAST;
    ExpressionNode expNode;

    public AdditiveNode(String n, String t) {
        this.opName = n;
        this.opType = t;
    }

    public void drawAdditiveNode(int seek_index) {

        String fillcolor = "/x11/gray96";
        String shape = "box";
        bgAST = BNFGrammarAST.getInstance();
        int graph_first_index = bgAST.graph_indexAST;
        bgAST.graphAST.append("n" + graph_first_index + " [label=\"" + opName + "\",fillcolor=\"" + fillcolor + "\",shape=" + shape + "]").append(System.getProperty("line.separator"));
        bgAST.graph_indexAST++;
        bgAST.graphAST.append("n" + seek_index + " -> n" + graph_first_index).append(System.getProperty("line.separator"));
    }

    public void drawOpNode(int seek_index) {
        String fillcolor = "/x11/gray96";
        String shape = "box";
        bgAST = BNFGrammarAST.getInstance();
        int graph_first_index = bgAST.graph_indexAST;
        bgAST.graphAST.append("n" + graph_first_index + " [label=\"" + opName + ": bool" + "\",fillcolor=\"" + fillcolor + "\",shape=" + shape + "]").append(System.getProperty("line.separator"));
        bgAST.graph_indexAST++;
        bgAST.graphAST.append("n" + seek_index + " -> n" + graph_first_index).append(System.getProperty("line.separator"));
    }

}
