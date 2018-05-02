package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.semantic.type.TypeIF;

public class Expression extends NonTerminal {

    private TypeIF returnType;
    private int value = -1;
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
        if (identifier != null) {
            return identifier;
        }

        if (getLeftExpression() != null) {
            return getLeftExpression().getIdentifier();
        }

        if (getRightExpression() != null) {
            return getRightExpression().getIdentifier();
        }

        if (getSingleExpression() != null) {
            return getSingleExpression().getIdentifier();
        }
        return null;
    }

    public ArrayRange getTo() {
        if (to != null) {
            return to;
        }

        if (getLeftExpression() != null) {
            return getLeftExpression().getTo();
        }

        if (getRightExpression() != null) {
            return getRightExpression().getTo();
        }

        if (getSingleExpression() != null) {
            return getSingleExpression().getTo();
        }
        return null;
    }

    public ArrayRange getFrom() {
        if (from != null) {
            return from;
        }

        if (getLeftExpression() != null) {
            return getLeftExpression().getFrom();
        }

        if (getRightExpression() != null) {
            return getRightExpression().getFrom();
        }

        if (getSingleExpression() != null) {
            return getSingleExpression().getFrom();
        }
        return null;
    }

    public int getValue() {
        if (value != -1) {
            return value;
        }

        if (getLeftExpression() != null) {
            return getLeftExpression().getValue();
        }

        if (getRightExpression() != null) {
            return getRightExpression().getValue();
        }

        if (getSingleExpression() != null) {
            return getSingleExpression().getValue();
        }
        return -1;
    }

    public Expression getLeftExpression() {
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

    public boolean isAssignation() {
        return isAssignation;
    }
}
