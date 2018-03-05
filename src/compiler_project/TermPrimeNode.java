/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler_project;

public class TermPrimeNode {

	public FactorNode factNodePTerm;
	public AdditiveNode opNodePTerm;
	
	public TermPrimeNode(){
		this.opNodePTerm=null;
		this.factNodePTerm =null;
	}
	
	public TermPrimeNode(AdditiveNode op, FactorNode fNode){
	
		this.opNodePTerm = op;
		this.factNodePTerm = fNode;
	}
}