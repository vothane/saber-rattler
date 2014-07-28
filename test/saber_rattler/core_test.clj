(ns saber-rattler.core-test
  (:require [clojure.test :refer :all]
            [saber-rattler.core :refer :all])
  (:use [cascalog.api]))

(query? "Yu Darvish"
 (println outcomes)
 (println averages))

(query? "Yu Darvish"
  (?<- (stdout) [?pitch ?stat ?value]
    (outcomes ?pitch ?stat ?value) 
    (averages ?pitch ?stat ?value) 
    (= ?pitch "Slider")))