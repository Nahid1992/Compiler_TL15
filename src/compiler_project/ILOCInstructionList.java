
package compiler_project;

public class ILOCInstructionList {
	
	public static String getILOCInstructions(String operator, String operand1, String operand2, String result){
		String insILOC = null;
		
		if(operator.equals("+")){
			insILOC = "add "+ operand1+", "+operand2+" =&gt; "+result;
			BNFGrammarAST.graphILOC.append("<tr>");
			PrintILOC.printILOCInst("add");
			PrintILOC.printILOCInst(operand1+", "+operand2);
			//PrintILOC.printILOC(db, "=&gt;");
			PrintILOC.printILOCInst("=&gt; "+result);
			BNFGrammarAST.graphILOC.append("</tr>");
			BNFGrammarAST.graphMIPS.append("#add "+ operand1+", "+operand2+" => "+result).append(System.getProperty("line.separator"));

		}
		
		if(operator.equals("-")){
			insILOC = "sub "+ operand1+", "+operand2+" =&gt; "+result;
			BNFGrammarAST.graphILOC.append("<tr>");
			PrintILOC.printILOCInst("sub");
			PrintILOC.printILOCInst(operand1+", "+operand2);
			//PrintILOC.printILOC(db, "=&gt;");
			PrintILOC.printILOCInst("=&gt; "+result);
			BNFGrammarAST.graphILOC.append("</tr>");
			BNFGrammarAST.graphMIPS.append("#sub "+ operand1+", "+operand2+" => "+result).append(System.getProperty("line.separator"));

		}
		
		if(operator.equals("*")){
			insILOC = "mul "+ operand1+", "+operand2+" =&gt; "+result;
			BNFGrammarAST.graphILOC.append("<tr>");
			PrintILOC.printILOCInst("mul");
			PrintILOC.printILOCInst(operand1+", "+operand2);
			//PrintILOC.printILOC(db, "=&gt;");
			PrintILOC.printILOCInst("=&gt; "+result);
			BNFGrammarAST.graphILOC.append("</tr>");
			BNFGrammarAST.graphMIPS.append("#mul "+ operand1+", "+operand2+" => "+result).append(System.getProperty("line.separator"));

		}
		
		if(operator.equals("div")){
			insILOC = "div "+ operand1+", "+operand2+" =&gt; "+result;
			BNFGrammarAST.graphILOC.append("<tr>");
			PrintILOC.printILOCInst("div");
			PrintILOC.printILOCInst(operand1+", "+operand2);
			//PrintILOC.printILOC(db, "=&gt;");
			PrintILOC.printILOCInst("=&gt; "+result);
			BNFGrammarAST.graphILOC.append("</tr>");
			BNFGrammarAST.graphMIPS.append("#div "+ operand1+", "+operand2+" => "+result).append(System.getProperty("line.separator"));

		}
		
		if(operator.equals("mod")){
			insILOC = "mod "+ operand1+", "+operand2+" =&gt; "+result;
			BNFGrammarAST.graphILOC.append("<tr>");
			PrintILOC.printILOCInst("mod");
			PrintILOC.printILOCInst(operand1+", "+operand2);
			//PrintILOC.printILOC(db, "=&gt;");
			PrintILOC.printILOCInst("=&gt; "+result);
			BNFGrammarAST.graphILOC.append("</tr>");
			BNFGrammarAST.graphMIPS.append("#mod "+ operand1+", "+operand2+" => "+result).append(System.getProperty("line.separator"));

		}
		
		if(operator.equals("<")){
			insILOC = "cmp_LT "+ operand1+", "+operand2+" =&gt; "+result;
			BNFGrammarAST.graphILOC.append("<tr>");
			PrintILOC.printILOCInst("cmp_LT");
			PrintILOC.printILOCInst(operand1+", "+operand2);
			//PrintILOC.printILOC(db, "=&gt;");
			PrintILOC.printILOCInst("=&gt; "+result);
			BNFGrammarAST.graphILOC.append("</tr>");
			BNFGrammarAST.graphMIPS.append("#cmp_LT "+ operand1+", "+operand2+" => "+result).append(System.getProperty("line.separator"));

		}
		
		if(operator.equals(">")){
			insILOC = "cmp_GT "+ operand1+", "+operand2+" =&gt; "+result;
			BNFGrammarAST.graphILOC.append("<tr>");
			PrintILOC.printILOCInst("cmp_GT");
			PrintILOC.printILOCInst(operand1+", "+operand2);
			//PrintILOC.printILOC(db, "=&gt;");
			PrintILOC.printILOCInst("=&gt; "+result);
			BNFGrammarAST.graphILOC.append("</tr>");
			BNFGrammarAST.graphMIPS.append("#cmp_GT "+ operand1+", "+operand2+" => "+result).append(System.getProperty("line.separator"));

		}
		
		if(operator.equals("<=")){
			insILOC = "cmp_LE "+ operand1+", "+operand2+" =&gt; "+result;
			BNFGrammarAST.graphILOC.append("<tr>");
			PrintILOC.printILOCInst("cmp_LE");
			PrintILOC.printILOCInst(operand1+", "+operand2);
			//PrintILOC.printILOC(db, "=&gt;");
			PrintILOC.printILOCInst("=&gt; "+result);
			BNFGrammarAST.graphILOC.append("</tr>");
			BNFGrammarAST.graphMIPS.append("#cmp_LE "+ operand1+", "+operand2+" => "+result).append(System.getProperty("line.separator"));

		}
		
		
		if(operator.equals(">=")){
			insILOC = "cmp_GE "+ operand1+", "+operand2+" =&gt; "+result;
			BNFGrammarAST.graphILOC.append("<tr>");
			PrintILOC.printILOCInst("cmp_GE");
			PrintILOC.printILOCInst(operand1+", "+operand2);
			//PrintILOC.printILOC(db, "=&gt;");
			PrintILOC.printILOCInst("=&gt; "+result);
			BNFGrammarAST.graphILOC.append("</tr>");
			BNFGrammarAST.graphMIPS.append("#cmp_GE "+ operand1+", "+operand2+" => "+result).append(System.getProperty("line.separator"));

		}
		
		if(operator.equals("=")){
			insILOC = "cmp_EQ "+ operand1+", "+operand2+" =&gt; "+result;
			BNFGrammarAST.graphILOC.append("<tr>");
			PrintILOC.printILOCInst("cmp_EQ");
			PrintILOC.printILOCInst(operand1+", "+operand2);
			//PrintILOC.printILOC(db, "=&gt;");
			PrintILOC.printILOCInst("=&gt; "+result);
			BNFGrammarAST.graphILOC.append("</tr>");
			BNFGrammarAST.graphMIPS.append("#cmp_EQ "+ operand1+", "+operand2+" => "+result).append(System.getProperty("line.separator"));

		}
		
		if(operator.equals("!=")){
			insILOC = "cmp_NE "+ operand1+", "+operand2+" =&gt; "+result;
			BNFGrammarAST.graphILOC.append("<tr>");
			PrintILOC.printILOCInst("cmp_NE");
			PrintILOC.printILOCInst(operand1+", "+operand2);
			//PrintILOC.printILOC(db, "=&gt;");
			PrintILOC.printILOCInst("=&gt; "+result);
			BNFGrammarAST.graphILOC.append("</tr>");
			BNFGrammarAST.graphMIPS.append("#cmp_NE "+ operand1+", "+operand2+" => "+result).append(System.getProperty("line.separator"));

		}
		return insILOC;
		
	}
}
