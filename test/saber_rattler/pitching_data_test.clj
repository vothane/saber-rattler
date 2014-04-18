(ns saber-rattler.pitching-data-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [saber-rattler.pitching-data :refer :all])
  (:use [cascalog.api]))

(def ^:dynamic *game-logs-url* "http://www.brooksbaseball.net/tabs.php?player=452657&gFilt=&time=month&minmax=ci&var=gl&s_type=2&startDate=03/30/2007&endDate=10/20/2013&balls=-1&strikes=-1&b_hand=-1")
(def ^:dynamic *pitch-outcomes-url* "http://www.brooksbaseball.net/tabs.php?player=452657&p_hand=-1&ppos=-1&cn=200&gFilt=&time=month&minmax=ci&var=po&s_type=2&startDate=03/30/2007&endDate=10/20/2013&balls=-1&strikes=-1&b_hand=-1")

(defn make-queryable [data]
  (reduce (fn [arr el] (apply conj arr (take (count el) el))) [] 
    (map (fn [row] (map vec row)) data)))

(def Jon-Lester-pitches (remove (fn [col] (= "Game" (first col))) 
                          (headers->categories *game-logs-url*)))

(def Jon-Lester-logs (table->data *game-logs-url*))

(def game-log-data (data-converter Jon-Lester-pitches Jon-Lester-logs))

(def query-logs (make-queryable game-log-data))

;;----------------------------------------------------------------------------------------

(def pitch-metrics (headers->categories *pitch-outcomes-url*))

(def Jon-Lester-stats (table->data *pitch-outcomes-url*))

(def game-metrics-data (data-converter pitch-metrics Jon-Lester-stats))

(def query-metrics (make-queryable game-metrics-data))

;;----------------------------------------------------------------------------------------

(?<- (stdout) [?game ?pitch ?metric ?stat]
  (query-logs ?game ?pitch ?num) (query-metrics ?pitch ?metric ?stat) (= ?pitch "Cutter"))