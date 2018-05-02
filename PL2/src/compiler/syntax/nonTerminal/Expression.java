package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.semantic.type.TypeIF;

public class Expression extends NonTerminal {

    private TypeIF returnType;
    private int value;
    private ArrayRange from;
    private ArrayRange to;
    private String identifier;
    private Expression leftExpresion;
    private Expression rightExpression;
    private Expression singleExpression;
    private CallFunction call;
    private Assignation assignation;
    private boolean isAssignation;

    public Expression(Assignation assignation, TypeIF returnType) {
        this.assignation = assignation;
        this.isAssignation = true;
        this.returnType = returnType;
    }

    public Expression(int value, TypeIF returnType) {
        this.value = value;
        this.isAssignation = false;
        this.returnType = returnType;
    }

    public Expression(String identifier, TypeIF returnType) {
        this.identifier = identifier;
        this.isAssignation = false;
        this.returnType = returnType;
    }

    public Expression(Expression singleExpression, TypeIF returnType) {
        this.singleExpression = singleExpression;
        this.isAssignation = false;
        this.returnType = returnType;
    }

    public Expression(Expression leftExpresion, Expression rightExpression, TypeIF returnType) {
        this.leftExpresion = leftExpresion;
        this.rightExpression = rightExpression;
        this.isAssignation = false;
        this.returnType = returnType;
    }

    public Expression(String identifier, ArrayRange from, ArrayRange to, TypeIF returnType) {
        this.identifier = identifier;
        this.from = from;
        this.to = to;
        this.isAssignation = false;
        this.returnType = returnType;
    }

    public Expression(CallFunction call, TypeIF returnType) {
        this.call = call;
        this.returnType = returnType;
        this.isAssignation = false;
        this.returnType = returnType;
    }

    public String getIdentifier() {
        return identifier;
    }

    public ArrayRange getTo() {
        return to;
    }

    public ArrayRange getFrom() {
        return from;
    }

    public int getValue() {
        return value;
    }

    public Expression getLeftExpresion() {
        return leftExpresion;
    }

    public Expression getRightExpression() {
        return rightExpression;
    }

    public Expression getSingleExpression() {
        return singleExpression;
    }

    public TypeIF getReturnType() {
        return returnType;
    }

    public boolean isAsignation() {
        return isAssignation;
    }
}
