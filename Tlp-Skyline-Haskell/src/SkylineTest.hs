module SkylineTest where

import Skyline

-- Función que realiza el test sobre la función principal de la práctica
--   Comprueba que la función resuelveSkyline hace su trabajo
--   Comprueba que la función divide cumple con su especificación
testPractica = map test pruebas
  where test (le,sk,_) = (resuelveSkyline le == sk, testDivide le)

-- Función que realiza el test sobre la función de la cuestión 1 (opcional)
testCuestion1 funcion = map test pruebas
  where test (_,sk,dib) = limpia ( funcion sk ) == limpia dib
        limpia s = filter (/= "") (lines s)

-- Batería de pruebas:
-- (Lista de Edificios , Skyline , Dibujo)
pruebas :: [([Edificio],Skyline,String)]
pruebas = [([(1,10,1),(2,9,2),(3,8,3),(4,7,4),(5,6,5)],
            [(1,1),(2,2),(3,3),(4,4),(5,5),(6,4),(7,3),(8,2),(9,1),(10,0)],
            "     *     \n    ***    \n   *****   \n  *******  \n ********* \n-----------"
           ),
           ([(1,5,8),(6,10,4),(11,12,10)],
            [(1,8),(5,0),(6,4),(10,0),(11,10),(12,0)],
            "           * \n           * \n ****      * \n ****      * \n ****      * \n ****      * \n **** **** * \n **** **** * \n **** **** * \n **** **** * \n-------------"
           ),
           ([(11,12,10),(1,5,8),(1,13,11),(6,10,4)],
            [(1,11),(13,0)],
            " ************ \n ************ \n ************ \n ************ \n ************ \n ************ \n ************ \n ************ \n ************ \n ************ \n ************ \n--------------"
           ),
           ([(2,9,2),(4,7,4),(3,8,3),(5,6,5),(1,10,1)],
            [(1,1),(2,2),(3,3),(4,4),(5,5),(6,4),(7,3),(8,2),(9,1),(10,0)],
            "     *     \n    ***    \n   *****   \n  *******  \n ********* \n-----------"
           )
          ]

--------------------------
-- Funciones auxiliares --
--------------------------

-- Función que comprueba que la función divide cumple con su especificación
--   Divide la lista de entrada en dos listas disjuntas
--   Y la diferencia de tamaño entre ellas es, a lo sumo 1
testDivide le = ( listasIguales (==) le (xs++ys) ) && (diferencia1 xs ys)
  where (xs,ys) = divide le
        diferencia1 [] [] = True
        diferencia1 [x] [] = True
        diferencia1 [] [x] = True
        diferencia1 (x:xs) (y:ys) = diferencia1 xs ys
        diferencia1 _ _ = False

-- Función que comprueba que dos listas son iguales (según una operación de igualdad) prescindiendo del orden
--  Si las dos listas son vacías, son iguales con independencia de la operación de igualdad usada
--  Si una de ellas no es vacía, se comprueba que:
--    1) su primer elemento pertenece a la segunda (bajo la operación de igualdad)
--    2) las listas resultado de eliminar ese primer elemento de ambas también son iguales (bajo la operación de igualdad)
--  En cualquier otro caso, las dos listas no son iguales
listasIguales :: (a -> b -> Bool) -> [a] -> [b] -> Bool
listasIguales _ [] [] = True
listasIguales operacion (x:xs) ys = pertenece && (listasIguales operacion xs resto)
  where (pertenece,resto) = comprueba x [] ys
        comprueba _ _ [] = (False,[])
        comprueba x previo (y:ys)
          | operacion x y = (True,previo++ys)
          | otherwise = comprueba x (y:previo) ys
listasIguales _ _ _ = False

