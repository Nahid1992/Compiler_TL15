
package compiler_project;

public class IfStatementNode extends StatementNode{

	public ExpressionNode expressionNode;
	public StatementSequenceNode ifstmtSequence, elsestmtSequence;
	BNFGrammarAST bgAST;
	public boolean typeOk;
	
	public IfStatementNode(ExpressionNode exp, StatementSequenceNode ifstmt, StatementSequenceNode elsestmt){
		
		this.expressionNode = exp;
		this.ifstmtSequence =ifstmt;
		this.elsestmtSequence =elsestmt;
		if(TypeCheck()){
			this.typeOk = true;
			}
			else{
				this.typeOk=false;
			}
	}
	
	public void drawIfStatementNode(int seek_index){
		String fillcolor="/x11/white";
		if(!TypeCheck()){
			fillcolor = "/pastel13/1";
		}
		
		String shape = "box";
		bgAST = BNFGrammarAST.getInstance();
		//bgAST.graphAST.append("n"+seek_index + " [label=\""+"decl list"+"\",fillcolor=\"/x11/white\",shape=box]").append(System.getProperty("line.separator"));
		int graph_first_index = bgAST.graph_indexAST;
		bgAST.graphAST.append("n"+graph_first_index+ " [label=\""+"if"+"\",fillcolor=\""+fillcolor+"\",shape="+shape+"]").append(System.getProperty("line.separator"));
		//int graph_first_index = bgAST.graph_indexAST;
		bgAST.graph_indexAST++;
		bgAST.graphAST.append("n"+seek_index+" -> n"+graph_first_index).append(System.getProperty("line.separator"));
		expressionNode.drawExpressionNode(graph_first_index);
		if(ifstmtSequence!=null){
			ifstmtSequence.drawStatetementSequenceNode(graph_first_index);
		}
		
		if(elsestmtSequence!=null){
			elsestmtSequence.drawStatetementSequenceNode(graph_first_index);
		}
	}
	
	boolean TypeCheck(){
		boolean checkType = false;
		if(elsestmtSequence!=null){
			checkType = expressionNode.typeOk && ifstmtSequence.typeOk && elsestmtSequence.typeOk;
		}
		
		else{
			checkType = expressionNode.typeOk && ifstmtSequence.typeOk;
		}
		
		return checkType;
		
	}
}
