/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler_project;

public class BoollitNode {

	public boolean boolVal;
	String boolType;
	BNFGrammarAST bgAST;
	public boolean typeOk;
	public BoollitNode(Boolean val){
		this.boolVal=val;
		boolType ="BOOL";
		this.typeOk = true;
	}
	
	public void drawBoollitNode(int seek_index){
                boolType = "bool";
		String fillcolor= "/x11/gray96";
		String shape = "box";
		bgAST = BNFGrammarAST.getInstance();
		int graph_first_index = bgAST.graph_indexAST;
		bgAST.graphAST.append("n"+graph_first_index+ " [label=\""+String.valueOf(boolVal)+": "+boolType+"\",fillcolor=\""+fillcolor+"\",shape="+shape+"]").append(System.getProperty("line.separator"));
                
		bgAST.graph_indexAST++;
		bgAST.graphAST.append("n"+seek_index+" -> n"+graph_first_index).append(System.getProperty("line.separator"));
	}
}

