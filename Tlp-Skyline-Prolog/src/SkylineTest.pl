%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% Carga el programa del estudiante junto a este.

:- consult('Skyline').

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% TEST PARA CORRECCIÓN
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%
%% test(N_orden,DEF_EDIFICIO,SKYLINE,DIBUJO).
%%

test(1,[ed(1,10,1),ed(2,9,2),ed(3,8,3),ed(4,7,4),ed(5,6,5)],
       [c(1,1),c(2,2),c(3,3),c(4,4),c(5,5),c(6,4),c(7,3),c(8,2),c(9,1),c(10,0)],
       '     *     \n    ***    \n   *****   \n  *******  \n ********* \n-----------'
    ).

test(2,[ed(1,5,8),ed(6,10,4),ed(11,12,10)],
       [c(1,8),c(5,0),c(6,4),c(10,0),c(11,10),c(12,0)],
       '           * \n           * \n ****      * \n ****      * \n ****      * \n ****      * \n **** **** * \n **** **** * \n **** **** * \n **** **** * \n-------------'
    ).

test(3,[ed(11,12,10),ed(1,5,8),ed(1,13,11),ed(6,10,4)],
       [c(1,11),c(13,0)],
      ' ************ \n ************ \n ************ \n ************ \n ************ \n ************ \n ************ \n ************ \n ************ \n ************ \n ************ \n--------------'
    ).

test(4,[ed(2,9,2),ed(4,7,4),ed(3,8,3),ed(5,6,5),ed(1,10,1)],
       [c(1,1),c(2,2),c(3,3),c(4,4),c(5,5),c(6,4),c(7,3),c(8,2),c(9,1),c(10,0)],
       '     *     \n    ***    \n   *****   \n  *******  \n ********* \n-----------'
    ).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% No debería haber colisiones entre nombres de predicados
%% definidos por programa.

test_resuelveSkyline :- test_resuelveSkyline(1),
                        test_resuelveSkyline(2),
                        test_resuelveSkyline(3),
                        test_resuelveSkyline(4).

test_resuelveSkyline(N):-
	test(N,EDIFS,SOL,_),
	write('En el test número '), write(N),
	resuelveSkyline(EDIFS,SKL),!,
	test_rskl_segunda(SOL,SKL).

test_resuelveSkyline(_):-
	write('No se genera un Skyline.'), nl.

test_rskl_segunda(SOL,SKL):-
	test_iguales(SOL,SKL),!,
	write(' es correcto el skyline generado.'), nl.
test_rskl_segunda(_,_):-
	write(' no es correcto el skyline generado.'), nl.

%% Identificar dos listas iguales, independientemente del orden de
%% sus elementos, pero puede haber elementos repetidos

test_iguales([],[]).
test_iguales([X|_],L2) :-
	test_no_esta_en(X,L2),!,fail.
test_iguales([X|R1],L2) :-
	test_quitar(X,L2,L3),!,
	test_iguales(R1,L3).

%% quita el elemento primer argumento, todas las veces que esté
%
test_quitar(_,[],[]).
test_quitar(X,[X|R],R2):- !, test_quitar(X,R,R2).
test_quitar(X,[Y|R],[Y|R2]):- test_quitar(X,R,R2).


%%%%%%%%%%%%%%%%%%%
%%

test_dibujaSkyline(N):-
	test(N,_,SOL,DIB),
	nl, write(' La solución es: '),nl,
	write(DIB),
	nl, write('La respuesta del estudiante es '),nl,
	dibujaSkyline(SOL).

%%%%%%%%%%%%%%%%%%%
%%

test_divide :- test_divide(1),
               test_divide(2),
               test_divide(3),
               test_divide(4).

test_divide(N):-
	test(N,EDFS,_,_),
        write('En el test número '), write(N), nl,
	divide(EDFS,E1,E2),
	test_tamanyo(E1,N1), test_tamanyo(E2,N2),
	dif_menor_q_uno(N1,N2),!,
	segunda_condicion(E1,E2).
test_divide(_):-
	write('No divide en dos listas de tamaño igual o con una diferencia de uno. Luego no cumple la especificación.'),nl.

segunda_condicion(E1,E2):-
	test_disj(E2,E1), !, write('Divide cumple la especificación.'),nl.
segunda_condicion(_,_):-
	write('Divide no cumple la especificación, porque las listas no son disjuntas.\n'),nl.


%% Para comprobar que divide(EDIFS,E1,E2) cumple con su
%% especificación: 'divide la lista de entrada en dos listas
%% disjuntas y la diferencia de tamaño entre ellas es a lo
%% sumo uno.'
%%
%% Primero hay que programar que dos listas son
%% disjuntas, es decir, no comparten elementos
%% independientemente del orden.
%% En segundo lugar hay que calcular el tamaño de as listas y
%% finalmente comprobar si la diferencia de tamaños de dos
%% listas es menor o igual a uno.
%%
%% Este algoritmo puede optimizarse. En esta programación
%% solo se cambia el orden: primero se compara los tamaños y
%% luego se comprueba sin son disjuntos.

test_disj([],_).
test_disj([X|R1],L2):- test_no_esta_en(X,L2), test_disj(R1,L2).

test_no_esta_en(_,[]).
test_no_esta_en(X,[X|_]):- !,fail.
test_no_esta_en(X,[_|R]):- test_no_esta_en(X,R).

test_tamanyo([],0).
test_tamanyo([_|R],N) :- test_tamanyo(R,M), N is M+1.

dif_menor_q_uno(N1,N2):- N1>=N2,!, X is N1-N2, test_decide(X).
dif_menor_q_uno(N1,N2):- N2>N1,!, X is N2-N1, test_decide(X).

test_decide(0):- !,write('Sí, la diferencia es cero.'),nl.
test_decide(1):- !,write('Sí, la diferencia es uno.'),nl.
test_decide(_):- write('No, la diferencia es superior a uno.'),nl.

