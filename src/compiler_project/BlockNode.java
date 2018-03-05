package compiler_project;

import java.util.ArrayList;

class BlockNode {

    public String blkName;
    public StringBuffer blkCodeList = new StringBuffer();
    public ArrayList<StringBuffer> ILOCList;
    public ArrayList<BlockNode> parentBlock;
    public ArrayList<BlockNode> childrenBlock;
    public ArrayList<MIPSInstructionFromat> mipsInstruction;
    public int blkNum;
    //public boolean blockVisited = false;
    BNFGrammarAST bgAST = new BNFGrammarAST();

    public BlockNode() {
        bgAST = BNFGrammarAST.getInstance();
        ++bgAST.current_blockNum;
        //blkCodeList = new StringBuffer();
        ILOCList = new ArrayList<StringBuffer>();
        parentBlock = new ArrayList<BlockNode>();
        childrenBlock = new ArrayList<BlockNode>();
        mipsInstruction = new ArrayList<MIPSInstructionFromat>();
        this.blkName = "B" + BNFGrammarAST.current_blockNum;
        this.blkNum = BNFGrammarAST.current_blockNum;

    }

    public void addILOCList(String code) {
        String st;
        blkCodeList.append(System.getProperty("line.separator"));
        blkCodeList.append(code);        
        ILOCList.add(blkCodeList);
    }

    public void addMipsInstruction(MIPSInstructionFromat mIns) {
        mipsInstruction.add(mIns);
    }

    public void addParentBlock(BlockNode bp) {
        parentBlock.add(bp);
    }

    public void addchildrenBlock(BlockNode bc) {
        childrenBlock.add(bc);
    }

    public ArrayList<StringBuffer> getAllILOCList() {
        return ILOCList;
    }

    public ArrayList<BlockNode> getAllParentBlock() {
        return parentBlock;
    }

    public ArrayList<BlockNode> getAllchildrenBlock() {
        return childrenBlock;
    }
}
