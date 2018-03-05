package compiler_project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

class BuildILOC {

    public static BlockNode ILOCDeclarationsNode(BlockNode db, DeclarationsNode decl) {

        String identName;
        String inst;
        BNFGrammarAST.graphILOC.append("n" + db.blkNum);
        BNFGrammarAST.graphILOC.append(" [label=<<table border=\"0\">");
        BNFGrammarAST.graphILOC.append("<tr><td border=\"1\" colspan=\"3\">" + db.blkName + "</td></tr>");
        BNFGrammarAST.graphMIPS.append(db.blkName + ":").append(System.getProperty("line.separator"));
        for (DeclaredVar d : decl.declVar) {
            
            identName = ILOCIdent(db, d.ident);
            db.addILOCList("loadI 0 =&gt; " + identName);
            //inst = "loadI 0 =&gt; "+identName;
            BNFGrammarAST.graphILOC.append("<tr>");
            PrintILOC.printILOC(db, "loadI");
            PrintILOC.printILOC(db, "0");
            //PrintILOC.printILOC(db, "=&gt;");
            PrintILOC.printILOC(db, "=&gt; " + identName);
            BNFGrammarAST.graphILOC.append("</tr>");
            db.addMipsInstruction(new MIPSInstructionFromat("#loadI", "0", null, identName));
            BNFGrammarAST.graphMIPS.append("#loadI 0 => " + identName).append(System.getProperty("line.separator"));
            ArrayList<String> mipsArrIns = arrayElements(identName);
            String arrIdentName = mipsArrIns.get(0);
            String de1Index = null;
            if (mipsArrIns.size() > 1) {
                de1Index = mipsArrIns.get(1);
            }
            ArrayList<MIPSInstructionFromat> mipsIns = new ArrayList<MIPSInstructionFromat>();
            //mipsIns = MIPSInstructionList.mipsInstruction(new MIPSInstructionFromat("loadI", "0", null, identName));
            mipsIns = MIPSInstructionList.mipsInstruction(new MIPSInstructionFromat("loadI", "0", null, arrIdentName, null, null, de1Index));
            for (MIPSInstructionFromat ins : mipsIns) {
                db.addMipsInstruction(ins);
            }
        }        
        return db;
    }

    
    public static String ILOCIdent(BlockNode idb, IdentNode ident) {
        String ins;
        if ((ident.expNode != null) && (ident.listType != null) && (ident.listType.equals("LIST"))) {
            ins = ILOCExpression(idb, ident.expNode);
            return ident.idName + "(" + ins + ")";
        } else if ((ident.listType != null) && (ident.listType.equals("LIST"))) {
            return ident.idName;
        } else {

            return "r_" + ident.idName;
        }
    }

    public static String ILOCBool(BlockNode bb, BoollitNode boolNode) {
        String boolILOC;
        String boolVal;
        int boolValue = 0;
        //BNFGrammarAST.ilocRegister++;
        //boolVal = "r"+BNFGrammarAST.ilocRegister;
        boolVal = MIPSRegister.getMIPSRegister();
        if (boolNode.boolVal) {
            boolValue = 1;
        }
        boolILOC = "loadI " + boolValue + "=&gt; " + boolVal;
        bb.addILOCList(boolILOC);

        BNFGrammarAST.graphILOC.append("<tr>");
        PrintILOC.printILOC(bb, "loadI");
        PrintILOC.printILOC(bb, Integer.toString(boolValue));
        //PrintILOC.printILOC(db, "=&gt;");
        PrintILOC.printILOC(bb, "=&gt; " + boolVal);
        BNFGrammarAST.graphILOC.append("</tr>");

        //MIPS
        bb.addMipsInstruction(new MIPSInstructionFromat("#loadI", Integer.toString(boolValue), null, boolVal));
        BNFGrammarAST.graphMIPS.append("#loadI " + boolValue + "=> " + boolVal).append(System.getProperty("line.separator"));
        ArrayList<MIPSInstructionFromat> mipsIns = new ArrayList<MIPSInstructionFromat>();
        mipsIns = MIPSInstructionList.mipsInstruction(new MIPSInstructionFromat("loadI", Integer.toString(boolValue), null, boolVal));
        for (MIPSInstructionFromat ins : mipsIns) {
            bb.addMipsInstruction(ins);
        }
        return boolVal;

    }

    public static String ILOCInt(BlockNode ib, NumNode num) {

        String intILOC;
        String intVal;
        //BNFGrammarAST.ilocRegister++;
        //intVal = "r"+BNFGrammarAST.ilocRegister;
        intVal = MIPSRegister.getMIPSRegister();
        intILOC = "loadI " + num.numVal + "=&gt; " + intVal;
        ib.addILOCList(intILOC);
        BNFGrammarAST.graphILOC.append("<tr>");
        PrintILOC.printILOC(ib, "loadI");
        PrintILOC.printILOC(ib, Long.toString(num.numVal));
        //PrintILOC.printILOC(db, "=&gt;");
        PrintILOC.printILOC(ib, "=&gt; " + intVal);
        BNFGrammarAST.graphILOC.append("</tr>");

        //MIPS
        ib.addMipsInstruction(new MIPSInstructionFromat("#loadI", Long.toString(num.numVal), null, intVal));
        BNFGrammarAST.graphMIPS.append("#loadI " + num.numVal + "=&gt; " + intVal).append(System.getProperty("line.separator"));
        ArrayList<MIPSInstructionFromat> mipsIns = new ArrayList<MIPSInstructionFromat>();
        mipsIns = MIPSInstructionList.mipsInstruction(new MIPSInstructionFromat("loadI", Long.toString(num.numVal), null, intVal));
        for (MIPSInstructionFromat ins : mipsIns) {
            ib.addMipsInstruction(ins);
        }

        return intVal;

    }

    public static String ILOCSimpleExpression(BlockNode seb, SimpleExpressionNode seNode) {

        String se1;
        String se2;
        String seVal;
        String inst;
        String result;
        se1 = ILOCTerm(seb, seNode.smExpTerm1);
        //System.out.println(se1);
        if (seNode.smExpOp != null) {
            //BNFGrammarAST.ilocRegister++;
            //seVal = "r"+BNFGrammarAST.ilocRegister;
            seVal = MIPSRegister.getMIPSRegister();
            se2 = ILOCTerm(seb, seNode.smExpTerm2);
            inst = ILOCInstructionList.getILOCInstructions(seNode.smExpOp.opName, se1, se2, seVal);
            seb.addILOCList(inst);
            //System.out.println(inst);
            ArrayList<String> mipsArrIns1 = arrayElements(se1);
            ArrayList<String> mipsArrIns2 = arrayElements(se2);
            String arrIdentName1 = mipsArrIns1.get(0);
            
            String sr1Index1 = null;

            String arrIdentName2 = mipsArrIns2.get(0);
            String sr1Index2 = null;
            if (mipsArrIns1.size() > 1) {
                sr1Index1 = mipsArrIns1.get(1);
            }

            if (mipsArrIns2.size() > 1) {
                sr1Index2 = mipsArrIns2.get(1);
            }
            //MIPS

            seb.addMipsInstruction(new MIPSInstructionFromat("#" + getOpName(seNode.smExpOp.opName), se1, se2, seVal));
            //BNFGrammarAST.graphMIPS.append("#"+ seNode.smExpOp.opName+ "=&gt; "+intVal).append(System.getProperty("line.separator"));
            ArrayList<MIPSInstructionFromat> mipsIns = new ArrayList<MIPSInstructionFromat>();
            mipsIns = MIPSInstructionList.mipsInstruction(new MIPSInstructionFromat(getOpName(seNode.smExpOp.opName), arrIdentName1, arrIdentName2, seVal, sr1Index1, sr1Index2, null));
            for (MIPSInstructionFromat ins : mipsIns) {
                seb.addMipsInstruction(ins);
            }

            return seVal;
        }

        return se1;

    }

    public static String ILOCExpression(BlockNode eb, ExpressionNode expNode) {

        String exp1;
        String exp2;
        String expVal;
        String inst;
        String result;
        //System.out.println(expNode.seNode1);
        //if(expNode.seNode1!=null){
        exp1 = ILOCSimpleExpression(eb, expNode.seNode1);
        //}
        if (expNode.expOp != null) {
            //BNFGrammarAST.ilocRegister++;
            //expVal = "r"+BNFGrammarAST.ilocRegister;
            expVal = MIPSRegister.getMIPSRegister();
            exp2 = ILOCSimpleExpression(eb, expNode.seNode2);
            inst = ILOCInstructionList.getILOCInstructions(expNode.expOp.opName, exp1, exp2, expVal);
            eb.addILOCList(inst);
            result = inst;
            if (inst.lastIndexOf(";") != -1) {
                result = inst.substring(inst.lastIndexOf(";") + 2, inst.length());
            }

            ArrayList<String> mipsArrIns1 = arrayElements(exp1);
            ArrayList<String> mipsArrIns2 = arrayElements(exp2);
            String arrIdentName1 = mipsArrIns1.get(0);
            String sr1Index1 = null;

            String arrIdentName2 = mipsArrIns2.get(0);
            String sr1Index2 = null;
            if (mipsArrIns1.size() > 1) {
                sr1Index1 = mipsArrIns1.get(1);
            }

            if (mipsArrIns2.size() > 1) {
                sr1Index2 = mipsArrIns2.get(1);
            }
            //MIPS

            eb.addMipsInstruction(new MIPSInstructionFromat("#" + getOpName(expNode.expOp.opName), exp1, exp2, expVal));
            ArrayList<MIPSInstructionFromat> mipsIns = new ArrayList<MIPSInstructionFromat>();
            mipsIns = MIPSInstructionList.mipsInstruction(new MIPSInstructionFromat(getOpName(expNode.expOp.opName), arrIdentName1, arrIdentName2, expVal, sr1Index1, sr1Index2, null));
            for (MIPSInstructionFromat ins : mipsIns) {
                eb.addMipsInstruction(ins);
            }

            return expVal;
        }
        return exp1;

    }

    public static String ILOCFactor(BlockNode fb, FactorNode fact) {

        if (fact.expNodeF != null) {
            return ILOCExpression(fb, fact.expNodeF);
        }else if (fact.idFactorF != null) {
          return ILOCIdent(fb, fact.idFactorF);
        } 
        else if (fact.numNodeF != null) {
            return ILOCInt(fb, fact.numNodeF);
        } else if (fact.boolNodeF != null) {
            return ILOCBool(fb, fact.boolNodeF);
        } else {
            return null;
        }
    }

    public static String ILOCTerm(BlockNode tb, TermNode t) {

        String term1;
        String term2;
        String termVal;
        String inst;
        term1 = ILOCFactor(tb, t.fNode1);
        //System.out.println(term1);
        if (t.opTerm != null) {
            //BNFGrammarAST.ilocRegister++;
            //termVal = "r"+BNFGrammarAST.ilocRegister;
            termVal = MIPSRegister.getMIPSRegister();
            term2 = ILOCFactor(tb, t.fNode2);
            inst = ILOCInstructionList.getILOCInstructions(t.opTerm.opName, term1, term2, termVal);
            tb.addILOCList(inst);

            ArrayList<String> mipsArrIns1 = arrayElements(term1);
            ArrayList<String> mipsArrIns2 = arrayElements(term2);
            String arrIdentName1 = mipsArrIns1.get(0);
            String sr1Index1 = null;

            String arrIdentName2 = mipsArrIns2.get(0);
            String sr1Index2 = null;
            if (mipsArrIns1.size() > 1) {
                sr1Index1 = mipsArrIns1.get(1);
            }

            if (mipsArrIns2.size() > 1) {
                sr1Index2 = mipsArrIns2.get(1);
            }
            //MIPS

            tb.addMipsInstruction(new MIPSInstructionFromat("#" + getOpName(t.opTerm.opName), term1, term2, termVal));
            ArrayList<MIPSInstructionFromat> mipsIns = new ArrayList<MIPSInstructionFromat>();
            mipsIns = MIPSInstructionList.mipsInstruction(new MIPSInstructionFromat(getOpName(t.opTerm.opName), arrIdentName1, arrIdentName2, termVal, sr1Index1, sr1Index2, null));
            for (MIPSInstructionFromat ins : mipsIns) {
                tb.addMipsInstruction(ins);
            }

            return termVal;
        }
        return term1;

    }

    public static BlockNode ILOCWriteInt(BlockNode wb, WriteIntNode wNode) {
        String writeInt;
        String inst;
        String result;
        writeInt = ILOCExpression(wb, wNode.wrintExpNode);
        result = writeInt;
        if (writeInt.lastIndexOf(";") != -1) {
            result = writeInt.substring(writeInt.lastIndexOf(";") + 2, writeInt.length());
        }
        
        inst = "writeint " + result;
        wb.addILOCList(inst);
        //System.out.println(wb.ILOCList.size());
        BNFGrammarAST.graphILOC.append("<tr>");
        PrintILOC.printILOC(wb, "writeint");
        //PrintILOC.printILOC(wb, "0");
        //PrintILOC.printILOC(db, "=&gt;");
        PrintILOC.printILOC(wb, result);
        BNFGrammarAST.graphILOC.append("</tr>");

        //MIPS
        wb.addMipsInstruction(new MIPSInstructionFromat("#" + "writeint", result, null, null));
        BNFGrammarAST.graphMIPS.append("#writeint " + result).append(System.getProperty("line.separator"));
        ArrayList<String> mipsArrIns = arrayElements(writeInt);
        String arrIdentName = mipsArrIns.get(0);
        String sr1Index = null;

        ArrayList<MIPSInstructionFromat> mipsIns = new ArrayList<MIPSInstructionFromat>();
        
        if (mipsArrIns.size() > 1) {
            sr1Index = mipsArrIns.get(1);
            mipsIns = MIPSInstructionList.mipsInstruction(new MIPSInstructionFromat("writeint", arrIdentName, null, null, sr1Index, null, null));
            for (MIPSInstructionFromat ins : mipsIns) {
                wb.addMipsInstruction(ins);
            }
        } else {
            //System.out.println("CHECK");
            mipsIns = MIPSInstructionList.mipsInstruction(new MIPSInstructionFromat("writeint", result, null, null));
            for (MIPSInstructionFromat ins : mipsIns) {
                wb.addMipsInstruction(ins);
            }
        }

        return wb;

    }

    public static BlockNode ILOCExit(BlockNode eb, ExitStatementNode exitNode) {

        eb.addILOCList("exit");
        BNFGrammarAST.graphILOC.append("<tr>");
        PrintILOC.printILOC(eb, "exit");

        BNFGrammarAST.graphILOC.append("</tr>");

        //MIPS
        eb.addMipsInstruction(new MIPSInstructionFromat("#" + "exit", null, null, null));
        BNFGrammarAST.graphMIPS.append("#exit ").append(System.getProperty("line.separator"));
        ArrayList<MIPSInstructionFromat> mipsIns = new ArrayList<MIPSInstructionFromat>();
        mipsIns = MIPSInstructionList.mipsInstruction(new MIPSInstructionFromat("exit", null, null, null));
        for (MIPSInstructionFromat ins : mipsIns) {
            eb.addMipsInstruction(ins);
        }
        return eb;

    }

    public static BlockNode ILOCAssignment(BlockNode ab, AssignmentNode aNode) {

        String asgn1;
        String asgn2;
        String inst;
        asgn1 = ILOCIdent(ab, aNode.identifierNode);        
        if (aNode.expressionNode != null) {
            if (aNode.asgnOp != null) {
                //System.out.println(aNode.expressionNode);
                asgn2 = ILOCExpression(ab, aNode.expressionNode);

                inst = "i2i " + asgn2 + "=&gt; " + asgn1;
                ab.addILOCList(inst);
                BNFGrammarAST.graphILOC.append("<tr>");
                PrintILOC.printILOC(ab, "i2i");
                PrintILOC.printILOC(ab, asgn2);
                //PrintILOC.printILOC(db, "=&gt;");
                PrintILOC.printILOC(ab, " =&gt; " + asgn1);
                BNFGrammarAST.graphILOC.append("</tr>");

                ArrayList<String> mipsArrIns1 = arrayElements(asgn1);
                ArrayList<String> mipsArrIns2 = arrayElements(asgn2);
                String arrIdentName1 = mipsArrIns1.get(0);
                String sr1Index1 = null;

                String arrIdentName2 = mipsArrIns2.get(0);
                String sr1Index2 = null;
                if (mipsArrIns1.size() > 1) {
                    sr1Index1 = mipsArrIns1.get(1);
                }

                if (mipsArrIns2.size() > 1) {
                    sr1Index2 = mipsArrIns2.get(1);
                }
                //MIPS
                ab.addMipsInstruction(new MIPSInstructionFromat("#" + "i2i", asgn2, null, asgn1));
                BNFGrammarAST.graphMIPS.append("#i2i " + asgn2 + "=> " + asgn1).append(System.getProperty("line.separator"));

                ArrayList<MIPSInstructionFromat> mipsIns = new ArrayList<MIPSInstructionFromat>();
                mipsIns = MIPSInstructionList.mipsInstruction(new MIPSInstructionFromat("i2i", arrIdentName2, null, arrIdentName1, sr1Index2, null, sr1Index1));
                for (MIPSInstructionFromat ins : mipsIns) {
                    ab.addMipsInstruction(ins);
                }

            }
        } else if (aNode.readInt != null) {
            
            if (aNode.readInt != null) {                
                inst = "readint =&gt; " + asgn1;
                ab.addILOCList(inst);
                BNFGrammarAST.graphILOC.append("<tr>");
                PrintILOC.printILOC(ab, "readint");
                //PrintILOC.printILOC(ab, Integer.toString(boolValue));
                PrintILOC.printILOC(ab, "");
                PrintILOC.printILOC(ab, "=&gt; " + asgn1);
                BNFGrammarAST.graphILOC.append("</tr>");

                ArrayList<String> mipsArrIns = arrayElements(asgn1);
                
                String arrIdentName = mipsArrIns.get(0);
                String de1Index = null;
                if (mipsArrIns.size() > 1) {
                    de1Index = mipsArrIns.get(1);                    
                }
                //MIPS
                ab.addMipsInstruction(new MIPSInstructionFromat("#" + "readint", null, null, asgn1));
                BNFGrammarAST.graphMIPS.append("#readint => " + asgn1).append(System.getProperty("line.separator"));
                ArrayList<MIPSInstructionFromat> mipsIns = new ArrayList<MIPSInstructionFromat>();
                //NOTICE
                mipsIns = MIPSInstructionList.mipsInstruction(new MIPSInstructionFromat("readint", null, null, arrIdentName, null, null, de1Index));
                
                for (MIPSInstructionFromat ins : mipsIns) {
                    ab.addMipsInstruction(ins);
                }
            }
        }
        return ab;

    }

    public static BlockNode ILOCWhileStatement(BlockNode whb, WhileStatementNode wsNode) {

        String jmpwhile;
        String jmpExit;
        String expWhile;
        String expInst;
        String nestedExp;
        String result;
        BlockNode whileBlock = whb;
        BlockNode expWhileBlock = new BlockNode();
        BlockNode stmtWhileBlock = new BlockNode();
        BlockNode stmtExitBlock = new BlockNode();

        jmpwhile = "jumpl -&gt; " + expWhileBlock.blkName;
        whb.addILOCList(jmpwhile);
        BNFGrammarAST.graphILOC.append("<tr>");
        PrintILOC.printILOC(whb, "jumpl");
        PrintILOC.printILOC(whb, "");
        //PrintILOC.printILOC(db, "=&gt;");
        PrintILOC.printILOC(whb, "-&gt; " + expWhileBlock.blkName);
        BNFGrammarAST.graphILOC.append("</tr>");

        //MIPS
        BNFGrammarAST.graphMIPS.append(System.getProperty("line.separator"));
        BNFGrammarAST.graphMIPS.append(System.getProperty("line.separator"));
        whb.addMipsInstruction(new MIPSInstructionFromat("#" + "jumpl", null, null, expWhileBlock.blkName));
        BNFGrammarAST.graphMIPS.append("#jumpl -> " + expWhileBlock.blkName).append(System.getProperty("line.separator"));
        ArrayList<MIPSInstructionFromat> mipsIns = new ArrayList<MIPSInstructionFromat>();
        mipsIns = MIPSInstructionList.mipsInstruction(new MIPSInstructionFromat("jumpl", null, null, expWhileBlock.blkName));
        for (MIPSInstructionFromat ins : mipsIns) {
            whb.addMipsInstruction(ins);
        }

        BNFGrammarAST.graphILOC.append("</table>>,fillcolor=\"/x11/white\",shape=box]").append(System.getProperty("line.separator"));
        //whb.blockVisited =true; //

        BNFGrammarAST.graphILOC.append("n" + whb.blkNum + " -> n" + expWhileBlock.blkNum).append(System.getProperty("line.separator"));;
        whb.addchildrenBlock(expWhileBlock);
        BNFGrammarAST.graphILOC.append("n" + expWhileBlock.blkNum);
        BNFGrammarAST.graphILOC.append(" [label=<<table border=\"0\">");
        BNFGrammarAST.graphILOC.append("<tr><td border=\"1\" colspan=\"3\">" + expWhileBlock.blkName + "</td></tr>");
        BNFGrammarAST.graphMIPS.append(expWhileBlock.blkName + ":").append(System.getProperty("line.separator"));

        expWhile = ILOCExpression(expWhileBlock, wsNode.whileExpressionNode);
        result = expWhile;
        if (expWhile.lastIndexOf(";") != -1) {
            result = expWhile.substring(expWhile.lastIndexOf(";") + 2, expWhile.length());
        }
        expInst = "cbr " + result + " -&gt; " + stmtWhileBlock.blkName + " , " + stmtExitBlock.blkName;
        BNFGrammarAST.graphILOC.append("<tr>");
        PrintILOC.printILOC(expWhileBlock, "cbr");
        PrintILOC.printILOC(expWhileBlock, result);
        //PrintILOC.printILOC(db, "=&gt;");
        PrintILOC.printILOC(expWhileBlock, "-&gt; " + stmtWhileBlock.blkName + " , " + stmtExitBlock.blkName);
        BNFGrammarAST.graphILOC.append("</tr>");
        expWhileBlock.addILOCList(expInst);
        BNFGrammarAST.graphILOC.append("</table>>,fillcolor=\"/x11/white\",shape=box]").append(System.getProperty("line.separator"));
        //expWhileBlock.blockVisited =true; //
        //MIPS
        expWhileBlock.addMipsInstruction(new MIPSInstructionFromat("#" + "cbr", result, stmtWhileBlock.blkName, stmtExitBlock.blkName));
        BNFGrammarAST.graphMIPS.append("#cbr " + result + " -> " + stmtWhileBlock.blkName + " , " + stmtExitBlock.blkName).append(System.getProperty("line.separator"));

        ArrayList<MIPSInstructionFromat> mipsIns1 = new ArrayList<MIPSInstructionFromat>();
        mipsIns1 = MIPSInstructionList.mipsInstruction(new MIPSInstructionFromat("cbr", result, stmtWhileBlock.blkName, stmtExitBlock.blkName));
        for (MIPSInstructionFromat ins : mipsIns1) {
            expWhileBlock.addMipsInstruction(ins);
        }

        expWhileBlock.addchildrenBlock(stmtWhileBlock);
        expWhileBlock.addchildrenBlock(stmtExitBlock);

        BNFGrammarAST.graphILOC.append("n" + expWhileBlock.blkNum + " -> n" + stmtWhileBlock.blkNum).append(System.getProperty("line.separator"));;
        BNFGrammarAST.graphILOC.append("n" + expWhileBlock.blkNum + " -> n" + stmtExitBlock.blkNum).append(System.getProperty("line.separator"));;

        BNFGrammarAST.graphILOC.append("n" + stmtWhileBlock.blkNum);
        BNFGrammarAST.graphILOC.append(" [label=<<table border=\"0\">");
        BNFGrammarAST.graphILOC.append("<tr><td border=\"1\" colspan=\"3\">" + stmtWhileBlock.blkName + "</td></tr>");
        BNFGrammarAST.graphMIPS.append(stmtWhileBlock.blkName + ":").append(System.getProperty("line.separator"));
        BlockNode nestedStmtBlock = ILOCStatementSequence(stmtWhileBlock, wsNode.whileStmtSeqNode);
        nestedExp = "jumpl -&gt; " + expWhileBlock.blkName;
        BNFGrammarAST.graphILOC.append("<tr>");
        PrintILOC.printILOC(whb, "jumpl");
        PrintILOC.printILOC(whb, "");
        //PrintILOC.printILOC(db, "=&gt;");
        PrintILOC.printILOC(whb, "-&gt; " + expWhileBlock.blkName);
        BNFGrammarAST.graphILOC.append("</tr>");
        BNFGrammarAST.graphILOC.append("</table>>,fillcolor=\"/x11/white\",shape=box]").append(System.getProperty("line.separator"));
        nestedStmtBlock.addILOCList(nestedExp);
        nestedStmtBlock.addParentBlock(expWhileBlock);

        //MIPS
        nestedStmtBlock.addMipsInstruction(new MIPSInstructionFromat("#" + "jumpl", null, null, expWhileBlock.blkName));
        BNFGrammarAST.graphMIPS.append("#jumpl -> " + expWhileBlock.blkName).append(System.getProperty("line.separator"));

        ArrayList<MIPSInstructionFromat> mipsIns2 = new ArrayList<MIPSInstructionFromat>();
        mipsIns2 = MIPSInstructionList.mipsInstruction(new MIPSInstructionFromat("jumpl", null, null, expWhileBlock.blkName));
        for (MIPSInstructionFromat ins : mipsIns2) {
            nestedStmtBlock.addMipsInstruction(ins);
        }

        BNFGrammarAST.graphILOC.append("n" + nestedStmtBlock.blkNum + " -> n" + expWhileBlock.blkNum).append(System.getProperty("line.separator"));;

        //expWhileBlock.addchildrenBlock(stmtWhileBlock);
        //expWhileBlock.addchildrenBlock(stmtExitBlock);
        BNFGrammarAST.graphILOC.append("n" + stmtExitBlock.blkNum);
        BNFGrammarAST.graphILOC.append(" [label=<<table border=\"0\">");
        BNFGrammarAST.graphILOC.append("<tr><td border=\"1\" colspan=\"3\">" + stmtExitBlock.blkName + "</td></tr>");
        BNFGrammarAST.graphMIPS.append(stmtExitBlock.blkName + ":").append(System.getProperty("line.separator"));
        //stmtExitBlock.blockVisited=true;
        return stmtExitBlock;

    }

    public static BlockNode ILOCIfStatement(BlockNode ib, IfStatementNode iNode) {

        String ifExp;
        String ifExpInst;
        BlockNode ifExpBlock = ib;
        //BlockNode ifExpBlock = new BlockNode();
        BlockNode ifStmtBlock = new BlockNode();
        BlockNode ifElseBlock = new BlockNode();
        BlockNode ifExitBlock = new BlockNode();
        BlockNode nestedStmtBlock = null;

        ifExp = ILOCExpression(ifExpBlock, iNode.expressionNode);
        if (iNode.elsestmtSequence.statementSequence.size() > 0) {
            ifExpInst = "cbr " + ifExp + " =&gt; " + ifStmtBlock.blkName + ", " + ifElseBlock.blkName;
            ifExpBlock.addILOCList(ifExpInst);
            BNFGrammarAST.graphILOC.append("<tr>");
            PrintILOC.printILOC(ifExpBlock, "cbr");
            PrintILOC.printILOC(ifExpBlock, ifExp);
            //PrintILOC.printILOC(db, "=&gt;");
            PrintILOC.printILOC(ifExpBlock, "-&gt; " + ifStmtBlock.blkName + ", " + ifElseBlock.blkName);
            BNFGrammarAST.graphILOC.append("</tr>");

            //MIPS
            ifExpBlock.addMipsInstruction(new MIPSInstructionFromat("#" + "cbr", ifExp, ifStmtBlock.blkName, ifElseBlock.blkName));
            BNFGrammarAST.graphMIPS.append("#cbr " + ifExp + " =&gt; " + ifStmtBlock.blkName + ", " + ifElseBlock.blkName).append(System.getProperty("line.separator"));
            ArrayList<MIPSInstructionFromat> mipsIns2 = new ArrayList<MIPSInstructionFromat>();
            mipsIns2 = MIPSInstructionList.mipsInstruction(new MIPSInstructionFromat("cbr", ifExp, ifStmtBlock.blkName, ifElseBlock.blkName));
            for (MIPSInstructionFromat ins : mipsIns2) {
                ifExpBlock.addMipsInstruction(ins);
            }
        } else {
            ifExpInst = "cbr " + ifExp + " =&gt; " + ifStmtBlock.blkName + ", " + ifExitBlock.blkName;
            ifExpBlock.addILOCList(ifExpInst);
            BNFGrammarAST.graphILOC.append("<tr>");
            PrintILOC.printILOC(ifExpBlock, "cbr");
            PrintILOC.printILOC(ifExpBlock, ifExp);
            //PrintILOC.printILOC(db, "=&gt;");
            PrintILOC.printILOC(ifExpBlock, "-&gt; " + ifStmtBlock.blkName + ", " + ifExitBlock.blkName);
            BNFGrammarAST.graphILOC.append("</tr>");

            //MIPS
            ifExpBlock.addMipsInstruction(new MIPSInstructionFromat("#" + "cbr", ifExp, ifStmtBlock.blkName, ifExitBlock.blkName));
            BNFGrammarAST.graphMIPS.append("#cbr " + ifExp + " =&gt; " + ifStmtBlock.blkName + ", " + ifExitBlock.blkName).append(System.getProperty("line.separator"));
            ArrayList<MIPSInstructionFromat> mipsIns2 = new ArrayList<MIPSInstructionFromat>();
            mipsIns2 = MIPSInstructionList.mipsInstruction(new MIPSInstructionFromat("cbr", ifExp, ifStmtBlock.blkName, ifExitBlock.blkName));
            for (MIPSInstructionFromat ins : mipsIns2) {
                ifExpBlock.addMipsInstruction(ins);
            }
        }

        BNFGrammarAST.graphILOC.append("</table>>,fillcolor=\"/x11/white\",shape=box]").append(System.getProperty("line.separator"));
        //ifExpBlock.blockVisited=true;
        BNFGrammarAST.graphMIPS.append(System.getProperty("line.separator"));
        BNFGrammarAST.graphMIPS.append(System.getProperty("line.separator"));

        ifExpBlock.addchildrenBlock(ifStmtBlock);

        BNFGrammarAST.graphILOC.append("n" + ifExpBlock.blkNum + " -> n" + ifStmtBlock.blkNum).append(System.getProperty("line.separator"));;

        BNFGrammarAST.graphILOC.append("n" + ifStmtBlock.blkNum);
        BNFGrammarAST.graphILOC.append(" [label=<<table border=\"0\">");
        BNFGrammarAST.graphILOC.append("<tr><td border=\"1\" colspan=\"3\">" + ifStmtBlock.blkName + "</td></tr>");
        BNFGrammarAST.graphMIPS.append(ifStmtBlock.blkName + ":").append(System.getProperty("line.separator"));
        nestedStmtBlock = ILOCStatementSequence(ifStmtBlock, iNode.ifstmtSequence);

        BNFGrammarAST.graphILOC.append("</table>>,fillcolor=\"/x11/white\",shape=box]").append(System.getProperty("line.separator"));

        nestedStmtBlock.addchildrenBlock(ifExitBlock);

        BNFGrammarAST.graphILOC.append("n" + nestedStmtBlock.blkNum + " -> n" + ifExitBlock.blkNum).append(System.getProperty("line.separator"));;

        if (iNode.elsestmtSequence.statementSequence.size() > 0) {
            ifExpBlock.addchildrenBlock(ifElseBlock);
            nestedStmtBlock.addMipsInstruction(new MIPSInstructionFromat("#" + "jumpl", null, null, ifExitBlock.blkName));
            BNFGrammarAST.graphMIPS.append("#jumpl -> " + ifExitBlock.blkName).append(System.getProperty("line.separator"));

            ArrayList<MIPSInstructionFromat> mipsIns2 = new ArrayList<MIPSInstructionFromat>();
            mipsIns2 = MIPSInstructionList.mipsInstruction(new MIPSInstructionFromat("jumpl", null, null, ifExitBlock.blkName));
            for (MIPSInstructionFromat ins : mipsIns2) {
                nestedStmtBlock.addMipsInstruction(ins);
            }

            BNFGrammarAST.graphILOC.append("n" + ifExpBlock.blkNum + " -> n" + ifElseBlock.blkNum).append(System.getProperty("line.separator"));;

            BNFGrammarAST.graphILOC.append("n" + ifElseBlock.blkNum);
            BNFGrammarAST.graphILOC.append(" [label=<<table border=\"0\">");
            BNFGrammarAST.graphILOC.append("<tr><td border=\"1\" colspan=\"3\">" + ifElseBlock.blkName + "</td></tr>");
            BNFGrammarAST.graphMIPS.append(ifElseBlock.blkName + ":").append(System.getProperty("line.separator"));

            BlockNode nestedElseStmtBlock = ILOCStatementSequence(ifElseBlock, iNode.elsestmtSequence);

            //BNFGrammarAST.graphILOC.append("</tr>");
            BNFGrammarAST.graphILOC.append("</table>>,fillcolor=\"/x11/white\",shape=box]").append(System.getProperty("line.separator"));

            nestedElseStmtBlock.addchildrenBlock(ifExitBlock);
            BNFGrammarAST.graphILOC.append("n" + nestedElseStmtBlock.blkNum + " -> n" + ifExitBlock.blkNum).append(System.getProperty("line.separator"));;

            BNFGrammarAST.graphILOC.append("n" + ifExitBlock.blkNum);
            BNFGrammarAST.graphILOC.append(" [label=<<table border=\"0\">");
            BNFGrammarAST.graphILOC.append("<tr><td border=\"1\" colspan=\"3\">" + ifExitBlock.blkName + "</td></tr>");
            BNFGrammarAST.graphMIPS.append(ifExitBlock.blkName + ":").append(System.getProperty("line.separator"));
        } else {
            //ifExpBlock.addchildrenBlock(ifExitBlock);

            ifExpBlock.addchildrenBlock(ifExitBlock);

            BNFGrammarAST.graphILOC.append("n" + ifExpBlock.blkNum + " -> n" + ifExitBlock.blkNum).append(System.getProperty("line.separator"));;

            BNFGrammarAST.graphILOC.append("n" + ifExitBlock.blkNum);
            BNFGrammarAST.graphILOC.append(" [label=<<table border=\"0\">");
            BNFGrammarAST.graphILOC.append("<tr><td border=\"1\" colspan=\"3\">" + ifExitBlock.blkName + "</td></tr>");
            BNFGrammarAST.graphMIPS.append(ifExitBlock.blkName + ":").append(System.getProperty("line.separator"));

        }
        return ifExitBlock;

    }

    public static BlockNode ILOCStatementSequence(BlockNode stmtb, StatementSequenceNode stmtNode) {

        BlockNode stmtBlock = stmtb;
        //Collections.reverse(stmtNode.statementSequence);
        for (StatementNode stmt : stmtNode.statementSequence) {
            if (stmt.getClass().getName().equals("compiler_project.WriteIntNode")) {
                WriteIntNode writeInt = (WriteIntNode) stmt;
                stmtBlock = ILOCWriteInt(stmtBlock, writeInt);
            }

            if (stmt.getClass().getName().equals("compiler_project.AssignmentNode")) {
                AssignmentNode asgn = (AssignmentNode) stmt;
                //System.out.println(asgn);
                stmtBlock = ILOCAssignment(stmtBlock, asgn);
            }

            if (stmt.getClass().getName().equals("compiler_project.IfStatementNode")) {
                IfStatementNode ifstmt = (IfStatementNode) stmt;
                stmtBlock = ILOCIfStatement(stmtBlock, ifstmt);
            }

            if (stmt.getClass().getName().equals("compiler_project.WhileStatementNode")) {
                WhileStatementNode whilestmt = (WhileStatementNode) stmt;
                stmtBlock = ILOCWhileStatement(stmtBlock, whilestmt);
            }

            if (stmt.getClass().getName().equals("compiler_project.ExitStatementNode")) {
                ExitStatementNode exitstmt = (ExitStatementNode) stmt;
                stmtBlock = ILOCExit(stmtBlock, exitstmt);
            }
        }
        return stmtBlock;

    }

    public static String getOpName(String operator) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("+", "add");
        map.put("-", "sub");
        map.put("*", "mul");
        map.put("div", "div");
        map.put("mod", "rem");
        map.put("<", "cmp_LT");
        map.put(">", "cmp_GT");
        map.put("<=", "cmp_LE");
        map.put(">=", "cmp_GE");
        map.put("=", "cmp_EQ");
        map.put("!=", "cmp_NE");

        return map.get(operator);
    }

    public static ArrayList<String> arrayElements(String arrayVar) {
        ArrayList<String> arrayIndex = new ArrayList<String>();
        //arrayIndex.add(arrayVar);
        //System.out.println(arrayVar);
        String arrName = null;
        String arrIndex = null;
        if (arrayVar.contains("[")) {
            arrName = arrayVar.substring(0, arrayVar.indexOf('['));
            arrIndex = arrayVar.substring(arrayVar.indexOf('[') + 1, arrayVar.indexOf(']'));
            arrayIndex.add(arrName);
            arrayIndex.add(arrIndex);
            return arrayIndex;
        }

        if (arrayVar.contains("(")) {
            arrName = arrayVar.substring(0, arrayVar.indexOf(')'));
            arrIndex = arrayVar.substring(arrayVar.indexOf('(') + 1, arrayVar.indexOf(')'));
            arrayIndex.add(arrName);
            arrayIndex.add(arrIndex);
            return arrayIndex;
        }

        arrayIndex.add(arrayVar);
        return arrayIndex;
    }
}
