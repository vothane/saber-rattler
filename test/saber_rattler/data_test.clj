(ns saber-rattler.data-test
  (:require [clojure.test :refer :all]
            [saber-rattler.data :refer :all])
  (:use [incanter.core]
        [saber-rattler.pitching-data])
  (:import [weka.core Attribute]
           [weka.core Instance]
           [weka.core Instances]
           [weka.core FastVector]))

(def ^:dynamic *data-url* "http://www.brooksbaseball.net/tabs.php?player=452657&gFilt=&time=month&minmax=ci&var=gl&s_type=2&startDate=03/30/2007&endDate=10/20/2013&balls=-1&strikes=-1&b_hand=-1")
(def ^:dynamic *pitch-type* [:fourseam :sinker :change :curve :cutter])

(def home-away (FastVector. 2))
(.addElement home-away "Home")
(.addElement home-away "Away")
(def attributes (FastVector.))
(.addElement attributes home-away)
;(reduce (fn [pitch dataset] (.addElement attributes pitch)))
(println attributes)
(def pitch-data (load-data *data-url*))
(def games ($ [:game] pitch-data))
(def pitches (to-matrix ($ *pitch-type* pitch-data)))

(deftest a-test
  (testing "home-away should have attributes"
    (is (= 2 (.size home-away)))))

(deftest b-test
  (testing "home-away should have attributes"
    (is (= 2 (.size home-away)))))
