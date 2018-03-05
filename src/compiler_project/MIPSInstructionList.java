package compiler_project;

import java.util.ArrayList;

public class MIPSInstructionList {

    public static int mipsTRegister = 0;
    public static int cbrCounter = 0;

    public static String nextMipsTRegister() {
        return "$t" + mipsTRegister++;
    }

    public static void mipsTRegisterReset() {
        mipsTRegister = 0;
    }

    public static ArrayList<MIPSInstructionFromat> mipsInstruction(MIPSInstructionFromat inst) {
        ArrayList<MIPSInstructionFromat> mipsIns = new ArrayList<MIPSInstructionFromat>();
        String src1;
        String src2;
        String mipsNextTreg;
        String mipsNextTreg1;
        String dest;
        if (inst.opName.equals("exit")) {
            mipsIns.add(new MIPSInstructionFromat("j", null, null, "exit"));
            BNFGrammarAST.graphMIPS.append("j exit").append(System.getProperty("line.separator"));
            return mipsIns;

        } else if (inst.opName.equals("loadI")) {
            String offset = HashTableforSymbol.getInsatnce().getIdOffset(inst.dest1);
            String destAddress = offset + "($fp)";
            mipsNextTreg = nextMipsTRegister();

            mipsIns.add(new MIPSInstructionFromat("li", inst.src1, null, mipsNextTreg));
            BNFGrammarAST.graphMIPS.append("li " + mipsNextTreg + ", " + inst.src1).append(System.getProperty("line.separator"));

            if (inst.dest1Index == null) {

                mipsIns.add(new MIPSInstructionFromat("sw", destAddress, null, mipsNextTreg));
                BNFGrammarAST.graphMIPS.append("sw " + mipsNextTreg + ", " + destAddress).append(System.getProperty("line.separator"));
            } else {
                //String ldArray = nextMipsTRegister();
                mipsNextTreg1 = nextMipsTRegister();
                offset = HashTableforSymbol.getInsatnce().getIdOffset(inst.dest1Index);
                destAddress = offset + "($fp)";
				 //System.out.println(inst.dest1Index);
                //int index = Integer.parseInt(inst.dest1Index.toString());
                //index  = index * 4; 
                mipsIns.add(new MIPSInstructionFromat("lw", destAddress, null, mipsNextTreg1));
                BNFGrammarAST.graphMIPS.append("lw " + mipsNextTreg1 + ", " + destAddress).append(System.getProperty("line.separator"));
                mipsIns.add(new MIPSInstructionFromat("mul", mipsNextTreg1, "4", mipsNextTreg1));
                BNFGrammarAST.graphMIPS.append("mul " + mipsNextTreg1 + ", " + mipsNextTreg1 + ", 4").append(System.getProperty("line.separator"));

                mipsIns.add(new MIPSInstructionFromat("sw", inst.dest1 + "(" + mipsNextTreg1 + ")", null, mipsNextTreg));
                BNFGrammarAST.graphMIPS.append("sw " + mipsNextTreg + ", " + inst.dest1 + "(" + mipsNextTreg1 + ")").append(System.getProperty("line.separator"));

            }
            mipsTRegisterReset();
            return mipsIns;
        } else if (inst.opName.equals("readint")) {
            String offset = HashTableforSymbol.getInsatnce().getIdOffset(inst.dest1);
            String destAddress = offset + "($fp)";
            mipsNextTreg = nextMipsTRegister();
            mipsIns.add(new MIPSInstructionFromat("li", "5", null, "$v0"));
            BNFGrammarAST.graphMIPS.append("li $v0, 5").append(System.getProperty("line.separator"));
            mipsIns.add(new MIPSInstructionFromat("syscall", null, null, null));
            BNFGrammarAST.graphMIPS.append("syscall ").append(System.getProperty("line.separator"));
            mipsIns.add(new MIPSInstructionFromat("add", "$v0", "$zero", mipsNextTreg));
            BNFGrammarAST.graphMIPS.append("add " + mipsNextTreg + ", $v0, $zero").append(System.getProperty("line.separator"));

            if (inst.dest1Index == null) {
                mipsIns.add(new MIPSInstructionFromat("sw", destAddress, null, mipsNextTreg));
                BNFGrammarAST.graphMIPS.append("sw " + mipsNextTreg + ", " + destAddress).append(System.getProperty("line.separator"));
            } else {
                mipsNextTreg1 = nextMipsTRegister();
                offset = HashTableforSymbol.getInsatnce().getIdOffset(inst.dest1Index);
                destAddress = offset + "($fp)";
                mipsIns.add(new MIPSInstructionFromat("lw", destAddress, null, mipsNextTreg1));
                BNFGrammarAST.graphMIPS.append("lw " + mipsNextTreg1 + ", " + destAddress).append(System.getProperty("line.separator"));
                mipsIns.add(new MIPSInstructionFromat("mul", mipsNextTreg1, "4", mipsNextTreg1));
                BNFGrammarAST.graphMIPS.append("mul " + mipsNextTreg1 + ", " + mipsNextTreg1 + ", 4").append(System.getProperty("line.separator"));

                mipsIns.add(new MIPSInstructionFromat("sw", inst.dest1 + "(" + mipsNextTreg1 + ")", null, mipsNextTreg));
                BNFGrammarAST.graphMIPS.append("sw " + mipsNextTreg + ", " + inst.dest1 + "(" + mipsNextTreg1 + ")").append(System.getProperty("line.separator"));

            }
            mipsTRegisterReset();
            return mipsIns;

        } else if (inst.opName.equals("writeint")) {
            String offset = HashTableforSymbol.getInsatnce().getIdOffset(inst.src1);
            String destAddress = offset + "($fp)";
            //mipsNextTreg = nextMipsTRegister();
            mipsIns.add(new MIPSInstructionFromat("li", "1", null, "$v0"));
            BNFGrammarAST.graphMIPS.append("li $v0, 1").append(System.getProperty("line.separator"));

            if (inst.src1Index == null) {
                mipsNextTreg = nextMipsTRegister();
                mipsIns.add(new MIPSInstructionFromat("lw", destAddress, null, mipsNextTreg));
                BNFGrammarAST.graphMIPS.append("lw " + mipsNextTreg + ", " + destAddress).append(System.getProperty("line.separator"));

            } else {

                mipsNextTreg = nextMipsTRegister();
                offset = HashTableforSymbol.getInsatnce().getIdOffset(inst.src1Index);
                destAddress = offset + "($fp)";
                mipsIns.add(new MIPSInstructionFromat("lw", destAddress, null, mipsNextTreg));
                BNFGrammarAST.graphMIPS.append("lw " + mipsNextTreg + ", " + destAddress).append(System.getProperty("line.separator"));
                mipsIns.add(new MIPSInstructionFromat("mul", mipsNextTreg, "4", mipsNextTreg));
                BNFGrammarAST.graphMIPS.append("mul " + mipsNextTreg + ", " + mipsNextTreg + ", 4").append(System.getProperty("line.separator"));

                mipsIns.add(new MIPSInstructionFromat("lw", inst.src1 + "(" + mipsNextTreg + ")", null, mipsNextTreg));
                BNFGrammarAST.graphMIPS.append("lw " + mipsNextTreg + ", " + inst.src1 + "(" + mipsNextTreg + ")").append(System.getProperty("line.separator"));

            }
            mipsIns.add(new MIPSInstructionFromat("add", mipsNextTreg, "$zero", "$a0"));
            BNFGrammarAST.graphMIPS.append("add $a0, " + mipsNextTreg + ", $zero").append(System.getProperty("line.separator"));
            mipsIns.add(new MIPSInstructionFromat("syscall", null, null, null));
            BNFGrammarAST.graphMIPS.append("syscall ").append(System.getProperty("line.separator"));
            mipsIns.add(new MIPSInstructionFromat("li", "4", null, "$v0"));
            BNFGrammarAST.graphMIPS.append("li $v0, 4").append(System.getProperty("line.separator"));
            mipsIns.add(new MIPSInstructionFromat("la", "newline", null, "$a0"));
            BNFGrammarAST.graphMIPS.append("la $a0, newline").append(System.getProperty("line.separator"));
            mipsIns.add(new MIPSInstructionFromat("syscall", null, null, null));
            BNFGrammarAST.graphMIPS.append("syscall ").append(System.getProperty("line.separator"));

            mipsTRegisterReset();
            return mipsIns;
        } else if (inst.opName.equals("i2i")) {
            String offset1 = HashTableforSymbol.getInsatnce().getIdOffset(inst.src1);
            String destAddress1 = offset1 + "($fp)";
            String offset2 = HashTableforSymbol.getInsatnce().getIdOffset(inst.dest1);
            String destAddress2 = offset2 + "($fp)";
            mipsNextTreg = nextMipsTRegister();

            if (inst.src1Index == null) {
                mipsIns.add(new MIPSInstructionFromat("lw", destAddress1, null, mipsNextTreg));
                BNFGrammarAST.graphMIPS.append("lw " + mipsNextTreg + ", " + destAddress1).append(System.getProperty("line.separator"));
            } else {
                offset1 = HashTableforSymbol.getInsatnce().getIdOffset(inst.src1Index);
                destAddress1 = offset1 + "($fp)";
                mipsIns.add(new MIPSInstructionFromat("lw", destAddress1, null, mipsNextTreg));
                BNFGrammarAST.graphMIPS.append("lw " + mipsNextTreg + ", " + destAddress1).append(System.getProperty("line.separator"));
                mipsIns.add(new MIPSInstructionFromat("mul", mipsNextTreg, "4", mipsNextTreg));
                BNFGrammarAST.graphMIPS.append("mul " + mipsNextTreg + ", " + mipsNextTreg + ", 4").append(System.getProperty("line.separator"));

                mipsIns.add(new MIPSInstructionFromat("lw", inst.src1 + "(" + mipsNextTreg + ")", null, mipsNextTreg));
                BNFGrammarAST.graphMIPS.append("lw " + mipsNextTreg + ", " + inst.src1 + "(" + mipsNextTreg + ")").append(System.getProperty("line.separator"));

            }

            if (inst.dest1Index == null) {
                mipsIns.add(new MIPSInstructionFromat("add", mipsNextTreg, "$zero", mipsNextTreg));
                BNFGrammarAST.graphMIPS.append("add " + mipsNextTreg + ", " + mipsNextTreg + ", $zero").append(System.getProperty("line.separator"));
                mipsIns.add(new MIPSInstructionFromat("sw", destAddress2, null, mipsNextTreg));
                BNFGrammarAST.graphMIPS.append("sw " + mipsNextTreg + ", " + destAddress2).append(System.getProperty("line.separator"));
            } else {
                mipsNextTreg1 = nextMipsTRegister();
                offset2 = HashTableforSymbol.getInsatnce().getIdOffset(inst.dest1Index);
                destAddress2 = offset2 + "($fp)";
                mipsIns.add(new MIPSInstructionFromat("lw", destAddress2, null, mipsNextTreg1));
                BNFGrammarAST.graphMIPS.append("lw " + mipsNextTreg1 + ", " + destAddress2).append(System.getProperty("line.separator"));
                mipsIns.add(new MIPSInstructionFromat("mul", mipsNextTreg1, "4", mipsNextTreg1));
                BNFGrammarAST.graphMIPS.append("mul " + mipsNextTreg1 + ", " + mipsNextTreg1 + ", 4").append(System.getProperty("line.separator"));

                mipsIns.add(new MIPSInstructionFromat("sw", inst.dest1 + "(" + mipsNextTreg1 + ")", null, mipsNextTreg));
                BNFGrammarAST.graphMIPS.append("sw " + mipsNextTreg + ", " + inst.dest1 + "(" + mipsNextTreg1 + ")").append(System.getProperty("line.separator"));

            }

            mipsTRegisterReset();
            return mipsIns;
        } else if (inst.opName.equals("jumpl")) {
            mipsIns.add(new MIPSInstructionFromat("j", null, null, inst.dest1));
            BNFGrammarAST.graphMIPS.append("j " + inst.dest1).append(System.getProperty("line.separator"));
            return mipsIns;

        } else if (inst.opName.equals("cbr")) {
            //second source is dest1, dest1 is dest2
            String offset1 = HashTableforSymbol.getInsatnce().getIdOffset(inst.src1);
            String destAddress1 = offset1 + "($fp)";
            mipsNextTreg = nextMipsTRegister();
            mipsIns.add(new MIPSInstructionFromat("lw", destAddress1, null, mipsNextTreg));
            BNFGrammarAST.graphMIPS.append("lw " + mipsNextTreg + ", " + destAddress1).append(System.getProperty("line.separator"));
            mipsIns.add(new MIPSInstructionFromat("bne", "$zero", inst.src2, mipsNextTreg));
            BNFGrammarAST.graphMIPS.append("bne " + mipsNextTreg + ", $zero, " + inst.src2).append(System.getProperty("line.separator"));
            mipsIns.add(new MIPSInstructionFromat("sw", destAddress1, null, mipsNextTreg));
            BNFGrammarAST.graphMIPS.append("sw " + mipsNextTreg + ", " + destAddress1).append(System.getProperty("line.separator"));
            cbrCounter++;
            BNFGrammarAST.graphMIPS.append("L" + cbrCounter + ":").append(System.getProperty("line.separator"));

            mipsIns.add(new MIPSInstructionFromat("j", null, null, inst.dest1));
            BNFGrammarAST.graphMIPS.append("j " + inst.dest1).append(System.getProperty("line.separator"));

            mipsTRegisterReset();
            return mipsIns;
        } else if (mipsArithOp(inst.opName) != null) {
            String offset1 = HashTableforSymbol.getInsatnce().getIdOffset(inst.src1);
            String srcAddress1 = offset1 + "($fp)";
            String offset2 = HashTableforSymbol.getInsatnce().getIdOffset(inst.src2);
            String srcAddress2 = offset2 + "($fp)";
            String offset3 = HashTableforSymbol.getInsatnce().getIdOffset(inst.dest1);
            String destAddress3 = offset3 + "($fp)";
            src1 = nextMipsTRegister();
            src2 = nextMipsTRegister();
            dest = nextMipsTRegister();
            if (inst.src1Index == null) {
                mipsIns.add(new MIPSInstructionFromat("lw", srcAddress1, null, src1));
                BNFGrammarAST.graphMIPS.append("lw " + src1 + ", " + srcAddress1).append(System.getProperty("line.separator"));
            } else {
                offset1 = HashTableforSymbol.getInsatnce().getIdOffset(inst.src1Index);
                srcAddress1 = offset1 + "($fp)";
                mipsIns.add(new MIPSInstructionFromat("lw", srcAddress1, null, src1));
                BNFGrammarAST.graphMIPS.append("lw " + src1 + ", " + srcAddress1).append(System.getProperty("line.separator"));
                mipsIns.add(new MIPSInstructionFromat("mul", src1, "4", src1));
                BNFGrammarAST.graphMIPS.append("mul " + src1 + ", " + src1 + ", 4").append(System.getProperty("line.separator"));

                mipsIns.add(new MIPSInstructionFromat("lw", inst.src1 + "(" + src1 + ")", null, src1));
                BNFGrammarAST.graphMIPS.append("lw " + src1 + ", " + inst.src1 + "(" + src1 + ")").append(System.getProperty("line.separator"));

            }

            if (inst.src2Index == null) {
                mipsIns.add(new MIPSInstructionFromat("lw", srcAddress2, null, src2));
                BNFGrammarAST.graphMIPS.append("lw " + src2 + ", " + srcAddress2).append(System.getProperty("line.separator"));
            } else {

                offset2 = HashTableforSymbol.getInsatnce().getIdOffset(inst.src1Index);
                srcAddress2 = offset2 + "($fp)";
                mipsIns.add(new MIPSInstructionFromat("lw", srcAddress2, null, src2));
                BNFGrammarAST.graphMIPS.append("lw " + src2 + ", " + srcAddress2).append(System.getProperty("line.separator"));
                mipsIns.add(new MIPSInstructionFromat("mul", src2, "4", src2));
                BNFGrammarAST.graphMIPS.append("mul " + src2 + ", " + src2 + ", 4").append(System.getProperty("line.separator"));

                mipsIns.add(new MIPSInstructionFromat("lw", inst.src2 + "(" + src2 + ")", null, src2));
                BNFGrammarAST.graphMIPS.append("lw " + src2 + ", " + inst.src2 + "(" + src2 + ")").append(System.getProperty("line.separator"));

            }
            mipsIns.add(new MIPSInstructionFromat(mipsArithOp(inst.opName), src1, src2, dest));
            BNFGrammarAST.graphMIPS.append(mipsArithOp(inst.opName) + " " + dest + ", " + src1 + ", " + src2).append(System.getProperty("line.separator"));

            if (inst.dest1Index == null) {
                mipsIns.add(new MIPSInstructionFromat("sw", destAddress3, null, dest));
                BNFGrammarAST.graphMIPS.append("sw " + dest + ", " + destAddress3).append(System.getProperty("line.separator"));
            } else {
                mipsNextTreg1 = nextMipsTRegister();
                offset3 = HashTableforSymbol.getInsatnce().getIdOffset(inst.dest1Index);
                destAddress3 = offset3 + "($fp)";
                mipsIns.add(new MIPSInstructionFromat("lw", destAddress3, null, mipsNextTreg1));
                BNFGrammarAST.graphMIPS.append("lw " + mipsNextTreg1 + ", " + destAddress3).append(System.getProperty("line.separator"));
                mipsIns.add(new MIPSInstructionFromat("mul", mipsNextTreg1, "4", mipsNextTreg1));
                BNFGrammarAST.graphMIPS.append("mul " + mipsNextTreg1 + ", " + mipsNextTreg1 + ", 4").append(System.getProperty("line.separator"));

                mipsIns.add(new MIPSInstructionFromat("sw", inst.dest1 + "(" + mipsNextTreg1 + ")", null, dest));
                BNFGrammarAST.graphMIPS.append("sw " + dest + ", " + inst.dest1 + "(" + mipsNextTreg1 + ")").append(System.getProperty("line.separator"));

            }
            mipsTRegisterReset();
            return mipsIns;
        } else if (mipsCFIns(inst.opName) != null) {
            String offset1 = HashTableforSymbol.getInsatnce().getIdOffset(inst.src1);
            String srcAddress1 = offset1 + "($fp)";
            String offset2 = HashTableforSymbol.getInsatnce().getIdOffset(inst.src2);
            String srcAddress2 = offset2 + "($fp)";
            String offset3 = HashTableforSymbol.getInsatnce().getIdOffset(inst.dest1);
            String destAddress3 = offset3 + "($fp)";
            src1 = nextMipsTRegister();
            src2 = nextMipsTRegister();
            dest = nextMipsTRegister();

            if (inst.src1Index == null) {
                mipsIns.add(new MIPSInstructionFromat("lw", srcAddress1, null, src1));
                BNFGrammarAST.graphMIPS.append("lw " + src1 + ", " + srcAddress1).append(System.getProperty("line.separator"));
            } else {
                offset1 = HashTableforSymbol.getInsatnce().getIdOffset(inst.src1Index);
                srcAddress1 = offset1 + "($fp)";
                mipsIns.add(new MIPSInstructionFromat("lw", srcAddress1, null, src1));
                BNFGrammarAST.graphMIPS.append("lw " + src1 + ", " + srcAddress1).append(System.getProperty("line.separator"));
                mipsIns.add(new MIPSInstructionFromat("mul", src1, "4", src1));
                BNFGrammarAST.graphMIPS.append("mul " + src1 + ", " + src1 + ", 4").append(System.getProperty("line.separator"));

                mipsIns.add(new MIPSInstructionFromat("lw", inst.src1 + "(" + src1 + ")", null, src1));
                BNFGrammarAST.graphMIPS.append("lw " + src1 + ", " + inst.src1 + "(" + src1 + ")").append(System.getProperty("line.separator"));

            }

            if (inst.src2Index == null) {
                mipsIns.add(new MIPSInstructionFromat("lw", srcAddress2, null, src2));
                BNFGrammarAST.graphMIPS.append("lw " + src2 + ", " + srcAddress2).append(System.getProperty("line.separator"));
            } else {
                offset2 = HashTableforSymbol.getInsatnce().getIdOffset(inst.src1Index);
                srcAddress2 = offset2 + "($fp)";
                mipsIns.add(new MIPSInstructionFromat("lw", srcAddress2, null, src2));
                BNFGrammarAST.graphMIPS.append("lw " + src2 + ", " + srcAddress2).append(System.getProperty("line.separator"));
                mipsIns.add(new MIPSInstructionFromat("mul", src2, "4", src2));
                BNFGrammarAST.graphMIPS.append("mul " + src2 + ", " + src2 + ", 4").append(System.getProperty("line.separator"));

                mipsIns.add(new MIPSInstructionFromat("lw", inst.src2 + "(" + src2 + ")", null, src2));
                BNFGrammarAST.graphMIPS.append("lw " + src2 + ", " + inst.src2 + "(" + src2 + ")").append(System.getProperty("line.separator"));

            }

            mipsIns.add(new MIPSInstructionFromat(mipsCFIns(inst.opName), src1, src2, dest));
            BNFGrammarAST.graphMIPS.append(mipsCFIns(inst.opName) + " " + dest + ", " + src1 + ", " + src2).append(System.getProperty("line.separator"));

            mipsIns.add(new MIPSInstructionFromat("sw", destAddress3, null, dest));
            BNFGrammarAST.graphMIPS.append("sw " + dest + ", " + destAddress3).append(System.getProperty("line.separator"));
            mipsTRegisterReset();
            return mipsIns;
        }

        return null;
    }

    public static String mipsArithOp(String opName) {
        String mipsOp = null;

        if (opName.equals("add")) {

            mipsOp = "addu";
        } else if (opName.equals("sub")) {

            mipsOp = "subu";
        } else if (opName.equals("mul")) {

            mipsOp = "mul";
        } else if (opName.equals("div")) {

            mipsOp = "div";
        } else if (opName.equals("rem")) {

            mipsOp = "rem";
        }

        return mipsOp;
    }

    public static String mipsCFIns(String opName) {
        String mipsOp = null;

        if (opName.equals("cmp_LT")) {

            mipsOp = "slt";
        } else if (opName.equals("cmp_GT")) {

            mipsOp = "sgt";
        } else if (opName.equals("cmp_LE")) {

            mipsOp = "sle";
        } else if (opName.equals("cmp_GE")) {

            mipsOp = "sge";
        } else if (opName.equals("cmp_EQ")) {

            mipsOp = "seq";
        } else if (opName.equals("cmp_NE")) {

            mipsOp = "sne";
        }
        return mipsOp;
    }

}
