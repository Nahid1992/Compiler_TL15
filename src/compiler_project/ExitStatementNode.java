package compiler_project;

public class ExitStatementNode extends StatementNode {

    String exitStmt;
    public boolean typeOk;
    BNFGrammarAST bgAST;

    public ExitStatementNode() {
        this.exitStmt = "exit";
        this.typeOk = true;
    }

    public void drawExitStatementNode(int seek_index) {
        String fillcolor = "/x11/lightgrey";
        String shape = "box";
        bgAST = BNFGrammarAST.getInstance();
        //bgAST.graphAST.append("n"+seek_index + " [label=\""+"decl list"+"\",fillcolor=\"/x11/white\",shape=box]").append(System.getProperty("line.separator"));
        int graph_first_index = bgAST.graph_indexAST;

        bgAST.graphAST.append("n" + graph_first_index + " [label=\"" + "" + "exit" + "\",fillcolor=\"" + fillcolor + "\",shape=" + shape + "]").append(System.getProperty("line.separator"));
        //int graph_first_index = bgAST.graph_indexAST;
        bgAST.graph_indexAST++;
        bgAST.graphAST.append("n" + seek_index + " -> n" + graph_first_index).append(System.getProperty("line.separator"));

    }
}
