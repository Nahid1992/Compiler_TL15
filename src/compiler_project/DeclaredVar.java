package compiler_project;

class DeclaredVar {

    public IdentNode ident;
    public TypeNode type;

    BNFGrammarAST bgAST;
    IdentNode idNode;
    TypeNode typeNode;
    public boolean checkDuplicate;
    HashTableforSymbol sym;

	//public boolean duplicate = false;
    public DeclaredVar(IdentNode id, TypeNode t) {
        this.ident = id;
        this.type = t;
        this.checkDuplicate = true;

        if (addToSymbolHashTable(id.idName, id.idType)) {
            this.checkDuplicate = false;
            //System.out.println("Variable declared twice");
        } else {
            this.checkDuplicate = true;
        }
    }

    public boolean addToSymbolHashTable(String id, String type) {

        HashTableforSymbol symTable = HashTableforSymbol.getInsatnce();

        if (symTable.getIdentName(id) == null) {
            symTable.addToHashTableforSymbol(id, type);
            return false;
        } else {
            return true;
        }
    }

    public void drawDeclaredVar(int seek_index) {
        sym = HashTableforSymbol.getInsatnce();
        typeNode = new TypeNode(type.typeNode);
        String fillcolor = "/x11/white";
        if (!this.checkDuplicate) {
            fillcolor = "/pastel13/1";
        } else {
            if (type.typeNode == null) {
                fillcolor = "/x11/gray99";
            } else if (type.typeNode.equals("BOOL")) {
                fillcolor = "/x11/gray96";
            } else if (type.typeNode.equals("INT")) {
                fillcolor = "/x11/gray96";
            }
        }
        //if(type.typeNode.equals("INT"))
        bgAST = BNFGrammarAST.getInstance();
        int graph_first_index = bgAST.graph_indexAST;
        idNode = new IdentNode("decl: '" + ident.idName+"'", ident.idType);
        idNode.drawIdentNode(seek_index, fillcolor, "box");
        //typeNode =new TypeNode(type.typeNode);
        //typeNode.drawTypeNode(graph_first_index);

    }
}
