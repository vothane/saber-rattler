(ns saber-rattler.core-test
  (:require [clojure.test :refer :all]
            [saber-rattler.core :refer :all])
  (:use [cascalog.api]))


(println "Will query this webpage of stats on Yu Darvish
          http://www.brooksbaseball.net/tabs.php?player=506433&p_hand=-1&ppos=-1&cn=200&gFilt=&time=month&minmax=ci&var=so&s_type=2&startDate=03/30/2007&endDate=10/20/2014&balls=-1&strikes=-1&b_hand=-1
          will show only stats of his cut-fastball pitch")

(query? "Yu Darvish"
  (?<- (stdout) [?pitch ?ball ?strike ?swing ?foul]
    (outcomes ?pitch ?count ?ball ?strike ?swing ?foul ?whiffs ?bip ?gb ?ld ?fb ?pu ?hr) 
    (= ?pitch "Cutter")))
