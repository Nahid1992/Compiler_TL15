package compiler_project;

class MIPSInstructionFromat {

    String opName;
    String src1;
    String src2;
    String dest1;
    String src1Index;
    String src2Index;
    String dest1Index;

	//String dest2;
    public MIPSInstructionFromat(String opName, String src1, String src2, String dest1) {
        this.opName = this.src1 = this.src2 = this.dest1 = null;
        this.opName = opName;
        this.src1 = src1;
        this.src2 = src2;
        this.dest1 = dest1;
        src1Index = src2Index = dest1Index = null;
    }

    public MIPSInstructionFromat(String opName, String src1, String src2, String dest1, String src1Index, String src2Index, String dest1Index) {
        this.opName = this.src1 = this.src2 = this.dest1 = null;
        this.opName = opName;
        this.src1 = src1;
        this.src2 = src2;
        this.dest1 = dest1;
        this.src1Index = src1Index;
        this.src2Index = src2Index;
        this.dest1Index = dest1Index;

    }

    /*public MIPSInstructionFromat(String opName, String src1, String src2, String dest1, String dest2){
     this.opName=opName;
     this.src1 =src1;
     this.src2 =src2;
     this.dest1 =dest1;
     this.dest2 = dest2;
     }*/
}
