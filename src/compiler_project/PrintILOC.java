
package compiler_project;

public class PrintILOC {

	static BNFGrammarAST bgILOC;
	
	public static void printILOC(BlockNode b, String decl){
		bgILOC = BNFGrammarAST.getInstance();
		BNFGrammarAST.graphILOC.append("<td align=\"left\">"+decl+"</td>");
	}
	
	public static void printILOCInst(String decl){
		bgILOC = BNFGrammarAST.getInstance();
		BNFGrammarAST.graphILOC.append("<td align=\"left\">"+decl+"</td>");
	}
}
