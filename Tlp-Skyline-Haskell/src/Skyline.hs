
module Skyline where

-- Cabecera del programa Skyline.hs
-- Práctica de Teoría de los Lenguajes de Programación
-- Curso 2015-2016

type Edificio = (Int,Int,Int)
type Coordenada = (Int,Int)
type Skyline = [Coordenada]

resuelveSkyline :: [Edificio] -> Skyline
resuelveSkyline [] = []
resuelveSkyline [x] = edificioAskyline(x)
resuelveSkyline xs = combina (resuelveSkyline(fst(divide(xs))),resuelveSkyline(snd(divide(xs))))

edificioAskyline :: Edificio -> Skyline
edificioAskyline (x1,x2,h) = [(x1,h),(x2,0)]

divide :: [Edificio] -> ([Edificio], [Edificio])
divide edificios = splitAt (((length edificios) + 1) `div` 2) edificios

combina :: (Skyline, Skyline) -> Skyline
combina ([], x) = x
combina (x, []) = x
combina ((ii, ia):ri,(di, da):rd) = subcombina((ii, ia):ri,0) ((di, da):rd,0) 0
    where subcombina ((ii, ia):ri, ih) ((di, da):rd, dh) uaa
                       | ii < di  && uaa /= max ia dh       = (ii, max ia dh)  : subcombina(ri,ia) ((di, da):rd, dh) (max ia dh)
                       | ii < di  && uaa == max ia dh       =                    subcombina(ri,ia) ((di, da):rd, dh) uaa
                       | ii == di && uaa /= max ih dh       = (ii, max ih dh)  : subcombina(ri,ih) (rd, dh) (max ih dh)
                       | ii == di && uaa == max ih dh       =                    subcombina(ri,ih) (rd, dh) uaa
                       | ii > di  && uaa /= max ih da       = (di, max ih da)  : subcombina((ii, ia):ri, ih) (rd, da) (max ih da)
                       | ii > di  && uaa == max ih da       =                    subcombina((ii, ia):ri, ih) (rd, da) uaa
          subcombina ([], _) (rd, _) uaa                    = rd
          subcombina (ri, _) ([], _) uaa                    = ri
