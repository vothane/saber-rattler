(ns saber-rattler.pitching-data-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [saber-rattler.pitching-data :refer :all])
  (:use [cascalog.api]))

(def ^:dynamic *data-url* "http://www.brooksbaseball.net/tabs.php?player=452657&gFilt=&time=month&minmax=ci&var=gl&s_type=2&startDate=03/30/2007&endDate=10/20/2013&balls=-1&strikes=-1&b_hand=-1")

(def Jon-Lester-pitches (get-pitcher-repertoire *data-url*))
(def Jon-Lester-logs (get-game-logs *data-url*))

(def game-log-data (game-log-converter Jon-Lester-logs Jon-Lester-pitches))

(doall (map println game-log-data))
(println (str "count >>>> " (count game-log-data)))
;(game-log-converter (get-game-logs *data-url*) Jon-Lester-pitches)
;(def game-log-data (first (game-log-converter (get-game-logs *data-url*) Jon-Lester-pitches)))

