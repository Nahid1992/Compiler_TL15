/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler_project;

public class TermNode {

	public FactorNode fNode1, fNode2;
	public AdditiveNode opTerm;
	BNFGrammarAST bgAST;
	public boolean typeOk;
	public String termType;
	
	public TermNode(FactorNode f1){
		this.fNode1=f1;
		this.opTerm= null;
		this.fNode2 =null;
		
	}
	
	public TermNode(FactorNode f1, AdditiveNode op, FactorNode f2){
		this.fNode1 =f1;
		if(op!=null){
		this.opTerm =op;
		this.fNode2 =f2;
		}
		else{
			this.opTerm=null;
			this.fNode2=null;
		}
		if(!TypeCheck()){
			this.typeOk=false;
		}
		
		else{
			this.typeOk =true;
		}
	}
	
	public void drawTermNode(int seek_index){
		String fillcolor= "/x11/gray96";
		if(!TypeCheck()){
			fillcolor = "/pastel13/1";
		}
		String shape = "box";
		bgAST = BNFGrammarAST.getInstance();
		int graph_first_index = bgAST.graph_indexAST;
		if(opTerm!=null){
			bgAST.graphAST.append("n"+graph_first_index+ " [label=\""+opTerm.opName+": int"+"\",fillcolor=\""+fillcolor+"\",shape="+shape+"]").append(System.getProperty("line.separator"));
			//System.out.println("CHECK100= "+opTerm.opName+" "+opTerm.opType);
			bgAST.graph_indexAST++;
			bgAST.graphAST.append("n"+seek_index+" -> n"+graph_first_index).append(System.getProperty("line.separator"));
			if(fNode1!=null & fNode2!=null){
				fNode1.drawFactorNode(graph_first_index);
				fNode2.drawFactorNode(graph_first_index);
			}
		}
		
		else if(fNode1!=null){
			fNode1.drawFactorNode(seek_index);
		}
	}
	
	boolean TypeCheck(){
		boolean checkType = false;
		//termType = fNode1.type;
		if((fNode1!=null && fNode2!=null) ){
			if(fNode1.type.equals(fNode2.type) && fNode1.type.equals("INT")){
			checkType=true;
			termType = fNode1.type;
			//termType = "INT";
			
			}	
			else{
				checkType=false;
				//termType = fNode1.type;
			}
			
			//System.out.println(fNode1.type);
		}
		else if((fNode1!=null) && (fNode2==null) && (opTerm ==null)){
			checkType=fNode1.typeOk;
			termType = fNode1.type;
			
		}
		
		
		return checkType;
		
	}
	
}
