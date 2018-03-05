/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler_project;
public class WriteIntNode extends StatementNode{

	public ExpressionNode wrintExpNode;
	BNFGrammarAST bgAST;
	IdentNode idNode;
	public boolean typeOk;
	public WriteIntNode(ExpressionNode exp){
		
		this.wrintExpNode = exp;
		if(TypeCheck()){
			this.typeOk=true;
		}
		else{
			this.typeOk=false;
		}
	}
	
	public void drawWriteIntNode(int seek_index){
		String fillcolor = "/x11/white";
		if(!TypeCheck()){
			fillcolor = "/pastel13/1";
		}
		bgAST = BNFGrammarAST.getInstance();
		//bgAST.graphAST.append("n"+seek_index + " [label=\""+"decl list"+"\",fillcolor=\"/x11/white\",shape=box]").append(System.getProperty("line.separator"));
		int graph_first_index = bgAST.graph_indexAST;
		if(wrintExpNode!=null){
		//fillcolor = "/x11/lightgrey";
		idNode = new IdentNode("writeint"," ");
		idNode.drawIdentNode(seek_index,fillcolor,"box");
		wrintExpNode.drawExpressionNode(graph_first_index);
		}
	}
	
	boolean TypeCheck(){
		boolean checkType = false;
		//if(!wrintExpNode.typeExp.equals("BOOL")){
			//checkType =false;
		//}
		//System.out.println(wrintExpNode.typeOk);

		if(wrintExpNode.typeExp.equals("INT") && wrintExpNode.typeOk){
			checkType =true;
		}
		
		if(wrintExpNode.typeExp.equals("BOOL") || wrintExpNode.typeExp.equals(null)){
			checkType = false;
		}
		return checkType;
		
	}
	
}

