(ns saber-rattler.core
  (:use [incanter.core]
        [saber-rattler.pitching-data])
        [cascalog.api])

(defn make-queryable [data]
  (reduce (fn [arr el] (apply conj arr (doall el))) [] 
    (map (fn [row] (map vec row)) data)))

(def pitchers 
  [[1 "Yu Darvish" 506433]
   [2 "Clayton Kershaw" 477132]
   [3 "Justin Verlander" 434378]
   [4 "Cole Hamels" 430935]
   [5 "Tim Lincecum" 453311]
   [6 "Jon Lester" 452657]
   [7 "Johnny Cueto" 456501]
   [8 "Clay Buchholz" 453329]
   [9 "Felix Hernandez" 433587]
   [10 "Adam Wainwright" 425794]
   [11 "David Price" 456034]
   [12 "Zack Greinke" 425844]])