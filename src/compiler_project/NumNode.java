/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler_project;

public class NumNode {

	public String typeNum;
	public long numVal;
	BNFGrammarAST bgAST;
	public boolean typeOk;
	public NumNode(long val){
		this.numVal =val;
		typeNum = "INT";
		if(!TypeCheck()){
			this.typeOk=false;
		}
		
		else{
			this.typeOk =true;
		}
	}
	
	public void drawNumNode(int seek_index){
                if (typeNum.equals("INT")) typeNum = "int";
		String fillcolor= "/x11/gray96";
		if(!TypeCheck()){
			fillcolor = "/pastel13/1";
		}
		String shape = "box";
		bgAST = BNFGrammarAST.getInstance();
		int graph_first_index = bgAST.graph_indexAST;
		bgAST.graphAST.append("n"+graph_first_index+ " [label=\""+Long.toString(numVal)+": "+typeNum+"\",fillcolor=\""+fillcolor+"\",shape="+shape+"]").append(System.getProperty("line.separator"));
		//int graph_first_index = bgAST.graph_indexAST;
		bgAST.graph_indexAST++;
		bgAST.graphAST.append("n"+seek_index+" -> n"+graph_first_index).append(System.getProperty("line.separator"));
	}
	
	boolean TypeCheck(){
		boolean checkType = false;
		if(this.numVal>=0 && this.numVal<=2147483647){
			checkType =true;
		}
		//System.out.println(checkType);
		return checkType;
		
	}
}

