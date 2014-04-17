(ns saber-rattler.pitching-data-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [saber-rattler.pitching-data :refer :all])
  (:use [cascalog.api]))

(def ^:dynamic *game-logs-url* "http://www.brooksbaseball.net/tabs.php?player=452657&gFilt=&time=month&minmax=ci&var=gl&s_type=2&startDate=03/30/2007&endDate=10/20/2013&balls=-1&strikes=-1&b_hand=-1")
(def ^:dynamic *pitch-outcomes-url* "http://www.brooksbaseball.net/tabs.php?player=452657&p_hand=-1&ppos=-1&cn=200&gFilt=&time=month&minmax=ci&var=po&s_type=2&startDate=03/30/2007&endDate=10/20/2013&balls=-1&strikes=-1&b_hand=-1")

(def Jon-Lester-pitches (get-pitcher-repertoire *game-logs-url*))
(def Jon-Lester-logs (get-game-logs *game-logs-url*))

(def game-log-data (game-log-converter Jon-Lester-logs Jon-Lester-pitches))

(def query-logs (reduce (fn [arr el] (apply conj arr (take (count el) el))) [] 
                  (map (fn [game] (map vec game)) game-log-data)))

(def pitch-metrics (get-outcome-metric *pitch-outcomes-url*))
(def Jon-Lester-stats (get-pitch-outcomes *pitch-outcomes-url*))

(def game-metrics-data (pitch-matrics-converter Jon-Lester-stats pitch-metrics))


(def query-metrics (reduce (fn [arr el] (apply conj arr (take (count el) el))) [] 
                     (map (fn [metric] (map vec metric)) game-metrics-data)))

(?<- (stdout) [?game ?pitch ?metric ?stat]
  (query-logs ?game ?pitch ?num) (query-metrics ?pitch ?metric ?stat) (= ?pitch :cutter))