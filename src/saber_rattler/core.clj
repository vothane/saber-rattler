(ns saber-rattler.core
  (:use [incanter.core]
        [saber-rattler.pitching-data]))

(defn make-queryable [data]
  (reduce (fn [arr el] (apply conj arr (doall el))) [] 
    (map (fn [row] (map vec row)) data)))
