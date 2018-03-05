package compiler_project;

public class AssignmentNode extends StatementNode {

    public IdentNode identifierNode;
    public AdditiveNode asgnOp;
    public ExpressionNode expressionNode;
    public IdentNode readInt;
    BNFGrammarAST bgAST;
    public boolean typeOk;

    public AssignmentNode(IdentNode idNode, AdditiveNode asgnNode, ExpressionNode expNode) {
        this.identifierNode = idNode;
        this.asgnOp = asgnNode;
        this.expressionNode = expNode;
        if (TypeCheck()) {
            this.typeOk = true;
        } else {
            this.typeOk = false;
        }
    }

    public AssignmentNode(IdentNode idNode, AdditiveNode asgnNode, IdentNode readInt) {
        this.identifierNode = idNode;
        this.asgnOp = asgnNode;
        this.readInt = readInt;
        //System.out.println("CHECK = "+this.identifierNode.idName + " " + this.asgnOp.opName + " " + this.readInt.idName);
        if (TypeCheck()) {
            this.typeOk = true;
        } else {
            this.typeOk = false;
        }
    }

    public void drawAssignmentNode(int seek_index) {
        String fillcolor = "/x11/white";
        if (!TypeCheck()) {
            fillcolor = "/pastel13/1";
        }
        String shape = "box";
        bgAST = BNFGrammarAST.getInstance();
        //bgAST.graphAST.append("n"+seek_index + " [label=\""+"decl list"+"\",fillcolor=\"/x11/white\",shape=box]").append(System.getProperty("line.separator"));
        int graph_first_index = bgAST.graph_indexAST;

        if (readInt != null) {
            if (asgnOp != null) {
                bgAST.graphAST.append("n" + graph_first_index + " [label=\"" + asgnOp.opName + "\",fillcolor=\"" + fillcolor + "\",shape=" + shape + "]").append(System.getProperty("line.separator"));
                bgAST.graph_indexAST++;

                bgAST.graphAST.append("n" + seek_index + " -> n" + graph_first_index).append(System.getProperty("line.separator"));
                identifierNode.drawReadIntNode(graph_first_index);//SOMETHING
                readInt.drawReadIntNode(graph_first_index);
            }
        } else if (expressionNode != null) {
            if (asgnOp != null) {
                bgAST.graphAST.append("n" + graph_first_index + " [label=\"" + asgnOp.opName + "\",fillcolor=\"" + fillcolor + "\",shape=" + shape + "]").append(System.getProperty("line.separator"));
                //int graph_first_index = bgAST.graph_indexAST;
                bgAST.graph_indexAST++;
                bgAST.graphAST.append("n" + seek_index + " -> n" + graph_first_index).append(System.getProperty("line.separator"));
                identifierNode.drawReadIntNode(graph_first_index);
                expressionNode.drawExpressionNode(graph_first_index);
            }
        }
    }

    boolean TypeCheck() {
        boolean checkType = false;
        if (readInt != null) {
            if (readInt.idType.equals(identifierNode.idType)) {
                checkType = true;
            }
        } else if (expressionNode != null) {
            if (expressionNode.typeOk && expressionNode.typeExp.equals(identifierNode.idType)) {//
                checkType = true;
            }
        }
        return checkType;

    }

}
