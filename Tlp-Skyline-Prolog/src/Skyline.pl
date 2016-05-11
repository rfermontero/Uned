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

llenaPares(A,B,[], []).
llenaPares(A,B,[c(X,H1)|R1], RES) :-
		A < X,
		append([c(A,B)], SOL, RES),
		sum(A,1,SIG),
		llenaPares(SIG,B,[c(X,H1)|R1],SOL).
llenaPares(A,B,[c(X,H1)|R1], RES) :-
     A >= X,
     append([c(X,H1)], SOL, RES),
     sum(X,1,SIG),
	 llenaPares(SIG,H1,R1,SOL).

sum(A,B,C):-C is A+B.
	
dibuja(X, DIB):-
	maxAltura(X,0,MAXALTURA),
	dibujaAltura(X,MAXALTURA,X,DIB).
dibujaAltura([],_,_,'').
dibujaAltura([c(X,Y)|[]],AA,ORIG,DIB):-
	AA=:=0,
	dibujaBase(DIB).
dibujaAltura([c(X,Y)|[]],AA,ORIG,DIB):-
	AA=\=0,
	dibujaCoordenada(Y,AA,DIB),
	sum(AA,-1,SIG),
	dibujaAltura(ORIG,SIG,ORIG,DIB).
dibujaBase(DIB):-'-'.
dibujaCoordenada(Y,AA,DIB):-
	Y>=AA,
	append('*', SOL, DIB).
dibujaCoordenada(Y,AA,DIB):-
	Y<AA,
	append(' ', SOL, DIB).
dibujaAltura([c(X,Y)|[]],AA,[c(OX,OY)], DIB):-
	AA=:=0,
	dibujaBase(DIB).
dibujaAltura([c(X,Y)|[]],AA,[c(OX,OY)], DIB):-
	AA=\=0,	
	dibujaCoordenada(Y,AA,DIB),
	dibujaSaltoLinea(DIB),
	sum(AA,-1,SIG),
	dibujaAltura([c(OX,OY)],SIG,[c(OX,OY)],DIB).
dibujaAltura([c(X,Y)|RL],AA,[c(OX,OY)], DIB):-
	AA=:=0,
	dibujaBase(DIB),
	dibujaAltura(RL,AA,[c(OX,OY)],DIB).
dibujaAltura([c(X,Y)|RL],AA,[c(OX,OY)], DIB):-
	AA=\=0,	
	dibujaCoordenada(Y,AA,DIB),
	dibujaAltura(RL,AA,[c(OX,OY)],DIB).	

dibujaSaltoLinea(DIB):-	append('\n', SOL, DIB).

maxAltura([],AA,SOL) :- max(0,AA,SOL).
maxAltura([c(X,Y)|[]],AA,SOL) :- max(Y, AA, SOL).
maxAltura([c(X,Y)|R1],AA,SOL) :- AA>Y,   maxAltura(R1,AA,SOL).
maxAltura([c(X,Y)|R1],AA,SOL) :- AA<Y,   maxAltura(R1,Y,SOL).
maxAltura([c(X,Y)|R1],AA,SOL) :- AA=:=Y, maxAltura(R1,Y,SOL).
























