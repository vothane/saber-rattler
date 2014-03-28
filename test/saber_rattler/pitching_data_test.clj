(ns saber-rattler.pitching-data-test
  (:require [clojure.test :refer :all]
            [saber-rattler.pitching-data :refer :all])
  (:use [incanter.core]))

(def ^:dynamic *data-url* "http://www.brooksbaseball.net/tabs.php?player=452657&gFilt=&time=month&minmax=ci&var=gl&s_type=2&startDate=03/30/2007&endDate=10/20/2013&balls=-1&strikes=-1&b_hand=-1")

(def pitch-data (load-data *data-url*))
  
(deftest pull-data-test
  (testing "should pull column names from the table columns."
    (is (= [:game :fourseam :sinker :change :curve :cutter] (col-names pitch-data)))))

(deftest read-rows-test 
  (testing "should read eleven data rows."
   (is (= 210 (nrow pitch-data)))))

(deftest read-columns-test
  (testing "should read six columns."
   (is (= 6 (ncol pitch-data)))))
