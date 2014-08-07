(ns saber-rattler.core-test
  (:require [clojure.test :refer :all]
            [saber-rattler.core :refer :all])
  (:use [cascalog.api]))

(query? "Yu Darvish"
  (?<- (stdout) [?pitch ?ball ?strike ?swing ?foul]; ?b1 ?b2 ?b3 ?hr]
    (outcomes ?pitch ?count ?ball ?strike ?swing ?foul ?whiffs ?bip ?gb ?ld ?fb ?pu ?hr) 
    ;(avgs ?pitch ?count ?ab ?k ?bb ?hbp ?b1 ?b2 ?b3 ?hr ?baa ?slg ?iso ?babip) 
    (= ?pitch "Cutter")))
