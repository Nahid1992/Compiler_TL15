/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler_project;

public class SimpleExpressionPrimeNode {

    //public SimpleExpressionPrimeNode smExpTerm;
    public AdditiveNode opTerm;
    public TermNode termNode;
    public SimpleExpressionNode smEx;

    public SimpleExpressionPrimeNode() {
        this.opTerm = null;
        this.termNode = null;
    }

   public SimpleExpressionPrimeNode(AdditiveNode op, TermNode tNode) {
   // public SimpleExpressionPrimeNode(AdditiveNode op, SimpleExpressionNode smEx) {
        this.opTerm = op;
        this.termNode = tNode;
       // this.smEx = smEx;
    }
}
