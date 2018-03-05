
package compiler_project;

public class SimpleExpressionNode {

	public TermNode smExpTerm1, smExpTerm2;
	public AdditiveNode smExpOp;
	BNFGrammarAST bgAST;
	public boolean typeOk;
	public String smExpType;
	
	public SimpleExpressionNode(TermNode t){
		this.smExpTerm1 =t;
		this.smExpOp =null;
		this.smExpTerm2=null;
	}
	
	
	public SimpleExpressionNode(TermNode t1, AdditiveNode op, TermNode t2){
		this.smExpTerm1 =t1;
		if(op!=null){
		this.smExpOp =op;
		this.smExpTerm2 =t2;
		}
		else{
			this.smExpOp =null;
			this.smExpTerm2=null;
		}
		
		if(TypeCheck()){
			this.typeOk = true;
			}
			else{
				this.typeOk=false;
			}
	}
	
	public void drawSimpleExpressionNode(int seek_index){
                String name = ": int";
		String fillcolor= "/x11/gray96";
		if(!TypeCheck()){
			fillcolor = "/pastel13/1";
                        name = "";
		}
		String shape = "box";
		bgAST = BNFGrammarAST.getInstance();
		int graph_first_index = bgAST.graph_indexAST;
		if(smExpOp!=null){
			bgAST.graphAST.append("n"+graph_first_index+ " [label=\""+smExpOp.opName+name+"\",fillcolor=\""+fillcolor+"\",shape="+shape+"]").append(System.getProperty("line.separator"));
			//System.out.println("CHECK200= "+smExpOp.opName+" "+smExpOp.opType); //ADDITIVE
			bgAST.graph_indexAST++;
			bgAST.graphAST.append("n"+seek_index+" -> n"+graph_first_index).append(System.getProperty("line.separator"));
			
			if(smExpTerm1!=null & smExpTerm2!=null){
				smExpTerm1.drawTermNode(graph_first_index);
				smExpTerm2.drawTermNode(graph_first_index);
			}
		}
		
		else if(smExpTerm1!=null){
			smExpTerm1.drawTermNode(seek_index);
		}
	}
	
	boolean TypeCheck(){
		//boolean checkType = smExpTerm1.typeOk;
		boolean checkType = false;
		smExpType="BOOL";
		//System.out.println(smExpTerm1.typeOk);
		if(smExpTerm1!=null && smExpOp!=null && smExpTerm2!=null ){
			if(smExpTerm1.typeOk && smExpTerm2.typeOk && smExpTerm1.termType.equals(smExpTerm2.termType) && smExpTerm1.termType.equals("INT")){
			checkType =true;
			//this.typeOk =true;
			smExpType=smExpTerm1.termType;
			}
		}
		
		else if(smExpTerm1!=null && smExpOp==null && smExpTerm2==null){
				if(smExpTerm1.typeOk){
					checkType =true;
					smExpType=smExpTerm1.termType;
					
				}
				
				else{
					
					checkType=false;
				}
			
			}
		
		
		//System.out.println(checkType);
		return checkType;
		
	}
	
}
