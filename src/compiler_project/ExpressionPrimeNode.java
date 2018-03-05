
package compiler_project;
public class ExpressionPrimeNode {

	SimpleExpressionNode smExpressionNode;
	AdditiveNode opNodesm;
	
	public ExpressionPrimeNode(){
		this.smExpressionNode = null;
		this.opNodesm = null;
	}
	
	public ExpressionPrimeNode(SimpleExpressionNode smexpNode, AdditiveNode op){
		
		this.smExpressionNode = smexpNode;
		this.opNodesm = op;
	}
}
