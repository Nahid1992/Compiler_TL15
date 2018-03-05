/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler_project;

public class ExpressionNode implements ASTtree{

	public SimpleExpressionNode seNode1, seNode2;
	public AdditiveNode expOp;
	public String typeExp;
	BNFGrammarAST bgAST;
	IdentNode idNode;
	public boolean typeOk;
	
	public ExpressionNode(SimpleExpressionNode se1){
		this.seNode1=se1;
		this.expOp =null;
		this.seNode2 =null;
	}
	
	
	public ExpressionNode(SimpleExpressionNode se1, AdditiveNode op, SimpleExpressionNode se2){
		this.seNode1=se1;
		if(op!=null){
			this.expOp =op;
			this.seNode2 =se2;
		}
		else{
			this.expOp =null;
			this.seNode2=null;
		}
		
		if(TypeCheck()){
			this.typeOk = true;
			}
			else{
				this.typeOk=false;
			}
	}
	
	public void drawExpressionNode(int seek_index){
		bgAST = BNFGrammarAST.getInstance();
		int graph_first_index = bgAST.graph_indexAST;
		int graph_next_index;
		String fillcolor= "/x11/gray96";
		if(!TypeCheck()){
			fillcolor = "/pastel13/1";
		}
		if(expOp!=null){
			//idNode = new IdentNode("Expression","expression");
			//idNode.drawIdentNode(seek_index,fillcolor,"box");
			//graph_next_index= bgAST.graph_indexAST;
			expOp.drawOpNode(seek_index);
			if(seNode1!=null & seNode2!=null){
				seNode1.drawSimpleExpressionNode(graph_first_index);
				seNode2.drawSimpleExpressionNode(graph_first_index);
			}
		}
		
		else if(seNode1!=null){
			seNode1.drawSimpleExpressionNode(seek_index);
		}
	}
	
	
	boolean TypeCheck(){
		boolean checkType = false;
		typeExp = seNode1.smExpType;
		if(seNode1!=null && expOp!=null && seNode2!=null && seNode1.typeOk && seNode2.typeOk && seNode1.smExpType.equals(seNode2.smExpType) && seNode1.smExpType.equals("INT")){
			checkType=true;
			typeExp = "BOOL";
			//System.out.println(seNode2.smExpType);
		}
		
		else if(seNode1!=null &&  expOp==null && seNode2==null){
			checkType =seNode1.typeOk;
			typeExp = seNode1.smExpType;
			
		}
		//System.out.println(checkType);
		return checkType;
		
	}
	
}

