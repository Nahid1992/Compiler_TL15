package compiler_project;

public class TypeNode {

    public String typeNode;
    BNFGrammarAST bgAST;
    HashTableforSymbol sym;

    public TypeNode(String typeNode) {
        this.typeNode = typeNode;
    }

    public void drawTypeNode(int seek_index) {
        bgAST = BNFGrammarAST.getInstance();
        sym = HashTableforSymbol.getInsatnce();
        String fillcolor = "/x11/gray99";
        String name = "";
        if (typeNode.equals("INT")){
            name = "int";
        } else if (typeNode.equals("BOOL")) {
            name = "bool";
        }
        String shape = "box";
        int graph_first_index = bgAST.graph_indexAST;
        bgAST.graphAST.append("n" + graph_first_index + " [label=\"" + name + "\",fillcolor=\"" + fillcolor + "\",shape=" + shape + "]").append(System.getProperty("line.separator"));
        bgAST.graph_indexAST++;
        bgAST.graphAST.append("n" + seek_index + " -> n" + graph_first_index).append(System.getProperty("line.separator"));

    }
}
