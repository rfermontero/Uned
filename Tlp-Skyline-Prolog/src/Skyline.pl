% Cabecera del programa Skyline.pl
% Práctica de Teoría de los Lenguajes de Programación
% Curso 2015-2016
:- use_module(library(lists)).
ed(X1,X2,H).
c(X,Y).

divide([], []).
divide(Xs, Ys, Zs) :-
  length(Xs, Len),
  Media is Len // 2,
  divide_por(Xs, Media, Ys, Zs).

divide_por(Xs, N, Ys, Zs) :- 
	length(Ys, N), 
	append(Ys, Zs, Xs).

edificioAskyline(ed(X1,X2,H),[c(X1,H),c(X2,0)]).


combina(L1,L2,D) :- combinaAux(L1,L2,D,0,0,0), !.
combinaAux([],[],[],_,_,_) :- !.
combinaAux([c(X,HX)|R],[],[c(X,HX)|R],_,_,LH) :- HX =\= LH, !.
combinaAux([c(X,HX)|R],[],R,_,_,LH) :- HX =:= LH, !.
combinaAux([],[c(Y,HY)|R],[c(Y,HY)|R],_,_,LH) :- HY =\= LH, !.
combinaAux([],[c(Y,HY)|R],R,_,_,LH) :- HY =:= LH, !.
combinaAux([c(X,H1)|R1],[c(Y,H2)|R2],[c(X,A)|D],_,_,LH) :- X =:= Y,
										                   max(H1,H2,A),
										                   A =\= LH,
										                   combinaAux(R1,R2,D,H1,H2,A).
combinaAux([c(X,H1)|R1],[c(Y,H2)|R2],D,_,_,LH) :- X =:= Y,
                                                  max(H1,H2,A),
									              A =:= LH,
									              combinaAux(R1,R2,D,H1,H2,A).
combinaAux([c(X,H1)|R1],[c(Y,H2)|R2],[c(X,A)|D],_,PH2,LH) :- X < Y,
                                                             max(H1,PH2,A),
											                 A =\= LH,
											                 combinaAux(R1,[c(Y,H2)|R2],D,H1,PH2,A).
combinaAux([c(X,H1)|R1],[c(Y,H2)|R2],D,_,PH2,LH) :- X < Y,
                                                    max(H1,PH2,A),
									                A =:= LH,
									                combinaAux(R1,[c(Y,H2)|R2],D,H1,PH2,A).
combinaAux([c(X,H1)|R1],[c(Y,H2)|R2],[c(Y,A)|D],PH1,_,LH) :- X > Y,
                                                             max(PH1,H2,A),
                   											 A =\= LH,
                                                             combinaAux([c(X,H1)|R1],R2,D,PH1,H2,A).
combinaAux([c(X,H1)|R1],[c(Y,H2)|R2],D,PH1,_,LH) :- X > Y,
													max(PH1,H2,A), A =:= LH,
                                                    combinaAux([c(X,H1)|R1],R2,D,PH1,H2,A).
max(A,B,C) :- (A>B -> C is A ; C is B).                                                   


resuelveSkyline([], SAL):- !.
resuelveSkyline([ed(X1,X2,H)], SAL) :- edificioAskyline(ed(X1,X2,H),SAL).
resuelveSkyline(EDS, SAL):-
			divide(EDS, X, Y),
			resuelveSkyline(X, A),
			resuelveSkyline(Y, B),
			combina(A, B, SAL).

sum(A,B,C):-C is A+B.

dibujaSkyline(LISTA,SOL) :- llenaPares(0,0,LISTA,PARES), maxh(LISTA,ALTURA), dibujaSkylineAuxiliar(PARES,ALTURA,PARES,SOL), !.
dibujaSkylineAuxiliar([],0,_,'-') :- !.
dibujaSkylineAuxiliar([c(_,_)|R],0,_,SOL) :- dibujaSkylineAuxiliar(R,0,_,SOL1), concat('-',SOL1,SOL).
dibujaSkylineAuxiliar([],M,L,SOL) :- N is M-1, dibujaSkylineAuxiliar(L,N,L,SOL1), concat('\n',SOL1,SOL).
dibujaSkylineAuxiliar([c(_,H)|R],M,L,SOL) :- H >= M, dibujaSkylineAuxiliar(R,M,L,SOL1), concat('*',SOL1,SOL).
dibujaSkylineAuxiliar([c(_,H)|R],M,L,SOL) :- dibujaSkylineAuxiliar(R,M,L,SOL1), concat(' ',SOL1,SOL).

maxh([c(_,H)|[]],H) :- !.
maxh([c(_,H)|R],H) :- maxh(R,C2), H > C2, !.
maxh([_|R],C) :- maxh(R,C).

llenaPares(A,B,[], []).
llenaPares(A,B,[c(C,D)|YS], RES) :-
        A < C,
        append([c(A,B)], SOL, RES),
        sum(A,1,SIG),
        llenaPares(SIG,B,[c(C,D)|YS],SOL), !.
llenaPares(A,B,[c(C,D)|YS], RES) :-
     A >= C,
     append([c(C,D)], SOL, RES),
     sum(C,1,SIG),
     llenaPares(SIG,D,YS, SOL), !.
getAlturas([],[]).
getAlturas([c(X,Y)],SOL) :- 
 llenaPares(0,0,[c(X,Y)], SOL).
















































